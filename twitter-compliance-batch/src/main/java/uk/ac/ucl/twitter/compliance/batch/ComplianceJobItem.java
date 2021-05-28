package uk.ac.ucl.twitter.compliance.batch;

import java.util.Collection;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class ComplianceJobItem {

  private String fileRef = "";

  private Queue<String> tweetIds = new ConcurrentLinkedQueue<>();

  public void setFileRef(final String f) {
    this.fileRef = f;
  }

  public String getFileRef() {
    return this.fileRef;
  }

  public void setTweetIds(Set<String> tweetIdSet) {
    Collection<String> tweetIdColl = tweetIdSet.stream()
        .sorted().collect(Collectors.toSet());
    tweetIds.addAll(tweetIdColl);
  }

  public Queue<String> getTweetIds() {
    return this.tweetIds;
  }

}
