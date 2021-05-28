package uk.ac.ucl.twitter.compliance.batch;

import java.util.Properties;

import jakarta.batch.operations.JobOperator;
import jakarta.batch.runtime.BatchRuntime;
import jakarta.enterprise.event.Observes;
import uk.ac.ucl.twitter.compliance.VerificationInterval;

public class ComplianceJobOperator {

  private final String jobName = "compliance";
  private final JobOperator jobOperator = BatchRuntime.getJobOperator();

  public void receiveProcessCall(@Observes VerificationInterval event) {
    final Properties properties = new Properties();
    properties.put(ComplianceJobContext.INTERVAL_PROPERTY_KEY, event);
    jobOperator.start(jobName, properties);
  }

}
