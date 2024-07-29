import java.util.ArrayList;

/**
 * The ExecutiveRoom class is a sub class of the abstract Room class that represents the executive room
 * entity in the hotel reservation system.
 */
public class ExecutiveRoom extends Room{

    /**
     * The price multiplier for an executive room type object.
     * 
     */
    public static final double PRICE_MULTIPLIER = 1.35;

    /**
     * Creates a new executive room object with the given name, base price, and price rates.
     * 
     * @param name the name of the executive room.
     * @param basePrice the base price of a room.
     * @param priceRate the list of room price rates across the entire month.
     */
    public ExecutiveRoom(String name, double basePrice, ArrayList<Double> priceRate) {
        super(name, basePrice, priceRate);
    }

    /**
     * Returns the price of the executive room object given the considered date.
     * 
     * @param date the date to be considered.
     * @return the price of the executive room.
     */
    @Override
    public double getPrice(int date) {
        return super.getPrice(date) * PRICE_MULTIPLIER;
    }
}

