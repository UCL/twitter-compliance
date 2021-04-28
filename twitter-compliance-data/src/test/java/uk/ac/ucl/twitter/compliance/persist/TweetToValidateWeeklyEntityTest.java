package uk.ac.ucl.twitter.compliance.persist;

import jakarta.persistence.Entity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@see uk.ac.ucl.twitter.compliance.persist.TweetToValidateWeeklyEntity}
 * based on the recommendations listed in
 * <a href="http://mjremijan.blogspot.com/2016/06/unit-testing-jpa-annotationsstop_20.html">
 * https://mjremijan.blogspot.com/2016/06/unit-testing-jpa-annotationsstop_20.html
 * </a>
 * @author David Guzman {@literal d.guzman at ucl.ac.uk}
 *
 */
class TweetToValidateWeeklyEntityTest {

  @Test
  public void hasEntityAnnotation() {
    Class<TweetToValidateWeeklyEntity> clEntity = TweetToValidateWeeklyEntity.class;
    Entity annotation = clEntity.getAnnotation(Entity.class);
    assertNotNull(annotation, "Expect class to be annotated as Entity");
    assertEquals("", annotation.name(), "Expect name to be empty");
  }

}
