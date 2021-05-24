package uk.ac.ucl.twitter.compliance.batch;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.event.Observes;
import uk.ac.ucl.twitter.compliance.VerificationInterval;

@Dependent
public class BatchFacade {

  public void receiveProcessCall(@Observes VerificationInterval event) {
    switch (event) {
      case WEEKLY:
        break;
      case MONTHLY:
        break;
    }
  }

}
