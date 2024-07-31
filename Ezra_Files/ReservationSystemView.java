import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The ReservationSystemView class fulfills the view role in the MVC architecture.
 * It is responsible for the user interface that is used to provided input and give output to the users.
 * 
 */
public class ReservationSystemView {
    /**
     * GUI Elements
     */
    private JFrame mainFrame;
    private JPanel createHotelPanel, viewHotelPanel, manageHotelPanel, simulateBookingPanel;
    private JPanel simulateBookingPane;
    private JTextField hotelName, standardNum, deluxeNum, executiveNum, simulateGuestName, simulateDiscount;
    private JTextArea hotelInfo;
    private JButton createHotel, displayLowLevelInformation, manageHotelCommands, simulateBooking; //displayAvailableBooked, displayRoomInfo;
    private JComboBox<String> hotelOptions, manageOptions, simulateHotelOptions, simulateDateInOptions, simulateDateOutOptions, simulateRoomOptions;
    private DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> model3 = new DefaultComboBoxModel<>();
    String[] roomTypes = {"Standard Room", "Deluxe Room", "Executive Room"};
    private DefaultComboBoxModel<String> model4 = new DefaultComboBoxModel<>(roomTypes);
    private DefaultComboBoxModel<String> modelDateIn = new DefaultComboBoxModel<>(getDates30());
    private DefaultComboBoxModel<String> modelDateOut = new DefaultComboBoxModel<>(getDates31());
    private JTabbedPane menu;

    /**
     * Constructor for the view which formats the main window for the user interface
     * 
     */
    public ReservationSystemView() {
        // Main frame formatting
        this.mainFrame = new JFrame("Hotel Reservation System");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(470, 720);
        this.mainFrame.setResizable(false);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setLocationRelativeTo(null);

        // Initializing each section of the tabbed pane
        initializeCreateHotel();
        initializeViewHotel();
        initializeManageHotel();
        initializeSimulateBooking();

        // Adding tabs in the tabbed pane
        this.menu = new JTabbedPane();
        this.menu.addTab("Create Hotel", this.createHotelPanel);
        this.menu.addTab("View Hotel", this.viewHotelPanel);
        this.menu.addTab("Manage Hotel", this.manageHotelPanel);
        this.menu.addTab("Simulate Booking", this.simulateBookingPanel);

        // Adding the tabbed pane to the main frame
        this.mainFrame.add(this.menu);

        // Sets the main frame as visible
        this.mainFrame.setVisible(true);    
    }

