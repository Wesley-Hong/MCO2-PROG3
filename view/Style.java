package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Utility class for providing consistent styling
 * Contains: fonts, colors, and button maker
 */

public class Style {

    // Colors
    public static final Color BG = Color.decode("#68BA7F");
    public static final Color BUTTON = Color.decode("#CFFFDC");

    // Fonts
    public static final Font TITLE_FONT = new Font("Times New Roman", Font.PLAIN, 50);
    public static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 20);
    public static final Font INPUT_FONT = new Font("Arial", Font.PLAIN, 20);

    /**
     * Create the appearance of the button
     * @param text button text
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the button width
     * @param height the button height
     * @return JButton
     */
    public static JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(LABEL_FONT);
        button.setBounds(x, y, width, height);
        button.setBackground(BUTTON);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return button;
    }

    /**
     * Create button with action listener
     * @param text button text
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the button width
     * @param height the button height
     * @param action the action listener for button clicks
     * @return button with action listener
     */
    public static JButton createButton(String text, int x, int y, int width, int height, ActionListener action) {
        JButton button = createButton(text, x, y, width, height);
        button.addActionListener(action);
        return button;
    }
}
