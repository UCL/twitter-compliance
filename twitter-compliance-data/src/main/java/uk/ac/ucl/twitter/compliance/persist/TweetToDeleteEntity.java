package uk.ac.ucl.twitter.compliance.persist;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * JPA entity class to hold tweets that are set to be deleted from the
 * collected JSON files. To be used as tracking info for batch processing.
 */
@Entity
@Table(name = "TWEET_TO_DELETE")
@NamedQueries({
  @NamedQuery(
      name = "TweetToDeleteEntity.findByTweetId",
      query =
        "SELECT t FROM TweetToDeleteEntity t WHERE t.tweetIdStr = :tweetIdStr"),
  @NamedQuery(
      name = "TweetToDeleteEntity.findMonthly",
      query = "SELECT new "
        + "uk.ac.ucl.twitter.compliance.persist.TweetToDeleteProjection("
        + "t.tweetIdStr, m.fileRef) FROM TweetToDeleteEntity t "
        + "JOIN TweetToVerifyMonthlyEntity m ON t.tweetIdStr = m.tweetIdStr"),
  @NamedQuery(
      name = "TweetToDeleteEntity.findWeekly",
      query = "SELECT new "
        + "uk.ac.ucl.twitter.compliance.persist.TweetToDeleteProjection("
        + "t.tweetIdStr, w.fileRef) FROM TweetToDeleteEntity t "
        + "JOIN TweetToVerifyWeeklyEntity w ON t.tweetIdStr = w.tweetIdStr")
})
public class TweetToDeleteEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Name of JPQL query. Selects all entities by tweet ID.
   */
  public static final String
    FIND_BY_TWEET_ID = "TweetToDeleteEntity.findByTweetId";

  /**
   * Name of JPQL query. Selects entities that were set for monthly validation.
   */
  public static final String FIND_MONTHLY = "TweetToDeleteEntity.findMonthly";

  /**
   * Name of JPQL query. Selects entities that were set for weekly validation.
   */
  public static final String FIND_WEEKLY = "TweetToDeleteEntity.findWeekly";

  /**
   * The table ID column. It should be configured to a serial primary key type,
   * as defined in PostgreSQL.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * Table column to store the tweet ID.
   */
  @Column(name = "TWEET_ID_STR", nullable = false)
  private String tweetIdStr;

  /**
   * Sets the Tweet ID, defined as a String containing a 64-bit number.
   * @param t The tweet ID
   */
  public void setTweetIdStr(final String t) {
    this.tweetIdStr = t;
  }

}
