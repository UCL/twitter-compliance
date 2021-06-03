package uk.ac.ucl.twitter.compliance.batch;

import jakarta.batch.runtime.BatchStatus;
import uk.ac.ucl.twitter.compliance.TweetReference;

import java.util.List;

public class MonthlyVerifiableBatchlet extends QueryVerifyAbstractBatchlet {

  @Override
  public String process() throws Exception {
    List<TweetReference> tweetList = entityAccess.getTweetsToVerifyMonthly();
    return BatchStatus.COMPLETED.name();
  }
}
