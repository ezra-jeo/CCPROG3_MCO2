import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The ReservationSystem class represents the main reservation system in the hotel reservation system.
 * It contains a collection of hotels through which a user can reserve/book a room.
 */
public class ReservationSystem {

    /**
     * Collection of hotels stored in the system.
     * 
     */
    private ArrayList<Hotel> hotelList;

    /**
     * Scanner instance which is used in all of the inputs in the reservation system.
     * 
     */
    private Scanner sc = new Scanner(System.in);

    /**
     * Instantiates an instance of the ReservationSystem class.
     * 
     */
    public ReservationSystem() {
        this.hotelList = new ArrayList<Hotel>();
    }

    /**
     * Creates a hotel instance with a given name and adds the it to the hotel list.
     * 
     * @param name the name of the hotel that is being created.
     */
    public boolean createHotel(String name, ArrayList<Integer> roomNum) {  

        Hotel hotel = new Hotel(name, roomNum);
        this.hotelList.add(hotel);

        return true;

        // do {
        //     error = false; // Error Flag
        //     try { 
        //         System.out.print("\nEnter the number of rooms (Quit to exit): "); // Prompts for the number of rooms of the hotel during instantiation.
        //         input = sc.nextLine();

        //         if (!input.equalsIgnoreCase("Quit")) { 
        //             roomNum = Integer.parseInt(input);
        //             if (roomNum >= Hotel.getMinRoom() && roomNum <= Hotel.getMaxRoom()) {
        //                 do {
        //                     invalid = false;
        //                     System.out.print("Create hotel \"" + name + "\"? (Yes/No) : ");
        //                     verify = sc.nextLine();
                
        //                     if (!verify.equalsIgnoreCase("Yes") && !verify.equalsIgnoreCase("No")) {
        //                         System.out.println("Invalid Input. Enter a New Input.");
        //                         invalid = true;
        //                     }
        //                 } while (invalid);

        //                 if (verify.equalsIgnoreCase("Yes")) {
        //                     Hotel hotel = new Hotel(name, roomNum);
        //                     this.hotelList.add(hotel);
        //                 }
        //             }
        //             else {
        //                 System.out.println("Invalid Room Number. Enter New Input");
        //                 error = true;
        //             }
        //         } 
        //     }
        //     catch(NumberFormatException exception) {
        //         System.out.println("Invalid Integer Input. Enter New Input.");
        //         error = true;
        //     }
        // } while (error);
    }

    /**
     * Displays high level and low level information of a hotel. 
     * Pre-condition: the hotel index provided exists based from the hotel list and is valid.
     * 
     * @param index the index of the hotel in the hotelList.
     */
    public String viewHotelHighLevel(int index) {
        String result = "";
        Hotel hotel = hotelList.get(index);

        result += "Hotel : " + hotel.getName() + "\n";
        result += "No. of Standard Rooms : " + hotel.getStandardRoomList().size() + "\n"; // Get Standard Room List TODO
        result += "No. of Deluxe Rooms : " + hotel.getDeluxeRoomList().size() + "\n";
        result += "No. of Executive Rooms : " + hotel.getExecutiveRoomList().size() + "\n";
        result += "Estimated Earnings : " + hotel.getEarnings() + "\n";

        
        return result;
    }

    public String viewHotelAvailableBooked(int index, int date) {
        String result = "";
        Hotel hotel = hotelList.get(index);
        ArrayList<Integer> available = hotel.getTotalAvailableRooms(date);
        ArrayList<Integer> booked = hotel.getTotalBookedRooms(date);
        int totalAvailable = 0;
        int totalBooked = 0;
        
        for (Integer num : available) {
            totalAvailable += num;
        }

        for (Integer num : booked) {
            totalBooked += num;
        }

        result += "Available Rooms : " + totalAvailable + "\n";
        result += "\tStandard Rooms : " + available.get(0) + "\n";
        result += "\tDeluxe Rooms : " + available.get(1) + "\n";
        result += "\tExecutive Rooms : " + available.get(2) + "\n";
        result += "Booked Rooms : " + totalBooked + "\n";
        result += "\tStandard Rooms : " + booked.get(0) + "\n";
        result += "\tDeluxe Rooms : " + booked.get(1) + "\n";
        result += "\tExecutive Rooms : " + booked.get(2) + "\n";
        
        return result;
    }

    public String viewHotelRoomInfo(int index, String roomName) {
        String result = "";
        Hotel hotel = hotelList.get(index);
        
        result = hotel.getRoomInfo(hotel.getRoomIndex(roomName));

        return result;
    }

