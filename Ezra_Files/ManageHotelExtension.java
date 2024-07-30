import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ManageHotelExtension {
    // GUI Elements
    private JFrame mainFrame;
    private JPanel renameHotelPanel, addRoomPanel, removeRoomPanel, updateBasePricePanel, modifyPriceRatePanel, removeReservationPanel, removeHotelPanel;
    private JTextField hotelNewName, standardRoomNum, deluxeRoomNum, executiveRoomNum, newBasePrice, newPriceRate;
    private JButton renameHotel, addRoom, removeRoom, updateBasePrice, modifyPriceRate, removeReservation, removeHotel;
    private JComboBox<String> removeRoomOptions, dateOptions, removeReservationOptions;
    private JTabbedPane menu;
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();

    /**
     * Constructor for the manage hotel extension which formats the window.
     * 
     */
    public ManageHotelExtension() {
        // Main frame
        this.mainFrame = new JFrame("Hotel Reservation System");
        this.mainFrame.setSize(470, 720);
        this.mainFrame.setResizable(false);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setLocationRelativeTo(null);

        // Intializing each section of the tabbed pane
        initializeRenameHotel();
        initializeAddRoom();
        initializeRemoveRoom();
        initializeUpdateBasePrice();
        initializeModifyPriceRate();
        initializeRemoveReservation();
        initializeRemoveHotel();

        // Tabbed pane
        this.menu = new JTabbedPane();
        this.menu.add(this.renameHotelPanel, "Rename Hotel");
        this.menu.add(this.addRoomPanel, "Add Room");
        this.menu.add(this.removeRoomPanel, "Remove Room");
        this.menu.add(this.updateBasePricePanel, "Update Base Price");
        this.menu.add(this.modifyPriceRatePanel, "Modify Price Rate");
        this.menu.add(this.removeReservationPanel, "Remove Reservation");
        this.menu.add(this.removeHotelPanel, "Remove Hotel");

        // Add to main frame
        this.mainFrame.add(this.menu);
    }

    /**
     * Initialize rename hotel section
     * 
     */
    public void initializeRenameHotel() {
        // Main panel
        this.renameHotelPanel = new JPanel(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Rename Hotel");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        // rename hotel pane
        JPanel renameHotelPane = new JPanel(new FlowLayout());
        renameHotelPane.setPreferredSize(new Dimension(250, 250));
        JLabel renamePrompt = new JLabel("Enter the new hotel name: ");
        renamePrompt.setPreferredSize(new Dimension(250, 100));
        this.hotelNewName = new JTextField();
        this.hotelNewName.setColumns(10);
        this.renameHotel = new JButton("Rename Hotel");
        
        // Add to pane
        renameHotelPane.add(renamePrompt);
        renameHotelPane.add(this.hotelNewName);
        renameHotelPane.add(this.renameHotel);
        
        // Add pane to panel
        this.renameHotelPanel.add(header, BorderLayout.NORTH);
        this.renameHotelPanel.add(renameHotelPane, BorderLayout.CENTER);

    }

    /**
     * Initialize add room section
     * 
     */
    public void initializeAddRoom() {
        // Main panel
        this.addRoomPanel = new JPanel(new BorderLayout());
        this.addRoomPanel.setPreferredSize(new Dimension(250, 550));

        // Header
        JLabel headerLabel = new JLabel("Add Room");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);
        
        // Add room pane
        JPanel addRoomPane = new JPanel(new BorderLayout());
        addRoomPane.setPreferredSize(new Dimension(250, 350));

        // Standard Room Num
        JPanel standardRoomNumPane = new JPanel(new FlowLayout());
        JLabel standardRoomTitle = new JLabel("Enter the number Standard Rooms to add: ");
        this.standardRoomNum = new JTextField();
        this.standardRoomNum.setColumns(10);
        standardRoomTitle.setHorizontalAlignment(JLabel.CENTER);
        standardRoomNumPane.add(standardRoomTitle);
        standardRoomNumPane.add(this.standardRoomNum);

        // Deluxe Room Num
        JPanel deluxeRoomNumPane = new JPanel(new FlowLayout());
        JLabel deluxeRoomTitle = new JLabel("Enter the number Deluxe Rooms to add: ");
        this.deluxeRoomNum = new JTextField();
        this.deluxeRoomNum.setColumns(10);
        deluxeRoomTitle.setHorizontalAlignment(JLabel.CENTER);
        deluxeRoomNumPane.add(deluxeRoomTitle);
        deluxeRoomNumPane.add(this.deluxeRoomNum);
      
        // Executive Room Num
        JPanel executiveRoomNumPane = new JPanel(new FlowLayout());
        JLabel executiveRoomTitle = new JLabel("Enter the number Executive Rooms to add: ");
        this.executiveRoomNum = new JTextField();
        this.executiveRoomNum.setColumns(10);
        executiveRoomTitle.setHorizontalAlignment(JLabel.CENTER);
        executiveRoomNumPane.add(executiveRoomTitle);
        executiveRoomNumPane.add(this.executiveRoomNum);

        // Add to pane
        addRoomPane.add(standardRoomNumPane, BorderLayout.NORTH);
        addRoomPane.add(deluxeRoomNumPane, BorderLayout.CENTER);
        addRoomPane.add(executiveRoomNumPane, BorderLayout.SOUTH);

        // Button pane
        JPanel addRoomButton = new JPanel(new FlowLayout());
        addRoomButton.setPreferredSize(new Dimension(250, 480));
        this.addRoom = new JButton("Add Room/s");
        this.addRoom.setPreferredSize(new Dimension(150, 25));
        addRoomButton.add(this.addRoom);

        // Add pane to panel
        this.addRoomPanel.add(header, BorderLayout.NORTH);
        this.addRoomPanel.add(addRoomPane, BorderLayout.CENTER);
        this.addRoomPanel.add(addRoomButton, BorderLayout.SOUTH);
    }

    /**
     * Initialize remove room section
     * 
     */
    public void initializeRemoveRoom() {
        // Main panel
        this.removeRoomPanel = new JPanel(new BorderLayout());
        this.removeRoomPanel.setPreferredSize(new Dimension(250, 550));

        // Header
        JLabel headerLabel = new JLabel("Remove Room");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        // Room options pane
        JPanel removeRoomOptionsPane = new JPanel(new BorderLayout());
        removeRoomOptionsPane.setPreferredSize(new Dimension(250, 50));
        JLabel removeRoomOptionsTitle = new JLabel("List of Rooms: ");
        removeRoomOptionsTitle.setHorizontalAlignment(JLabel.CENTER);
        this.removeRoomOptions = new JComboBox<>(model);
        removeRoomOptionsPane.add(removeRoomOptionsTitle, BorderLayout.NORTH);
        removeRoomOptionsPane.add(this.removeRoomOptions, BorderLayout.CENTER);

        // Button
        JPanel removeRoomButton = new JPanel(new FlowLayout());
        removeRoomButton.setPreferredSize(new Dimension(250, 520));
        this.removeRoom = new JButton("Remove Room");
        this.removeRoom.setPreferredSize(new Dimension(150, 25));
        removeRoomButton.add(this.removeRoom);

        // Add to panel
        this.removeRoomPanel.add(header, BorderLayout.NORTH);
        this.removeRoomPanel.add(removeRoomOptionsPane, BorderLayout.CENTER);
        this.removeRoomPanel.add(removeRoomButton, BorderLayout.SOUTH);
    }

   /**
     * Initialize update base price section
     * 
     */
    public void initializeUpdateBasePrice() {
        // Main panel
        this.updateBasePricePanel = new JPanel(new BorderLayout());
        this.updateBasePricePanel.setPreferredSize(new Dimension(250, 50));

        // Header
        JLabel headerLabel = new JLabel("Update Base Price");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        // Update base price pane
        JPanel updateBasePricePane = new JPanel(new FlowLayout());
        updateBasePricePane.setPreferredSize(new Dimension(250, 250));
        JLabel updateBasePricePrompt = new JLabel("Enter the new hotel base price: ");
        updateBasePricePrompt.setPreferredSize(new Dimension(250, 100));
        this.newBasePrice = new JTextField();
        this.newBasePrice.setColumns(10);
        this.updateBasePrice = new JButton("Update Base Price");

        // Add to pane
        updateBasePricePane.add(updateBasePricePrompt);
        updateBasePricePane.add(this.newBasePrice);
        updateBasePricePane.add(this.updateBasePrice);

        // Add pane to panel
        this.updateBasePricePanel.add(header, BorderLayout.NORTH);
        this.updateBasePricePanel.add(updateBasePricePane, BorderLayout.CENTER);
    }

   /**
     * Initialize modify price rate section
     * 
     */
    public void initializeModifyPriceRate() {
        // Main panel
        this.modifyPriceRatePanel = new JPanel(new BorderLayout());
        this.modifyPriceRatePanel.setPreferredSize(new Dimension(250, 50));

        // Header
        JLabel headerLabel = new JLabel("Modify Price Rate");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        // Modfiy price rate pane
        JPanel modifyPriceRatePane = new JPanel(new BorderLayout());
        modifyPriceRatePane.setPreferredSize(new Dimension(250, 550));

        // Date options
        JPanel modifyPriceRateOptionsPane = new JPanel(new BorderLayout());
        modifyPriceRateOptionsPane.setPreferredSize(new Dimension(250, 50));
        JLabel modifyPriceRateOptionsTitle = new JLabel("List of Dates: ");
        modifyPriceRateOptionsTitle.setHorizontalAlignment(JLabel.CENTER);
        this.dateOptions = new JComboBox<>(getDates31());
        modifyPriceRateOptionsPane.add(modifyPriceRateOptionsTitle, BorderLayout.NORTH);
        modifyPriceRateOptionsPane.add(this.dateOptions, BorderLayout.CENTER);
        
        // New price rate input
        JPanel modifyPriceRateInputPane = new JPanel(new FlowLayout());
        modifyPriceRateInputPane.setPreferredSize(new Dimension(250, 50));
        JLabel modifyPriceRatePrompt = new JLabel("Enter the new price rate for the date (in %): ");
        modifyPriceRatePrompt.setPreferredSize(new Dimension(250, 100));
        this.newPriceRate = new JTextField();
        this.newPriceRate.setColumns(10);
        this.modifyPriceRate = new JButton("Modify Price Rate");
        modifyPriceRateInputPane.add(modifyPriceRatePrompt);
        modifyPriceRateInputPane.add(this.newPriceRate);
        modifyPriceRateInputPane.add(this.modifyPriceRate);

        // Add to pane
        modifyPriceRatePane.add(modifyPriceRateOptionsPane, BorderLayout.NORTH);
        modifyPriceRatePane.add(modifyPriceRateInputPane, BorderLayout.CENTER);

        // Add pane to panel
        this.modifyPriceRatePanel.add(header, BorderLayout.NORTH);
        this.modifyPriceRatePanel.add(modifyPriceRatePane, BorderLayout.CENTER);

    }

    /**
     * Initialize remove reservation section
     * 
     */
    public void initializeRemoveReservation() {
        // Main panel
        this.removeReservationPanel = new JPanel(new BorderLayout());
        this.removeReservationPanel.setPreferredSize(new Dimension(250, 550));

        // Header
        JLabel headerLabel = new JLabel("Remove Reservation");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);
        
        // Reservation options
        JPanel removeReservationOptionsPane = new JPanel(new BorderLayout());
        removeReservationOptionsPane.setPreferredSize(new Dimension(250, 50));
        JLabel removeReservationOptionsTitle = new JLabel("List of Reservations: ");
        removeReservationOptionsTitle.setHorizontalAlignment(JLabel.CENTER);
        this.removeReservationOptions = new JComboBox<>(model2);
        removeReservationOptionsPane.add(removeReservationOptionsTitle, BorderLayout.NORTH);
        removeReservationOptionsPane.add(this.removeReservationOptions, BorderLayout.CENTER);

        // Remove reservation button
        JPanel removeReservationButton = new JPanel(new FlowLayout());
        removeReservationButton.setPreferredSize(new Dimension(250, 520));
        this.removeReservation = new JButton("Remove Reservation");
        this.removeReservation.setPreferredSize(new Dimension(175, 25));
        removeReservationButton.add(this.removeReservation);

        // Add to panel
        this.removeReservationPanel.add(header, BorderLayout.NORTH);
        this.removeReservationPanel.add(removeReservationOptionsPane, BorderLayout.CENTER);
        this.removeReservationPanel.add(removeReservationButton, BorderLayout.SOUTH);

    }

    /**
     * Initialize remove hotel section
     * 
     */
    public void initializeRemoveHotel() {
        // Main panel
        this.removeHotelPanel = new JPanel(new BorderLayout());
        this.removeHotelPanel.setPreferredSize(new Dimension(250, 550));

        // Header
        JLabel headerLabel = new JLabel("Remove Hotel");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        // Remove hotel button
        JPanel removeHotelButton = new JPanel(new FlowLayout());
        removeHotelButton.setPreferredSize(new Dimension(200, 520));
        this.removeHotel = new JButton("Remove Hotel");
        this.removeHotel.setPreferredSize(new Dimension(175, 25));
        removeHotelButton.add(this.removeHotel);

        // Add to panel
        this.removeHotelPanel.add(header, BorderLayout.NORTH);
        this.removeHotelPanel.add(removeHotelButton, BorderLayout.CENTER);

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
     * Removes an option in the remove room combobox
     * 
     * @param index index of the room/option to remove
     */
    public void removeRoomOptionComboBox(int index) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.removeRoomOptions.getModel();

        model.removeElementAt(index);
    }

    /**
     * Removes an option in the remove reservation combobox
     * 
     * @param index index of the reservation/option to remove
     */
    public void removeReservationOptionComboBox(int index) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.removeReservationOptions.getModel();

        model.removeElementAt(index);
    }

    /**
     * Updates the room options for the remove room
     * 
     * @param items array list of rooms of the hotel
     */
    public void updateRemoveRoomOptions(ArrayList<Room> items) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.removeRoomOptions.getModel();
        model.removeAllElements();

        for (Room room : items) {
            model.addElement(room.getName());
        }
    }

    
    /**
     * Updates the reservation options for the remove reservation
     * 
     * @param items array list of reservations of the hotel
     */
    public void updateRemoveReservationOptions(ArrayList<Reservation> items) {
        if (items.size() > 0) {
            String result;
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.removeReservationOptions.getModel();

            model.removeAllElements();
            for (Reservation reservation : items) {
                result = reservation.getRoom().getName() + ": ";
                result += "In: " + reservation.getCheckInDate() + " - Out: " + reservation.getCheckOutDate() + " ";
                result += "Guest: " + reservation.getGuestName();
                model.addElement(result);
            }
        }
    }

    /**
     * Adds a rename hotel action listener
     * 
     * @param actionListener action listener instance
     */
    public void setRenameHotelListener(ActionListener actionListener) {
        this.renameHotel.addActionListener(actionListener);
    }

    /**
     * Adds an add room listener
     * 
     * @param actionListener action listener instance
     */
    public void setAddRoomListener(ActionListener actionListener) {
        this.addRoom.addActionListener(actionListener);
    }

    /**
     * Adds a remove room action listener
     * 
     * @param actionListener action listener instance
     */
    public void setRemoveRoomListener(ActionListener actionListener) {
        this.removeRoom.addActionListener(actionListener);
    }

    /**
     * Adds an update base price action listener
     * 
     * @param actionListener action listener instance
     */
    public void setUpdateBasePriceListener(ActionListener actionListener) {
        this.updateBasePrice.addActionListener(actionListener);
    }

    /**
     * Adds an modify price rate action listener
     * 
     * @param actionListener action listener instance
     */
    public void setModifyPriceRateListener(ActionListener actionListener) {
        this.modifyPriceRate.addActionListener(actionListener);
    }

    /**
     * Adds an remove reservation action listener
     * 
     * @param actionListener action listener instance
     */
    public void setRemoveReservationListener(ActionListener actionListener) {
        this.removeReservation.addActionListener(actionListener);
    }

    /**
     * Adds an remove hotel action listener
     * 
     * @param actionListener action listener instance
     */
    public void setRemoveHotelListener(ActionListener actionListener) {
        this.removeHotel.addActionListener(actionListener);
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
        this.hotelNewName.setText("");
        this.standardRoomNum.setText("");
        this.deluxeRoomNum.setText("");
        this.executiveRoomNum.setText("");
        this.newBasePrice.setText("");
        this.newPriceRate.setText("");
    }

    /**
     * Clear selections from the comboboxes of every sections
     * 
     */
    public void clearComboboxSelection(){
        this.removeRoomOptions.setSelectedItem(null);
        this.dateOptions.setSelectedItem(null);
        this.removeReservationOptions.setSelectedItem(null);
    }

    /**
     * Returns the main frame
     * 
     * @return JFrame instance
     */
    public JFrame getMainFrame() {
        return this.mainFrame;
    }
    
    /**
     * Returns the rename hotel text field
     * 
     * @return JTextField instance
     */
    public JTextField getRenameHotelName() {
        return this.hotelNewName;
    }

    /**
     * Returns the standard room number text field in add room section
     * 
     * @return JTextField instance
     */
    public JTextField getStandardAddRoomNum() {
        return this.standardRoomNum;
    }
    
    /**
     * Returns the deluxe room number text field in add room section
     * 
     * @return JTextField instance
     */
    public JTextField getDeluxeAddRoomNum() {
        return this.deluxeRoomNum;
    }
    
    /**
     * Returns the executive room number text field in add room section
     * 
     * @return JTextField instance
     */
    public JTextField getExecutiveAddRoomNum() {
        return this.executiveRoomNum;
    }

    /**
     * Returns the new base price text field
     * 
     * @return JTextField instance
     */
    public JTextField getNewBasePrice() {
        return this.newBasePrice;
    }

    /**
     * Returns the new price rate text field
     * 
     * @return JTextField instance
     */
    public JTextField getNewPriceRate() {
        return this.newPriceRate;
    }

    /**
     * Returns the remove room options
     * 
     * @return JComboBox<String> instance
     */
    public JComboBox<String> getRemoveRoomOptions() {
        return this.removeRoomOptions;
    }
    
    /**
     * Returns the date options
     * 
     * @return JComboBox<String> instance
     */
    public JComboBox<String> getDateOptions() {
        return this.dateOptions;
    }
    
    /**
     * Returns the remove reservation options
     * 
     * @return JComboBox<String> instance
     */
    public JComboBox<String> getRemoveReservationOptions() {
        return this.removeReservationOptions;
    }

}
