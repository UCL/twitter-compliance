package uk.ac.ucl.twitter.compliance.persist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

import java.io.Serializable;

/**
 * Table definitions to contain information on tweets to be monitored for
 * compliance on a weekly basis.
 */
@Entity
@Table(name = "TWEET_TO_VERIFY_MONTHLY")
@NamedQueries({
  @NamedQuery(
      name = "TweetToVerifyMonthlyEntity.findAll",
      query = "SELECT t from TweetToVerifyMonthlyEntity t"),
  @NamedQuery(
      name = "TweetToVerifyMonthlyEntity.findByTweetId",
      query = "SELECT t from TweetToVerifyMonthlyEntity t "
      + "WHERE t.tweetIdStr = :tweetIdStr")
})
public class TweetToVerifyMonthlyEntity extends TweetToVerifyImpl
    implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Name of JPQL query. Selects all entities.
   */
  public static final String QUERY_FIND_ALL =
      "TweetToVerifyMonthlyEntity.findAll";

  /**
   * Name of JPQL query. Selects entities by tweet id.
   */
  public static final String QUERY_FIND_BY_TWEET_ID =
      "TweetToVerifyMonthlyEntity.findByTweetId";

  /**
   * The table ID column. It should be configured to a serial primary key type,
   * as defined in PostgreSQL.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

}