    public String viewHotelReservationInfo(int index, int reservationIndex) {
        String result = "";
        Hotel hotel = hotelList.get(index);

        result = hotel.getReservationInfo(reservationIndex);

        return result;
        

    }
        // Low level information prompt
            

        // choiceInfo = sc.nextLine();

        // if (choiceInfo.equals("1")) {
        //     do {    
        //         error = false;
        //         try {
        //             System.out.print("\nEnter the date to see available and booked rooms (Quit to exit): ");
        //             input = sc.nextLine();
    
        //             if (!input.equalsIgnoreCase("Quit")) {      
        //                 date = Integer.parseInt(input);
        //                 totalAvailable = hotel.getTotalAvailableRooms(date);
        //                 totalBooked = hotel.getTotalBookedRooms(date);
    
        //                 if (totalAvailable != -1 && totalBooked != -1) {
        //                     System.out.println("Available Rooms : " + totalAvailable);
        //                     System.out.println("Booked Rooms : " + totalBooked);
        //                 }
        //                 else {
        //                     throw new IndexOutOfBoundsException();

        //                 }
        //             }
        //         }
        //         catch (NumberFormatException exception) {
        //             System.out.println("Invalid Input. Enter a new input.");
        //             error = true;
        //         }
        //         catch (IndexOutOfBoundsException exception) {
        //             error = true;
        //         }
            
        //     } while (error) ;
    
        // }
        
        // // Rooms

        // // Display
        // else if (choiceInfo.equals("2")) {
        //     System.out.println("\nList of Rooms in \"" + hotel.getName() + "\" : ");

        //     for (Room room : hotel.getRoomList()) {
        //         System.out.println(room.getName());     
        //     }
    
        //     // Input
        //     do {
        //         error = false;
        //         try {
                    
        //             System.out.print("\nEnter the room to get room information (Quit to exit): ");
        //             roomName = sc.nextLine();

        //             if (!roomName.equalsIgnoreCase("Quit")) {
        //                 roomInfo = hotel.getRoomInfo(hotel.getRoomIndex(roomName));

        //                 if (roomInfo != null) {
        //                     System.out.println(roomInfo);
        //                 }
        //                 else {
        //                     throw new IndexOutOfBoundsException();
        //                 }
        //             }                    
        //         }
        //         catch (IndexOutOfBoundsException exception) {
        //             error = true;
        //         }
        //     } while (error);
    
        // }
        // // Reservation
        // else if (choiceInfo.equals("3")) {
        //     if (hotel.getReservationList().size() > 0) {
        //         // Display
        //         System.out.println("\nList of Rooms in " + hotel.getName() + " with reservations : ");
    
        //         for (int i = 0; i < hotel.getReservationList().size(); i++) {
        //             System.out.println("["+(i+1)+"] " + hotel.getReservationList().get(i).getRoom().getName() + " : " + hotel.getReservationList().get(i).getGuestName() + "\n\tCheck In: " +
        //                hotel.getReservationList().get(i).getCheckInDate() + "\n\tCheck Out: " + hotel.getReservationList().get(i).getCheckOutDate());
        //         }
                
        //         // Input
        //         do {
        //             error = false;
        //             try {
                    
        //                 System.out.print("\nEnter the room to get reservation information (Quit to exit): ");
        //                 input = sc.nextLine();
                        
        //                 if (!input.equalsIgnoreCase("Quit")) {
        //                     reservationIndex = Integer.parseInt(input)-1;
        //                     reservationInfo = hotel.getReservationInfo(reservationIndex);
    
        //                     if (reservationInfo != null) {
        //                         System.out.println(reservationInfo);
        //                     }
        //                     else {
        //                         throw new IndexOutOfBoundsException();
        //                     }
        //                 }
        //             }
        //             catch (NumberFormatException exception) {
        //                 System.out.println("Invalid Integer Input. Enter New Input.");
        //                 error = true;
        //             }
        //             catch (IndexOutOfBoundsException exception) {
        //                 error = true;
        //             }
        //         } while (error);
        //     }
        //     else {
        //         System.out.println("No Existing Reservation. Enter Reservations.");
        //     }
        // }

    // }

    /**
     * Prompts the user to rename a given hotel.
     * Pre-condition: the hotel index provided exists based from the hotel list and is valid.
     * 
     * @param index the index of the hotel in the hotelList.
     */
    public void renameHotel(int index, String newName) {
        // Assume index is valid.
       
        this.hotelList.get(index).setName(newName);

    }

