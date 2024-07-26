import java.util.ArrayList;

/**
 * The StandardRoom class is a sub class of the abstract Room class that represents the standard room
 * entity in the hotel reservation system.
 */
public class StandardRoom extends Room {

    /**
     * Creates a new standard room object with the given name, base price, and price rates.
     * 
     * @param name the name of the standard room.
     * @param basePrice the base price of a room.
     * @param priceRate the list of room price rates across the entire month.
     */
    public StandardRoom(String name, double basePrice, ArrayList<Double> priceRate) {
        super(name, basePrice, priceRate);
    }
}
