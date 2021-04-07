package logic.view;

import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.bean.BookingBean;
import logic.model.Person;

public class UserProfileView extends Application {

	private ScrollPane scrollPane = new ScrollPane();

	private Label lblUsername = new Label("Username");

	private Button btnMainMenu = new Button("Main Menu");

	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(20, 20, 20, 20));

		HBox hBoxTitle = new HBox();
		hBoxTitle.setAlignment(Pos.CENTER_LEFT);

		Text title = new Text("Boat To Go");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 28));

		Region regionCenterLeft = new Region();
		HBox.setHgrow(regionCenterLeft, Priority.ALWAYS);
		Region regionCenterRight = new Region();
		HBox.setHgrow(regionCenterRight, Priority.ALWAYS);
		hBoxTitle.getChildren().addAll(title, regionCenterLeft, btnMainMenu, regionCenterRight, lblUsername);

		borderPane.setTop(hBoxTitle);
		borderPane.setCenter(this.scrollPane);

		Scene scene = new Scene(borderPane, 1200, 800);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public void addMainMenuHandler(EventHandler<ActionEvent> handler) {

		btnMainMenu.setOnAction(handler);

	}

	public void setUsername(String username) {

		lblUsername.setText(username);

	}

	public void setBookings(List<BookingBean> bookingsList, EventHandler<ActionEvent> deleteHandler,
			EventHandler<ActionEvent> resubmitHandler) {

		VBox vBox = new VBox(10);

		for (BookingBean bean : bookingsList) {

			vBox.getChildren().add(new BookingBox(bean, deleteHandler, resubmitHandler));

		}

		this.scrollPane.setContent(vBox);
		this.scrollPane.setFitToWidth(true);

	}

	private class BookingBox extends HBox {

		private Label lblBoatShop = new Label();
		private Label lblCheckIn = new Label();
		private Label lblCheckOut = new Label();

		private Button btnDelete = new Button("Delete");
		private Button btnResubmit = new Button("Resubmit");

		public BookingBox(BookingBean bean, EventHandler<ActionEvent> deleteHandler,
				EventHandler<ActionEvent> resubmitHandler) {

			super(10);

			this.setPadding(new Insets(10, 10, 10, 10));

			GridPane gridPane = new GridPane();
			gridPane.setHgap(10);
			gridPane.setVgap(10);

			this.lblBoatShop.setText(bean.getBoatShop());
			this.lblCheckIn.setText(bean.getCheckIn().toString());
			this.lblCheckOut.setText(bean.getCheckOut().toString());

			gridPane.add(new Label("Boat Shop Booked"), 0, 0);
			gridPane.add(lblBoatShop, 0, 1);
			gridPane.add(new Label("Check-In"), 1, 0);
			gridPane.add(lblCheckIn, 1, 1);
			gridPane.add(new Label("Check-Out"), 2, 0);
			gridPane.add(lblCheckOut, 2, 1);

			GridPane peopleGrid = new GridPane();
			peopleGrid.setHgap(5);
			peopleGrid.setVgap(5);

			peopleGrid.add(new Label("First Name"), 0, 0);
			peopleGrid.add(new Label("Last Name"), 1, 0);

			int row = 1;

			for (Person person : bean.getPeople()) {
				peopleGrid.add(new Label(person.getName()), 0, row);
				peopleGrid.add(new Label(person.getLastname()), 1, row);
				row++;
			}

			VBox vBox = new VBox(10);
			vBox.getChildren().add(new Label("State: " + bean.getState()));

			switch (bean.getState()) {

			case SUBMITTED:
				this.btnDelete.setOnAction(deleteHandler);
				this.btnDelete.setId(String.valueOf(bean.getBookingId()));
				vBox.getChildren().add(this.btnDelete);
				break;
			case DELETED:
				this.btnResubmit.setOnAction(resubmitHandler);
				this.btnResubmit.setId(String.valueOf(bean.getBookingId()));
				vBox.getChildren().add(this.btnResubmit);
				break;
			default:
				break;

			}

			Region region = new Region();
			HBox.setHgrow(region, Priority.ALWAYS);

			this.getChildren().addAll(gridPane, peopleGrid, region, vBox);

		}

	}

}
