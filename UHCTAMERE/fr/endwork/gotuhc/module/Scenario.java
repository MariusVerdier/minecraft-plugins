package fr.endwork.gotuhc.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Scenario {

  /**
   * @return The human-readable name of the scenario
   */
  String name();

  /**
   * @return A human-readable description of the scenario
   */
  String desc();

}
