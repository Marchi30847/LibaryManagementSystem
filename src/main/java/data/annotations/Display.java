package data.annotations;

import java.lang.annotation.*;

/**
 * Annotation used to mark fields that should be included in the application's display or UI components.
 *
 * <p>This annotation can be used to indicate that a specific field should appear in elements such as tables, forms, or other visible UI components.</p>
 *
 * <p>Attributes:</p>
 * <ul>
 *   <li>None</li>
 * </ul>
 *
 * <p>Behavior:</p>
 * <ul>
 *   <li>This annotation does not affect field behavior directly but can be processed by tools or frameworks at runtime.</li>
 * </ul>
 */
@Target(ElementType.FIELD) // Applicable to fields only
@Retention(RetentionPolicy.RUNTIME) // Retained at runtime for reflective processing
public @interface Display {
}