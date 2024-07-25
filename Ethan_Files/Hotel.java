import java.util.ArrayList;

/**
 * The Hotel class represents the hotel entity in the hotel reservation system.
 * It has a name, which is unique from the other hotels in the system, list of rooms, with
 * respect to the minimum and maximum number of rooms in a hotel, list of reservations, and
 * base price and price rates for all the rooms under the hotel.
 */
public class Hotel {

    /**
     * The minimum number of rooms in a hotel class.
     * 
     */
    public static final int MIN_ROOM = 1;

    /**
     * The maximum number of rooms in a hotel class.
     * 
     */
    public static final int MAX_ROOM = 50;

    /**
     * The name of the hotel object.
     * 
     */
    private String name;

    /**
     * The list of rooms under the hotel object.
     * 
     */
    private ArrayList<Room> roomList;

    /**
     * The list of reservations under the hotel object.
     * 
     */
    private ArrayList<Reservation> reservationList;

    /**
     * The base price for all the rooms under the hotel object.
     * 
     */
    private double roomBasePrice = 1299.0;

    /**
     * The price rates for all the rooms across the entire month under the hotel object.
     * 
     */
    private ArrayList<Double> roomPriceRate;

    /**
     * Creates a new hotel object for the given name and creates a number of room/s for the hotel based on the given number for each type.
     * Pre-condition: name of the hotel is unique from the other hotels in the system and 
     *                sum of the values in the list of number of rooms for each type is less than or equal to 50.
     * 
     * @param name the name of the hotel.
     * @param numRoomList the list of number of rooms for each type to be created.
     */
    public Hotel(String name, ArrayList<Integer> numRoomList) {
        StandardRoom standardRoom;
        DeluxeRoom deluxeRoom;
        ExecutiveRoom executiveRoom;
        String roomName;
        String roomNum;
        int roomCtr = 1;
        int i;

        this.name = name;
        this.roomList = new ArrayList<Room>();
        this.reservationList = new ArrayList<Reservation>();
        this.roomPriceRate = new ArrayList<Double>();

        // Initializes the price rate for each day in the entire month to be 100%
        for (i = Room.MIN_DATE - 1; i < Room.MAX_DATE - 1; i++)
            this.roomPriceRate.add(1.0);

        // Creates standard room types based on the given value
        for (i = 1; i <= numRoomList.get(0); i++) {
            roomNum = String.format("%02d", roomCtr);
            roomName = this.name.substring(0, 1) + roomNum + "-ST";
            standardRoom = new StandardRoom(roomName, this.roomBasePrice, this.roomPriceRate);
            this.roomList.add(standardRoom);
            roomCtr++;
        }

        // Creates deluxe room types based on the given value
        for (i = 1; i <= numRoomList.get(1); i++) {
            roomNum = String.format("%02d", roomCtr);
            roomName = this.name.substring(0, 1) + roomNum + "-DL";
            deluxeRoom = new DeluxeRoom(roomName, this.roomBasePrice, this.roomPriceRate);
            this.roomList.add(deluxeRoom);
            roomCtr++;
        }

        // Creates executive room types based on the given value
        for (i = 1; i <= numRoomList.get(2); i++) {
            roomNum = String.format("%02d", roomCtr);
            roomName = this.name.substring(0, 1) + roomNum + "-EX";
            executiveRoom = new ExecutiveRoom(roomName, this.roomBasePrice, this.roomPriceRate);
            this.roomList.add(executiveRoom);
            roomCtr++;
        }
    }

    /**
     * Sorts the rooms in the list of rooms under the hotel object based on their room numbers.
     * 
     */
    private void sortRoomList() {
        int size = this.roomList.size();
        int i, j;
        int minIndex;
        int minNum;
        int roomNum;
        Room room;

        // Executes a selection sort algorithm to sort the rooms by their room numbers.
        for (i = 0; i < size - 1; i++) {
            minIndex = i;
            for (j = i + 1; j < size; j++) {
                minNum = Integer.parseInt(this.roomList.get(minIndex).getName().substring(1,3));
                roomNum = Integer.parseInt(this.roomList.get(j).getName().substring(1,3));

                if (minNum > roomNum)
                    minIndex = j;
            }

            if (minIndex != i) {
                room = this.roomList.remove(minIndex);
                this.roomList.add(i, room);
            }
        } 
    }