    /**
     * Removes a hotel from the hotel list.
     * Pre-condition: the hotel index provided exists based from the hotel list and is valid.
     * 
     * @param index the index of the hotel in the hotelList.
     */
    public void removeHotel(int index) {
        
        this.hotelList.remove(index);
        
    }

    /**
     * Facilitates the 'Manage Hotel' feature of the reservation system.
     * Prompts the user regarding commands that can be ran to manipulate the characteristics of a hotel.
     * Checks the preconditions before running the command.
     * Pre-condition: the hotel index provided exists based from the hotel list and is valid.
     * 
     * @param index the index of the hotel in the hotelList.
     */
    // public void manageHotel(int index) {
    //     // Assume index is valid.
        
    //     String choice;
    //     boolean quit = false;
    //     Hotel hotel = this.hotelList.get(index);
        
    //     do {
    //         System.out.println("\n================================");
    //         System.out.println("\n++++++++ Manage Hotel ++++++++++\n");
    //         System.out.println("Options: ");
    //         System.out.println("[1] Rename Hotel\n[2] Add Room\n[3] Remove Room/s\n[4] Update base price\n[5] Remove Reservation\n[6] Remove Hotel\n[Quit] to exit");
    //         System.out.print("\nEnter the command to run: ");
    
    //         choice = sc.nextLine();

    //         if (choice.equals("1")) {
    //             renameHotel(index);
    //         }

    //         else if (choice.equals("2")) {
    //             String input;
    //             int roomNum = 1;
    //             boolean error = false;

    //             if (hotel.getRoomList().size() < 50) {
    //                 do {
    //                     error = false;
    //                     try {
    //                         System.out.print("\nEnter the amount of rooms to add (Quit to exit): ");
    //                         input = sc.nextLine();
                            
    //                         if (!input.equalsIgnoreCase("Quit")) {
    //                             roomNum = Integer.parseInt(input);

    //                             if (roomNum + hotel.getRoomList().size() < 50 && roomNum > 0) {
    //                                 hotel.addRoom(roomNum);
    //                             }
    //                             else {
    //                                 System.out.println("Number of Rooms Invalid. Enter New Input.");
    //                             }
    //                         }
    //                     }   
    //                     catch (NumberFormatException exception) {
    //                         System.out.println("Invalid Integer Input. Enter New Input");
    //                         error = true;
    //                     }
    //                     catch (IndexOutOfBoundsException exception) {
    //                         error = true;
    //                     }
    //                 } while (error);
    //             }
    //             else {
    //                 System.out.println("Hotel is full.");
    //             }
    //         }
    //         else if (choice.equals("3")) {
    //             String roomName;
    //             String repeatChoice;
    //             boolean result = false;
    //             boolean error = false;
    //             boolean repeat = false;
    //             ArrayList<Room> roomList = hotel.getRoomList();
    //             ArrayList<Integer> roomToRemove = new ArrayList<Integer>();

    //             if (roomList.size() > 1) {
    //                 // Display 
    //                 System.out.println("Rooms in " + hotel.getName() + ":");
        
    //                 for (Room room : roomList) {
    //                     System.out.println(room.getName());
    //                 }
        
    //                 // Input
    //                 do {
    //                     error = false;
    //                     try {
    //                         do {
    //                             repeat = false;
    //                             System.out.print("\nEnter the room/s to remove (Quit to exit): ");
    //                             roomName = sc.nextLine();
                
    //                             if (!roomName.equalsIgnoreCase("Quit")) {
    //                                 roomToRemove.add(hotel.getRoomIndex(roomName));
    //                                 do {
    //                                     System.out.print("\nRemove another room? (Yes/No): ");
    //                                     repeatChoice = sc.nextLine();
    //                                 } while (!repeatChoice.equalsIgnoreCase("Yes") && !repeatChoice.equalsIgnoreCase("No"));
    
    //                                 if (repeatChoice.equalsIgnoreCase("Yes")) {
    //                                     repeat = true;
    //                                 }    
    //                             }    
    //                         } while (repeat && hotel.getRoomList().size() - roomToRemove.size() > 0);
                            
    //                         if (!roomName.equalsIgnoreCase("Quit")) {
    //                             if (hotel.getRoomList().size() - roomToRemove.size() > 0) {
    //                                 result = hotel.removeRoom(roomToRemove);

