package uk.ac.ucl.twitter.compliance.batch;

import jakarta.batch.operations.JobOperator;
import jakarta.batch.runtime.BatchRuntime;

public class ComplianceJobOperator {

  private final JobOperator jobOperator = BatchRuntime.getJobOperator();

}