    /**
     * Changes the name of all the rooms under the hotel object based on the initial letter of the name of the hotel object.
     * 
     */
    private void changeAllRoomName() {
        for (Room room : this.roomList) {
            room.setName(this.name.charAt(0));
        }
    }

    /**
     * Checks if the given room is available within the given range of check-in and check-out dates.
     * Pre-condition: checkInDate is before checkOut, and checkInDate and checkOutDate is within the range of the minimum and maximum date reservation values.
     * 
     * @param room the room object to be checked whether is available or not.
     * @param checkInDate the starting date to consider the availability of the room object.
     * @param checkOutDate the ending date to consider the availability of the room object.
     * @return true if the given room is available within the given range and false otherwise.
     */
    private boolean checkAvailability(Room room, int checkInDate, int checkOutDate) {
        boolean available = true;
        int i = checkInDate - 1;

        // Loops through the concerned dates and checks whether a date is not available.
        while (i < checkOutDate - 1 && available) {
            if (room.getAvailability().get(i) == false)
                available = false;

            i++;
        }

        return available;
    }

    /**
     * Checks the availability of the rooms represented by the given list of indexes.
     * Pre-condition: the list of index values are valid from the list of rooms under the hotel.
     * 
     * @param roomIndexList the list of room index values to be checked.
     * @return true if all the rooms are available and false otherwise.
     */
    public boolean checkRoomAvailabilityIndexList(ArrayList<Integer> roomIndexList) {
        boolean available = true;
        int i = 0;

        while (i < roomIndexList.size() && available) {
            if (!(checkAvailability(this.roomList.get(roomIndexList.get(i)), Room.MIN_DATE, Room.MAX_DATE)))
                available = false;

            i++;
        }

        return available;
    }

    /**
     * Checks the availability of all the rooms in the hotel object within the entire month.
     * 
     * @return true if all the rooms are available and false otherwise.
     */
    public boolean checkAllRoomAvailability() {
        int size = this.roomList.size();
        int i = 0;
        boolean available = true;

        // Loops through all the rooms in the list and checks their availability within the entire month.
        while (i < size && available) {
            if (!checkAvailability(this.roomList.get(i), Room.MIN_DATE, Room.MAX_DATE))
                available = false;

            i++;
        }

        return available;
    }
    
    /**
     * Checks whether the given discount code is applicable with the given date values, if needed.
     * Pre-condition: check-in and check-out date values are valid.
     * 
     * @param discountCode the discount code to be checked.
     * @param checkInDate the starting date for a possible reservation.
     * @param checkOutDate the ending date for a possible reservation.
     * @return true if applicable and false otherwise.
     */
    public boolean checkDiscountApplicable(String discountCode, int checkInDate, int checkOutDate) {
        boolean applicable = false;

        if (discountCode.equals("I_WORK_HERE"))
            applicable = true;
        else if (discountCode.equals("STAY4_GET1") && checkOutDate - checkInDate >= 4)
            applicable = true;
        else if (discountCode.equals("PAYDAY") && ((checkInDate <= 15 && 15 < checkOutDate) || (checkInDate <= 30 && 30 < checkOutDate)))
            applicable = true;

        return applicable;
    }

    /**
     * Searches and gets the room index of the given room name based on the list of rooms under the hotel object.
     * 
     * @param name the room name to be searched on from the list of rooms.
     * @return the index of the room if found and -1 if not found.
     */
    public int getRoomIndex(String name) {
        int size = this.roomList.size();
        int i = 0;
        boolean found = false;

        // Loops through the list of rooms and checks whether it is the same with the given name.
        while (i < size && !found) {
            if (name.equals(this.roomList.get(i).getName()))
                found = true;

            i++;
        }

        if (found)
            return --i;
        else
            return -1;
    }