    //                                 if (result == false) {
    //                                     throw new IndexOutOfBoundsException();
    //                                 }
    //                             }
    //                             else {
    //                                 System.out.println("Rooms to remove exceeds the limit.");
    //                             }
    //                         }
    //                     }
    //                     catch (IndexOutOfBoundsException exception) {
    //                         error = true;
    //                     }
    //                 } while (error);
    //             }
    //             else
    //             {
    //                 System.out.println("Hotel has only 1 room. It can not be removed.");
    //             }
    //         }
    //         else if (choice.equals("4")) {
    //             String input;
    //             boolean result;
    //             boolean error;
    //             double roomPrice = 0;
                
    //             // Get price input.
    //             if (hotel.checkAllRoomAvailability()) {
    //                 do {
    //                     error = false;
    //                     try {
    //                         System.out.print("\nEnter the new price (Quit to exit): ");
    //                         input = sc.nextLine();

    //                         if (!input.equalsIgnoreCase("Quit")) {
    //                             roomPrice = Double.parseDouble(input);
    //                             result = hotel.updateRoomPrice(roomPrice);

    //                             if (result == false) {
    //                                 throw new InputMismatchException();
    //                             }
    //                         }
                                
    //                     }
    //                     catch (NumberFormatException exception) {
    //                         System.out.println("Invalid Double Input. Enter a new Input.");
    //                         error = true;
    //                     }
    //                     catch (InputMismatchException exception) {
    //                         error = true;
    //                     }

    //                 } while (error);
    //             }
    //             else {  
    //                 System.out.println("Hotel \"" + hotel.getName() + "\" has existing reservation/s.");
    //             }
    //         }
    //         else if (choice.equals("5")) {
    //             String input;
    //             boolean result;
    //             boolean error;
    //             int reservationIndex = -1;
    //             if (hotel.getReservationList().size() > 0) {
    //                 System.out.println("\nList of Rooms in " + hotel.getName() + " with reservations : ");
    //                 for (int i = 0; i < hotel.getReservationList().size(); i++) {
    //                     System.out.println("["+(i+1)+"] " + hotel.getReservationList().get(i).getRoom().getName() + " : " + hotel.getReservationList().get(i).getGuestName() + "\n\tCheck In: " +
    //                     hotel.getReservationList().get(i).getCheckInDate() + "\n\tCheck Out: " + hotel.getReservationList().get(i).getCheckOutDate());
    //                 }     
                        
    //                 do {
    //                     error = false;
    //                     try {   
    //                         System.out.print("\nEnter the number of the reservation to remove : ");
    //                         input = sc.nextLine();
                            
    //                         if (!input.equalsIgnoreCase("Quit")) {
    //                             reservationIndex = Integer.parseInt(input)-1; 
    //                             result = hotel.removeReservation(reservationIndex);

    //                             if (result == false) {
    //                                 throw new IndexOutOfBoundsException();
    //                             }   
    //                         }
    //                     }
    //                     catch (IndexOutOfBoundsException exception) {
    //                         error = true;
    //                     }
    
    //                 } while (error);
    //             }
    //             else {
    //                 System.out.println("No existing reservations. Enter Reservations.");
    //             }
    //         }
    //         else if (choice.equals("6")) {
    //             removeHotel(index);
    //             quit = true;
    //         }
    //         else if (choice.equalsIgnoreCase("Quit")) {
    //             quit = true;
    //         }

    //     } while (!quit);

    // }

    // /**
    //  * Simulates how a user can reserve a room in the chosen hotel.
    //  * Pre-condition: the hotel index provided exists based from the hotel list and is valid.
    //  * 
    //  * @param index the index of the hotel in the hotelList.
    //  */
    // public void simulateBooking(int index) {
    //     Hotel hotel = this.hotelList.get(index);
    //     String input = "";
    //     String guestName = "";
    //     boolean error;
    //     boolean result = false;
    //     int checkInDate = -1;
    //     int checkOutDate = -1;

    //     System.out.println("\n================================");
    //     System.out.println("\n++++++ Simulate Booking ++++++++");
    //     System.out.print("\nEnter your name : ");
    //     guestName = sc.nextLine();

    //     do {
    //         // Guest name
    //         do { 
    //             error = false;

    //             try {
    //                 System.out.print("\nEnter your check in date (Quit to exit): ");
    //                 input = sc.nextLine();
    //                 if (!input.equalsIgnoreCase("Quit")) {
    //                     checkInDate = Integer.parseInt(input);
    //                 }
    //             }
    //             catch (NumberFormatException exception) {
    //                 System.out.println("Invalid Integer Input. Enter New Input.");
    //                 error = true;
    //             }
    //         } while (error);

