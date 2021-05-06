package uk.ac.ucl.twitter.compliance.persist;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

/**
 * Base class containing the JPA column definitions for tweet tracking.
 * <p>
 * Classes extending it are expected to provide the table definitions and each
 * extending class should support a specific verification interval (weekly,
 * monthly, etc).
 * <p>
 * All extending classes must implement {@code TweetToVerify}.
 */
@MappedSuperclass
public abstract class TweetToVerifyImpl implements TweetToVerify, Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Table column to store the tweet ID.
   */
  @Column(name = "TWEET_ID_STR", nullable = false)
  private String tweetIdStr;

  /**
   * Table column to store a reference to the file where the tweet is stored.
   */
  @Column(name = "FILE_REF", nullable = false)
  private String fileRef;


  /**
   * The Tweet ID, defined as a String containing a 64-bit number.
   * @return The tweet ID
   */
  @Override
  public String getTweetIdStr() {
    return tweetIdStr;
  }

  /**
   * Sets the Tweet ID, defined as a String containing a 64-bit number.
   * The column definition does not allow null values.
   * @param t The tweet ID
   */
  public void setTweetIdStr(final String t) {
    this.tweetIdStr = t;
  }

  /**
   * Obtains the file reference where the tweet is stored.
   * @return The file reference
   */
  @Override
  public String getFileRef() {
    return fileRef;
  }

  /**
   * Sets the file reference where the tweet is stored.
   * @param f The file reference
   */
  public void setFileRef(final String f) {
    this.fileRef = f;
  }

}
