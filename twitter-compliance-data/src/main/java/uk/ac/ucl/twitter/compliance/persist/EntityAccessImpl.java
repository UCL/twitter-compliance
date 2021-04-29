package uk.ac.ucl.twitter.compliance.persist;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uk.ac.ucl.twitter.compliance.TweetReference;
import uk.ac.ucl.twitter.compliance.VerificationInterval;

@Stateless
public class EntityAccessImpl implements EntityAccess {

  /**
   * JPA entity manager.
   */
  @PersistenceContext
  private EntityManager entityManager;

  private final TypedQuery<TweetToValidateWeeklyEntity> tweetToValidateWeeklyQuery = entityManager
      .createNamedQuery(TweetToValidateWeeklyEntity.QUERY_FIND_ALL, TweetToValidateWeeklyEntity.class);

  private final TypedQuery<TweetToValidateMonthlyEntity> tweetToValidateMonthlyQuery = entityManager
      .createNamedQuery(TweetToValidateMonthlyEntity.QUERY_FIND_ALL, TweetToValidateMonthlyEntity.class);
  
  private final TypedQuery<TweetToDeleteEntity> tweetToDeleteQuery = entityManager
      .createNamedQuery(TweetToDeleteEntity.FIND_BY_TWEETID, TweetToDeleteEntity.class);
  
  private final Function<TweetToValidate, TweetReference> mapEntityToReference = (TweetToValidate t) -> {
    TweetReference instance = new TweetReference();
    instance.setFileRef(t.getFileRef());
    instance.setIdStr(t.getTweetIdStr());
    return instance;
  };

  @Override
  public List<TweetReference> getTweetsToVerifyWeekly() {
    return tweetToValidateWeeklyQuery.getResultList()
      .stream()
      .map(mapEntityToReference)
      .collect(Collectors.toList());
  }

  @Override
  public List<TweetReference> getTweetsToVerifyMonthly() {
    return tweetToValidateMonthlyQuery.getResultList()
      .stream()
      .map(mapEntityToReference)
      .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public void setTweetsToDelete(List<String> tweetsToDelete) {
    int counter = 0;
    for (String s : tweetsToDelete) {
      TweetToDeleteEntity entity = new TweetToDeleteEntity();
      entity.setTweetIdStr(s);
      entityManager.persist(entity);
      if ((counter % 1000) == 0) {
        entityManager.flush();
        entityManager.clear();
      }
    }
  }

  @Override
  public List<TweetReference> getBatchOfTweetsToDelete(int batchSize, VerificationInterval interval) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setDeletionComplete(String tweetId) {
    tweetToDeleteQuery.setParameter("tweetIdStr", tweetId);
    TweetToDeleteEntity entity = tweetToDeleteQuery.getSingleResult();
    entityManager.remove(entity);
    entityManager.flush();
  }

}
