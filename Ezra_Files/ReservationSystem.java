import java.util.ArrayList;

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

        result += "Hotel Name                    : " + hotel.getName() + "\n";
        result += "No. of Standard Rooms  : " + hotel.getStandardRoomList().size() + "\n"; // Get Standard Room List TODO
        result += "No. of Deluxe Rooms     : " + hotel.getDeluxeRoomList().size() + "\n";
        result += "No. of Executive Rooms : " + hotel.getExecutiveRoomList().size() + "\n";
        result += "Estimated Earnings         : " + hotel.getEarnings() + "\n";

        
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
        result += "\tStandard Rooms  : " + available.get(0) + "\n";
        result += "\tDeluxe Rooms     : " + available.get(1) + "\n";
        result += "\tExecutive Rooms : " + available.get(2) + "\n";
        result += "Booked Rooms   : " + totalBooked + "\n";
        result += "\tStandard Rooms  : " + booked.get(0) + "\n";
        result += "\tDeluxe Rooms     : " + booked.get(1) + "\n";
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
     * @return hotelList, arraylist containing the hotels in the system.
     */
    public ArrayList<Hotel> getHotelList() {
        return this.hotelList;
    }
}