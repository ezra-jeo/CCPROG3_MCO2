import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class ReservationSystemController {

    private ReservationSystem reservationSystem;
    private ReservationSystemView view; 
    private ViewHotelExtension viewHotel;
    private ManageHotelExtension manageHotel; 

    /**
     * Constructor for the controller which calls various functions to add functionalities that bridges the view and the model classes.
     * 
     * @param reservationSystem instance of ReservationSystem that serves as the model which holds the data.
     * @param view instance of ReservationSystemView that serves as the view which holds the UI elements.
     * @param viewHotel extension of the viewHotel section in the view.
     * @param manageHotel extension of the manageHotel section in the view.
     * 
     */

    public ReservationSystemController(ReservationSystem reservationSystem, ReservationSystemView view, ViewHotelExtension viewHotel, ManageHotelExtension manageHotel) {
        this.reservationSystem = reservationSystem;
        this.view = view;
        this.viewHotel = viewHotel; // View Hotel Extension
        this.manageHotel = manageHotel; // Manage Hotel Extension

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
                else {
                    view.setHotelInfo(""); // Sets the high level information text area

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
                    Hotel hotel = reservationSystem.getHotelList().get(hotelIndex); // Hotel instance
                    viewHotel.setRoomInfo(hotel.getRoomInfo(roomIndex)); // Sets the room information text area
                } 
                else {
                    viewHotel.setRoomInfo(""); // Sets the room information text area
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
                }
                else {
                    viewHotel.setReservationInfo(""); // Sets the reservation information text area
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
         */
        viewHotel.setDateAvailableBookedListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int dateIndex = viewHotel.getDateOptions().getSelectedIndex() + 1; // Date whose information to show
                int hotelIndex = view.getHotelOptions().getSelectedIndex(); // Hotel index
                
                if (hotelIndex != -1 && dateIndex != 0) {
                    viewHotel.setAvailableBooked(reservationSystem.viewHotelAvailableBooked(hotelIndex, dateIndex)); // Sets the available and booked rooms information text area
                }
                else {
                    viewHotel.setAvailableBooked("");
                }
            }
        });

         /**
         * Adds an action listener to the manage hotel button, makes the manage hotel extension window visible.
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
         * Adds an action listener to the manage hotel button, runs the process for renaming hotel selections.
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

        /**
         * Adds an action listener to the update base price button, changes the base price 
         * and ensures that there is no reservations present.
         * 
         */
        manageHotel.setUpdateBasePriceListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Hotel hotel = reservationSystem.getHotelList().get(view.getManageOptions().getSelectedIndex()); // Hotel instance
                    double newBasePrice = Double.parseDouble(manageHotel.getNewBasePrice().getText()); // New base price

                    if (hotel.getReservationList().size() == 0 && newBasePrice >= 100.0) {

                        if (manageHotel.setModConfirmFeedback("Update base price from \"" + hotel.getRoomBasePrice() + "\" to \"" + newBasePrice + "\"?") == 0) {
                            hotel.setRoomBasePrice(newBasePrice); // Sets the base price for the hotel rooms
                            manageHotel.clearTextFields(); // Clears the text fields
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

        /**
         * Adds an action listener to the modify price rate button, changes the price rate of the specified date 
         * and ensures that there is no reservations present for that date.
         * 
         */

        manageHotel.setModifyPriceRateListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Hotel hotel = reservationSystem.getHotelList().get(view.getManageOptions().getSelectedIndex()); // Hotel instance
                    double newPriceRate = Double.parseDouble(manageHotel.getNewPriceRate().getText()); // New price rate
                    int date = manageHotel.getDateOptions().getSelectedIndex() + 1; // Date whose price rate will change
                    
                    if (hotel.checkDateAvailability(date) && newPriceRate >= 50 && newPriceRate <= 150) {
                        if (manageHotel.setModConfirmFeedback("Modify price rate for day " + date + " from \"" + hotel.getRoomPriceRate().get(date-1)*100 + "\" to \"" + newPriceRate +"\"?") == 0) {
                            hotel.setRoomPriceRate(date, newPriceRate/100); // Sets the price rate
                            manageHotel.clearTextFields(); // Clears the text fields
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

        /**
         * Adds an action listener to the remove reservation button, performs validity checking and removes the specified reservation.
         * 
         */
        manageHotel.setRemoveReservationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hotel hotel = reservationSystem.getHotelList().get(view.getManageOptions().getSelectedIndex()); // Hotel instance
                int index = manageHotel.getRemoveReservationOptions().getSelectedIndex(); // Reservation index

                if (index != -1) {
                    if (manageHotel.setModConfirmFeedback("Remove Reservation for Room \"" + hotel.getReservationList().get(index).getRoom().getName() + "\"?") == 0) {
                        hotel.removeReservation(index); // Removes the reservation
                        manageHotel.removeReservationOptionComboBox(index); // removes the option for the combobox
                        viewHotel.removeReservationOption(index);  // removes the option for the combobox
                    }
                }
                else {
                    manageHotel.setErrorFeedback("Select a Reservation to remove");
                }
            }
        });

        /**
         * Adds an action listener to the remove hotel button, performs validity checking and removes the hotel.
         * 
         */
        manageHotel.setRemoveHotelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hotelIndex = view.getManageOptions().getSelectedIndex(); // Hotel index
                Hotel hotel = reservationSystem.getHotelList().get(hotelIndex); // Hotel instance

                if (manageHotel.setModConfirmFeedback("Remove Hotel \"" + hotel.getName() + "\"?") == 0) {
                    reservationSystem.removeHotel(view.getManageOptions().getSelectedIndex()); // Removes the hotel
                    view.updateHotelOptions(reservationSystem.getHotelList()); // Updates the hotel options in the view section
                    manageHotel.getMainFrame().dispose(); // Closes the manage hotel extension window
                }
            }
        });

        /**
         * Adds an action listener to the remove hotel button, performs validity checking and removes the hotel.
         * 
         */

        view.setSimulateBookingListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hotelIndex = view.getSimulateHotelOptions().getSelectedIndex(); // Hotel index
                int dateIn = view.getSimulateDateInOptions().getSelectedIndex() + 1; // Check in date
                int dateOut = view.getSimulateDateOutOptions().getSelectedIndex() + 1; // Check out date
                String guestName = view.getGuestName().getText(); // Guest name 
                String roomType = (String) view.getSimulateRoomOptions().getSelectedItem(); // Type of room to reserve
                String discountCode = view.getDiscountCode().getText(); // Discount code, if any


                if (hotelIndex != -1 && dateIn != -1 && dateOut != -1 && !guestName.equals("") && dateIn < dateOut) {
                    Hotel hotel = reservationSystem.getHotelList().get(hotelIndex); // Hotel instance
                    int roomIndex = hotel.getAvailableRoomType(roomType, dateIn, dateOut); // room index, auto generated

                    if (roomIndex != -1) {
                        // Two case of add reservation, with and without discount code
                        if (!discountCode.equals("") && hotel.checkDiscountApplicable(discountCode, dateIn, dateOut)) {
                            if (view.setModConfirmFeedback("Confirm Reservation for \"" + guestName + "\" in room \"" + hotel.getRoomList().get(roomIndex).getName() + 
                            "\" from " + dateIn + " to " + dateOut + "?") == 0) {
                                hotel.addReservation(guestName, dateIn, dateOut, roomIndex, discountCode); // adds reservation
                                viewHotel.updateReservationOptions(hotel.getReservationList()); // updates the reservation options for view hotel section
                                manageHotel.updateRemoveReservationOptions(hotel.getReservationList()); // updates the reservation options for manage hotel section
                            }
                        }
                        else if (discountCode.equals("")) {
                            if (view.setModConfirmFeedback("Confirm Reservation for \"" + guestName + "\" in room \"" + hotel.getRoomList().get(roomIndex).getName() + 
                                "\" from " + dateIn + " to " + dateOut + "?") == 0) {
                                hotel.addReservation(guestName, dateIn, dateOut, roomIndex);  // adds reservation
                                viewHotel.updateReservationOptions(hotel.getReservationList()); // updates the reservation options for view hotel section
                                manageHotel.updateRemoveReservationOptions(hotel.getReservationList()); // updates the reservation options for manage hotel section

                            }
                        }
                        else if (!hotel.checkDiscountApplicable(discountCode, dateIn, dateOut)) {
                            view.setErrorFeedback("Enter a valid discount code.");
                        } 
            
                        view.clearTextFields();
                        view.clearSimulateComboboxSelection();
                        viewHotel.clearComboboxSelection();
                    }
                    else {
                        view.setErrorFeedback("No room is available for the specified date");
                    }
                }
                else if (hotelIndex == -1) {
                    view.setErrorFeedback("Hotel list is empty.");
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
}
