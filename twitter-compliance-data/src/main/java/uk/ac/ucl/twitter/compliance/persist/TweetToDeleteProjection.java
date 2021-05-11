package uk.ac.ucl.twitter.compliance.persist;

public class TweetToDeleteProjection implements TweetToVerify {

  private String tweetIdStr;
  private String fileRef;

  public TweetToDeleteProjection(final String t, final String f) {
    this.tweetIdStr = t;
    this.fileRef = f;
  }

  public String getTweetIdStr() {
    return this.tweetIdStr;
  }

  public String getFileRef() {
    return this.fileRef;
  }

}
