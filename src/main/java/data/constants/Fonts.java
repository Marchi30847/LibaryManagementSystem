package data.constants;

import java.awt.*;

/**
 * Enum representing a set of predefined fonts and colors for UI elements.
 * Each constant defines a specific font style and color used throughout the application.
 */
public enum Fonts {

    /**
     * Font for headers, with bold style and a larger size.
     */
    HEADER_FONT(
            new Font("Georgia", Font.BOLD, 40),
            new Color(55, 71, 79)
    ),

    /**
     * Font for body text, with regular style and moderate size.
     */
    BODY_FONT(
            new Font("Open Sans", Font.PLAIN, 18),
            new Color(55, 71, 79)
    ),

    /**
     * Font for selected body text, with italic style and white color.
     */
    SELECTED_BODY_FONT(
            new Font("Open Sans", Font.ITALIC, 18),
            new Color(255, 255, 255)
    ),

    /**
     * Font for buttons, with regular style and a larger size.
     */
    BUTTON_FONT(
            new Font("Georgia", Font.PLAIN, 25),
            new Color(55, 71, 79)
    );

    private final Font font;
    private final Color color;

    /**
     * Constructor to initialize the font and color associated with the enum constant.
     *
     * @param font The Font object representing the font style and size.
     * @param color The Color object representing the text color.
     */
    Fonts(Font font, Color color) {
        this.font = font;
        this.color = color;
    }

    /**
     * Applies the specified font and color to a given component.
     *
     * @param component The UI component to which the font and color will be applied.
     * @param font The enum constant that defines the font and color to apply.
     */
    public static void applyToComponent(Component component, Fonts font) {
        component.setFont(font.font);
        component.setForeground(font.color);
    }
}