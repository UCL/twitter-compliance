package uk.ac.ucl.twitter.compliance.persist;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;

public abstract class TweetToVerifyImpl implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private Integer id;

  @Column(name = "TWEET_ID_STR")
  private String tweetIdStr;

  @Column(name = "FILE_REF")
  protected String fileRef;

  public String getTweetIdStr() {
    return tweetIdStr;
  }

  public void setTweetIdStr(String tweetIdStr) {
    this.tweetIdStr = tweetIdStr;
  }

  public String getFileRef() {
    return fileRef;
  }

  public void setFileRef(String fileRef) {
    this.fileRef = fileRef;
  }

}
