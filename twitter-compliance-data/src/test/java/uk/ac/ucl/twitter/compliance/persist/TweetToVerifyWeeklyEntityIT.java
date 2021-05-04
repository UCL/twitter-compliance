package uk.ac.ucl.twitter.compliance.persist;

import javax.naming.Context;

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

  @BeforeAll
  public void initContainer() {
    ejbContainer = EJBContainer.createEJBContainer();
    System.out.println("Opening the container" );
    ctx = ejbContainer.getContext();
  }

  @AfterAll
  public void closeContainer() {
    ejbContainer.close();
    System.out.println("Closing the container" );
  }

  @Test
  public void testHenlo() {
    System.out.println("HENLO");
  }

}
