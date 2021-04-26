package uk.ac.ucl.twitter.compliance.persist;

import jakarta.persistence.Entity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TweetToValidateWeeklyEntityTest {

  @Test
  public void hasEntityAnnotation() {
    Class<TweetToValidateWeeklyEntity> clEntity = TweetToValidateWeeklyEntity.class;
    Entity annotation = clEntity.getAnnotation(Entity.class);
    assertNotNull(annotation, "Expect class to be annotated as Entity");
    assertEquals("", annotation.name(), "Expect name to be empty");
  }
}
