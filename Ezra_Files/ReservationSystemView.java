import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.crypto.spec.DESKeySpec;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;


public class ReservationSystemView {
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

    public ReservationSystemView() {
        this.mainFrame = new JFrame("Hotel Reservation System");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(470, 720);
        this.mainFrame.setResizable(false);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setLocationRelativeTo(null);

        this.simulateBookingPanel = new JPanel();

        initializeCreateHotel();
        initializeViewHotel();
        initializeManageHotel();
        initializeSimulateBooking();

        this.menu = new JTabbedPane();
        this.menu.addTab("Create Hotel", this.createHotelPanel);
        this.menu.addTab("View Hotel", this.viewHotelPanel);
        this.menu.addTab("Manage Hotel", this.manageHotelPanel);
        this.menu.addTab("Simulate Booking", this.simulateBookingPanel);

        this.mainFrame.add(this.menu);
        this.mainFrame.setVisible(true);    
    }

    public void initializeCreateHotel() {
        this.createHotelPanel = new JPanel(new BorderLayout()); // Panel

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

        
        // Pane
        JPanel createHotelPane = new JPanel(new FlowLayout());
        createHotelPane.setPreferredSize(new Dimension(250, 250));

        JLabel hotelPrompt = new JLabel("Enter Hotel Name: ");
        JLabel standardPrompt = new JLabel("Enter Number of Standard Rooms: ");
        JLabel deluxePrompt = new JLabel("Enter Number of Deluxe Rooms: ");
        JLabel executivePrompt = new JLabel("Enter Number of Executive Rooms: ");

        hotelPrompt.setPreferredSize(new Dimension(250, 100));
        standardPrompt.setPreferredSize(new Dimension(250, 100));
        deluxePrompt.setPreferredSize(new Dimension(250, 100));
        executivePrompt.setPreferredSize(new Dimension(250, 100));

        this.hotelName = new JTextField();
        this.hotelName.setColumns(10);
        this.standardNum = new JTextField();
        this.standardNum.setColumns(10);
        this.deluxeNum = new JTextField();
        this.deluxeNum.setColumns(10);
        this.executiveNum = new JTextField();
        this.executiveNum.setColumns(10);
        this.createHotel = new JButton("Create Hotel");

        createHotelPane.add(hotelPrompt);
        createHotelPane.add(this.hotelName);

        createHotelPane.add(standardPrompt);
        createHotelPane.add(this.standardNum);

        createHotelPane.add(deluxePrompt);
        createHotelPane.add(this.deluxeNum);

        createHotelPane.add(executivePrompt);
        createHotelPane.add(this.executiveNum);

        createHotelPane.add(this.createHotel);

        this.createHotelPanel.add(header, BorderLayout.NORTH);
        this.createHotelPanel.add(createHotelPane, BorderLayout.CENTER);

    }
    
