package uk.ac.ucl.twitter.compliance.persist;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * JPA entity class to hold tweets that are set to be deleted from the
 * collected JSON files. To be used as tracking info for batch processing.
 */
@Entity
@Table(name = "PUBLIC.TWEET_TO_DELETE")
@NamedQuery(
    name = "TweetToDeleteEntity.findByTweetId",
    query =
      "SELECT t FROM TweetToDeleteEntity t WHERE t.tweetIdStr = :tweetIdStr")
public class TweetToDeleteEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Name of JPQL query. Selects all entities by tweet ID.
   */
  public static final String
    FIND_BY_TWEETID = "TweetToDeleteEntity.findByTweetId";

  /**
   * The table ID column. It should be configured to a serial primary key type,
   * as defined in PostgreSQL.
   */
  @Id
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