    //         // Check in and Check out
    //         if (!input.equalsIgnoreCase("Quit")) {
    //             do {
    //                 error = false;
        
    //                 try {
    //                     System.out.print("\nEnter your check out date (Quit to exit): ");
    //                     input = sc.nextLine();
    //                     if (!input.equalsIgnoreCase("Quit")) {
    //                         checkOutDate = Integer.parseInt(input);
    //                     }
    //                 }
    //                 catch (NumberFormatException exception) {
    //                     System.out.println("Invalid Integer Input. Enter New Input.");
    //                     error = true;
    //                 }
    //             } while (error);
    //         }

    //         if (!input.equalsIgnoreCase("Quit")) {
    //             result = hotel.addReservation(guestName, checkInDate, checkOutDate);
    //         }
        
    //     } while (result == false && !input.equalsIgnoreCase("Quit"));
    // }

    /**
     * Determines if the hotel name provided is from an existing hotel and returns its index, -1 otherwise.
     * 
     * @param name the name of the hotel to check.
     * @return the index, or -1 if non-existent
     */
    public int isExisting(String name) {
        
        int result = -1;

        for (int i = 0; i < this.hotelList.size() && result == -1; i++) {
            if (this.hotelList.get(i).getName().equals(name)) {
                result = i;
            }
        }

        return result;
    }

    // /**
    //  * Facilitates the process of the reservation system.
    //  * Prompts the user on commands that can be ran.
    //  * Checks the necessary conditions for receiving the hotel name as input (hotel names must be unique).
    //  */
    // public void runSystem() {
    //     String choice;

    //     do {
    //         System.out.println("\n================================");
    //         System.out.println("\n----Hotel Reservation System----\n");
    //         System.out.println("Processes: "); 
    //         System.out.println("[1] Create Hotel\n[2] View Hotel\n[3] Manage Hotel\n[4] Simulate Booking\n[Quit] Exit Reservation System");
    //         System.out.print("\nEnter the number of the process to run : ");
    
    //         choice = sc.nextLine();
            
    //         // Options and various method runs
    //         if (choice.equals("1")) {
                
    //             String hotelName;
    //             int hotelIndex = -1;
                
    //             do {
    //                 System.out.println("\n================================");
    //                 System.out.println("\n++++++++ Create Hotel ++++++++++");

    //                 System.out.print("\nEnter a hotel name : ");
    //                 hotelName = sc.nextLine();
    
    //                 if (!hotelName.equalsIgnoreCase("Quit")) {
    //                     hotelIndex = isExisting(hotelName);
                        
    //                     if (hotelIndex == -1) {
    //                         createHotel(hotelName);
    //                     }
    //                     else {
    //                         System.out.println("Hotel " + hotelName + " already exists. Enter New Input.");
    //                     }
    //                 }
    //             } while (hotelIndex != -1);
    //         }
    //         else if (choice.equals("2") || choice.equals("3") || choice.equals("4")) {
    //             if (this.hotelList.size() > 0) {
    //                 String hotelName;
    //                 int hotelIndex = -1;
    //                 System.out.println("\n================================");
    //                 System.out.println("\nList of Hotels: ");
    //                 for (int i = 0; i < this.hotelList.size(); i++) {
    //                     System.out.println(this.hotelList.get(i).getName());
    //                 }
                    
    //                 do {    
    //                     System.out.print("\nEnter a hotel name : ");
    //                     hotelName = sc.nextLine();
        
    //                     if (!hotelName.equalsIgnoreCase("Quit")) {
    //                         hotelIndex = isExisting(hotelName);
                            
    //                         if (hotelIndex != -1) {
    //                             if (choice.equals("2")) {
    //                                 viewHotel(hotelIndex);
    //                             } 
    //                             else if (choice.equals("3")) {
    //                                 manageHotel(hotelIndex);
    //                             }   
    //                             else if (choice.equals("4")) {
    //                                 simulateBooking(hotelIndex);
    //                             }       
    //                         }
    //                         else {
    //                             System.out.println("Hotel " + hotelName + " does not exists. Enter New Input.");
    //                         }
    
    //                     }
    //                 } while (hotelIndex == -1);
    //             }
    //             else {
    //                 System.out.println("There are no hotels in the system. Create hotels.");
    //             }
    //         }
    //     } while (!choice.equalsIgnoreCase("Quit"));
    // }

    /**
     * Returns the hotel list as an arraylist.
     * 
     * @return hotelList, arraylist containing the hotels in the system.
     */
    public ArrayList<Hotel> getHotelList() {
        return this.hotelList;
    }
}