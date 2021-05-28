package uk.ac.ucl.twitter.compliance.batch;

import jakarta.batch.api.AbstractBatchlet;
import jakarta.inject.Inject;
import uk.ac.ucl.twitter.compliance.persist.EntityAccess;

public abstract class QueryVerifyAbstractBatchlet extends AbstractBatchlet {

  @Inject
  protected ComplianceJobContext jobContext;

  @Inject
  protected EntityAccess entityAccess;

}
