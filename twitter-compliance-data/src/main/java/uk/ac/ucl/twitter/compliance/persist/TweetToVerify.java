package uk.ac.ucl.twitter.compliance.persist;

/**
 * A tweet that needs to be verified for compliance against the Twitter API.
 * <p>
 * Twitter users can delete or make a tweet private, so each tweet in the
 * collection must be verified at different intervals.
 * <p>
 * Classes implementing this interface must also extend the abstract class
 * {@code TweetToVerifyImpl}.
 */
public interface TweetToVerify {

  /**
   * Obtains a reference to the file where the tweet is stored.
   * @return The file reference
   */
  String getFileRef();

  /**
   * Obtains the Tweet ID, defined as a 64-bit number.
   * @return The tweet ID
   */
  String getTweetIdStr();

}
