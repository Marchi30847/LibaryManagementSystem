package data.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify that a field must match a specific regular expression pattern.
 *
 * <p>Applies to fields and is retained at runtime for reflective access.</p>
 *
 * <p>Attributes:</p>
 * <ul>
 *   <li>{@code value}: The regular expression pattern the field's value must match.</li>
 * </ul>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchPattern {
    /**
     * Defines the regular expression that the annotated field's value must match.
     *
     * @return A string containing the regex pattern.
     */
    String value();
}