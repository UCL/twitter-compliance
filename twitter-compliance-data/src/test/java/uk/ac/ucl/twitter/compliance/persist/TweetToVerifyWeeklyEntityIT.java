package uk.ac.ucl.twitter.compliance.persist;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.derby.drda.NetworkServerControl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import jakarta.ejb.embeddable.EJBContainer;
import uk.ac.ucl.twitter.compliance.VerificationInterval;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@TestInstance(Lifecycle.PER_CLASS)
public class TweetToVerifyWeeklyEntityIT {

  private Context ctx;
  private EJBContainer ejbContainer;
  private NetworkServerControl derbyServer;
  private DataSource dataSource;
  private Connection connection;

  @BeforeAll
  public void initContainer() throws Exception {
    derbyServer = new NetworkServerControl();
    derbyServer.start(null);
    ejbContainer = EJBContainer.createEJBContainer();
    System.out.println("Opening the EJB container" );
    ctx = ejbContainer.getContext();
    dataSource = (DataSource) ctx.lookup("jdbc/__default");
    connection = dataSource.getConnection();
  }

  @AfterAll
  public void closeContainer() throws Exception {
    connection.close();
    ejbContainer.close();
    derbyServer.shutdown();
    System.out.println("Closing the EJB container" );
  }

  @Test
  public void testGetBatchOfTweetsToDelete() throws NamingException, SQLException {
    ResultSet resultSet = connection.createStatement()
      .executeQuery("SELECT * FROM TWEET_TO_DELETE");
    int fetchSizr = resultSet.getFetchSize();
    System.out.println("TEST QUERY");
    System.out.println(fetchSizr);
    final String jndiName =
      "java:global/twitter-compliance-data-1.0.0-SNAPSHOT/EntityAccessImpl!"
        + "uk.ac.ucl.twitter.compliance.persist.EntityAccess";
    EntityAccess entityAccess = (EntityAccess) ctx.lookup(jndiName);
    entityAccess.getBatchOfTweetsToDelete(10, VerificationInterval.WEEKLY);
  }
}
