import java.util.ArrayList;

public class HotelTest {
    public static void main(String[] args) {
        ArrayList<Integer> numRoomList = new ArrayList<Integer>();

        numRoomList.add(2);
        numRoomList.add(2);
        numRoomList.add(2);

        Hotel hotel = new Hotel("Hotel", numRoomList);

        System.out.println(hotel.getName());

        ArrayList<Room> roomList = hotel.getRoomList();
        
        System.out.println();
        for (Room room : roomList) 
            System.out.println(room.getName());

        System.out.println();
        ArrayList<StandardRoom> standardRoomList = hotel.getStandardRoomList();

        for (StandardRoom room : standardRoomList)
            System.out.println(room.getName() + " - " + room.getPrice(1));

        System.out.println();
        ArrayList<DeluxeRoom> deluxeRoomList = hotel.getDeluxeRoomList();

        for (DeluxeRoom room : deluxeRoomList)
            System.out.println(room.getName() + " - " + room.getPrice(1));
        
        System.out.println();
        ArrayList<ExecutiveRoom> executiveRoomList = hotel.getExecutiveRoomList();

        for (ExecutiveRoom room : executiveRoomList)
            System.out.println(room.getName() + " - " + room.getPrice(1));

        hotel.setName("Marriot");

        System.out.println();
        System.out.println(hotel.getName());

        roomList = hotel.getRoomList();

        System.out.println();
        for (Room room : roomList) 
            System.out.println(room.getName());

        hotel.setRoomBasePrice(500);

        System.out.println();
        System.out.println("Standard - " + standardRoomList.get(0).getPrice(1));
        System.out.println("Deluxe - " + deluxeRoomList.get(0).getPrice(1));
        System.out.println("Executive - " + executiveRoomList.get(0).getPrice(1));

        hotel.setRoomPriceRate(1, 2.0);
        System.out.println();
        System.out.println("Standard - " + standardRoomList.get(0).getPrice(1));
        System.out.println("Deluxe - " + deluxeRoomList.get(0).getPrice(1));
        System.out.println("Executive - " + executiveRoomList.get(0).getPrice(1));

        // System.out.println(hotel.getRoomInfo(0));

        // ArrayList<Integer> validRoomIndexList = new ArrayList<Integer>();
        // validRoomIndexList.add(3);
        // validRoomIndexList.add(12);
        // validRoomIndexList.add(6);

        // ArrayList<Integer> invalidRoomIndexList = new ArrayList<Integer>();
        // invalidRoomIndexList.add(3);
        // invalidRoomIndexList.add(15);
        // invalidRoomIndexList.add(1);

        // System.out.println();
        // System.out.println(hotel.checkRoomIndexListValidity(validRoomIndexList));
        // System.out.println(hotel.checkRoomIndexListValidity(invalidRoomIndexList));

        // hotel.removeRoom(validRoomIndexList);

        // roomList = hotel.getRoomList();

        // System.out.println();
        // for (Room room : roomList) 
        //     System.out.println(room.getName());

        // StandardRoom standardRoom = new StandardRoom("M04-EX", 0, null);
        // DeluxeRoom deluxeRoom = new DeluxeRoom("M07-ST", 0, null);
        // ExecutiveRoom executiveRoom = new ExecutiveRoom("M13-DL", 0, null);

        // hotel.add(standardRoom);
        // hotel.add(deluxeRoom);
        // hotel.add(executiveRoom);

        // roomList = hotel.getRoomList();

        // System.out.println();
        // for (Room room : roomList) 
        //     System.out.println(room.getName());

        // numRoomList.clear();
        // numRoomList.add(2);
        // numRoomList.add(0);
        // numRoomList.add(2);

        // hotel.addRoom(numRoomList);

        // roomList = hotel.getRoomList();

        // System.out.println();
        // for (Room room : roomList) 
        //     System.out.println(room.getName());

        // System.out.println();
        // System.out.println(hotel.getRoomIndex("M07-ST"));
        // System.out.println(hotel.getRoomIndex("M15-EX"));
        // System.out.println(hotel.getRoomIndex("M17-EX"));

        System.out.println();
        System.out.println(hotel.checkAllRoomAvailability());

        int roomIndex1 = hotel.getAvailableRoomType("standard", 1, 5);
        int roomIndex2 = hotel.getAvailableRoomType("deluxe", 1, 5);
        int roomIndex3 = hotel.getAvailableRoomType("executive", 1, 5);

        numRoomList.clear();
        numRoomList.add(roomIndex1);
        numRoomList.add(roomIndex2);
        numRoomList.add(roomIndex3);

        System.out.println();
        System.out.println(hotel.checkRoomAvailabilityIndexList(numRoomList));

        System.out.println();
        System.out.println(roomIndex1 + " " + roomIndex2 + " " + roomIndex3);

        hotel.addReservation("Guest1", 1, 5, roomIndex1);
        hotel.addReservation("Guest2", 1, 5, roomIndex2);
        hotel.addReservation("Guest3", 1, 5, roomIndex3);

        System.out.println();
        System.out.println(hotel.checkRoomAvailabilityIndexList(numRoomList));

        System.out.println();
        System.out.println(hotel.checkAllRoomAvailability());

        roomIndex1 = hotel.getAvailableRoomType("standard", 1, 5);
        roomIndex2 = hotel.getAvailableRoomType("deluxe", 1, 5);
        roomIndex3 = hotel.getAvailableRoomType("executive", 1, 5);

        System.out.println();
        System.out.println(roomIndex1 + " " + roomIndex2 + " " + roomIndex3);

        hotel.addReservation("Guest4", 1, 5, roomIndex1);
        hotel.addReservation("Guest5", 1, 5, roomIndex2);
        hotel.addReservation("Guest6", 1, 5, roomIndex3);

        roomIndex1 = hotel.getAvailableRoomType("standard", 1, 5);
        roomIndex2 = hotel.getAvailableRoomType("deluxe", 1, 5);
        roomIndex3 = hotel.getAvailableRoomType("executive", 1, 5);

        System.out.println();
        System.out.println(roomIndex1 + " " + roomIndex2 + " " + roomIndex3);

        // ArrayList<Reservation> reservationList = hotel.getReservationList();

        // System.out.println();
        // for (Reservation reservation : reservationList)
        //     System.out.println(reservation.getGuestName());

        // System.out.println(hotel.getReservationInfo(0));
        // System.out.println(hotel.getReservationInfo(1));
        // System.out.println(hotel.getReservationInfo(2));
        // System.out.println(hotel.getReservationInfo(3));
        // System.out.println(hotel.getReservationInfo(4));
        // System.out.println(hotel.getReservationInfo(5));

        // System.out.println();
        // System.out.println(hotel.getEarnings());

        // System.out.println(hotel.getRoomInfo(0));
        // System.out.println(hotel.getRoomInfo(1));
        // System.out.println(hotel.getRoomInfo(2));

        // ArrayList<Integer> availableRoomList = new ArrayList<Integer>();
        // ArrayList<Integer> bookedRoomList = new ArrayList<Integer>();

        // availableRoomList = hotel.getTotalAvailableRooms(3);
        // bookedRoomList = hotel.getTotalBookedRooms(3);

        // System.out.println("\nAvailable");
        // for (Integer total : availableRoomList)
        //     System.out.println(total);

        // System.out.println("\nBooked");
        // for (Integer total : bookedRoomList)
        //     System.out.println(total);

        // availableRoomList = hotel.getTotalAvailableRooms(5);
        // bookedRoomList = hotel.getTotalBookedRooms(5);

        // System.out.println("\nAvailable");
        // for (Integer total : availableRoomList)
        //     System.out.println(total);

        // System.out.println("\nBooked");
        // for (Integer total : bookedRoomList)
        //     System.out.println(total);

        System.out.println();
        System.out.println(hotel.checkDiscountApplicable("EWAN", 1, 5));
        System.out.println(hotel.checkDiscountApplicable("I_WORK_HERE", 1, 5));
        System.out.println(hotel.checkDiscountApplicable("STAY4_GET1", 1, 5));    
        System.out.println(hotel.checkDiscountApplicable("STAY4_GET1", 1, 4));
        System.out.println(hotel.checkDiscountApplicable("PAYDAY", 14, 15));
        System.out.println(hotel.checkDiscountApplicable("PAYDAY", 15, 16));
        System.out.println(hotel.checkDiscountApplicable("PAYDAY", 28, 30));
        System.out.println(hotel.checkDiscountApplicable("PAYDAY", 28, 31));

        ArrayList<Reservation> reservationList = hotel.getReservationList();

        System.out.println();
        for (Reservation reservation : reservationList)
            System.out.println(reservation.getGuestName());

        hotel.removeReservation(3);
        hotel.removeReservation(3);
        hotel.removeReservation(3);

        System.out.println();
        for (Reservation reservation : reservationList)
            System.out.println(reservation.getGuestName());

        roomIndex1 = hotel.getAvailableRoomType("Standard", 11, 16);
        roomIndex2 = hotel.getAvailableRoomType("Deluxe", 11, 16);
        roomIndex3 = hotel.getAvailableRoomType("Executive", 11, 16);

        System.out.println();
        System.out.println(roomIndex1 + " " + roomIndex2 + " " + roomIndex3);

        hotel.addReservation("Guest7", 11, 16, roomIndex1, "I_WORK_HERE");
        hotel.addReservation("Guest8", 11, 16, roomIndex2, "STAY4_GET1");
        hotel.addReservation("Guest9", 11, 16, roomIndex3, "PAYDAY");

        System.out.println(hotel.getRoomInfo(roomIndex1));
        System.out.println(hotel.getRoomInfo(roomIndex2));
        System.out.println(hotel.getRoomInfo(roomIndex3));

        System.out.println(hotel.getReservationInfo(3));
        System.out.println(hotel.getReservationInfo(4));
        System.out.println(hotel.getReservationInfo(5));





    }
}
