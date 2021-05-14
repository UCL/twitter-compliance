package uk.ac.ucl.twitter.compliance.schedule;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import uk.ac.ucl.twitter.compliance.VerificationEvent;
import uk.ac.ucl.twitter.compliance.VerificationInterval;

/**
 * EJB container-manager scheduler responsible to trigger verification events.
 */
@Singleton
@Startup
public class VerificationScheduler {

  /**
   * CDI event that starts the verification process.
   */
  @Inject
  @VerificationEvent
  private Event<VerificationInterval> callVerification;

  /**
   * Fire event to verify tweets on a weekly basis.
   */
  @Schedule(dayOfWeek = "Sun")
  public void fireWeekly() {
    callVerification.fireAsync(VerificationInterval.WEEKLY);
  }

  /**
   * Fire event to verify tweets on a monthly basis.
   */
  @Schedule(dayOfMonth = "Last")
  public void fireMonthly() {
    callVerification.fireAsync(VerificationInterval.MONTHLY);
  }

}
