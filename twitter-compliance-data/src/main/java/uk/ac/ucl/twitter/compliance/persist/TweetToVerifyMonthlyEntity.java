package uk.ac.ucl.twitter.compliance.persist;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

import java.io.Serializable;

/**
 * Table definitions to contain information on tweets to be monitored for
 * compliance on a weekly basis.
 */
@Entity
@Table(name = "PUBLIC.TWEET_TO_VALIDATE_MONTHLY")
@NamedQuery(
    name = "TweetToVerifyMonthlyEntity.findAll",
    query = "SELECT t from TweetToVerifyMonthlyEntity t")
public class TweetToVerifyMonthlyEntity extends TweetToVerifyImpl
    implements TweetToVerify, Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Name of JPQL query. Selects all entities.
   */
  public static final String QUERY_FIND_ALL =
      "TweetToVerifyMonthlyEntity.findAll";

}
