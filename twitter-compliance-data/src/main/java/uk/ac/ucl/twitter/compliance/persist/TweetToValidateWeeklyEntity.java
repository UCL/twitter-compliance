package uk.ac.ucl.twitter.compliance.persist;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "PUBLIC.TWEET_TO_VALIDATE_WEEKLY")
public class TweetToValidateWeeklyEntity extends TweetToValidate {

  private static final long serialVersionUID = 1L;

  public TweetToValidateWeeklyEntity() { }

}
