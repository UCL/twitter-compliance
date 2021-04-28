package uk.ac.ucl.twitter.compliance.persist;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "PUBLIC.TWEET_TO_VALIDATE_WEEKLY")
@NamedQuery(name = "TweetToValidateWeeklyEntity.findAll", query = "SELECT t FROM TweetToValidateWeeklyEntity t")
public class TweetToValidateWeeklyEntity extends TweetToValidateImpl implements TweetToValidate, Serializable {

  private static final long serialVersionUID = 1L;

  public static final String QUERY_FIND_ALL = "TweetToValidateWeeklyEntity.findAll";

  public TweetToValidateWeeklyEntity() {
  }

}
