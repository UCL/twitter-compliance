package uk.ac.ucl.twitter.compliance.batch;

import java.util.List;

import jakarta.batch.runtime.BatchStatus;
import uk.ac.ucl.twitter.compliance.TweetReference;

public class WeeklyVerifiableBatchlet extends QueryVerifyAbstractBatchlet {

  @Override
  public String process() throws Exception {
    List<TweetReference> tweetList = entityAccess.getTweetsToVerifyWeekly();
    return BatchStatus.COMPLETED.name();
  }

}