    public void initializeViewHotel() {
        this.viewHotelPanel = new JPanel(new BorderLayout());

        JLabel headerLabel = new JLabel("View Hotel");
        JPanel header = new JPanel(new BorderLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(150, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        JPanel viewHotelPane = new JPanel(new BorderLayout());
        viewHotelPane.setPreferredSize(new Dimension(250, 50));

        JLabel viewHotelTitle = new JLabel("List of Hotels:  ");
        viewHotelTitle.setHorizontalAlignment(JLabel.CENTER);
        this.hotelOptions = new JComboBox<>(model);
        this.hotelOptions.setEditable(false);
        this.hotelOptions.setSelectedIndex(-1);
        viewHotelPane.add(viewHotelTitle, BorderLayout.NORTH);
        viewHotelPane.add(this.hotelOptions, BorderLayout.CENTER);
        
        JPanel infoPane = new JPanel();
        infoPane.setPreferredSize(new Dimension(500, 550));

        JPanel hotelInfoHighPane = new JPanel(new BorderLayout());
        hotelInfoHighPane.setPreferredSize(new Dimension(250, 100));
        JLabel hotelInfoHighTitle = new JLabel("High Level Information: ");
        hotelInfoHighTitle.setHorizontalAlignment(JLabel.CENTER);
        this.hotelInfo = new JTextArea(); 
        this.hotelInfo.setEditable(false);
        hotelInfoHighPane.add(hotelInfoHighTitle, BorderLayout.NORTH);
        hotelInfoHighPane.add(this.hotelInfo, BorderLayout.CENTER);
        
        JPanel hotelInfoLowOptions = new JPanel(new FlowLayout());
        hotelInfoLowOptions.setPreferredSize(new Dimension(250, 100));
        this.displayLowLevelInformation = new JButton("Low Level Information ");
        this.displayLowLevelInformation.setHorizontalAlignment(JLabel.CENTER);
        hotelInfoLowOptions.add(this.displayLowLevelInformation);

        infoPane.add(hotelInfoHighPane, BorderLayout.NORTH);
        infoPane.add(hotelInfoLowOptions, BorderLayout.CENTER);

        // Delete
        this.viewHotelPanel.add(header, BorderLayout.NORTH);
        this.viewHotelPanel.add(viewHotelPane, BorderLayout.CENTER);
        this.viewHotelPanel.add(infoPane, BorderLayout.SOUTH);

    }

    public void initializeManageHotel() {
        this.manageHotelPanel = new JPanel(new BorderLayout());

        JLabel headerLabel = new JLabel("Manage Hotel");
        JPanel header = new JPanel(new BorderLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(150, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);
        
        JPanel manageHotelPane = new JPanel(new BorderLayout());
        manageHotelPane.setPreferredSize(new Dimension(250, 50));

        JLabel manageHotelTitle = new JLabel("List of Hotels:  ");
        manageHotelTitle.setHorizontalAlignment(JLabel.CENTER);
        this.manageOptions = new JComboBox<>(model2);
        this.manageOptions.setEditable(false);
        this.manageOptions.setSelectedIndex(-1);
        manageHotelPane.add(manageHotelTitle, BorderLayout.NORTH);
        manageHotelPane.add(this.manageOptions, BorderLayout.CENTER);

        JPanel manageHotelCommandsPane = new JPanel(new FlowLayout());
        manageHotelCommandsPane.setPreferredSize(new Dimension(250, 550));
        this.manageHotelCommands = new JButton("Manage Hotel Commands");
        this.manageHotelCommands.setSize(50, 150);
        manageHotelCommandsPane.add(this.manageHotelCommands);

        this.manageHotelPanel.add(header, BorderLayout.NORTH);
        this.manageHotelPanel.add(manageHotelPane, BorderLayout.CENTER);
        this.manageHotelPanel.add(manageHotelCommandsPane, BorderLayout.SOUTH);
   }

    public void initializeSimulateBooking() {
        this.simulateBookingPanel = new JPanel(new BorderLayout());
        this.simulateBookingPanel.setPreferredSize(new Dimension(250, 50));

        JLabel headerLabel = new JLabel("Simulate Booking");
        JPanel header = new JPanel(new BorderLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        this.simulateBookingPane = new JPanel(new BorderLayout());
        this.simulateBookingPane.setPreferredSize(new Dimension(250, 550));

        JPanel simulateBookingHotelOptionsPane = new JPanel(new BorderLayout());
        simulateBookingHotelOptionsPane.setPreferredSize(new Dimension(250,40));
        JLabel simulateBookingHotelTitle = new JLabel("List of Hotels:  ");
        simulateBookingHotelTitle.setHorizontalAlignment(JLabel.CENTER);
        this.simulateHotelOptions = new JComboBox<>(model3);
        this.simulateHotelOptions.setEditable(false);
        this.simulateHotelOptions.setSelectedIndex(-1);

        simulateBookingHotelOptionsPane.add(simulateBookingHotelTitle, BorderLayout.NORTH);
        simulateBookingHotelOptionsPane.add(this.simulateHotelOptions, BorderLayout.CENTER);

        JPanel simulateBookingGuestPane = new JPanel(new FlowLayout());
        simulateBookingGuestPane.setPreferredSize(new Dimension(250, 50));
        JLabel simulateBookingGuestPrompt = new JLabel("Enter the Guest Name: ");
        simulateBookingGuestPrompt.setPreferredSize(new Dimension(250, 50));
        this.simulateGuestName = new JTextField();
        this.simulateGuestName.setColumns(10);
        simulateBookingGuestPane.add(simulateBookingGuestPrompt);
        simulateBookingGuestPane.add(this.simulateGuestName);

        JPanel simulateBookingRoomDatePane = new JPanel(new BorderLayout());
        simulateBookingRoomDatePane.setPreferredSize(new Dimension(250,150));

        JPanel simulateBookingRoomPane = new JPanel(new BorderLayout());
        simulateBookingRoomPane.setPreferredSize(new Dimension(250, 60));
        JLabel simulateBookingRoomTitle = new JLabel("List of Rooms: ");
        simulateBookingRoomTitle.setHorizontalAlignment(JLabel.CENTER);
        this.simulateRoomOptions = new JComboBox<>(model4);
        this.simulateRoomOptions.setEditable(false);
        this.simulateRoomOptions.setSelectedIndex(-1);
        
        simulateBookingRoomPane.add(simulateBookingRoomTitle, BorderLayout.NORTH);
        simulateBookingRoomPane.add(this.simulateRoomOptions, BorderLayout.CENTER);


        JPanel simulateBookingDateInPane = new JPanel(new BorderLayout());
        simulateBookingDateInPane.setPreferredSize(new Dimension(250, 50));
        JLabel simulateBookingInDateTitle = new JLabel("Check In Date:  ");
        simulateBookingInDateTitle.setHorizontalAlignment(JLabel.CENTER);
        this.simulateDateInOptions = new JComboBox<>(modelDateIn);
        this.simulateDateInOptions.setEditable(false);
        this.simulateDateInOptions.setSelectedIndex(-1);

        simulateBookingDateInPane.add(simulateBookingInDateTitle, BorderLayout.NORTH);
        simulateBookingDateInPane.add(this.simulateDateInOptions, BorderLayout.CENTER);

        JPanel simulateBookingDateOutPane = new JPanel(new BorderLayout());
        simulateBookingDateOutPane.setPreferredSize(new Dimension(250, 60));
        JLabel simulateBookingOutDateTitle = new JLabel("Check Out Date:  ");
        simulateBookingOutDateTitle.setHorizontalAlignment(JLabel.CENTER);
        this.simulateDateOutOptions = new JComboBox<>(modelDateOut);
        this.simulateDateOutOptions.setEditable(false);
        this.simulateDateOutOptions.setSelectedIndex(-1);

        simulateBookingDateOutPane.add(simulateBookingOutDateTitle, BorderLayout.NORTH);
        simulateBookingDateOutPane.add(this.simulateDateOutOptions, BorderLayout.CENTER);

        simulateBookingRoomDatePane.add(simulateBookingRoomPane, BorderLayout.NORTH);
        simulateBookingRoomDatePane.add(simulateBookingDateInPane, BorderLayout.CENTER);
        simulateBookingRoomDatePane.add(simulateBookingDateOutPane, BorderLayout.SOUTH);


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

        this.simulateBookingPanel.add(header, BorderLayout.NORTH);
        this.simulateBookingPanel.add(simulateBookingHotelOptionsPane, BorderLayout.CENTER);
        this.simulateBookingPanel.add(simulateBookingPane, BorderLayout.SOUTH);

    }

    public String[] getDates30() {
        String[] dates = new String[30];
        for (int j = 0; j < dates.length; j++) {
            dates[j] = String.valueOf(j+1);
        }

        return dates;
    }
    
    public String[] getDates31() {
        String[] dates = new String[31];
        for (int j = 0; j < dates.length; j++) {
            dates[j] = String.valueOf(j+1);
        }

        return dates;
    }

    public void setHotelInfo(String message) {
        this.hotelInfo.setText(message);
    }

    public void addHotelOption(String item) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.hotelOptions.getModel();
        DefaultComboBoxModel<String> model2 = (DefaultComboBoxModel<String>) this.manageOptions.getModel();
        DefaultComboBoxModel<String> model3 = (DefaultComboBoxModel<String>) this.simulateHotelOptions.getModel();

        model.addElement(item);
        model2.addElement(item);
        model3.addElement(item);
    }

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

        // this.hotelOptions.setSelectedIndex(-1);
        // this.manageOptions.setSelectedIndex(-1);
        // this.simulateHotelOptions.setSelectedIndex(-1);

    }

    public void setMenuChangeListener(ChangeListener changeListener) {
        this.menu.addChangeListener(changeListener);
    }

    public void setCreateHotelListener(ActionListener actionListener) {
        this.createHotel.addActionListener(actionListener);
    }

    public void setViewHotelItemListener(ItemListener itemListener) {
        this.hotelOptions.addItemListener(itemListener);
    }

    public void setViewHotelListener(ActionListener actionListener) {
        this.displayLowLevelInformation.addActionListener(actionListener);
    }

    public void setManageHotelListener(ActionListener actionListener) {
        this.manageHotelCommands.addActionListener(actionListener);
    }

    public void setManageHotelItemListener(ItemListener itemListener) {
        this.manageOptions.addItemListener(itemListener);
    }

    public void setSimulateBookingListener(ActionListener actionListener) {
        this.simulateBooking.addActionListener(actionListener);
    }


    public void setErrorFeedback(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public int setModConfirmFeedback(String confirmation) {
        return JOptionPane.showConfirmDialog(null, confirmation, "Proceed?", JOptionPane.YES_NO_OPTION);
    }
    
    public void clearTextFields() {
        this.hotelName.setText("");
        this.standardNum.setText("");
        this.deluxeNum.setText("");
        this.executiveNum.setText("");
        this.simulateGuestName.setText("");
        this.simulateDiscount.setText("");
    }

    public void clearComboboxSelection() {
        this.hotelOptions.setSelectedItem(null);
        this.manageOptions.setSelectedItem(null);
        this.simulateHotelOptions.setSelectedItem(null);
        this.simulateRoomOptions.setSelectedItem(null);
        this.simulateDateInOptions.setSelectedItem(null);
        this.simulateDateOutOptions.setSelectedItem(null);
    }

    // Getters for Input Fields

    public JTextField getHotelName() {
        return this.hotelName;
    }

    public JTextField getStandardNum() {
        return this.standardNum;
    }
    
    public JTextField getDeluxeNum() {
        return this.deluxeNum;
    }
    
    public JTextField getExecutiveNum() {
        return this.executiveNum;
    }

    public JTextField getGuestName() {
        return this.simulateGuestName;
    }

    public JTextField getDiscountCode() {
        return this.simulateDiscount;
    }

    public JComboBox<String> getHotelOptions() {
        return this.hotelOptions;
    }

    public JComboBox<String> getManageOptions() {
        return this.manageOptions;
    }

    public JComboBox<String> getSimulateHotelOptions() {
        return this.simulateHotelOptions;
    }

    public JComboBox<String> getSimulateRoomOptions() {
        return this.simulateRoomOptions;
    }

    public JComboBox<String> getSimulateDateInOptions() {
        return this.simulateDateInOptions;
    }

    public JComboBox<String> getSimulateDateOutOptions() {
        return this.simulateDateOutOptions;
    }

}