    /**
     * Checks the validity of all room index values from the given list.
     * 
     * @param roomIndexList the list of room index values to be checked.
     * @return true if all values are valid and false otherwise.
     */
    public boolean checkRoomIndexListValidity(ArrayList<Integer> roomIndexList) {
        boolean valid = true;
        int i = 0;

        while (i < roomIndexList.size() && valid) {
            if (!(roomIndexList.get(i) >= 0 && roomIndexList.get(i) < this.roomList.size()))
                    valid = false;

            i++;
        }

        return valid;
    }

    /**
     * Creates and adds a number of rooms for each type to the list of rooms under the hotel object based on the given number list.
     * Pre-condition: adding to the list based on the given values will not exceed the maximum number of rooms for the hotel object.
     * 
     * @param numRoomList the list of number of rooms for each type to create and add to the list of rooms.
     */
    public void addRoom(ArrayList<Integer> numRoomList) {
        StandardRoom standardRoom;
        DeluxeRoom deluxeRoom;
        ExecutiveRoom executiveRoom;
        String roomName;
        String roomNumString;
        boolean roomExists;
        int size = this.roomList.size();
        int roomNum = 1;
        int i = 1, j = 0, k = 0;

        // Automatically creates rooms names based on the sequential room naming convention and immediately creates the concerned rooms based on the given amount.
        for (k = 0; k < 3; k++) {
            for (i = 1; i <= numRoomList.get(k); i++) {
                sortRoomList();
                roomExists = true;

                while (j < size && roomExists) {
                    if (roomNum != Integer.parseInt(this.roomList.get(j).getName().substring(1,3)))
                        roomExists = false;
                    else 
                        j++;
                    
                    roomNum++;
                }

                if (roomExists) {
                    roomNumString = String.format("%02d", Integer.parseInt(this.roomList.get(this.roomList.size()-1).getName().substring(1,3)) + 1);
                    roomName = this.name.substring(0,1) + roomNumString;
                }
                else {
                    roomNumString = String.format("%02d", roomNum - 1);
                    roomName = this.name.substring(0,1) + roomNumString;
                }                

                if (k == 0) {
                    roomName += "-ST";
                    standardRoom = new StandardRoom(roomName, this.roomBasePrice, this.roomPriceRate);
                    this.roomList.add(standardRoom);
                }
                else if (k == 1) {
                    roomName += "-DL";
                    deluxeRoom = new DeluxeRoom(roomName, this.roomBasePrice, this.roomPriceRate);
                    this.roomList.add(deluxeRoom);
                }
                else if (k == 2) {
                    roomName += "-EX";
                    executiveRoom = new ExecutiveRoom(roomName, this.roomBasePrice, this.roomPriceRate);
                    this.roomList.add(executiveRoom);
                }
            }
        }
    }

    /**
     * Removes a number of available rooms based on the given list of room index from the list of rooms.
     * Pre-condition: removing a number of available rooms will not make the hotel room list empty, and
     *                list of index values is valid from the list of rooms under the hotel, and all rooms
     *                to be removed is available.
     * 
     * @param indexList the list of index of the rooms to be removed.
     */
    public void removeRoom(ArrayList<Integer> indexList) {
        ArrayList<Room> roomList = new ArrayList<Room>();

        // Removes all concerned rooms from the list.
        for (Integer index : indexList)
            roomList.add(this.roomList.get(index)); // Adds the concerned rooms to a list.

        this.roomList.removeAll(roomList); // Using the list to removed the concerned rooms.
    }

    /**
     * Searches and gets the index of an available room for the specified type with the given date values.
     * Pre-condition: check-in and check-out date values are valid.
     * 
     * @param roomType the room type of the available room to look for.
     * @param checkInDate the starting date for the possible reservation.
     * @param checkOutDate the ending date for the possible reservation.
     * @return the index of the available room and -1 if there is no available room.
     */
    public int getAvailableRoomType(String roomType, int checkInDate, int checkOutDate) {
        Room room;
        boolean available = false;
        int i =  0;

        while (i < this.roomList.size() && !available) {
            room = this.roomList.get(i);

            if (checkAvailability(room, checkInDate, checkOutDate)) {
                if (roomType.equalsIgnoreCase("Standard") && room instanceof StandardRoom)
                    available = true;
                else if (roomType.equalsIgnoreCase("Deluxe") && room instanceof DeluxeRoom)
                    available = true;
                else if (roomType.equalsIgnoreCase("Execute") && room instanceof ExecutiveRoom)
                    available = true;
            }

            i++;
        }

        if (available)
            return --i;
        else
            return -1;
    }

