package uk.ac.ucl.twitter.compliance.persist;

import javax.naming.Context;

import org.apache.derby.drda.NetworkServerControl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import jakarta.ejb.embeddable.EJBContainer;

@TestInstance(Lifecycle.PER_CLASS)
public class TweetToVerifyWeeklyEntityIT {

  private Context ctx;
  private EJBContainer ejbContainer;
  private NetworkServerControl derbyServer;

  @BeforeAll
  public void initContainer() throws Exception {
    derbyServer = new NetworkServerControl();
    derbyServer.start(null);
    ejbContainer = EJBContainer.createEJBContainer();
    System.out.println("Opening the container" );
    ctx = ejbContainer.getContext();
  }

  @AfterAll
  public void closeContainer() throws Exception {
    ejbContainer.close();
    derbyServer.shutdown();
    System.out.println("Closing the container" );
  }

  @Test
  public void testHenlo() {
    System.out.println("HENLO");
  }

}
