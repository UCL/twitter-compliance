package uk.ac.ucl.twitter.compliance.persist;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.derby.drda.NetworkServerControl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import jakarta.ejb.embeddable.EJBContainer;
import uk.ac.ucl.twitter.compliance.TweetReference;
import uk.ac.ucl.twitter.compliance.VerificationInterval;

@TestInstance(Lifecycle.PER_CLASS)
public class EntityAccessIT {

  private Context ctx;
  private EJBContainer ejbContainer;
  private NetworkServerControl derbyServer;
  private Connection connection;
  private EntityAccess entityAccess;

  @BeforeAll
  public void initContainer() throws Exception {
    derbyServer = new NetworkServerControl();
    derbyServer.start(null);
    ejbContainer = EJBContainer.createEJBContainer();
    System.out.println("Opening the EJB container" );
    ctx = ejbContainer.getContext();
    final String jndiName =
        "java:global/twitter-compliance-data-1.0.0-SNAPSHOT/EntityAccessImpl!"
          + "uk.ac.ucl.twitter.compliance.persist.EntityAccess";
    entityAccess = (EntityAccess) ctx.lookup(jndiName);
    final DataSource dataSource = (DataSource) ctx.lookup("jdbc/__default");
    connection = dataSource.getConnection();
  }

  @AfterAll
  public void closeContainer() throws Exception {
    connection.close();
    ctx.close();
    ejbContainer.close();
    derbyServer.shutdown();
    System.out.println("Closing the EJB container" );
  }

  @Test
  public void testGetBatchOfTweetsToDeleteWeekly() throws NamingException, SQLException {
    Statement statement = connection.createStatement();
    statement.addBatch("INSERT INTO TWEET_TO_DELETE (TWEET_ID_STR) VALUES ('12345679')");
    statement.addBatch("INSERT INTO TWEET_TO_VERIFY_WEEKLY (FILE_REF, TWEET_ID_STR) VALUES ('fileRef1', '12345679')");
    statement.executeBatch();
    List<TweetReference> result = entityAccess
        .getBatchOfTweetsToDelete(1, VerificationInterval.WEEKLY);
    Assertions.assertEquals("12345679", result.get(0).getIdStr());
    Assertions.assertEquals("fileRef1", result.get(0).getFileRef());
  }

  @Test
  public void testGetBatchOfTweetsToDeleteMonthly() throws NamingException, SQLException {
    Statement statement = connection.createStatement();
    statement.addBatch("INSERT INTO TWEET_TO_DELETE (TWEET_ID_STR) VALUES ('12345678')");
    statement.addBatch("INSERT INTO TWEET_TO_VERIFY_MONTHLY (FILE_REF, TWEET_ID_STR) VALUES ('fileRef2', '12345678')");
    statement.executeBatch();
    List<TweetReference> result = entityAccess
        .getBatchOfTweetsToDelete(1, VerificationInterval.MONTHLY);
    Assertions.assertEquals("12345678", result.get(0).getIdStr());
    Assertions.assertEquals("fileRef2", result.get(0).getFileRef());
  }
}
