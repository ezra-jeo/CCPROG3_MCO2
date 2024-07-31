import java.util.ArrayList;

/**
 * The ReservationSystem class represents the model for the reservation system with regards to the MVC architecture.
 * It contains a collection of hotels through which a user can reserve/book a room.
 */
public class ReservationSystem {

    /**
     * Collection of hotels stored in the system.
     * 
     */
    private ArrayList<Hotel> hotelList;

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
     * @param name the name of the hotel that is being created
     * @param roomNum the arraylist of integers containing the room numbers for standard, deluxe, and executive room types
     */
    public void createHotel(String name, ArrayList<Integer> roomNum) {  

        Hotel hotel = new Hotel(name, roomNum);
        this.hotelList.add(hotel);

    }

    /**
     * Returns a string containing high level information of a hotel. 
     * Pre-condition: the hotel index provided exists based from the hotel list and is valid.
     * 
     * @param index the index of the hotel in the hotelList
     * @return string containing the information
     */
    public String viewHotelHighLevel(int index) {
        String result = "";
        Hotel hotel = hotelList.get(index);

        result += "Hotel Name                    : " + hotel.getName() + "\n";
        result += "No. of Standard Rooms  : " + hotel.getStandardRoomList().size() + "\n"; 
        result += "No. of Deluxe Rooms     : " + hotel.getDeluxeRoomList().size() + "\n";
        result += "No. of Executive Rooms : " + hotel.getExecutiveRoomList().size() + "\n";
        result += "Estimated Earnings         : " + String.format("%.2f", hotel.getEarnings()) + "\n";

        
        return result;
    }

    /**
     * Returns a string containing the available and booked rooms of a hotel. 
     * Pre-condition: the hotel index provided exists based from the hotel list and is valid. The date is within the range of minimum
     * and maximum dates possible.
     * 
     * @param index the index of the hotel in the hotelList
     * @param date the date whose information to display
     * @return string containing the information
     */
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
        result += "\tStandard Rooms  : " + available.get(0) + "\n";
        result += "\tDeluxe Rooms     : " + available.get(1) + "\n";
        result += "\tExecutive Rooms : " + available.get(2) + "\n";
        result += "Booked Rooms   : " + totalBooked + "\n";
        result += "\tStandard Rooms  : " + booked.get(0) + "\n";
        result += "\tDeluxe Rooms     : " + booked.get(1) + "\n";
        result += "\tExecutive Rooms : " + booked.get(2) + "\n";
        
        return result;
    }

    /**
     * Returns a string containing the room information.
     * Pre-condition: the hotel index provided exists based from the hotel list and is valid. The reservation index is based from 
     * the reservation list and is valid
     * 
     * @param index the index of the hotel in the hotelList
     * @param roomName the name of the room whose information to display
     * @return string containing the information
     */
    public String viewHotelRoomInfo(int index, String roomName) {
        String result = "";
        Hotel hotel = hotelList.get(index);
        
        result = hotel.getRoomInfo(hotel.getRoomIndex(roomName));

        return result;
    }

    /**
     * Returns a string containing the reservation information.
     * Pre-condition: the hotel index provided exists based from the hotel list and is valid. The reservation index is based from 
     * the reservation list and is valid
     * 
     * @param index the index of the hotel in the hotelList
     * @param reservationIndex the index of the reservation whose information to display
     * @return string containing the information
     */
    public String viewHotelReservationInfo(int index, int reservationIndex) {
        String result = "";
        Hotel hotel = hotelList.get(index);

        result = hotel.getReservationInfo(reservationIndex);

        return result;
        
    }

    /**
     * Sets the name of a hotel given a new name
     * Pre-condition: the hotel index provided exists based from the hotel list and is valid. The new name provided is unique.
     * 
     * @param index the index of the hotel in the hotelList
     * @param newName the new name to replace the original
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

    /**
     * Returns the hotel list as an arraylist.
     * 
     * @return arraylist containing the hotels in the system.
     */
    public ArrayList<Hotel> getHotelList() {
        return this.hotelList;
    }
}