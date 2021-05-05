package uk.ac.ucl.twitter.compliance.persist;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.io.Serializable;

/**
 * Table definitions to contain information on tweets to be monitored for
 * compliance on a weekly basis.
 */
@Entity
@Table(name = "PUBLIC.TWEET_TO_VALIDATE_MONTHLY")
@NamedQueries({
  @NamedQuery(
      name = "TweetToVerifyMonthlyEntity.findAll",
      query = "SELECT t from TweetToVerifyMonthlyEntity t"),
  @NamedQuery(
      name = "TweetToVerifyMonthlyEntity.findAllToDelete",
      query = "SELECT t from TweetToVerifyMonthlyEntity t "
      + "INNER JOIN TweetToDeleteEntity d")
})
public class TweetToVerifyMonthlyEntity extends TweetToVerifyImpl
    implements TweetToVerify, Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Tweets flagged to be deleted.
   */
  @OneToOne
  @JoinTable(
    name = "PUBLIC.TWEET_TO_DELETE_MONTHLY",
    joinColumns = {@JoinColumn(
        name = "TWEET_TO_VALIDATE_MONTHLY_ID",
        referencedColumnName = "tweetIdStr")},
    inverseJoinColumns = {@JoinColumn(
        name = "TWEET_TO_DELETE_ID",
        referencedColumnName = "tweetIdStr")}
  )
  private TweetToDeleteEntity tweetToDelete;

  /**
   * Name of JPQL query. Selects all entities.
   */
  public static final String QUERY_FIND_ALL =
      "TweetToVerifyMonthlyEntity.findAll";

  /**
   * The table ID column. It should be configured to a serial primary key type,
   * as defined in PostgreSQL.
   */
  @Id
  private Integer id;

}
