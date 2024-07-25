import java.util.ArrayList;

/**
 * The abstract Room class represents the base for the hotel rooms in the hotel reservation system.
 * It has a name, which is unique to the hotel, a base price, which is the base cost per night, 
 * a list of availability, which represents its availability across the entire month, and a list of
 * price rates, which represents its price rates across the entire month, with respect to the minimum 
 * and maximum date reservation value.
 */
public abstract class Room {

    /**
     * The minimum date reservation value for a room class.
     * 
     */
    public static final int MIN_DATE = 1;

    /**
     * The maximum date reservation value for a room class.
     * 
     */
    public static final int MAX_DATE = 31; 

    /**
     * The name of a room object.
     * 
     */
    private String name;

    /**
     * The base price of a room object.
     * 
     */
    private double basePrice;

    /**
     * The list of availability of a room object across the entire month.
     * 
     */
    private ArrayList<Boolean> availability;

    /**
     * The list of price rates of a room object across the entire month.
     * 
     */
    private ArrayList<Double> priceRate;

    /**
     * Initializes a new room object with the given name, base price, and price rates, 
     * and sets its availability to true across the entire month.
     * Pre-condition: name is unique from the other rooms in the system, and base price
     *                and price rates are within the valid values.
     * 
     * @param name the name of the room.
     * @param basePrice the base price of the room.
     * @param priceRate the list of room price rates across the entire month.
     */
    public Room(String name, double basePrice, ArrayList<Double> priceRate) {
        int i;

        this.name = name;
        this.basePrice = basePrice;
        this.priceRate = priceRate;

        this.availability = new ArrayList<Boolean>();

        for (i = MIN_DATE; i <= MAX_DATE; i++)
            this.availability.add(true);
    }

    /**
     * Returns the name of the room object.
     * 
     * @return the name of the room.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the price of the room object given the considered date.
     * 
     * @param date the date to be considered.
     * @return the price of the room.
     */
    public double getPrice(int date) {
        return this.basePrice * this.priceRate.get(date - 1);
    }

    /**
     * Returns the list of availability of the room object across the entire month.
     * 
     * @return the arraylist of availability of the room.
     */
    public ArrayList<Boolean> getAvailability() {
        return this.availability;
    }

    /**
     * Replaces and sets the first character of the name of the room object with given letter.
     * 
     * @param letter the letter to use to replace the first character of the name of the room.
     */
    public void setName(char letter) {
        this.name = this.name.replace(this.name.charAt(0), letter);
    }

    /**
     * Sets the base price of the room object to the given base price value.
     * Pre-condition: base price is greater than or equal to 100.0.
     * 
     * @param basePrice the base price to use to set the base price of the room.
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * Sets the availability status of the room object for a range of dates in a list based on the provided start and end dates.
     * Pre-condition: startDate is before endDate, and startDate and endDate is within the range of the minimum and maximum date reservation values.
     * 
     * @param startDate the starting date for which availability of the room object needs to be set.
     * @param endDate the ending date for which availability of the room object needs to be considered.
     * @param available the availability status to set the availability of the room object.
     */
    public void setAvailability(int startDate, int endDate, boolean available) {
        int i;

        // If the endDate is not 31 (MAX_DATE), the availability of the endDate should not be set to the given availability status.
        if (endDate < MAX_DATE) {
            for (i = startDate - 1; i < endDate - 1; i++) 
                this.availability.set(i, available);
        }
        // Otherwise, the availability of the endDate should be set to the given availability status.
        else {
            for (i = startDate - 1; i < endDate; i++) 
                this.availability.set(i, available);
        }
    }

    /**
     * Sets the price rates of the room with the given list of price rates.
     * Pre-condition: price rate values are within the range of 0.50 to 1.50.
     * 
     * @param priceRate the new list of price rates for the room.
     */
    public void setPriceRate(ArrayList<Double> priceRate) {
        this.priceRate = priceRate;
    }
}