    /**
     * Creates and adds a reservation to the list of reservations under the hotel object given the guest name, check-in and check-out dates, and room index.
     * Pre-condition: check-in and check-out date values are valid and room index value is valid.
     * 
     * @param guestName the name of the guest for the reservation.
     * @param checkInDate the starting date for the reservation.
     * @param checkOutDate the ending date for the reservation.
     * @param roomIndex the index of the room to be reserved for the reservation.
     */
    public void addReservation(String guestName, int checkInDate, int checkOutDate, int roomIndex) {
        Room room = this.roomList.get(roomIndex);

        // Creates a reservation and adds to the list of reservations.
        Reservation reservation = new Reservation(guestName, checkInDate, checkOutDate, room);
        this.reservationList.add(reservation);
    }

    /**
     * Creates and adds a reservation to the list of reservations under the hotel object given the guest name, check-in and check-out dates, room index, and discount code.
     * Pre-condition: check-in and check-out date values are valid and room index value is valid. Discount code to be applied is applicable.
     * 
     * @param guestName the name of the guest for the reservation.
     * @param checkInDate the starting date for the reservation.
     * @param checkOutDate the ending date for the reservation.
     * @param roomIndex the index of the room to be reserved for the reservation.
     * @param discountCode the discount code to be applied for the reservation.
     */
    public void addReservation(String guestName, int checkInDate, int checkOutDate, int roomIndex, String discountCode) {
        Room room = this.roomList.get(roomIndex);

        // Creates a reservation and adds to the list of reservations.
        Reservation reservation = new Reservation(guestName, checkInDate, checkOutDate, room, discountCode);
        this.reservationList.add(reservation);
    }

    /**
     * Removes a reservation from the list of reservations under the hotel object given the reservation index.
     * Pre-condition: there is an existing reservation under the hotel object and
     *                index value is valid from the list of reservations.
     * 
     * @param index the index of the reservation from the list of reservations.
     */
    public void removeReservation(int index) {
        Reservation reservation = this.reservationList.get(index);
            
        // Removes the reservation from the room.
        reservation.getRoom().setAvailability(reservation.getCheckInDate(), reservation.getCheckOutDate(), true);
        // Removes the reservation from the list.
        this.reservationList.remove(index);
    }

    /**
     * Gets the total number of available rooms for each type under the hotel object based on the given date.
     * Pre-condition: date value is valid within the range of minimum and maximum dates.
     * 
     * @param date the date to be considered in getting the number of available rooms.
     * @return the total number of available rooms for each type with the given date.
     */
    public ArrayList<Integer> getTotalAvailableRooms(int date) {
        ArrayList<Integer> totalList = new ArrayList<Integer>();
        int i;
        int ctr;

        for (i = 0; i < 3; i++)
            totalList.add(0);

        // Loops through the list of rooms and counts the number of available rooms for each type with the specified date.        
        for (Room room : this.roomList) {
            if (room.getAvailability().get(date - 1) == true) {
                if (room instanceof StandardRoom) {
                    ctr = totalList.get(0);
                    ctr++;
                    totalList.set(0, ctr);
                }
                else if (room instanceof DeluxeRoom) {
                    ctr = totalList.get(1);
                    ctr++;
                    totalList.set(1, ctr);
                }
                else if (room instanceof ExecutiveRoom) {
                    ctr = totalList.get(2);
                    ctr++;
                    totalList.set(2, ctr);
                }
            }
        }

        return totalList;
    }

