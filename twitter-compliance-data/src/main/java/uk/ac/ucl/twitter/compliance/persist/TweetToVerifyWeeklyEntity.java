package uk.ac.ucl.twitter.compliance.persist;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * Table definitions to contain information on tweets to be monitored for
 * compliance on a weekly basis.
 */
@Entity
@Table(name = "TWEET_TO_VERIFY_WEEKLY")
@NamedQueries({
  @NamedQuery(
      name = "TweetToVerifyWeeklyEntity.findAll",
      query = "SELECT t FROM TweetToVerifyWeeklyEntity t"),
  @NamedQuery(
      name = "TweetToVerifyWeeklyEntity.findByTweetId",
      query = "SELECT t FROM TweetToVerifyWeeklyEntity t "
      + "WHERE t.tweetIdStr = :tweetIdStr")
})
public class TweetToVerifyWeeklyEntity extends TweetToVerifyImpl
    implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Name of JPQL query. Selects all entities.
   */
  public static final String QUERY_FIND_ALL =
      "TweetToVerifyWeeklyEntity.findAll";

  /**
   * Name of JPQL query. Selects entities by tweet id.
   */
  public static final String QUERY_FIND_BY_TWEET_ID =
      "TweetToVerifyWeeklyEntity.findByTweetId";

  /**
   * The table ID column. It should be configured to a serial primary key type,
   * as defined in PostgreSQL.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

}
