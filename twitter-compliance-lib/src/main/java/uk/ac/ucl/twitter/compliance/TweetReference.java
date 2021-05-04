package uk.ac.ucl.twitter.compliance;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * A reference to a tweet, defined by its Twitter ID and its file location.
 * <p>
 * This reference is used as a unique definition object to be passed across
 * application components to verify whether a tweet has not been deleted or
 * made private.
 * <p>
 * This reference does not hold information as to whether it is flagged for
 * deletion or not. This process is implemented as part of the logic of the
 * set of applications.
 */
public class TweetReference {

  /**
   * Twitter ID defined as a 64-bit unsigned integer.
   */
  private String idStr;

  /**
   * Reference to the file containing the tweet.
   */
  private String fileRef;

  /**
   * The maximum character size of the Twitter ID.
   */
  private static final int ID_STR_SIZE = 19;

  /**
   * Twitter ID defined as a 64-bit unsigned integer.
   * @return The tweet ID
   */
  public String getIdStr() {
    return idStr;
  }

  /**
   * Twitter ID defined as a 64-bit unsigned integer handled as a String
   * for compatibility across programming languages.
   * The tweet ID must not be null.
   * @param id The tweet ID
   */
  public void setIdStr(@NotNull @Size(max = ID_STR_SIZE) final String id) {
    this.idStr = id;
  }

  /**
   * Reference to the file containing the tweet.
   * @return The reference to the file
   */
  public String getFileRef() {
    return fileRef;
  }

  /**
   * Reference to the file containing the tweet.
   * The file reference must not be null.
   * @param ref The reference to the file
   */
  public void setFileRef(@NotNull final String ref) {
    this.fileRef = ref;
  }

}
