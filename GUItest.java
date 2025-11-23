import javax.swing.*;
import java.awt.*;
import java.util.jar.JarEntry;

public class GUItest {

    private final Font TITLE_FONT = new Font("Times New Roman", Font.PLAIN, 50);
    private final Font LABEL_FONT = new Font("Arial", Font.BOLD, 20);
    private final Font INPUT_FONT = new Font("Arial", Font.PLAIN, 20);

    public static void main(String[] args) {
        new GUItest().start();
    }

    // start method
    public void start() {

        // Create window
        JFrame frame = new JFrame("Green Property Exchange");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Main content panel
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(Color.decode("#68BA7F"));

        // Show main panel inside content
        content.add(mainMenuPanel(content), BorderLayout.CENTER);

        frame.add(content);
        frame.setVisible(true);
    }

    // main menu panel
    public JPanel mainMenuPanel(JPanel content) {

        JPanel menuPanel = new JPanel(null);
        menuPanel.setBackground(Color.decode("#68BA7F"));

        JLabel label = new JLabel("Green Property Exchange", SwingConstants.CENTER);
        label.setFont(TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        menuPanel.add(label);

        JButton b1 = new JButton("Create Property");
        JButton b2 = new JButton("View Property");
        JButton b3 = new JButton("Manage Property");
        JButton b4 = new JButton("Simulate Booking");

        JButton[] buttons = {b1, b2, b3, b4};
        int y = 100;

        for (JButton button : buttons) {
            button.setBounds(340, y, 220, 60);
            button.setFont(LABEL_FONT);
            button.setBackground(Color.decode("#CFFFDC"));
            button.setFocusPainted(false);
            button.setBorder(null);
            menuPanel.add(button);
            y += 100;
        }

        // ---- BUTTON LISTENERS ----
        b1.addActionListener(e -> createProperty(content));
        b2.addActionListener(e -> showViewProperty(content));
        b3.addActionListener(e -> manageProperty(content));
        b4.addActionListener(e -> simulateBooking(content));

        return menuPanel;
    }

    // going back to main menu
    public void showMenu(JPanel content) {
        content.removeAll();
        content.add(mainMenuPanel(content)); // reconstructed menu
        content.revalidate();
        content.repaint();
    }

    // create property
    public void createProperty(JPanel content) {
        content.removeAll();

        JPanel p = new JPanel(null);
        p.setBackground(Color.decode("#68BA7F"));

        // title
        JLabel label = new JLabel("Create Property", SwingConstants.CENTER);
        label.setFont(TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        p.add(label);

        // panel for user input
        JPanel p1 = new JPanel(new GridLayout(2, 2,10,20));
        p1.setBounds(100,150,400,100);
        p1.setBackground(Color.decode("#68BA7F"));

        JLabel propertyNameLabel = new JLabel("Property Name:");
        JTextField propertyNameField = new JTextField();

        JLabel propertyTypeLabel = new JLabel("Property Type:");
        String[] propertyTypes = {"Eco-Apartment", "Sustainable House", "Green Resort", "Eco-Glamping"};
        JComboBox<String> propertyTypeField = new JComboBox<>(propertyTypes);

        propertyNameLabel.setFont(LABEL_FONT);
        propertyNameField.setFont(INPUT_FONT);
        propertyTypeLabel.setFont(LABEL_FONT);
        propertyTypeField.setFont(INPUT_FONT);

        p1.add(propertyNameLabel);
        p1.add(propertyNameField);
        p1.add(propertyTypeLabel);
        p1.add(propertyTypeField);

        JTextArea notes = new JTextArea("Price Rate: \n" +
                "Eco-Apartment: Base Price \n" +
                "Sustainable House: Base Price * 1.2\n" +
                "Green Resort: Base Price * 1.35\n" +
                "Eco-Glamping: Base Price * 1.5\n");
        notes.setBackground(Color.decode("#68BA7F"));
        notes.setFont(LABEL_FONT);
        notes.setBounds(100,300,400,150);

        p.add(p1);
        p.add(notes);

        p.add(createBackButton(content));
        p.add(createSubmitButton(content));

        content.add(p);
        content.revalidate();
        content.repaint();
    }

    // view property info
    public void showViewProperty(JPanel content) {
        content.removeAll();

        JPanel p = new JPanel(null);
        p.setBackground(Color.decode("#68BA7F"));

        // title
        JLabel label = new JLabel("View Property", SwingConstants.CENTER);
        label.setFont(TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        p.add(label);

        JButton b1 = new JButton("Calendar");
        JButton b2 = new JButton("Property Summary");
        JButton b3 = new JButton("Availability (date range)");
        JButton b4 = new JButton("Information (specific date)");
        JButton b5 = new JButton("Reservation Details");

        JButton[] buttons = {b1, b2, b3, b4, b5};
        int y = 100;

        for (JButton button : buttons) {
            button.setBounds(300, y, 300, 50);
            button.setFont(LABEL_FONT);
            button.setBackground(Color.decode("#CFFFDC"));
            button.setFocusPainted(false);
            button.setBorder(null);
            p.add(button);
            y += 70;

        }

        // need to fix
        b1.addActionListener(e -> calendar(content));
        b2.addActionListener(e -> propertySummary(content));
        b3.addActionListener(e -> mainMenuPanel(content));
        b4.addActionListener(e -> mainMenuPanel(content));
        b5.addActionListener(e -> mainMenuPanel(content));



        p.add(createBackButton(content));
        p.add(createSubmitButton(content));

        content.add(p);
        content.revalidate();
        content.repaint();
    }

    // manage property
    public void manageProperty(JPanel content) {
        content.removeAll();

        JPanel p = new JPanel(null);
        p.setBackground(Color.decode("#68BA7F"));

        // title
        JLabel label = new JLabel("Manage Property", SwingConstants.CENTER);
        label.setFont(TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        p.add(label);

        JButton b1 = new JButton("Change Property Name");
        JButton b2 = new JButton("Update Base Price");
        JButton b3 = new JButton("Remove Reservation");
        JButton b4 = new JButton("Remove Property");

        JButton[] buttons = {b1, b2, b3, b4};
        int y = 100;

        for (JButton button : buttons) {
            button.setBounds(80, y, 240, 60);
            button.setFont(LABEL_FONT);
            button.setBackground(Color.decode("#CFFFDC"));
            button.setFocusPainted(false);
            button.setBorder(null);
            p.add(button);
            y += 100;
        }

        JPanel smallBox = new JPanel();
        smallBox.setBounds(350, 100, 500, 360);
        smallBox.setBackground(Color.decode("#68BA7F"));
        smallBox.setLayout(null);
        smallBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        p.add(smallBox);

        b1.addActionListener(e -> changePropertyName(smallBox));
        b2.addActionListener(e -> updateBasePrice(smallBox));
        b3.addActionListener(e -> removeReservation(smallBox));
        b4.addActionListener(e -> removeProperty(smallBox));

        p.add(createBackButton(content));
        p.add(createSubmitButton(content));

        content.add(p);
        content.revalidate();
        content.repaint();

    }

    // simulate booking
    public void simulateBooking(JPanel content) {
        content.removeAll();

        JPanel p = new JPanel(null);
        p.setBackground(Color.decode("#68BA7F"));

        // title
        JLabel label = new JLabel("Simulate Booking", SwingConstants.CENTER);
        label.setFont(TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        p.add(label);



        p.add(createBackButton(content));
        p.add(createSubmitButton(content));

        content.add(p);
        content.revalidate();
        content.repaint();

    }

    // buttons (need to create button maker)
    public JButton createBackButton(JPanel content) {
        JButton back = new JButton("Back");
        back.setFont(LABEL_FONT);
        back.setBounds(750, 500, 100, 40);
        back.setBackground(Color.decode("#CFFFDC"));
        back.setFocusPainted(false);
        back.setBorder(null);
        back.addActionListener(e -> showMenu(content));
        return back;
    }

    public JButton createSubmitButton(JPanel content) {
        JButton submit = new JButton("Submit");
        submit.setFont(LABEL_FONT);
        submit.setBounds(630, 500, 100, 40);
        submit.setBackground(Color.decode("#CFFFDC"));
        submit.setFocusPainted(false);
        submit.setBorder(null);
        submit.addActionListener(e -> showMenu(content));
        return submit;
    }

    // view property functions
    public void calendar(JPanel content) {
        content.removeAll();

        JPanel p = new JPanel(null);
        p.setBackground(Color.decode("#68BA7F"));

        // title
        JLabel label = new JLabel("Calendar", SwingConstants.CENTER);
        label.setFont(TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        p.add(label);

        // sunday to saturday
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        JPanel dayHeader = new JPanel(new GridLayout(1, 7));
        dayHeader.setBounds(50, 80, 800, 40);
        dayHeader.setBackground(Color.decode("#68BA7F"));

        for (String d : days) {
            JLabel label1 = new JLabel(d, SwingConstants.CENTER);
            label1.setFont(new Font("Arial", Font.BOLD, 18));
            label1.setOpaque(true);

            label1.setBackground(Color.YELLOW);

            label1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            dayHeader.add(label1);
        }
        p.add(dayHeader);

        // calendar grid
        JPanel grid = new JPanel(new GridLayout(5, 7, 3, 3));
        grid.setBounds(50, 120, 800, 360);
        grid.setBackground(Color.decode("#68BA7F"));

        for (int day = 1; day <= 35; day++) {

            JPanel cell = new JPanel(new BorderLayout());
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cell.setBackground(Color.WHITE);

            if (day <= 30) {

                // TOP-LEFT: Day number (small panel)
                JPanel topLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
                topLeft.setOpaque(false);
                JLabel dayLabel = new JLabel(String.valueOf(day));
                dayLabel.setFont(new Font("Arial", Font.BOLD, 16));
                topLeft.add(dayLabel);

                // BOTTOM-RIGHT: Price label
                JPanel bottomRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
                bottomRight.setOpaque(false);
                JLabel priceLabel = new JLabel("₱1500");
                priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                bottomRight.add(priceLabel);

                // Add to cell
                cell.add(topLeft, BorderLayout.NORTH);
                cell.add(bottomRight, BorderLayout.SOUTH);

            } else {
                // Empty cell
                cell.add(new JLabel(""), BorderLayout.CENTER);
            }

            grid.add(cell);
        }


        p.add(grid);

        p.add(createBackButton(content));

        content.add(p);
        content.revalidate();
        content.repaint();
    }

    public void propertySummary(JPanel content) {
        content.removeAll();

        JPanel p = new JPanel(null);
        p.setBackground(Color.decode("#68BA7F"));

        // title
        JLabel label = new JLabel("Property Summary", SwingConstants.CENTER);
        label.setFont(TITLE_FONT);
        label.setBounds(0, 0, 900, 80);
        p.add(label);

        // information
        JLabel name = new JLabel("Property: ");
        JLabel type = new JLabel("Type: ");
        JLabel availableDates = new JLabel("Total number of available dates: ");
        JLabel earnings = new JLabel("Estimated earnings: ");

        name.setFont(LABEL_FONT);
        type.setFont(LABEL_FONT);
        availableDates.setFont(LABEL_FONT);
        earnings.setFont(LABEL_FONT);

        name.setBounds(250, 120, 400, 30);
        type.setBounds(250, 160, 400, 30);
        availableDates.setBounds(250, 200, 400, 30);
        earnings.setBounds(250, 240, 400, 30);

        p.add(name);
        p.add(type);
        p.add(availableDates);
        p.add(earnings);

        p.add(createBackButton(content));
        p.add(createSubmitButton(content));

        content.add(p);
        content.revalidate();
        content.repaint();
    }





    // manage property functions
    public void changePropertyName(JPanel box) {
        box.removeAll();

        JLabel label = new JLabel("Change property name to:", SwingConstants.CENTER);
        label.setFont(LABEL_FONT);
        label.setBounds(0, 10, 280, 50);

        JTextField answer = new JTextField();
        answer.setFont(INPUT_FONT);
        answer.setBounds(285, 10, 200, 50);

        box.add(label);
        box.add(answer);

        box.revalidate();
        box.repaint();
    }

    public void updateBasePrice(JPanel box) {
        box.removeAll();

        JLabel label = new JLabel("Update Base Price:", SwingConstants.CENTER);
        label.setFont(LABEL_FONT);
        label.setBounds(0, 10, 280, 50);

        JTextField answer = new JTextField();
        answer.setFont(INPUT_FONT);
        answer.setBounds(285, 10, 200, 50);

        box.add(label);
        box.add(answer);

        box.revalidate();
        box.repaint();
    }

    public void removeReservation(JPanel box) {
        box.removeAll();

        JLabel title = new JLabel("Total Reservation ##:", SwingConstants.CENTER);
        title.setFont(LABEL_FONT);
        title.setBounds(0, 10, 280, 50);

        JLabel label = new JLabel("Reservation to delete:", SwingConstants.CENTER);
        label.setFont(LABEL_FONT);
        label.setBounds(0, 60, 280, 50);

        JTextField answer = new JTextField();
        answer.setFont(INPUT_FONT);
        answer.setBounds(285, 60, 200, 50);

        box.add(title);
        box.add(label);
        box.add(answer);

        box.revalidate();
        box.repaint();
    }

    public void removeProperty(JPanel box) {
        box.removeAll();

        JLabel label = new JLabel("Are you sure to the delete property?", SwingConstants.CENTER);
        label.setFont(LABEL_FONT);
        label.setBounds(0, 10, 350, 50);

        box.add(label);

        box.revalidate();
        box.repaint();
    }

}
