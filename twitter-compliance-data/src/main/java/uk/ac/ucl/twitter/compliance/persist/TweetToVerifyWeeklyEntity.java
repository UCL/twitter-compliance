package uk.ac.ucl.twitter.compliance.persist;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "PUBLIC.TWEET_TO_VALIDATE_WEEKLY")
@NamedQuery(name = "TweetToVerifyWeeklyEntity.findAll", query = "SELECT t FROM TweetToVerifyWeeklyEntity t")
public class TweetToVerifyWeeklyEntity extends TweetToVerifyImpl implements TweetToVerify, Serializable {

  private static final long serialVersionUID = 1L;

  public static final String QUERY_FIND_ALL = "TweetToVerifyWeeklyEntity.findAll";

  public TweetToVerifyWeeklyEntity() {
  }

}
