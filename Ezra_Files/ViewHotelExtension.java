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


public class ViewHotelExtension {
    private JFrame mainFrame;
    private JPanel viewAvailableBookedPanel, viewRoomPanel, viewReservationPanel;
    private JPanel viewRoomPane, viewReservationPane, viewAvailableBookedPane; // Method Panes;
    private JTextArea availableBooked, roomInfo, reservationInfo;
    private JComboBox<String> hotelOptions, roomOptions, dateOptions, reservationOptions;
    private JTabbedPane menu;
    DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();



    public ViewHotelExtension() {
        this.mainFrame = new JFrame("Hotel Reservation System");
        this.mainFrame.setSize(470, 720);
        this.mainFrame.setResizable(false);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setLocationRelativeTo(null);

        this.menu = new JTabbedPane();

        initializeViewAvailableBooked();
        initializeViewRoom();
        initializeViewReservation();

        this.menu.add(this.viewAvailableBookedPanel, "View Available and Booked Rooms");
        this.menu.add(this.viewRoomPanel, "View Room");
        this.menu.add(this.viewReservationPanel, "View Reservation");

        this.mainFrame.add(this.menu);
    }
    
    public void initializeViewAvailableBooked() {
        this.viewAvailableBookedPanel = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("View Available and Booked Rooms");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        this.viewAvailableBookedPane = new JPanel(new BorderLayout());
        this.viewAvailableBookedPane.setPreferredSize(new Dimension(250, 100));
        JLabel viewAvailableBookedTitle = new JLabel("List of Dates: ");
        viewAvailableBookedTitle.setHorizontalAlignment(JLabel.CENTER);

        this.dateOptions = new JComboBox<>(getDates31());
        this.dateOptions.setEditable(false);
        this.dateOptions.setPreferredSize(new Dimension(250, 50));
        this.dateOptions.setSelectedIndex(-1);
        this.availableBooked = new JTextArea();
        this.availableBooked.setEditable(false);
        this.availableBooked.setLineWrap(true);
        this.availableBooked.setPreferredSize(new Dimension(250, 550));
        this.viewAvailableBookedPane.add(viewAvailableBookedTitle, BorderLayout.NORTH);
        this.viewAvailableBookedPane.add(this.dateOptions, BorderLayout.CENTER);
        this.viewAvailableBookedPane.add(this.availableBooked, BorderLayout.SOUTH);

        this.viewAvailableBookedPanel.add(header, BorderLayout.NORTH);
        this.viewAvailableBookedPanel.add(this.viewAvailableBookedPane, BorderLayout.CENTER);

    }

