import java.util.ArrayList;

/**
 * The Reservation class represents the reservation entity in the hotel reservation system.
 * It has the guest name, check-in and check-out dates, information on the reserved room,
 * discount code applied, total price breakdown, and total price.
 */
public class Reservation {
    
    /**
     * The name of the guest under the reservation object.
     * 
     */
    private String guestName;

    /**
     * The check-in date under the reservation object.
     * 
     */
    private int checkInDate;

    /**
     * The check-out date under the reservation object.
     * 
     */
    private int checkOutDate;

    /**
     * The room reserved under the reservation object.
     * 
     */
    private Room room;

    /**
     * The discount code applied on the reservation.
     * 
     */
    private String discountCode;

    /**
     * The list of price breakdown under the reservation object.
     * 
     */
    private ArrayList<Double> priceBreakdown;
    
    /**
     * The total price under the reservation object.
     * 
     */
    private double totalPrice;
    
    /**
     * Creates a new reservation object for the given guest name, check-in and check-out dates, and room to be reserved.
     * 
     * @param guestName the name of the guest under the reservation object.
     * @param checkInDate the starting date to be considered under the reservation object.
     * @param checkOutDate the ending date to be considered under the reservation object.
     * @param room the room to be reserved under the reservation object.
     */
    public Reservation(String guestName, int checkInDate, int checkOutDate, Room room) {
        int i;
        double price;

        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.discountCode = null;
        this.priceBreakdown = new ArrayList<Double>();
        this.totalPrice = 0;

        // Reserves the room
        room.setAvailability(checkInDate, checkOutDate, false);

        // Gets the price breakdown and total price
        for (i = checkInDate; i < checkOutDate; i++) {
            price = room.getPrice(i);
            this.priceBreakdown.add(price);
            this.totalPrice += price;
        }
    }

    /**
     * Creates a new reservation object for the given guest name, check-in and check-out dates, room to be reserved, and discount code to be applied.
     * Pre-condition: discount code to be applied is applicable.
     * 
     * @param guestName the name of the guest under the reservation object.
     * @param checkInDate the starting date to be considered under the reservation object.
     * @param checkOutDate the ending date to be considered under the reservation object.
     * @param room the room to be reserved under the reservation object.
     * @param discountCode the discount code to be applied on the reservation.
     */
    public Reservation(String guestName, int checkInDate, int checkOutDate, Room room, String discountCode) {
        int i;
        double price;

        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.discountCode = discountCode;
        this.priceBreakdown = new ArrayList<Double>();
        this.totalPrice = 0;

        // Reserves the room
        room.setAvailability(checkInDate, checkOutDate, false);

        // Gets the price breakdown and total price
        for (i = checkInDate; i < checkOutDate; i++) {
            price = room.getPrice(i);
            this.priceBreakdown.add(price);
            this.totalPrice += price;
        }

        // Applies the discount
        if (discountCode.equals("I_WORK_HERE"))
            this.totalPrice = this.totalPrice * 0.90;
        else if (discountCode.equals("STAY4_GET1")) {
            this.priceBreakdown.set(0, 0.0);
            this.totalPrice -= room.getPrice(checkInDate);
        }
        else if (discountCode.equals("PAYDAY"))
            this.totalPrice = this.totalPrice * 0.93;
    }

    /**
     * Returns the guest name under the reservation object.
     * 
     * @return the guest name under the reservation.
     */
    public String getGuestName() {
        return this.guestName;
    }

    /**
     * Returns the check-in date under the reservation object.
     * 
     * @return the check-in date under the reservation.
     */
    public int getCheckInDate() {
        return this.checkInDate;
    }

    /**
     * Returns the check-out date under the reservation object.
     * 
     * @return the check-out date under the reservation.
     */
    public int getCheckOutDate() {
        return this.checkOutDate;
    }

    /**
     * Returns the room object under the reservation object.
     * 
     * @return the room object under the reservation.
     */
    public Room getRoom() {
        return this.room;
    }

    /**
     * Returns the discount code applied on the reservation.
     * 
     * @return the discount code applied.
     */
    public String getDiscountCode() {
        return this.discountCode;
    }

    /**
     * Returns the list of price breakdown under the reservation object.
     * 
     * @return the price breakdown under the reservation.
     */
    public ArrayList<Double> getPriceBreakdown() {
        return this.priceBreakdown;
    }

    /**
     * Returns the total price for the whole reservation.
     * 
     * @return the total price for the reservation.
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }
}
