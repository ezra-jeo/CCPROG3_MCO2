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

public class ManageHotelExtension {
    private JFrame mainFrame;
    private JPanel renameHotelPanel, addRoomPanel, removeRoomPanel, updateBasePricePanel, modifyPriceRatePanel, removeReservationPanel, removeHotelPanel;
    private JTextField hotelNewName, standardRoomNum, deluxeRoomNum, executiveRoomNum, newBasePrice, newPriceRate;
    private JButton renameHotel, addRoom, removeRoom, updateBasePrice, modifyPriceRate, removeReservation, removeHotel;
    private JComboBox<String> removeRoomOptions, dateOptions, removeReservationOptions;
    private JTabbedPane menu;
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();

    public ManageHotelExtension() {
        this.mainFrame = new JFrame("Hotel Reservation System");
        this.mainFrame.setSize(470, 720);
        this.mainFrame.setResizable(false);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setLocationRelativeTo(null);

        initializeRenameHotel();
        initializeAddRoom();
        initializeRemoveRoom();
        initializeUpdateBasePrice();
        initializeModifyPriceRate();
        initializeRemoveReservation();
        initializeRemoveHotel();

        this.menu = new JTabbedPane();
        this.menu.add(this.renameHotelPanel, "Rename Hotel");
        this.menu.add(this.addRoomPanel, "Add Room");
        this.menu.add(this.removeRoomPanel, "Remove Room");
        this.menu.add(this.updateBasePricePanel, "Update Base Price");
        this.menu.add(this.modifyPriceRatePanel, "Modify Price Rate");
        this.menu.add(this.removeReservationPanel, "Remove Reservation");
        this.menu.add(this.removeHotelPanel, "Remove Hotel");

        this.mainFrame.add(this.menu);
    }

    public void initializeRenameHotel() {
        this.renameHotelPanel = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("Rename Hotel");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        JPanel renameHotelPane = new JPanel(new FlowLayout());
        renameHotelPane.setPreferredSize(new Dimension(250, 250));
        JLabel renamePrompt = new JLabel("Enter the new hotel name: ");
        renamePrompt.setPreferredSize(new Dimension(250, 100));
        this.hotelNewName = new JTextField();
        this.hotelNewName.setColumns(10);
        this.renameHotel = new JButton("Rename Hotel");
        
        renameHotelPane.add(renamePrompt);
        renameHotelPane.add(this.hotelNewName);
        renameHotelPane.add(this.renameHotel);
        this.renameHotelPanel.add(header, BorderLayout.NORTH);
        this.renameHotelPanel.add(renameHotelPane, BorderLayout.CENTER);

    }

    public void initializeAddRoom() {
        this.addRoomPanel = new JPanel(new BorderLayout());
        this.addRoomPanel.setPreferredSize(new Dimension(250, 550));
        JLabel headerLabel = new JLabel("Add Room");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);
        
        JPanel addRoomPane = new JPanel(new BorderLayout());
        addRoomPane.setPreferredSize(new Dimension(250, 350));

        JPanel standardRoomNumPane = new JPanel(new FlowLayout());
        JLabel standardRoomTitle = new JLabel("Enter the number Standard Rooms to add: ");
        this.standardRoomNum = new JTextField();
        this.standardRoomNum.setColumns(10);
        standardRoomTitle.setHorizontalAlignment(JLabel.CENTER);
        standardRoomNumPane.add(standardRoomTitle);
        standardRoomNumPane.add(this.standardRoomNum);

        JPanel deluxeRoomNumPane = new JPanel(new FlowLayout());
        JLabel deluxeRoomTitle = new JLabel("Enter the number Deluxe Rooms to add: ");
        this.deluxeRoomNum = new JTextField();
        this.deluxeRoomNum.setColumns(10);
        deluxeRoomTitle.setHorizontalAlignment(JLabel.CENTER);
        deluxeRoomNumPane.add(deluxeRoomTitle);
        deluxeRoomNumPane.add(this.deluxeRoomNum);
      
        JPanel executiveRoomNumPane = new JPanel(new FlowLayout());
        JLabel executiveRoomTitle = new JLabel("Enter the number Executive Rooms to add: ");
        this.executiveRoomNum = new JTextField();
        this.executiveRoomNum.setColumns(10);
        executiveRoomTitle.setHorizontalAlignment(JLabel.CENTER);
        executiveRoomNumPane.add(executiveRoomTitle);
        executiveRoomNumPane.add(this.executiveRoomNum);


        addRoomPane.add(standardRoomNumPane, BorderLayout.NORTH);
        addRoomPane.add(deluxeRoomNumPane, BorderLayout.CENTER);
        addRoomPane.add(executiveRoomNumPane, BorderLayout.SOUTH);
        //this.addRoomPane.add(this.addRoom, BorderLayout.AFTER_LAST_LINE);