    /**
     * Gets the total number of booked rooms for each type under the hotel object based on the given date.
     * Pre-condition: date value is valid within the range of minimum and maximum dates.
     * 
     * @param date the date to be considered in getting the number of booked rooms.
     * @return the arraylist of total number of booked rooms for each type with the given date.
     */
    public ArrayList<Integer> getTotalBookedRooms(int date) {
        ArrayList<Integer> totalList = new ArrayList<Integer>();
        int i;
        int ctr;

        for (i = 0; i < 3; i++)
            totalList.add(0);

        // Loops through the list of rooms and counts the number of booked rooms for each type with the specified date.        
        for (Room room : this.roomList) {
            if (room.getAvailability().get(date - 1) == false) {
                if (room instanceof StandardRoom) {
                    ctr = totalList.get(0);
                    ctr++;
                    totalList.set(0, ctr);
                }
                else if (room instanceof DeluxeRoom) {
                    ctr = totalList.get(1);
                    ctr++;
                    totalList.set(1, ctr);
                }
                else if (room instanceof ExecutiveRoom) {
                    ctr = totalList.get(2);
                    ctr++;
                    totalList.set(2, ctr);
                }
            }
        }

        return totalList;
    }

    /**
     * Gets the information on the room based on the given index from the list of rooms under the hotel object.
     * Pre-condition: index value is valid from the list of rooms.
     * 
     * @param index the index of the room to be considered from the list of rooms.
     * @return the string room information.
     */
    public String getRoomInfo(int index) {
        Room room = this.roomList.get(index);
        String roomInfo;
        ArrayList<Boolean> availability;
        boolean available = false;
        int i;

        roomInfo = "\nRoom Name : " + room.getName();
        roomInfo += "\n\nPrice per Night : ";

        for (i = Room.MIN_DATE; i < Room.MAX_DATE; i++)
            roomInfo += "\nDay " + i + " to " + (i + 1) + " -> " + room.getPrice(i);
        
        roomInfo += "\n\nAvailability : ";

        availability = room.getAvailability();
            
        // Checks if there is an available date.
        while (i < availability.size() && !available) {
            if (availability.get(index) == true)
                available = true;

            i++;
        }

        if (available) {
            // Loops through the list of availability of the room and stores the available dates.
            for (i = 0; i < availability.size(); i++) {
                if (availability.get(i))
                    roomInfo += (i+1) + ", ";
            }

            roomInfo += "\b\b ";
        }
        else 
            roomInfo += "None";

        return roomInfo;
    }

    /**
     * Gets the information on the reservation based on the given index from the list of reservations under the hotel object.
     * Pre-condition: index value is valid from the list of reservations.
     * 
     * @param index the index of the reservation to be considered from the list of reservations.
     * @return the string reservation information.
     */
    public String getReservationInfo(int index) {
        Reservation reservation = this.reservationList.get(index);
        ArrayList<Double> priceBreakdown = reservation.getPriceBreakdown();
        String reservationInfo;
        int i;
        int ctr = 0;

        reservationInfo = "\nGuest Information : " + reservation.getGuestName();
        reservationInfo += "\nRoom Name : " + reservation.getRoom().getName();
        reservationInfo += "\nCheck-In Date : " + reservation.getCheckInDate();
        reservationInfo += "\nCheck-Out Date : " + reservation.getCheckOutDate();
        reservationInfo += "\nDiscount Code : ";

        if (reservation.getDiscountCode() == null)
            reservationInfo += "N/A";
        else
            reservationInfo += reservation.getDiscountCode();

        reservationInfo += "\n\nPrice Breakdown : ";

        for (i = reservation.getCheckInDate(); i < reservation.getCheckOutDate(); i++) {
            reservationInfo = "\nDay " + i + " to " + (i + 1) + " -> " + priceBreakdown.get(ctr);
            ctr++;
        }

        reservationInfo += "\n\nTotal Price : " + reservation.getTotalPrice();

        return reservationInfo;
    }

    /**
     * Returns the name of the hotel object.
     * 
     * @return the name of the hotel.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the sorted list of room object/s under the hotel object.
     * 
     * @return the arraylist of room object/s under the hotel.
     */
    public ArrayList<Room> getRoomList() {
        sortRoomList();

        return this.roomList;
    }

