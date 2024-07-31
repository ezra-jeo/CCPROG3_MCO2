import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * The ViewHotelExtension class provides an extension for the view hotel portion of the ReservationSystemView class
 * 
 */
public class ViewHotelExtension {
    // GUI Elements
    private JFrame mainFrame;
    private JPanel viewAvailableBookedPanel, viewRoomPanel, viewReservationPanel;
    private JPanel viewRoomPane, viewReservationPane, viewAvailableBookedPane; // Method Panes;
    private JTextArea availableBooked, roomInfo, reservationInfo;
    private JComboBox<String> roomOptions, dateOptions, reservationOptions;
    private JTabbedPane menu;
    DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();

    /**
     * Constructor for the view hotel extension which formats the window.
     * 
     */
    public ViewHotelExtension() {
        // Main frame
        this.mainFrame = new JFrame("Hotel Reservation System");
        this.mainFrame.setSize(470, 720);
        this.mainFrame.setResizable(false);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setLocationRelativeTo(null);

        // Initializing each section
        initializeViewAvailableBooked();
        initializeViewRoom();
        initializeViewReservation();

        // Tabbed pane
        this.menu = new JTabbedPane();
        this.menu.add(this.viewAvailableBookedPanel, "View Available and Booked Rooms");
        this.menu.add(this.viewRoomPanel, "View Room");
        this.menu.add(this.viewReservationPanel, "View Reservation");

        // Add to main frame
        this.mainFrame.add(this.menu);
    }
    
