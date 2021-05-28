package uk.ac.ucl.twitter.compliance.batch;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import jakarta.batch.runtime.context.JobContext;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class ComplianceJobContext {

  public static final String INTERVAL_PROPERTY_KEY = "interval";

  private Queue<ComplianceJobItem> jobItemQueue = new ConcurrentLinkedQueue<>();

  @Inject
  private JobContext jobContext;

  public Queue<ComplianceJobItem> getJobItemQueue() {
    return getComplianceJobContext().jobItemQueue;
  }

  private ComplianceJobContext getComplianceJobContext() {
    if (jobContext.getTransientUserData() == null) {
      jobContext.setTransientUserData(this);
    }
    return (ComplianceJobContext) jobContext.getTransientUserData();
  }

}
