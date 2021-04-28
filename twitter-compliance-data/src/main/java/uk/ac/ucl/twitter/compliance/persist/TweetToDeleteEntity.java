package uk.ac.ucl.twitter.compliance.persist;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "PUBLIC.TWEET_TO_DELETE")
@NamedQuery(name = "TweetToDeleteEntity.findByTweetId", query = "SELECT t FROM TweetToDeleteEntity t WHERE t.tweetIdStr = :tweetIdStr")
public class TweetToDeleteEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  
  public static final String FIND_BY_TWEETID = "TweetToDeleteEntity.findByTweetId";

  @Id
  private Integer id;

  @Column(name = "TWEET_ID_STR")
  private String tweetIdStr;

  public void setTweetIdStr(String tweetIdStr) {
    this.tweetIdStr = tweetIdStr;
  }

}