    /**
     * Returns the sorted list of standard room object/s under the hotel object.
     * 
     * @return the arraylist of standard room object/s under the hotel.
     */
    public ArrayList<StandardRoom> getStandardRoomList() {
        ArrayList<StandardRoom> standardRoomList = new ArrayList<StandardRoom>();

        sortRoomList();
        for (Room room : this.roomList) {
            if (room instanceof StandardRoom)
                standardRoomList.add((StandardRoom) room);
        }

        return standardRoomList;
    }

    /**
     * Returns the sorted list of deluxe room object/s under the hotel object.
     * 
     * @return the arraylist of deluxe room object/s under the hotel.
     */
    public ArrayList<DeluxeRoom> getDeluxeRoomList() {
        ArrayList<DeluxeRoom> deluxeRoomList = new ArrayList<DeluxeRoom>();

        sortRoomList();
        for (Room room : this.roomList) {
            if (room instanceof DeluxeRoom)
                deluxeRoomList.add((DeluxeRoom) room);
        }

        return deluxeRoomList;
    }

    /**
     * Returns the sorted list of executive room object/s under the hotel object.
     * 
     * @return the arraylist of executive room object/s under the hotel.
     */
    public ArrayList<ExecutiveRoom> getExecutiveRoomList() {
        ArrayList<ExecutiveRoom> executiveRoomList = new ArrayList<ExecutiveRoom>();

        sortRoomList();
        for (Room room : this.roomList) {
            if (room instanceof ExecutiveRoom)
                executiveRoomList.add((ExecutiveRoom) room);
        }

        return executiveRoomList;
    }

    /**
     * Returns the list of reservation object/s under the hotel object.
     * 
     * @return the arraylist of reservation object/s under the hotel.
     */
    public ArrayList<Reservation> getReservationList() {
        return this.reservationList;
    }

    /**
     * Returns the base price for all the rooms under the hotel object.
     * 
     * @return the base price for all the rooms.
     */
    public double getRoomBasePrice() {
        return this.roomBasePrice;
    }

    /**
     * Returns the price rates for all the rooms across the entire month under the hotel object.
     * 
     * @return the arraylist of price rates for all the rooms across the entire month.
     */
    public ArrayList<Double> getRoomPriceRate() {
        return this.roomPriceRate;
    }

    /**
     * Gets and returns the total estimated earnings of the hotel object based on its reservation list.
     * 
     * @return the total estimated earnings of the hotel.
     */
    public double getEarnings() {
        double earnings = 0;
        
        for (Reservation reservation : this.reservationList)
            earnings += reservation.getTotalPrice();

        return earnings;
    }

    /**
     * Sets the name of the hotel object with the given name. This also changes the name of all the rooms under the hotel object.
     * Pre-condition: name is unique from the other hotels in the system.
     * 
     * @param name the new name to be used to replace the name of the hotel.
     */
    public void setName(String name) {
        this.name = name;
        changeAllRoomName();
    }

    /**
     * Sets the base price for all the rooms under the hotel object with the given base price.
     * Pre-condition: there are no existing reservations under the hotel object, and the base
     *                price is greater than or equal to 100.
     * 
     * @param basePrice the new base price to be applied on all the rooms.
     */
    public void setRoomBasePrice(double basePrice) {
        this.roomBasePrice = basePrice;

        for (Room room : this.roomList)
            room.setBasePrice(this.roomBasePrice);
    }

    /**
     * Sets the price rate of all the rooms under the hotel object on the considered date with the given price rate.
     * Pre-condition: there are no existing reservations under the considered date under the hotel object, and
     *                the price rate is within the range of 0.50 to 1.50.
     * 
     * @param date the date to be considered in changing the price rate.
     * @param priceRate the new price rate to be applied on the considered date.
     */
    public void setRoomPriceRate(int date, double priceRate) {
        this.roomPriceRate.set(date - 1, priceRate);

        for (Room room : this.roomList)
            room.setPriceRate(this.roomPriceRate);
    }
}