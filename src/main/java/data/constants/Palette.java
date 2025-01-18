package data.constants;

import java.awt.*;

/**
 * Enum representing a color palette used for UI design.
 * Each constant defines a specific color used throughout the application UI.
 */
public enum Palette {

    /**
     * Background color for the UI.
     */
    BACKGROUND(new Color(215, 217, 224)),

    /**
     * Color used for selected UI elements.
     */
    SELECTED(new Color(91, 95, 198)),

    /**
     * Color for header rows in tables.
     */
    HEADER_TABLE_ROW(new Color(245, 245, 245)),

    /**
     * Color for odd rows in tables.
     */
    ODD_TABLE_ROW(new Color(240, 240, 240)),

    /**
     * Color for even rows in tables.
     */
    EVEN_TABLE_ROW(new Color(220, 220, 220));

    private final Color color;

    /**
     * Constructor to initialize the color associated with the enum constant.
     *
     * @param color The Color object representing the specific UI color.
     */
    Palette(Color color) {
        this.color = color;
    }

    /**
     * Retrieves the color associated with the enum constant.
     *
     * @return The Color object for the specific UI color.
     */
    public Color getColor() {
        return color;
    }
}