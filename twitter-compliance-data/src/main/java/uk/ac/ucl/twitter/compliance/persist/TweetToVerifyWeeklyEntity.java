package uk.ac.ucl.twitter.compliance.persist;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * Table definitions to contain information on tweets to be monitored for
 * compliance on a weekly basis.
 */
@Entity
@Table(name = "PUBLIC.TWEET_TO_VALIDATE_WEEKLY")
@NamedQuery(
    name = "TweetToVerifyWeeklyEntity.findAll",
    query = "SELECT t FROM TweetToVerifyWeeklyEntity t")
public class TweetToVerifyWeeklyEntity extends TweetToVerifyImpl
    implements TweetToVerify, Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Name of JPQL query. Selects all entities.
   */
  public static final String QUERY_FIND_ALL =
      "TweetToVerifyWeeklyEntity.findAll";

  /**
   * The table ID column. It should be configured to a serial primary key type,
   * as defined in PostgreSQL.
   */
  @Id
  private Integer id;

}
