import java.util.ArrayList;

/**
 * The DeluxeRoom class is a sub class of the abstract Room class that represents the deluxe room
 * entity in the hotel reservation system.
 */
public class DeluxeRoom extends Room{

    /**
     * The price multiplier for a deluxe room type object.
     * 
     */
    public static final double PRICE_MULTIPLIER = 1.20;

    /**
     * Creates a new deluxe room object with the given name, base price, and price rates.
     * 
     * @param name the name of the deluxe room.
     * @param basePrice the base price of a room.
     * @param priceRate the list of room price rates across the entire month.
     */
    public DeluxeRoom(String name, double basePrice, ArrayList<Double> priceRate) {
        super(name, basePrice, priceRate);
    }

    /**
     * Returns the price of the deluxe room object given the considered date.
     * 
     * @param date the date to be considered.
     * @return the price of the deluxe room.
     */
    @Override
    public double getPrice(int date) {
        return super.getPrice(date) * PRICE_MULTIPLIER;
    }
}