    public void initializeViewRoom() {
        this.viewRoomPanel = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("View Room");
        JPanel header = new JPanel(new FlowLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(450, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        this.viewRoomPane = new JPanel(new BorderLayout());
        this.viewRoomPane.setPreferredSize(new Dimension(250, 150));
        JLabel viewRoomTitle = new JLabel("List of Rooms: ");
        viewRoomTitle.setHorizontalAlignment(JLabel.CENTER);
        this.roomOptions = new JComboBox<>(model1);
        this.roomOptions.setEditable(false);
        this.roomOptions.setSelectedIndex(-1);
        
        JPanel viewRoomInfoPane = new JPanel(new BorderLayout());
        viewRoomInfoPane.setPreferredSize(new Dimension(250, 550));
        JLabel viewRoomInfoTitle = new JLabel("Room Information: ");
        viewRoomInfoTitle.setHorizontalAlignment(JLabel.CENTER);
        this.roomInfo = new JTextArea();
        this.roomInfo.setEditable(false);
        this.roomInfo.setLineWrap(true);
        viewRoomInfoPane.add(viewRoomInfoTitle, BorderLayout.NORTH);
        viewRoomInfoPane.add(this.roomInfo, BorderLayout.CENTER);
        viewRoomInfoPane.setPreferredSize(new Dimension(250, 550));

        this.viewRoomPane.add(viewRoomTitle, BorderLayout.NORTH);
        this.viewRoomPane.add(this.roomOptions, BorderLayout.CENTER);
        this.viewRoomPane.add(viewRoomInfoPane, BorderLayout.SOUTH);

        this.viewRoomPanel.add(header, BorderLayout.NORTH);
        this.viewRoomPanel.add(this.viewRoomPane, BorderLayout.CENTER);
    }

    public void initializeViewReservation() {
        this.viewReservationPanel = new JPanel(new BorderLayout());

        JLabel headerLabel = new JLabel("View Reservation");
        JPanel header = new JPanel(new BorderLayout());
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.white);
        headerLabel.setPreferredSize(new Dimension(150, 50));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        header.setBackground(Color.darkGray);
        header.add(headerLabel);

        this.viewReservationPane = new JPanel(new BorderLayout());
        this.viewReservationPane.setPreferredSize(new Dimension(250, 50));
        this.reservationOptions = new JComboBox<>(model2);
        this.reservationOptions.setEditable(false);
        this.reservationOptions.setSelectedIndex(-1);
        
        JPanel viewReservationInfoPane = new JPanel(new BorderLayout());
        viewReservationInfoPane.setPreferredSize(new Dimension(250, 550));
        JLabel viewReservationInfoTitle = new JLabel("List of Reservations: ");
        viewReservationInfoTitle.setHorizontalAlignment(JLabel.CENTER);
        this.reservationInfo = new JTextArea();
        this.reservationInfo.setEditable(false);
        viewReservationInfoPane.add(this.reservationInfo, BorderLayout.CENTER);
        this.viewReservationPane.add(viewReservationInfoTitle, BorderLayout.NORTH);
        this.viewReservationPane.add(this.reservationOptions, BorderLayout.CENTER);
        this.viewReservationPane.add(viewReservationInfoPane, BorderLayout.SOUTH);

        this.viewReservationPanel.add(header, BorderLayout.NORTH);
        this.viewReservationPanel.add(this.viewReservationPane, BorderLayout.CENTER);
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

    public void setAvailableBooked(String message) {
        this.availableBooked.setText(message);
    }

    public void setRoomInfo(String message) {
        this.roomInfo.setText(message);
    }

    public void setReservationInfo(String message) {
        this.reservationInfo.setText(message);
    }

    public void removeRoomOption(int index) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.roomOptions.getModel();

        model.removeElementAt(index);
    }

    public void removeReservationOption(int index) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.reservationOptions.getModel();

        model.removeElementAt(index);
    }

    public void updateRoomOptions(ArrayList<Room> items) {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.roomOptions.getModel();

        model.removeAllElements();

        for (Room room : items) {
            model.addElement(room.getName());
        }

        model.setSelectedItem(null);
    }

    public void updateReservationOptions(ArrayList<Reservation> items) {
        if (items.size() > 0) {
            String result;
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.reservationOptions.getModel();

            model.removeAllElements();
            for (Reservation reservation : items) {
                result = reservation.getRoom().getName() + ": ";
                result += "In: " + reservation.getCheckInDate() + " - Out: " + reservation.getCheckOutDate() + " ";
                result += "Guest: " + reservation.getGuestName();
                model.addElement(result);
            }
            model.setSelectedItem(null);
        }
    }

    public void setMenuChangeListener(ChangeListener changeListener) {
        this.menu.addChangeListener(changeListener);
    }

    public void setViewHotelListener(ItemListener itemListener) {
        this.hotelOptions.addItemListener(itemListener);
    }

    public void setViewRoomListener(ItemListener itemListener) {
        this.roomOptions.addItemListener(itemListener);
    }

    public void setViewReservationListener(ItemListener itemListener) {
        this.reservationOptions.addItemListener(itemListener);
    }

    public void setDateAvailableBookedListener(ItemListener itemListener) {
        this.dateOptions.addItemListener(itemListener);
    }


    public JFrame getMainFrame() {
        return this.mainFrame;
    }

    public JComboBox<String> getHotelOptions() {
        return this.hotelOptions;
    }

    public JComboBox<String> getRoomOptions() {
        return this.roomOptions;
    }
    
    public JComboBox<String> getDateOptions() {
        return this.dateOptions;
    }

    public JComboBox<String> getReservationOptions() {
        return this.reservationOptions;
    }
}
