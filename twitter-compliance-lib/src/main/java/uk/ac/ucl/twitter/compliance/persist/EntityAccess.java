package uk.ac.ucl.twitter.compliance.persist;

import java.util.List;

import jakarta.ejb.Local;
import uk.ac.ucl.twitter.compliance.TweetReference;
import uk.ac.ucl.twitter.compliance.VerificationInterval;

/**
 * Local methods to interact with the tweet tracking information managed by
 * the persistence layer.
 * <p>
 * It uses {@code TweetReference} as a container to pass information between
 * applications.
 * <p>
 * @see uk.ac.ucl.twitter.compliance.TweetReference
 */
@Local
public interface EntityAccess {

  /**
   * Obtain the list of tweets that need to be verified on a weekly basis.
   * @return The {@code TweetReference} items to be verified
   */
  List<TweetReference> getTweetsToVerifyWeekly();

  /**
   * Obtain the list of tweets that need to be verified on a monthly basis.
   * @return The {@code TweetReference} items to be verified
   */
  List<TweetReference> getTweetsToVerifyMonthly();

  /**
   * Stores the list of tweets that will be deleted. This information is kept
   * in a transient data store that acts as an inbox for the actual removal of
   * the tweet from the collected files.
   * @param tweetsToDelete The list of tweets to be deleted
   */
  void setTweetsToDelete(List<String> tweetsToDelete);

  /**
   * Obtains the list of tweets that will be processed for deletion from the
   * collected files.
   * @param batchSize The size of the batch to process
   * @param interval Whether it corresponds to the weekly or monthly
   * verification
   * @return The list of tweets to process
   */
  List<TweetReference> getBatchOfTweetsToDelete(
    int batchSize, VerificationInterval interval
  );

  /**
   * Remove the tweet ID from the list of tweets to be deleted. Classes
   * calling this method must ensure that the deletion of the tweet from the
   * collected files has been completed successfully.
   * @param tweetId The tweet ID
   */
  void setDeletionComplete(String tweetId);

}
