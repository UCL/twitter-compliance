package uk.ac.ucl.twitter.compliance.batch;

import jakarta.batch.api.BatchProperty;
import jakarta.batch.api.Decider;
import jakarta.batch.runtime.StepExecution;
import jakarta.inject.Inject;

public class IntervalDecider implements Decider {

  @Inject
  @BatchProperty(name = ComplianceJobContext.INTERVAL_PROPERTY_KEY)
  private String interval;

  @Override
  public String decide(StepExecution[] executions) throws Exception {
    return interval.toLowerCase();
  }

}
