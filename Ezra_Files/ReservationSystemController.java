import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.Action;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.ArrayList;

public class ReservationSystemController {

    private ReservationSystem reservationSystem;
    private ReservationSystemView view; 
    private ViewHotelExtension viewHotel;
    private ManageHotelExtension manageHotel; 

    public ReservationSystemController() {
        this.reservationSystem = new ReservationSystem();
        this.view = new ReservationSystemView();
        this.viewHotel = new ViewHotelExtension(); // View Hotel Extension
        this.manageHotel = new ManageHotelExtension(); // Manage Hotel Extension

        /**
         * Adds an action listener to the create hotel button, running the process for hotel creation.
         * 
         */
        view.setCreateHotelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String hotelName = view.getHotelName().getText();
                    int standardNum = Integer.parseInt(view.getStandardNum().getText());
                    int deluxeNum = Integer.parseInt(view.getDeluxeNum().getText());
                    int executiveNum = Integer.parseInt(view.getExecutiveNum().getText());
                    int roomNum = standardNum + deluxeNum + executiveNum;
                    ArrayList<Integer> roomTypeNum = new ArrayList<Integer>();
                    
                    // Adds room number per type to an arraylist
                    roomTypeNum.add(standardNum);
                    roomTypeNum.add(deluxeNum);
                    roomTypeNum.add(executiveNum);

                    if (!hotelName.equals("") && reservationSystem.isExisting(hotelName) == -1 && roomNum <= 50 && roomNum >= 1) { 
                    
                        if (view.setModConfirmFeedback("Add Hotel " + hotelName +"?") == 0) { // Confirmation Prompt
                            reservationSystem.createHotel(hotelName, roomTypeNum);
                            view.addHotelOption(hotelName);
                            view.clearTextFields();
                        }
                    }
                    else if (hotelName.equals("")) {
                        view.setErrorFeedback("Enter a valid hotel name."); 
                    }
                    else if (roomNum > 50 || roomNum < 1){
                        view.setErrorFeedback("Enter a valid room number."); 
                    }
                    else {
                        view.setErrorFeedback("Hotel " + hotelName + " already exists."); 
                    }
                }
                catch (NumberFormatException exception) {
                    view.setErrorFeedback("Enter a valid integer input.");
                } 
            }
        });

        
        /**
         * Adds an item listener to the view hotel combobox, running the process for the view hotel functionality.
         * 
         */
        view.setViewHotelItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent e) {
                int hotelIndex = view.getHotelOptions().getSelectedIndex(); // Hotel index chosen

                if (hotelIndex != -1) {
                    Hotel hotel = reservationSystem.getHotelList().get(hotelIndex); // Hotel instance
                    view.setHotelInfo(reservationSystem.viewHotelHighLevel(hotelIndex)); // Sets the high level information text area
                    viewHotel.updateRoomOptions(hotel.getRoomList()); // Updates the room combobox in the view room functionality
                    viewHotel.updateReservationOptions(hotel.getReservationList()); // Updates the reservation combobox in the view reservation functionality
                }
           }
        });

        /**
         * Adds an item listener to the view room combobox, running the process for the view room functionality.
         * 
         */
        viewHotel.setViewRoomListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int hotelIndex = view.getHotelOptions().getSelectedIndex(); // Hotel index
                int roomIndex = viewHotel.getRoomOptions().getSelectedIndex(); // Room index

                if (hotelIndex != -1 && roomIndex != -1) {
                    Hotel hotel = reservationSystem.getHotelList().get(view.getHotelOptions().getSelectedIndex()); // Hotel instance
                    viewHotel.setRoomInfo(hotel.getRoomInfo(roomIndex)); // Sets the room information text area
                    System.out.println(hotel.getRoomInfo(roomIndex));
                    System.out.println(hotel.getRoomList().size());    
                } 
            }   
        });

        /**
         * Adds an item listener to the view reservation combobox, running the process for the view reservation functionality.
         * 
         */
        viewHotel.setViewReservationListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int hotelIndex = view.getHotelOptions().getSelectedIndex();
                int reservationIndex = viewHotel.getReservationOptions().getSelectedIndex();

                if (hotelIndex != -1 && reservationIndex != -1) {
                    Hotel hotel = reservationSystem.getHotelList().get(hotelIndex); // Hotel instance
                    viewHotel.setReservationInfo(hotel.getReservationInfo(reservationIndex)); // Sets the reservation information text area
                    System.out.println(hotel.getReservationInfo(reservationIndex)); 
                    System.out.println(hotel.getReservationList().size());    
                }

            }
        });

        /**
         * Adds an action listener to the view hotel low level information button, makes the view hotel extension window visible.
         * 
         */

        view.setViewHotelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hotelIndex = view.getHotelOptions().getSelectedIndex(); // Hotel index
                int hotelCount = view.getHotelOptions().getItemCount(); // Hotel count

                if (hotelCount > 0 && hotelIndex != -1) {
                    viewHotel.getMainFrame().setVisible(true); // Sets the view hotel extension window to be visible
                }   
                else if (hotelCount == 0) {
                    view.setErrorFeedback("Hotel list is empty."); 
                }
                else {
                    view.setErrorFeedback("Select a hotel to view.");
                }
            }
        });

        /**
         * Adds an item listener to the date combobox in the show available and booked rooms section.
         * 
         * 
         */
        viewHotel.setDateAvailableBookedListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int dateIndex = viewHotel.getDateOptions().getSelectedIndex() + 1; // Date whose information to show
                int hotelIndex = view.getHotelOptions().getSelectedIndex(); // Hotel index
                
                if (hotelIndex != -1 && dateIndex != -1)
                    viewHotel.setAvailableBooked(reservationSystem.viewHotelAvailableBooked(hotelIndex, dateIndex)); // Sets the available and booked rooms information text area
            }
        });

         /**
         * Adds an action listener to the manage hotel button, makes the view hotel extension window visible.
         * 
         */
        view.setManageHotelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.getManageOptions().getItemCount() > 0 && view.getManageOptions().getSelectedIndex() != -1) { 
                    Hotel hotel = reservationSystem.getHotelList().get(view.getManageOptions().getSelectedIndex()); // Hotel instance
                    manageHotel.updateRemoveRoomOptions(hotel.getRoomList()); // Updates the remove room combobox
                    manageHotel.updateRemoveReservationOptions(hotel.getReservationList()); // Updates the remove reservation combobox
                    manageHotel.getMainFrame().setVisible(true); // Sets the manage hotel extension window to be visible
                }
                else if (view.getManageOptions().getItemCount() == 0) {
                    view.setErrorFeedback("Hotel list is empty.");
                }
                else {
                    view.setErrorFeedback("Select a hotel to manage.");
                }
            }
        });

         /**
         * Adds an action listener to the manage hotel button, makes the view hotel extension window visible.
         * 
         */
        manageHotel.setRenameHotelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = manageHotel.getRenameHotelName().getText();
                int hotelIndex = view.getManageOptions().getSelectedIndex();
                Hotel hotel = reservationSystem.getHotelList().get(hotelIndex);

                if (reservationSystem.isExisting(newName) == -1 && !newName.equals("")) {
                    if (manageHotel.setModConfirmFeedback("Rename Hotel " + hotel.getName() + " to \"" + newName + "\"?") == 0) {
                        reservationSystem.renameHotel(hotelIndex, newName);
                        manageHotel.clearTextFields();
                        view.updateHotelOptions(reservationSystem.getHotelList());
                    }
                }
                else if (!newName.equals("")) {
                    manageHotel.setErrorFeedback("Enter a valid name.");
                }
                else {
                    manageHotel.setErrorFeedback("Hotel " + newName + " already exists.");
                }
            
            }
        });

        /**
         * Adds an action listener to the add room button, performs validity checking and adds the number of rooms specified.
         * 
         */
        manageHotel.setAddRoomListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hotel hotel = reservationSystem.getHotelList().get(view.getManageOptions().getSelectedIndex()); // Hotel instance

                try {
                    int standardNum = Integer.parseInt(manageHotel.getStandardAddRoomNum().getText()); // Number of Standard rooms
                    int deluxeNum = Integer.parseInt(manageHotel.getDeluxeAddRoomNum().getText()); // Number of Deluxe rooms
                    int executiveNum = Integer.parseInt(manageHotel.getExecutiveAddRoomNum().getText()); // Number of Executive rooms
                    int roomNum = standardNum + deluxeNum + executiveNum + hotel.getRoomList().size(); // Total number of rooms including those to be added
                    ArrayList<Integer> roomNums= new ArrayList<Integer>(); // Arraylist for each room type number
                    roomNums.add(standardNum);
                    roomNums.add(deluxeNum);
                    roomNums.add(executiveNum);

                    if (manageHotel.setModConfirmFeedback("Add these rooms to hotel " + hotel.getName() + "?") == 0 && roomNum <= 50) {
                        hotel.addRoom(roomNums); // Adds the room to the hotel's room list
                        manageHotel.updateRemoveRoomOptions(hotel.getRoomList()); // Updates room combobox in manage hotel section
                        viewHotel.updateRoomOptions(hotel.getRoomList()); // Updates room combobox in simulate booking section
                    }
                    else if (roomNum > 50) {
                        manageHotel.setErrorFeedback("Hotels must have at most fifty (50) rooms.");
                    }
            
                }
                catch (NumberFormatException exception) {
                    manageHotel.setErrorFeedback("Enter a valid integer.");
                }
            }
        });

        /**
         * Adds an action listener to the remove room button, performs validity checking and removes the room specified.
         * 
         */
        manageHotel.setRemoveRoomListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int roomIndex = manageHotel.getRemoveRoomOptions().getSelectedIndex(); // Room index to remove
                Hotel hotel = reservationSystem.getHotelList().get(view.getManageOptions().getSelectedIndex()); // hotel instance

                if (hotel.getRoomList().size() > 1 && hotel.checkRoomAvailability(roomIndex)) {
                    if (manageHotel.setModConfirmFeedback("Remove Room \"" + hotel.getRoomList().get(roomIndex).getName() + "\"?") == 0) { 
                        hotel.removeRoom(roomIndex); // Removes room from hotel's room list
                        manageHotel.removeRoomOptionComboBox(roomIndex); // Updates room combobox in manage hotel section
                        viewHotel.updateRoomOptions(hotel.getRoomList()); // Updates room combobox in simulate booking section
                    }
                }
                else if (!hotel.checkRoomAvailability(roomIndex)) {
                    manageHotel.setErrorFeedback("Hotel " + hotel.getName() + " has existing reservations for Room \"" +  hotel.getRoomList().get(roomIndex).getName() + "\".");
                }
                else {
                    manageHotel.setErrorFeedback("Hotels must have at least one (1) room.");
                }
            }
        });

        manageHotel.setUpdateBasePriceListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Hotel hotel = reservationSystem.getHotelList().get(view.getManageOptions().getSelectedIndex());
                    double newBasePrice = Double.parseDouble(manageHotel.getNewBasePrice().getText());

                    if (hotel.getReservationList().size() == 0 && newBasePrice >= 100.0) {
                        System.out.println(hotel.getRoomBasePrice());

                        if (manageHotel.setModConfirmFeedback("Update base price from \"" + hotel.getRoomBasePrice() + "\" to \"" + newBasePrice + "\"?") == 0) {
                            hotel.setRoomBasePrice(newBasePrice);
                            System.out.println(hotel.getRoomBasePrice());
                            manageHotel.clearTextFields();
                        }
                    }
                    else if (newBasePrice < 100.0) {
                        manageHotel.setErrorFeedback("New price needs to be above 100.0");
                    }
                    else {
                        manageHotel.setErrorFeedback("Hotel " + hotel.getName() + " has existing reservations.");
                    }
                }
                catch (NumberFormatException exception) {
                    manageHotel.setErrorFeedback("Enter a valid price.");
                }
            }
        });

        manageHotel.setModifyPriceRateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Hotel hotel = reservationSystem.getHotelList().get(view.getManageOptions().getSelectedIndex());
                    double newPriceRate = Double.parseDouble(manageHotel.getNewPriceRate().getText());
                    int date = manageHotel.getDateOptions().getSelectedIndex() + 1;
                    System.out.println(hotel.getRoomPriceRate().get(date-1));
                    
                    if (hotel.checkDateAvailability(date) && newPriceRate >= 50 && newPriceRate <= 150) {
                        if (manageHotel.setModConfirmFeedback("Modify price rate for day " + date + " from \"" + hotel.getRoomPriceRate().get(date-1)*100 + "\" to \"" + newPriceRate +"\"?") == 0) {
                            hotel.setRoomPriceRate(date, newPriceRate/100);
                            manageHotel.clearTextFields();
                            System.out.println(hotel.getRoomPriceRate().get(date-1));
                        }
                    }
                    else if (newPriceRate < 50 || newPriceRate > 150) {
                        manageHotel.setErrorFeedback("New price rate must be between 50% and 150%.");
                    }   
                    else {
                        manageHotel.setErrorFeedback("Hotel " + hotel.getName() + " has existing reservations on " + date + ".");
                    }
                }
                catch (NumberFormatException exception) {
                    manageHotel.setErrorFeedback("Enter a valid double input.");
                }
            }
        });

        manageHotel.setRemoveReservationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hotel hotel = reservationSystem.getHotelList().get(view.getManageOptions().getSelectedIndex());
                int index = manageHotel.getRemoveReservationOptions().getSelectedIndex();

                if (index != -1) {
                    if (manageHotel.setModConfirmFeedback("Remove Reservation for Room \"" + hotel.getReservationList().get(index).getRoom().getName() + "\"?") == 0) {
                        hotel.removeReservation(index);
                        manageHotel.removeReservationOptionComboBox(index);
                        viewHotel.removeReservationOption(index);
                    }
                }
                else {
                    manageHotel.setErrorFeedback("Select a Reservation to remove");
                }
            }
        });

        manageHotel.setRemoveHotelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hotelIndex = view.getManageOptions().getSelectedIndex();
                Hotel hotel = reservationSystem.getHotelList().get(hotelIndex);

                if (manageHotel.setModConfirmFeedback("Remove Hotel \"" + hotel.getName() + "\"?") == 0) {
                    reservationSystem.removeHotel(view.getManageOptions().getSelectedIndex());
                    view.updateHotelOptions(reservationSystem.getHotelList());
                    manageHotel.getMainFrame().dispose();
                }
            }
        });

        view.setSimulateBookingListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hotelIndex = view.getSimulateHotelOptions().getSelectedIndex();
                int dateIn = view.getSimulateDateInOptions().getSelectedIndex() + 1;
                int dateOut = view.getSimulateDateOutOptions().getSelectedIndex() + 1;
                String guestName = view.getGuestName().getText();
                String roomType = (String) view.getSimulateRoomOptions().getSelectedItem();
                String discountCode = view.getDiscountCode().getText();


                if (hotelIndex != -1 && dateIn != -1 && dateOut != -1 && !guestName.equals("") && dateIn < dateOut) {
                    Hotel hotel = reservationSystem.getHotelList().get(hotelIndex);
                    int roomIndex = hotel.getAvailableRoomType(roomType, dateIn, dateOut);

                    if (roomIndex != -1) {
                        if (!discountCode.equals("") && hotel.checkDiscountApplicable(discountCode, dateIn, dateOut)) {
                            if (view.setModConfirmFeedback("Confirm Reservation for \"" + guestName + "\" in room \"" + hotel.getRoomList().get(roomIndex).getName() + 
                            "\" from " + dateIn + " to " + dateOut + "?") == 0) {
                                hotel.addReservation(guestName, dateIn, dateOut, roomIndex, discountCode);
                                viewHotel.updateReservationOptions(hotel.getReservationList());
                                manageHotel.updateRemoveReservationOptions(hotel.getReservationList());
                            }
                        }
                        else if (discountCode.equals("")) {
                            if (view.setModConfirmFeedback("Confirm Reservation for \"" + guestName + "\" in room \"" + hotel.getRoomList().get(roomIndex).getName() + 
                                "\" from " + dateIn + " to " + dateOut + "?") == 0) {
                                hotel.addReservation(guestName, dateIn, dateOut, roomIndex);
                                viewHotel.updateReservationOptions(hotel.getReservationList());
                                manageHotel.updateRemoveReservationOptions(hotel.getReservationList());

                            }
                        }
                        else if (!hotel.checkDiscountApplicable(discountCode, dateIn, dateOut)) {
                            view.setErrorFeedback("Enter a valid discount code.");
                        } 
                            
                        view.clearTextFields();
                        view.clearComboboxSelection();
                    }
                    else {
                        view.setErrorFeedback("No room is available for the specified date");
                    }
                }
                else if (guestName.equals("")) {
                    view.setErrorFeedback("Enter a valid guest name");
                }
                else if (hotelIndex == -1) {
                    view.setErrorFeedback("Select a Hotel.");
                }
                else if (dateIn == -1) {
                    view.setErrorFeedback("Select a valid check in date.");
                }
                else if (dateOut == -1) {
                    view.setErrorFeedback("Select a valid check out date.");
                }
                else if (dateIn >= dateOut) {
                    view.setErrorFeedback("Enter a valid date range.");
                }
            }
        });
    }


    public static void main(String[] args) {
        new ReservationSystemController();
    }
    
}
