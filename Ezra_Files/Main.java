/**
 * The Main class creates, instantiates, and executes the model-view-controller architecture of the hotel reservation system.
 * 
 * @author Ethan Axl S. Burayag
 * @author Ezra Jeonadab G. Del Rosario
 * 
 * The design of the user interface is recommended for Mac devices
 */
public class Main {

    /**
     * Driver method that runs the program.
     * 
     * @param args string array containing the command-line arguments passed.
     */
    public static void main(String[] args) {
        ReservationSystem rs = new ReservationSystem();
        ReservationSystemView view = new ReservationSystemView();   
        ViewHotelExtension viewHotel = new ViewHotelExtension();
        ManageHotelExtension manageHotel = new ManageHotelExtension();
        ReservationSystemController controller = new ReservationSystemController(rs, view, viewHotel, manageHotel);
        
    }
}
