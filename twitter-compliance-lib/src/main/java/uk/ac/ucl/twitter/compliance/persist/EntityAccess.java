package uk.ac.ucl.twitter.compliance.persist;

import java.util.List;

import jakarta.ejb.Local;
import uk.ac.ucl.twitter.compliance.TweetReference;

@Local
public interface EntityAccess {

  public List<TweetReference> getTweetsToValidateWeekly();

  public List<TweetReference> getTweetsToValidateMonthly();

  public void setTweetsToDelete(List<TweetReference> tweetsToDelete);

  public List<TweetReference> getBatchOfTweetsToDelete();

  public void setDeletionComplete(TweetReference tweetReference);

}
