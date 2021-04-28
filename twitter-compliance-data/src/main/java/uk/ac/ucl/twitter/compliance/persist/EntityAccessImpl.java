package uk.ac.ucl.twitter.compliance.persist;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import uk.ac.ucl.twitter.compliance.TweetReference;

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

  private final Function<TweetToValidate, TweetReference> mapEntityToReference = t -> {
    TweetReference instance = new TweetReference();
    instance.setFileRef(t.getFileRef());
    instance.setIdStr(t.getTweetIdStr());
    return instance;
  };

  @Override
  public List<TweetReference> getTweetsToValidateWeekly() {
    return tweetToValidateWeeklyQuery.getResultList()
      .stream()
      .map(mapEntityToReference)
      .collect(Collectors.toList());
  }

  @Override
  public List<TweetReference> getTweetsToValidateMonthly() {
    return tweetToValidateMonthlyQuery.getResultList()
      .stream()
      .map(mapEntityToReference)
      .collect(Collectors.toList());
  }

  @Override
  public void setTweetsToDelete(List<TweetReference> tweetsToDelete) {
    // TODO Auto-generated method stub
  }

  @Override
  public List<TweetReference> getBatchOfTweetsToDelete() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setDeletionComplete(TweetReference tweetReference) {
    // TODO Auto-generated method stub
  }

}
