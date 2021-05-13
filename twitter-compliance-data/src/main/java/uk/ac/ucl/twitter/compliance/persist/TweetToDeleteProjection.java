package uk.ac.ucl.twitter.compliance.persist;

/**
 * A container for results of a JPA select query.
 * <p>
 * Instances of this class are used to return results of a JPA query that uses
 * a join statement. The results are returned using projections, with the JPA
 * query using the statement
 * {@code new uk.ac.ucl.twitter.compliance.persist.TweetToDeleteProjection}.
 */
public class TweetToDeleteProjection implements TweetToVerify {

  /**
   * The Tweet ID, defined as a String containing a 64-bit number.
   */
  private String tweetIdStr;

  /**
   * Reference to the file where the tweet is stored.
   */
  private String fileRef;

  /**
   * Creates a new instance.
   * @param t The tweet ID
   * @param f The file reference
   */
  public TweetToDeleteProjection(final String t, final String f) {
    this.tweetIdStr = t;
    this.fileRef = f;
  }

  /**
   * Obtains the Tweet ID, defined as a 64-bit number.
   * @return The tweet ID
   */
  @Override
  public String getTweetIdStr() {
    return this.tweetIdStr;
  }

  /**
   * Obtains a reference to the file where the tweet is stored.
   * @return The file reference
   */
  @Override
  public String getFileRef() {
    return this.fileRef;
  }

}
