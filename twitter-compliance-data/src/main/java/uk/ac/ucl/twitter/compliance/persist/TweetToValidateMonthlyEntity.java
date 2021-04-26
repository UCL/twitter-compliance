package uk.ac.ucl.twitter.compliance.persist;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "PUBLIC.TWEET_TO_VALIDATE_MONTHLY")
public class TweetToValidateMonthlyEntity extends TweetToValidate implements Serializable {

  private static final long serialVersionUID = 1L;

  public TweetToValidateMonthlyEntity() {}

}