        JPanel addRoomButton = new JPanel(new FlowLayout());
        addRoomButton.setPreferredSize(new Dimension(250, 480));
        this.addRoom = new JButton("Add Room/s");
        this.addRoom.setPreferredSize(new Dimension(150, 25));
        addRoomButton.add(this.addRoom);
        this.addRoomPanel.add(header, BorderLayout.NORTH);
        this.addRoomPanel.add(addRoomPane, BorderLayout.CENTER);
        this.addRoomPanel.add(addRoomButton, BorderLayout.SOUTH);

    }

    public void initializeRemoveRoom() {
        this.removeRoomPanel = new JPanel(new BorderLayout());
        this.removeRoomPanel.setPreferredSize(new Dimension(250, 550));

        JLabel headerLabel = new JLabel("Remove Room");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        JPanel removeRoomOptionsPane = new JPanel(new BorderLayout());
        removeRoomOptionsPane.setPreferredSize(new Dimension(250, 50));
        JLabel removeRoomOptionsTitle = new JLabel("List of Rooms: ");
        removeRoomOptionsTitle.setHorizontalAlignment(JLabel.CENTER);
        this.removeRoomOptions = new JComboBox<>(model);

        removeRoomOptionsPane.add(removeRoomOptionsTitle, BorderLayout.NORTH);
        removeRoomOptionsPane.add(this.removeRoomOptions, BorderLayout.CENTER);

        JPanel removeRoomButton = new JPanel(new FlowLayout());
        removeRoomButton.setPreferredSize(new Dimension(250, 520));

        this.removeRoom = new JButton("Remove Room");
        this.removeRoom.setPreferredSize(new Dimension(150, 25));
        removeRoomButton.add(this.removeRoom);

        this.removeRoomPanel.add(header, BorderLayout.NORTH);
        this.removeRoomPanel.add(removeRoomOptionsPane, BorderLayout.CENTER);
        this.removeRoomPanel.add(removeRoomButton, BorderLayout.SOUTH);

    }

    public void initializeUpdateBasePrice() {
        this.updateBasePricePanel = new JPanel(new BorderLayout());
        this.updateBasePricePanel.setPreferredSize(new Dimension(250, 50));

        JLabel headerLabel = new JLabel("Update Base Price");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        JPanel updateBasePricePane = new JPanel(new FlowLayout());
        updateBasePricePane.setPreferredSize(new Dimension(250, 250));
        JLabel updateBasePricePrompt = new JLabel("Enter the new hotel base price: ");
        updateBasePricePrompt.setPreferredSize(new Dimension(250, 100));
        this.newBasePrice = new JTextField();
        this.newBasePrice.setColumns(10);
        this.updateBasePrice = new JButton("Update Base Price");
        
        updateBasePricePane.add(updateBasePricePrompt);
        updateBasePricePane.add(this.newBasePrice);
        updateBasePricePane.add(this.updateBasePrice);

        this.updateBasePricePanel.add(header, BorderLayout.NORTH);
        this.updateBasePricePanel.add(updateBasePricePane, BorderLayout.CENTER);
    }

    public void initializeModifyPriceRate() {
        this.modifyPriceRatePanel = new JPanel(new BorderLayout());
        this.modifyPriceRatePanel.setPreferredSize(new Dimension(250, 50));

        JLabel headerLabel = new JLabel("Modify Price Rate");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        JPanel modifyPriceRatePane = new JPanel(new BorderLayout());
        modifyPriceRatePane.setPreferredSize(new Dimension(250, 550));

        JPanel modifyPriceRateOptionsPane = new JPanel(new BorderLayout());
        modifyPriceRateOptionsPane.setPreferredSize(new Dimension(250, 50));
        JLabel modifyPriceRateOptionsTitle = new JLabel("List of Dates: ");
        modifyPriceRateOptionsTitle.setHorizontalAlignment(JLabel.CENTER);
        this.dateOptions = new JComboBox<>(getDates31());
 
        modifyPriceRateOptionsPane.add(modifyPriceRateOptionsTitle, BorderLayout.NORTH);
        modifyPriceRateOptionsPane.add(this.dateOptions, BorderLayout.CENTER);
        
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

        modifyPriceRatePane.add(modifyPriceRateOptionsPane, BorderLayout.NORTH);
        modifyPriceRatePane.add(modifyPriceRateInputPane, BorderLayout.CENTER);

        this.modifyPriceRatePanel.add(header, BorderLayout.NORTH);
        this.modifyPriceRatePanel.add(modifyPriceRatePane, BorderLayout.CENTER);

    }

    public void initializeRemoveReservation() {
        this.removeReservationPanel = new JPanel(new BorderLayout());
        this.removeReservationPanel.setPreferredSize(new Dimension(250, 550));

        JLabel headerLabel = new JLabel("Remove Reservation");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        JPanel removeReservationOptionsPane = new JPanel(new BorderLayout());
        removeReservationOptionsPane.setPreferredSize(new Dimension(250, 50));
        JLabel removeReservationOptionsTitle = new JLabel("List of Reservations: ");
        removeReservationOptionsTitle.setHorizontalAlignment(JLabel.CENTER);
        this.removeReservationOptions = new JComboBox<>(model2);

        removeReservationOptionsPane.add(removeReservationOptionsTitle, BorderLayout.NORTH);
        removeReservationOptionsPane.add(this.removeReservationOptions, BorderLayout.CENTER);

        JPanel removeReservationButton = new JPanel(new FlowLayout());
        removeReservationButton.setPreferredSize(new Dimension(250, 520));

        this.removeReservation = new JButton("Remove Reservation");
        this.removeReservation.setPreferredSize(new Dimension(175, 25));
        removeReservationButton.add(this.removeReservation);


        this.removeReservationPanel.add(header, BorderLayout.NORTH);
        this.removeReservationPanel.add(removeReservationOptionsPane, BorderLayout.CENTER);
        this.removeReservationPanel.add(removeReservationButton, BorderLayout.SOUTH);

    }

    public void initializeRemoveHotel() {
        this.removeHotelPanel = new JPanel(new BorderLayout());
        this.removeHotelPanel.setPreferredSize(new Dimension(250, 550));

        JLabel headerLabel = new JLabel("Remove Hotel");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        JPanel removeHotelButton = new JPanel(new FlowLayout());
        removeHotelButton.setPreferredSize(new Dimension(200, 520));

        this.removeHotel = new JButton("Remove Hotel");
        this.removeHotel.setPreferredSize(new Dimension(175, 25));
        removeHotelButton.add(this.removeHotel);

        this.removeHotelPanel.add(header, BorderLayout.NORTH);
        this.removeHotelPanel.add(removeHotelButton, BorderLayout.CENTER);

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

    public void removeRoomOptionComboBox(int index) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.removeRoomOptions.getModel();

        model.removeElementAt(index);
    }
    
    public void removeReservationOptionComboBox(int index) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.removeReservationOptions.getModel();

        model.removeElementAt(index);
    }


    public void updateRemoveRoomOptions(ArrayList<Room> items) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.removeRoomOptions.getModel();
        model.removeAllElements();

        for (Room room : items) {
            model.addElement(room.getName());
        }
    }

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

    public void setMenuChangeListener(ChangeListener changeListener) {
        this.menu.addChangeListener(changeListener);
    }

    public void setRenameHotelListener(ActionListener actionListener) {
        this.renameHotel.addActionListener(actionListener);
    }

    public void setAddRoomListener(ActionListener actionListener) {
        this.addRoom.addActionListener(actionListener);
    }

    public void setRemoveRoomListener(ActionListener actionListener) {
        this.removeRoom.addActionListener(actionListener);
    }

    public void setUpdateBasePriceListener(ActionListener actionListener) {
        this.updateBasePrice.addActionListener(actionListener);
    }

    public void setModifyPriceRateListener(ActionListener actionListener) {
        this.modifyPriceRate.addActionListener(actionListener);
    }

    public void setRemoveReservationListener(ActionListener actionListener) {
        this.removeReservation.addActionListener(actionListener);
    }

    public void setRemoveHotelListener(ActionListener actionListener) {
        this.removeHotel.addActionListener(actionListener);
    }

    public JFrame getMainFrame() {
        return this.mainFrame;
    }
    
    public JTextField getRenameHotelName() {
        return this.hotelNewName;
    }

    public JTextField getStandardAddRoomNum() {
        return this.standardRoomNum;
    }
    
    public JTextField getDeluxeAddRoomNum() {
        return this.deluxeRoomNum;
    }
    
    public JTextField getExecutiveAddRoomNum() {
        return this.executiveRoomNum;
    }

    public JTextField getNewBasePrice() {
        return this.newBasePrice;
    }

    public JTextField getNewPriceRate() {
        return this.newPriceRate;
    }

    public JComboBox<String> getRemoveRoomOptions() {
        return this.removeRoomOptions;
    }
    
    public JComboBox<String> getDateOptions() {
        return this.dateOptions;
    }
    
    public JComboBox<String> getRemoveReservationOptions() {
        return this.removeReservationOptions;
    }
        
    public void setErrorFeedback(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public int setModConfirmFeedback(String confirmation) {
        return JOptionPane.showConfirmDialog(null, confirmation, "Proceed?", JOptionPane.YES_NO_OPTION);
    }
    
    public void clearTextFields() {
        this.hotelNewName.setText("");
        this.standardRoomNum.setText("");
        this.deluxeRoomNum.setText("");
        this.executiveRoomNum.setText("");
        this.newBasePrice.setText("");
        this.newPriceRate.setText("");
    }


    
}
