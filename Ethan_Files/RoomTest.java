import java.util.ArrayList;

public class RoomTest {
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
            System.out.println(room.getName() + " : " + room.getPrice(1));
    }
}
