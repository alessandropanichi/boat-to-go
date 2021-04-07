package logic.view;

import java.util.ArrayList;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.bean.BoatBean;


public class ManageBoatListView extends Application {

	private ScrollPane scrollPane = new ScrollPane();

	private Button btnAddBoat = new Button("AddBoat");
	private Button btnBack = new Button("Back");
	private Button btnBooking = new Button("Booking");

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(20, 20, 20, 20));

		HBox hBoxTitle = new HBox();
		hBoxTitle.setAlignment(Pos.CENTER_LEFT);

		Text title = new Text("Boat To Go");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
		hBoxTitle.getChildren().add(title);
		VBox vBoxLeft = new VBox(5);
		vBoxLeft.setPadding(new Insets(50, 20, 20, 0));
		vBoxLeft.getChildren().addAll(btnAddBoat, btnBooking, btnBack);
		
		HBox hBoxAddBoatShop = new HBox(10);
		hBoxAddBoatShop.setAlignment(Pos.BOTTOM_RIGHT);
		hBoxAddBoatShop.getChildren().addAll();

		HBox hBoxTop = new HBox();
		HBox.setHgrow(hBoxTitle, Priority.ALWAYS);
		hBoxTop.getChildren().addAll(hBoxTitle);
		hBoxTitle.setMaxWidth(Double.MAX_VALUE);
		

		borderPane.setLeft(vBoxLeft);
		borderPane.setTop(hBoxTop);
		
		borderPane.setCenter(this.scrollPane);
		
	

		Scene scene = new Scene(borderPane, 1200, 800);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public void populateView(List<BoatBean> list2, EventHandler<ActionEvent> buttonHandler,
			EventHandler<ActionEvent> buttonHandler1) {

		VBox vBox = new VBox(10);
		List<HBoxCell> list = new ArrayList<>();

		for (int i = 0; i < list2.size(); i++)
			list.add(new HBoxCell(list2.get(i).getId(), buttonHandler, buttonHandler1));

		vBox.getChildren().addAll(list);
		vBox.setMaxWidth(Double.MAX_VALUE);

		this.scrollPane.setContent(vBox);
		this.scrollPane.setFitToWidth(true);

	}

	public class HBoxCell extends HBox {

		private Label name = new Label();
		private Label address = new Label();
		private Button button = new Button();

		public HBoxCell(String name, EventHandler<ActionEvent> buttonHandler,
				EventHandler<ActionEvent> buttonHandler1) {

			super();

			this.name.setText(name);
			this.name.setMaxWidth(Double.MAX_VALUE);
			HBox.setHgrow(this.name, Priority.ALWAYS);

		

			this.button.setText("Delete");
			this.button.setId(name);
			this.button.setOnAction(buttonHandler);

			this.button.setText("More");
			this.button.setId(name);
			this.button.setOnAction(buttonHandler1);

			this.getChildren().addAll(this.name, this.address, this.button);

		}

		public HBoxCell(int id, EventHandler<ActionEvent> buttonHandler, EventHandler<ActionEvent> buttonHandler1) {
		}

		public String getName() {
			return this.name.getText();
		}

		public String getAddress() {
			return this.address.getText();
		}

	}

	public void addBackHandler(EventHandler<ActionEvent> handler) {

		btnBack.setOnAction(handler);

	}

}
