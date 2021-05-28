package uk.ac.ucl.twitter.compliance.batch;

import java.io.Serializable;

public class TweetCounterCheckpoint implements Serializable {

  /**
   * Default Serializable UID.
   */
  private static final long serialVersionUID = 1L;

  private int tweetsToProcess = 0;

  public void setTweetsToProcess(final int t) {
    this.tweetsToProcess = t;
  }

  public int getTweetsToProcess() {
    return this.tweetsToProcess;
  }

  public boolean hasPending() {
    return this.tweetsToProcess < 1;
  }

  public void tweetProcessed() {
    this.tweetsToProcess--;
  }
}
