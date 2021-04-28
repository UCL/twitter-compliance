package uk.ac.ucl.twitter.compliance.persist;

import java.util.List;

import jakarta.ejb.Local;
import uk.ac.ucl.twitter.compliance.TweetReference;
import uk.ac.ucl.twitter.compliance.ValidationInterval;

@Local
public interface EntityAccess {

  public List<TweetReference> getTweetsToValidateWeekly();

  public List<TweetReference> getTweetsToValidateMonthly();

  public void setTweetsToDelete(List<String> tweetsToDelete);

  public List<TweetReference> getBatchOfTweetsToDelete(int batchSize, ValidationInterval interval);

  public void setDeletionComplete(String tweetId);

}
