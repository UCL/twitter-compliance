package uk.ac.ucl.twitter.compliance.persist;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uk.ac.ucl.twitter.compliance.TweetReference;
import uk.ac.ucl.twitter.compliance.VerificationInterval;

/**
 * Implements methods for interaction with the tweet tracking information
 * managed by the persistence layer.
 */
@Stateless
public class EntityAccessImpl implements EntityAccess {

  /**
   * JPA entity manager.
   */
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Batch size for flushing changes to the database.
   */
  private static final int BATCH_SIZE_FLUSH = 1000;

  /**
   * Selects all tweets set for weekly verification.
   */
  private TypedQuery<TweetToVerifyWeeklyEntity> tweetToVerifyWeeklyQuery;

  /**
   * Selects all tweets set for monthly verification.
   */
  private TypedQuery<TweetToVerifyMonthlyEntity> tweetToVerifyMonthlyQuery;

  /**
   * Selects single tweet from pool set for deletion.
   */
  private TypedQuery<TweetToDeleteEntity> tweetToDeleteQuery;

  /**
   * Converts from Entity classes to {@code TweetReference}.
   */
  private final Function<TweetToVerify, TweetReference>
    mapEntityToReference = (TweetToVerify t) -> {
      TweetReference instance = new TweetReference();
      instance.setFileRef(t.getFileRef());
      instance.setIdStr(t.getTweetIdStr());
      return instance;
    };

  /**
   * Initialisation of named queries.
   */
  @PostConstruct
  public void postConstruct() {
    tweetToVerifyWeeklyQuery = entityManager.createNamedQuery(
            TweetToVerifyWeeklyEntity.QUERY_FIND_ALL,
            TweetToVerifyWeeklyEntity.class);
    tweetToVerifyMonthlyQuery = entityManager.createNamedQuery(
            TweetToVerifyMonthlyEntity.QUERY_FIND_ALL,
            TweetToVerifyMonthlyEntity.class);
    tweetToDeleteQuery = entityManager.createNamedQuery(
            TweetToDeleteEntity.FIND_BY_TWEETID,
            TweetToDeleteEntity.class);
  }

  /**
   * Obtains the list of tweets set for weekly verification.
   */
  @Override
  public List<TweetReference> getTweetsToVerifyWeekly() {
    return tweetToVerifyWeeklyQuery.getResultList().stream()
        .map(mapEntityToReference).collect(Collectors.toList());
  }

  /**
   * Obtains the list of tweets set for monthly verification.
   */
  @Override
  public List<TweetReference> getTweetsToVerifyMonthly() {
    return tweetToVerifyMonthlyQuery.getResultList().stream()
        .map(mapEntityToReference).collect(Collectors.toList());
  }

  /**
   * Persists the list of tweets that will be deleted. This list will be
   * obtained using Twitter Compliance Batch jobs.
   * It flushes changes in batches of 1,000 entities for improved
   * performance.
   */
  @Override
  @Transactional
  public void setTweetsToDelete(final List<String> tweetsToDelete) {
    int counter = 0;
    for (String s : tweetsToDelete) {
      TweetToDeleteEntity entity = new TweetToDeleteEntity();
      entity.setTweetIdStr(s);
      entityManager.persist(entity);
      if ((counter % BATCH_SIZE_FLUSH) == 0) {
        entityManager.flush();
        entityManager.clear();
      }
    }
  }

  /**
   * Obtains the list of tweets that will be deleted from the collected pool
   * of JSON files.
   */
  @Override
  public List<TweetReference> getBatchOfTweetsToDelete(final int batchSize,
      final VerificationInterval interval) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Removes the tweet ID from the list of tweets to be deleted. Method to be
   * called once the process of deletion from the collected JSON files has
   * been completed successfully.
   */
  @Override
  public void setDeletionComplete(final String tweetId) {
    tweetToDeleteQuery.setParameter("tweetIdStr", tweetId);
    TweetToDeleteEntity entity = tweetToDeleteQuery.getSingleResult();
    entityManager.remove(entity);
    entityManager.flush();
  }

}
