package logic;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.controller.MainMenuController;
import logic.model.BookBoatShopController;
import logic.model.dao.CreateDatabase;
import logic.view.BookBoatShopListView;
import logic.view.BookingView;
import logic.view.BoatShopView;
import logic.view.MainMenuView;
import logic.view.ManageBoatShopListView;
import logic.view.ManageBoatListView;
import logic.view.UserProfileView;

/**
 * Main class of the program. The application starts from here.
 * This class manage the switching between views.
 */
public class Main extends Application{
	
	private static final Logger LOGGER = Logger.getLogger( Main.class.getName() );
	
	/**
	 * Reference to the main menu.
	 */
	private MainMenuView mainMenuView;
	
	/**
	 * Reference to the user profile view.
	 */
	private UserProfileView userProfileView;
	
	/**
	 * Reference to the list view.
	 */
	private BookBoatShopListView bookBoatShopListView;
	
	/**
	 * Reference to boat shop view.
	 */
	private BoatShopView boatShopView;
	
	/**
	 * Reference to booking view.
	 */
	private BookingView bookingView;

	private ManageBoatShopListView manageBoatShopListView;
	
	private ManageBoatListView manageBoatListView;
	
	/**
	 * The primary stage of the javaFX application.
	 */
	private static Stage primaryStage;
	
	/**
	 * Reference to instance of this class.
	 */
	private static Main instance = null;
	
	/**
	 * Constructor of this class. It initialize every view.
	 */
	public Main() {		
		
		this.mainMenuView = new MainMenuView();
		this.bookBoatShopListView = new BookBoatShopListView();
		this.boatShopView = new BoatShopView();
		this.bookingView = new BookingView();
		this.userProfileView = new UserProfileView();
		this.manageBoatShopListView = new ManageBoatShopListView();
		this.manageBoatListView = new ManageBoatListView();
	}
	
	/**
	 * The application starts from here.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CreateDatabase.createTables();
		launch(args);

	}

	/**
	 * start methods. It call the start of the main menu and creates the controller of the main menu.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		this.setStage(primaryStage);
		mainMenuView.start(Main.primaryStage);
		
		new MainMenuController(this.mainMenuView, BookBoatShopController.getInstance());
		
	}
	
	public synchronized void setStage(Stage primaryStage) {
		Main.primaryStage = primaryStage;
	}
	
	/**
	 * Change the view to user profile view.
	 * 
	 * @throws Exception
	 */
	public void changeToUserProfileView()  {
		
		try {
			this.userProfileView.start(primaryStage);
		} catch (Exception e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
		}
		
	}
	
	/**
	 * Return the User profile view.
	 * 
	 * @return user profile view.
	 */
	public UserProfileView getUserProfileView() {
		
		return this.userProfileView;
		
	}
	
	public void changeToMainMenuView()  {
		
		try {
			this.mainMenuView.start(primaryStage);
		} catch (Exception e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
		}
		
	}
	
	/**
	 * Change the view to BookBoatShopListView.
	 * 
	 * @throws Exception
	 */
	public void changeToBookBoatShopListView() {
		
		try {
			this.bookBoatShopListView.start(primaryStage);
		} catch (Exception e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
		}
		
	}
	
	/**
	 * Change the view to BoatShopView.
	 * 
	 * @throws Exception
	 */
	public void changeToBoatShopView() {
		
		try {
			this.boatShopView.start(primaryStage);
		} catch (Exception e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
		}
		
	}
	
	/**
	 * Change the view to BookingView.
	 * 
	 * @throws Exception
	 */
	public void changeToBookingView() {
		
		try {
			this.bookingView.start(primaryStage);
		} catch (Exception e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
		}
		
	}
	
	/**
	 * Get Instance method.
	 * 
	 * @return the only instance of this class.
	 */
	public static synchronized  Main getInstance() {
		
		if(Main.instance == null) 
			
			Main.instance = new Main();
		
		return Main.instance;
		
	}
	
	/**
	 * Get method.
	 * 
	 * @return MainMenuView.
	 */
	public MainMenuView getMainMenuView() { return this.mainMenuView; }
	
	/**
	 * Get method.
	 * 
	 * @return	BookHotelListView.
	 */
	public BookBoatShopListView getBookBoatShopListView() { return this.bookBoatShopListView; }
	
	/**
	 * Get method.
	 * 
	 * @return BoatShopView.
	 */
	public BoatShopView getBoatShopView() { return this.boatShopView; }
	
	/**
	 * Get method.
	 * 
	 * @return BookingView.
	 */
	public BookingView getBookingView() { return this.bookingView; }
	
	
	
	/**
	 * @throws Exception
	 */
	
	public void changeToManageBoatShopListView()  {
		try {
			this.manageBoatShopListView.start(primaryStage);
		} catch (Exception e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
		}
	
		
	}
	
	public ManageBoatShopListView getManageBoatShopListView() {return this.manageBoatShopListView;}
	
	
	public void changeToManageBoatListView() {
		try {
			this.manageBoatListView.start(primaryStage);
		} catch (Exception e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
		}
	
		
	}
	
	public ManageBoatListView getManageBoatListView() {return this.manageBoatListView;}
	
}
