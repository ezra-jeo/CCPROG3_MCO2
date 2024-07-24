import java.util.ArrayList;

public class ReservationTest {
    public static void main(String[] args) {
        double basePrice = 500;
        ArrayList<Double> priceRate = new ArrayList<Double>();
        ArrayList<Room> roomList = new ArrayList<Room>();

        for (int i = 0; i < 31; i++) {
            priceRate.add(1.0);
        }

        StandardRoom sRoom = new StandardRoom("S1", basePrice, priceRate);
        DeluxeRoom dRoom = new DeluxeRoom("D1", basePrice, priceRate);
        ExecutiveRoom eRoom = new ExecutiveRoom("E1", basePrice, priceRate);

        roomList.add(sRoom);
        roomList.add(dRoom);
        roomList.add(eRoom);

        for (Room room : roomList)
            System.out.println(room.getName() + " : " + room.getPrice(1));

        System.out.println();
        basePrice = 1000;

        for (Room room : roomList)
            room.setBasePrice(basePrice);

        for (Room room : roomList)
            System.out.println(room.getName() + " : " + room.getPrice(1));

        System.out.println();
        priceRate.set(0, 0.5);

        for (Room room : roomList)
            room.setPriceRate(priceRate);

        for (Room room : roomList)
            System.out.println(room.getName() + " : " + room.getPrice(1));

        System.out.println();
        ArrayList<Double> priceBreakdown = new ArrayList<Double>();
        ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
        Reservation reservation1 = new Reservation("Guest1", 1, 5, sRoom);
        Reservation reservation2 = new Reservation("GUEST2", 6, 10, sRoom, "I_WORK_HERE");
        Reservation reservation3 = new Reservation("Guest3", 6, 11, dRoom, "STAY4_GET1");
        Reservation reservation4 = new Reservation("Guest4", 15, 16, eRoom, "PAYDAY");

        reservationList.add(reservation1);
        reservationList.add(reservation2);
        reservationList.add(reservation3);
        reservationList.add(reservation4);

        for (Reservation reservation : reservationList) {
            System.out.println(reservation.getGuestName());
            priceBreakdown = reservation.getPriceBreakdown();
            for (Double price : priceBreakdown)
                System.out.println(price);
            System.out.println(reservation.getTotalPrice());
            System.out.println();
        }
    }
}