    /**
     * Initializes the create hotel section 
     * 
     */
    public void initializeCreateHotel() {
        // Main panel
        this.createHotelPanel = new JPanel(new BorderLayout()); 

        // Header
        JLabel headerLabel = new JLabel("Create Hotel");
        JPanel header = new JPanel(new BorderLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(150, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);
        
        // Create hotel pane
        JPanel createHotelPane = new JPanel(new FlowLayout());
        createHotelPane.setPreferredSize(new Dimension(250, 250));

        // Prompts for text fields
        JLabel hotelPrompt = new JLabel("Enter Hotel Name: ");
        JLabel standardPrompt = new JLabel("Enter Number of Standard Rooms: ");
        JLabel deluxePrompt = new JLabel("Enter Number of Deluxe Rooms: ");
        JLabel executivePrompt = new JLabel("Enter Number of Executive Rooms: ");

        hotelPrompt.setPreferredSize(new Dimension(250, 100));
        standardPrompt.setPreferredSize(new Dimension(250, 100));
        deluxePrompt.setPreferredSize(new Dimension(250, 100));
        executivePrompt.setPreferredSize(new Dimension(250, 100));

        // Text fields
        this.hotelName = new JTextField();
        this.hotelName.setColumns(10);
        this.standardNum = new JTextField();
        this.standardNum.setColumns(10);
        this.deluxeNum = new JTextField();
        this.deluxeNum.setColumns(10);
        this.executiveNum = new JTextField();
        this.executiveNum.setColumns(10);
        this.createHotel = new JButton("Create Hotel");

        // Add to pane
        createHotelPane.add(hotelPrompt);
        createHotelPane.add(this.hotelName);

        createHotelPane.add(standardPrompt);
        createHotelPane.add(this.standardNum);

        createHotelPane.add(deluxePrompt);
        createHotelPane.add(this.deluxeNum);

        createHotelPane.add(executivePrompt);
        createHotelPane.add(this.executiveNum);

        createHotelPane.add(this.createHotel);

        // Add header and pane to panel
        this.createHotelPanel.add(header, BorderLayout.NORTH);
        this.createHotelPanel.add(createHotelPane, BorderLayout.CENTER);
    }

    /**
     * Initializes the view hotel section 
     * 
     */
    public void initializeViewHotel() {
        // Main panel
        this.viewHotelPanel = new JPanel(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("View Hotel");
        JPanel header = new JPanel(new BorderLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(150, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        // View hotel pane
        JPanel viewHotelPane = new JPanel(new BorderLayout());
        viewHotelPane.setPreferredSize(new Dimension(250, 50));

        // Hotel options
        JLabel viewHotelTitle = new JLabel("List of Hotels:  ");
        viewHotelTitle.setHorizontalAlignment(JLabel.CENTER);
        this.hotelOptions = new JComboBox<>(model);
        this.hotelOptions.setEditable(false);
        this.hotelOptions.setSelectedIndex(-1);
        viewHotelPane.add(viewHotelTitle, BorderLayout.NORTH);
        viewHotelPane.add(this.hotelOptions, BorderLayout.CENTER);
        
        // Info pane for high level information and low level information
        JPanel infoPane = new JPanel();
        infoPane.setPreferredSize(new Dimension(500, 550));

        // High level information pane
        JPanel hotelInfoHighPane = new JPanel(new BorderLayout());
        hotelInfoHighPane.setPreferredSize(new Dimension(250, 100));
        JLabel hotelInfoHighTitle = new JLabel("High Level Information: ");
        hotelInfoHighTitle.setHorizontalAlignment(JLabel.CENTER);
        this.hotelInfo = new JTextArea(); 
        this.hotelInfo.setEditable(false);
        hotelInfoHighPane.add(hotelInfoHighTitle, BorderLayout.NORTH);
        hotelInfoHighPane.add(this.hotelInfo, BorderLayout.CENTER);
        
        // Low level information pane, holds the button for the view hotel extension
        JPanel hotelInfoLowPane = new JPanel(new FlowLayout());
        hotelInfoLowPane.setPreferredSize(new Dimension(250, 100));
        this.displayLowLevelInformation = new JButton("Low Level Information ");
        this.displayLowLevelInformation.setHorizontalAlignment(JLabel.CENTER);
        hotelInfoLowPane.add(this.displayLowLevelInformation);

        // Add to info pane
        infoPane.add(hotelInfoHighPane, BorderLayout.NORTH);
        infoPane.add(hotelInfoLowPane, BorderLayout.CENTER);

        // Add all panes and header to main panel
        this.viewHotelPanel.add(header, BorderLayout.NORTH);
        this.viewHotelPanel.add(viewHotelPane, BorderLayout.CENTER);
        this.viewHotelPanel.add(infoPane, BorderLayout.SOUTH);
    }

    /**
     * Initializes the manage hotel section 
     * 
     */
    public void initializeManageHotel() {
        // Main panel
        this.manageHotelPanel = new JPanel(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Manage Hotel");
        JPanel header = new JPanel(new BorderLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(150, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);
        
        // Manage hotel pane
        JPanel manageHotelPane = new JPanel(new BorderLayout());
        manageHotelPane.setPreferredSize(new Dimension(250, 50));

        // Hotel options for manage hotel
        JLabel manageHotelTitle = new JLabel("List of Hotels:  ");
        manageHotelTitle.setHorizontalAlignment(JLabel.CENTER);
        this.manageOptions = new JComboBox<>(model2);
        this.manageOptions.setEditable(false);
        this.manageOptions.setSelectedIndex(-1);
        manageHotelPane.add(manageHotelTitle, BorderLayout.NORTH);
        manageHotelPane.add(this.manageOptions, BorderLayout.CENTER);

        // Button for manage hotel extension
        JPanel manageHotelCommandsPane = new JPanel(new FlowLayout());
        manageHotelCommandsPane.setPreferredSize(new Dimension(250, 550));
        this.manageHotelCommands = new JButton("Manage Hotel Commands");
        this.manageHotelCommands.setSize(50, 150);
        manageHotelCommandsPane.add(this.manageHotelCommands);

        // Add panes to main panel
        this.manageHotelPanel.add(header, BorderLayout.NORTH);
        this.manageHotelPanel.add(manageHotelPane, BorderLayout.CENTER);
        this.manageHotelPanel.add(manageHotelCommandsPane, BorderLayout.SOUTH);
   }

    /**
     * Initializes the simulate booking section 
     * 
     */
    public void initializeSimulateBooking() {
        // Main panel
        this.simulateBookingPanel = new JPanel(new BorderLayout());
        this.simulateBookingPanel.setPreferredSize(new Dimension(250, 50));

        // Header
        JLabel headerLabel = new JLabel("Simulate Booking");
        JPanel header = new JPanel(new BorderLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        // Simulate booking pane
        this.simulateBookingPane = new JPanel(new BorderLayout());
        this.simulateBookingPane.setPreferredSize(new Dimension(250, 550));

        // Hotel options
        JPanel simulateBookingHotelOptionsPane = new JPanel(new BorderLayout());
        simulateBookingHotelOptionsPane.setPreferredSize(new Dimension(250,40));
        JLabel simulateBookingHotelTitle = new JLabel("List of Hotels:  ");
        simulateBookingHotelTitle.setHorizontalAlignment(JLabel.CENTER);
        this.simulateHotelOptions = new JComboBox<>(model3);
        this.simulateHotelOptions.setEditable(false);
        this.simulateHotelOptions.setSelectedIndex(-1);

        // Add hotel options to simulate booking pane
        simulateBookingHotelOptionsPane.add(simulateBookingHotelTitle, BorderLayout.NORTH);
        simulateBookingHotelOptionsPane.add(this.simulateHotelOptions, BorderLayout.CENTER);

        // Guest pane for name input
        JPanel simulateBookingGuestPane = new JPanel(new FlowLayout());
        simulateBookingGuestPane.setPreferredSize(new Dimension(250, 50));
        JLabel simulateBookingGuestPrompt = new JLabel("Enter the Guest Name: ");
        simulateBookingGuestPrompt.setPreferredSize(new Dimension(250, 50));
        this.simulateGuestName = new JTextField();
        this.simulateGuestName.setColumns(10);
        simulateBookingGuestPane.add(simulateBookingGuestPrompt);
        simulateBookingGuestPane.add(this.simulateGuestName);

        // Date pane for the check in and check out date
        JPanel simulateBookingRoomDatePane = new JPanel(new BorderLayout());
        simulateBookingRoomDatePane.setPreferredSize(new Dimension(250,150));

        // Room pane for room type selection
        JPanel simulateBookingRoomPane = new JPanel(new BorderLayout());
        simulateBookingRoomPane.setPreferredSize(new Dimension(250, 60));
        JLabel simulateBookingRoomTitle = new JLabel("List of Room Types: ");
        simulateBookingRoomTitle.setHorizontalAlignment(JLabel.CENTER);
        this.simulateRoomOptions = new JComboBox<>(model4);
        this.simulateRoomOptions.setEditable(false);
        this.simulateRoomOptions.setSelectedItem(null);
        simulateBookingRoomPane.add(simulateBookingRoomTitle, BorderLayout.NORTH);
        simulateBookingRoomPane.add(this.simulateRoomOptions, BorderLayout.CENTER);

        JPanel simulateBookingDateInPane = new JPanel(new BorderLayout());
        simulateBookingDateInPane.setPreferredSize(new Dimension(250, 50));
        JLabel simulateBookingInDateTitle = new JLabel("Check In Date:  ");
        simulateBookingInDateTitle.setHorizontalAlignment(JLabel.CENTER);
        this.simulateDateInOptions = new JComboBox<>(modelDateIn);
        this.simulateDateInOptions.setEditable(false);
        this.simulateDateInOptions.setSelectedItem(null);
        simulateBookingDateInPane.add(simulateBookingInDateTitle, BorderLayout.NORTH);
        simulateBookingDateInPane.add(this.simulateDateInOptions, BorderLayout.CENTER);

        JPanel simulateBookingDateOutPane = new JPanel(new BorderLayout());
        simulateBookingDateOutPane.setPreferredSize(new Dimension(250, 60));
        JLabel simulateBookingOutDateTitle = new JLabel("Check Out Date:  ");
        simulateBookingOutDateTitle.setHorizontalAlignment(JLabel.CENTER);
        this.simulateDateOutOptions = new JComboBox<>(modelDateOut);
        this.simulateDateOutOptions.setEditable(false);
        this.simulateDateOutOptions.setSelectedItem(null);
        simulateBookingDateOutPane.add(simulateBookingOutDateTitle, BorderLayout.NORTH);
        simulateBookingDateOutPane.add(this.simulateDateOutOptions, BorderLayout.CENTER);

        // Add to date pane both check in and check out dates
        simulateBookingRoomDatePane.add(simulateBookingRoomPane, BorderLayout.NORTH);
        simulateBookingRoomDatePane.add(simulateBookingDateInPane, BorderLayout.CENTER);
        simulateBookingRoomDatePane.add(simulateBookingDateOutPane, BorderLayout.SOUTH);

        // Book reservation button and discount code inputs
        JPanel simulateBookingButton = new JPanel(new FlowLayout());
        simulateBookingButton.setPreferredSize(new Dimension(250, 320));
        JLabel discountPrompt = new JLabel("Enter a Discount Code: ");
        discountPrompt.setPreferredSize(new Dimension(250, 50));
        this.simulateDiscount = new JTextField();
        this.simulateDiscount.setColumns(10);
        this.simulateBooking = new JButton("Book Reservation");
        simulateBookingButton.add(discountPrompt);
        simulateBookingButton.add(this.simulateDiscount);
        simulateBookingButton.add(this.simulateBooking);

        simulateBookingPane.add(simulateBookingGuestPane, BorderLayout.NORTH);
        simulateBookingPane.add(simulateBookingRoomDatePane, BorderLayout.CENTER);
        simulateBookingPane.add(simulateBookingButton, BorderLayout.SOUTH);

        // Add panes to main panel
        this.simulateBookingPanel.add(header, BorderLayout.NORTH);
        this.simulateBookingPanel.add(simulateBookingHotelOptionsPane, BorderLayout.CENTER);
        this.simulateBookingPanel.add(simulateBookingPane, BorderLayout.SOUTH);

    }

    /**
     * Creates an array of strings with values 1 to 30 for the dates
     * 
     * @return string array of dates 1 to 30
     */
    public String[] getDates30() {
        String[] dates = new String[30];
        for (int j = 0; j < dates.length; j++) {
            dates[j] = String.valueOf(j+1);
        }

        return dates;
    }
    
    /**
     * Creates an array of strings with values 1 to 31 for the dates
     * 
     * @return string array of dates 1 to 31
     */
    public String[] getDates31() {
        String[] dates = new String[31];
        for (int j = 0; j < dates.length; j++) {
            dates[j] = String.valueOf(j+1);
        }

        return dates;
    }

    /**
     * Sets the high level information of the hotel
     * 
     * @param message string to display in the text area
     */
    public void setHotelInfo(String message) {
        this.hotelInfo.setText(message);
    }

    /**
     * Add options to each combobox in every section in the main window.
     * 
     * @param item String to add as option
     */
    public void addHotelOption(String item) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.hotelOptions.getModel();
        DefaultComboBoxModel<String> model2 = (DefaultComboBoxModel<String>) this.manageOptions.getModel();
        DefaultComboBoxModel<String> model3 = (DefaultComboBoxModel<String>) this.simulateHotelOptions.getModel();

        model.addElement(item);
        model2.addElement(item);
        model3.addElement(item);
    }

    /**
     * Updates the options of each combobox in every section in the main window.
     * 
     * @param items array list of rooms of the hotel
     */
    public void updateHotelOptions(ArrayList<Hotel> items) {
        
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.hotelOptions.getModel();
        DefaultComboBoxModel<String> model2 = (DefaultComboBoxModel<String>) this.manageOptions.getModel();
        DefaultComboBoxModel<String> model3 = (DefaultComboBoxModel<String>) this.simulateHotelOptions.getModel();

        model.removeAllElements();
        model2.removeAllElements();
        model3.removeAllElements();

        for (Hotel hotel : items) {
            model.addElement(hotel.getName());
            model2.addElement(hotel.getName());
            model3.addElement(hotel.getName());
        }
    }

    /**
     * Adds create hotel action listener
     * 
     * @param actionListener action listener instance
     */
    public void setCreateHotelListener(ActionListener actionListener) {
        this.createHotel.addActionListener(actionListener);
    }

    /**
     * Adds view hotel item listener
     * 
     * @param itemListener item listener instance
     */
    public void setViewHotelItemListener(ItemListener itemListener) {
        this.hotelOptions.addItemListener(itemListener);
    }

    /**
     * Adds view hotel action listener
     * 
     * @param actionListener action listener instance
     */
    public void setViewHotelListener(ActionListener actionListener) {
        this.displayLowLevelInformation.addActionListener(actionListener);
    }

    
    /**
     * Adds manage hotel action listener
     * 
     * @param actionListener action listener instance
     */
    public void setManageHotelListener(ActionListener actionListener) {
        this.manageHotelCommands.addActionListener(actionListener);
    }

    /**
     * Adds manage hotel item listener
     * 
     * @param itemListener item listener instance
     */
    public void setManageHotelItemListener(ItemListener itemListener) {
        this.manageOptions.addItemListener(itemListener);
    }

    /**
     * Adds simulate booking action listener
     * 
     * @param actionListener action listener instance
     */
    public void setSimulateBookingListener(ActionListener actionListener) {
        this.simulateBooking.addActionListener(actionListener);
    }

    /**
     * Displays an error pop up dialog
     * 
     * @param error error message to display in the dialog
     */
    public void setErrorFeedback(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays an confirmatin pop up dialog
     * 
     * @param confirmation confirmation message to display in the dialog
     * @return integer value equivalent of the user input
     */
    public int setModConfirmFeedback(String confirmation) {
        return JOptionPane.showConfirmDialog(null, confirmation, "Proceed?", JOptionPane.YES_NO_OPTION);
    }
    
    /**
     * Clear text fields in every input
     * 
     */
    public void clearTextFields() {
        this.hotelName.setText("");
        this.standardNum.setText("");
        this.deluxeNum.setText("");
        this.executiveNum.setText("");
        this.simulateGuestName.setText("");
        this.simulateDiscount.setText("");
    }

        
    /**
     * Clear selections from the comboboxes of simulate booking section.
     * 
     */
    public void clearSimulateComboboxSelection() {
        this.simulateHotelOptions.setSelectedItem(null);
        this.simulateRoomOptions.setSelectedItem(null);
        this.simulateDateInOptions.setSelectedItem(null);
        this.simulateDateOutOptions.setSelectedItem(null);
    }
        
    /**
     * Clear selections from the comboboxes of every section.
     * 
     */
    public void clearComboboxSelection() {
        this.hotelOptions.setSelectedItem(null);
        this.manageOptions.setSelectedItem(null);
        this.simulateHotelOptions.setSelectedItem(null);
        this.simulateRoomOptions.setSelectedItem(null);
        this.simulateDateInOptions.setSelectedItem(null);
        this.simulateDateOutOptions.setSelectedItem(null);
    }

    // Getters for Input Fields

    /**
     * Getter for hotel name text field
     * 
     * @return JTextField instance
     */
    public JTextField getHotelName() {
        return this.hotelName;
    }

    /**
     * Getter for standard room number text field
     * 
     * @return JTextField instance
     */
    public JTextField getStandardNum() {
        return this.standardNum;
    }
    
    /**
     * Getter for deluxe room number text field
     * 
     * @return JTextField instance
     */
    public JTextField getDeluxeNum() {
        return this.deluxeNum;
    }
    
     /**
     * Getter for executive room number text field
     * 
     * @return JTextField instance
     */
    public JTextField getExecutiveNum() {
        return this.executiveNum;
    }

     /**
     * Getter for guest name text field
     * 
     * @return JTextField instance
     */
    public JTextField getGuestName() {
        return this.simulateGuestName;
    }

    /**
     * Getter for discount text field
     * 
     * @return JTextField instance
     */
    public JTextField getDiscountCode() {
        return this.simulateDiscount;
    }

    /**
     * Getter for hotel options combobox for view hotel
     * 
     * @return JComboBox instance
     */
    public JComboBox<String> getHotelOptions() {
        return this.hotelOptions;
    }

    /**
     * Getter for hotel options combobox for manage hotel
     * 
     * @return JComboBox instance
     */
    public JComboBox<String> getManageOptions() {
        return this.manageOptions;
    }

    /**
     * Getter for hotel options combobox for simulate booking
     * 
     * @return JComboBox instance
     */
    public JComboBox<String> getSimulateHotelOptions() {
        return this.simulateHotelOptions;
    }
    
    /**
     * Getter for room options combobox for simulate booking
     * 
     * @return JComboBox instance
     */
    public JComboBox<String> getSimulateRoomOptions() {
        return this.simulateRoomOptions;
    }

    /**
     * Getter for check in date options combobox for simulate booking
     * 
     * @return JComboBox instance
     */
    public JComboBox<String> getSimulateDateInOptions() {
        return this.simulateDateInOptions;
    }

    /**
     * Getter for check out date options combobox for simulate booking
     * 
     * @return JComboBox instance
     */
    public JComboBox<String> getSimulateDateOutOptions() {
        return this.simulateDateOutOptions;
    }

}