    /**
     * Initializes view available and booked rooms section
     * 
     */
    public void initializeViewAvailableBooked() {
        // Main panel
        this.viewAvailableBookedPanel = new JPanel(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("View Available and Booked Rooms");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        // View available booked pane
        this.viewAvailableBookedPane = new JPanel(new BorderLayout());
        this.viewAvailableBookedPane.setPreferredSize(new Dimension(250, 100));
        
        // Date options
        JLabel viewAvailableBookedTitle = new JLabel("List of Dates: ");
        viewAvailableBookedTitle.setHorizontalAlignment(JLabel.CENTER);
        this.dateOptions = new JComboBox<>(getDates31());
        this.dateOptions.setEditable(false);
        this.dateOptions.setPreferredSize(new Dimension(250, 50));
        this.dateOptions.setSelectedItem(null);

        // Text area to display information
        this.availableBooked = new JTextArea();
        this.availableBooked.setEditable(false);
        this.availableBooked.setLineWrap(true);
        this.availableBooked.setPreferredSize(new Dimension(250, 550));

        // Add to pane
        this.viewAvailableBookedPane.add(viewAvailableBookedTitle, BorderLayout.NORTH);
        this.viewAvailableBookedPane.add(this.dateOptions, BorderLayout.CENTER);
        this.viewAvailableBookedPane.add(this.availableBooked, BorderLayout.SOUTH);

        // Add pane and header to panel
        this.viewAvailableBookedPanel.add(header, BorderLayout.NORTH);
        this.viewAvailableBookedPanel.add(this.viewAvailableBookedPane, BorderLayout.CENTER);
    }

    /**
     * Initializes view room section
     * 
     */    
    public void initializeViewRoom() { 
        // Main panel
        this.viewRoomPanel = new JPanel(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("View Room");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        // View room pane
        this.viewRoomPane = new JPanel(new BorderLayout());
        this.viewRoomPane.setPreferredSize(new Dimension(250, 150));

        // Room options
        JLabel viewRoomTitle = new JLabel("List of Rooms: ");
        viewRoomTitle.setHorizontalAlignment(JLabel.CENTER);
        this.roomOptions = new JComboBox<>(model1);
        this.roomOptions.setEditable(false);
        this.roomOptions.setSelectedItem(null);
        
        // Info pane for view room section
        JPanel viewRoomInfoPane = new JPanel(new BorderLayout());
        viewRoomInfoPane.setPreferredSize(new Dimension(250, 550));
        JLabel viewRoomInfoTitle = new JLabel("Room Information: ");
        viewRoomInfoTitle.setHorizontalAlignment(JLabel.CENTER);
        this.roomInfo = new JTextArea();
        this.roomInfo.setEditable(false);
        this.roomInfo.setLineWrap(true);

        // Add to info pane
        viewRoomInfoPane.add(viewRoomInfoTitle, BorderLayout.NORTH);
        viewRoomInfoPane.add(this.roomInfo, BorderLayout.CENTER);
        viewRoomInfoPane.setPreferredSize(new Dimension(250, 550));

        // Add to view room pane
        this.viewRoomPane.add(viewRoomTitle, BorderLayout.NORTH);
        this.viewRoomPane.add(this.roomOptions, BorderLayout.CENTER);
        this.viewRoomPane.add(viewRoomInfoPane, BorderLayout.SOUTH);

        // Add to main panel
        this.viewRoomPanel.add(header, BorderLayout.NORTH);
        this.viewRoomPanel.add(this.viewRoomPane, BorderLayout.CENTER);
    }

    /**
     * Initializes view reservation section
     * 
     */    
    public void initializeViewReservation() {
        // Main panel
        this.viewReservationPanel = new JPanel(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("View Reservation");
        JPanel header = new JPanel(new BorderLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(150, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        // View reservation pane
        this.viewReservationPane = new JPanel(new BorderLayout());
        this.viewReservationPane.setPreferredSize(new Dimension(250, 50));
        
        // Reservation options
        this.reservationOptions = new JComboBox<>(model2);
        this.reservationOptions.setEditable(false);
        this.reservationOptions.setSelectedItem(null);
        
        // Info pane for view reservation section
        JPanel viewReservationInfoPane = new JPanel(new BorderLayout());
        viewReservationInfoPane.setPreferredSize(new Dimension(250, 550));
        JLabel viewReservationInfoTitle = new JLabel("List of Reservations: ");
        viewReservationInfoTitle.setHorizontalAlignment(JLabel.CENTER);
        this.reservationInfo = new JTextArea();
        this.reservationInfo.setEditable(false);

        // Add to info pane
        viewReservationInfoPane.add(this.reservationInfo, BorderLayout.CENTER);

        // Add to view reservation pane
        this.viewReservationPane.add(viewReservationInfoTitle, BorderLayout.NORTH);
        this.viewReservationPane.add(this.reservationOptions, BorderLayout.CENTER);
        this.viewReservationPane.add(viewReservationInfoPane, BorderLayout.SOUTH);

        // Add to main panel
        this.viewReservationPanel.add(header, BorderLayout.NORTH);
        this.viewReservationPanel.add(this.viewReservationPane, BorderLayout.CENTER);
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
     * Sets the text for view available and booked rooms information
     * 
     * @param message string to display in the text area
     */
    public void setAvailableBooked(String message) {
        this.availableBooked.setText(message);
    }

    /**
     * Sets the text for view room information
     * 
     * @param message string to display in the text area
     */
    public void setRoomInfo(String message) {
        this.roomInfo.setText(message);
    }

    /**
     * Set the text for the view reservation information
     * 
     * @param message string to display in the text area
     */
    public void setReservationInfo(String message) {
        this.reservationInfo.setText(message);
    }

    /**
     * Removes the room option in the combobox
     * 
     * @param index the index of the room option to remove
     */
    public void removeRoomOption(int index) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.roomOptions.getModel();

        model.removeElementAt(index);
    }

    /**
     * Removes the room option in the combobox
     * 
     * @param index the index of the reservation option to remove
     */
    public void removeReservationOption(int index) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.reservationOptions.getModel();

        model.removeElementAt(index);
    }

    /**
     * Updates the room options for the view room
     * 
     * @param items array list of rooms of the hotel
     */
    public void updateRoomOptions(ArrayList<Room> items) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.roomOptions.getModel();

        model.removeAllElements();

        for (Room room : items) {
            model.addElement(room.getName());
        }

        model.setSelectedItem(null);
    }

    /**
     * Updates the reservation options for the view reservation
     * 
     * @param items array list of reservations of the hotel
     */
    public void updateReservationOptions(ArrayList<Reservation> items) {
        if (items.size() > 0) {
            String result;
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.reservationOptions.getModel();

            model.removeAllElements();
            for (Reservation reservation : items) {
                result = reservation.getRoom().getName() + ": ";
                result += "In: " + reservation.getCheckInDate() + " --> Out: " + reservation.getCheckOutDate() + " ";
                result += "Guest: \"" + reservation.getGuestName() + "\"";
                model.addElement(result);
            }
            model.setSelectedItem(null);
        }
    }

    /**
     * Adds a view room listener
     * 
     * @param itemListener item listener instance
     */
    public void setViewRoomListener(ItemListener itemListener) {
        this.roomOptions.addItemListener(itemListener);
    }

    /**
     * Adds a view reservation listener
     * 
     * @param itemListener item listener instance
     */
    public void setViewReservationListener(ItemListener itemListener) {
        this.reservationOptions.addItemListener(itemListener);
    }

    /**
     * Adds an item listener for the date option in the view available and booked rooms section
     * 
     * @param itemListener item listener instance
     */
    public void setDateAvailableBookedListener(ItemListener itemListener) {
        this.dateOptions.addItemListener(itemListener);
    }

    /**
     * Returns the main frame window
     * 
     * @return JFrame instance
     */
    public JFrame getMainFrame() {
        return this.mainFrame;
    }

    /**
     * Returns the room options
     * 
     * @return JComboBox instance
     */
    public JComboBox<String> getRoomOptions() {
        return this.roomOptions;
    }

    /**
     * Returns the date options
     * 
     * @return JComboBox instance
     */
    public JComboBox<String> getDateOptions() {
        return this.dateOptions;
    }

    /**
     * Returns the reservation options
     * 
     * @return JComboBox instance
     */
    public JComboBox<String> getReservationOptions() {
        return this.reservationOptions;
    }

    /**
     * Clears the combobox selection for every section
     * 
     */
    public void clearComboboxSelection() {
        this.roomOptions.setSelectedItem(null);
        this.dateOptions.setSelectedItem(null);
        this.reservationOptions.setSelectedItem(null);
    }
}
