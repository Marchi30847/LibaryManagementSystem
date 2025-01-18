package data.annotations;

import java.lang.annotation.*;

/**
 * This annotation is used to mark fields that should be displayed in the application.
 * It can be applied to class fields to indicate that these fields should be included in the UI
 * (e.g., in tables or forms) or considered for display in some way.
 * The annotation itself doesn't affect the field's behavior directly but is used for filtering
 * and processing by tools or frameworks that recognize this annotation.
 */
@Target(ElementType.FIELD) // Specifies that this annotation can be applied to fields only
@Retention(RetentionPolicy.RUNTIME) // Makes this annotation available at runtime
public @interface Display {
}