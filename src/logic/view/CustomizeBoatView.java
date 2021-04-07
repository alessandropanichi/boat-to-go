package logic.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomizeBoatView extends Application {

	private TextField txtSize = new TextField();

	private Label lblToiletsCount = new Label("0");
	private Label lblSeatsCount = new Label("0");

	private Button btnSave = new Button("Save");
	private Button btnSaveAndAdd = new Button("Save and add");

	private Button btnPlus = new Button("+");
	private Button btnMinus = new Button("-");

	private Button btnPlus1 = new Button("+");
	private Button btnMinus1 = new Button("-");

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Boat To Go");

		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(20, 20, 20, 20));

		HBox hBoxTitle = new HBox();
		hBoxTitle.setAlignment(Pos.CENTER_LEFT);

		Text title = new Text("Boat To Go");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
		hBoxTitle.getChildren().add(title);

		HBox hBoxTop = new HBox();
		HBox.setHgrow(hBoxTitle, Priority.ALWAYS);
		hBoxTitle.setMaxWidth(Double.MAX_VALUE);
		hBoxTop.getChildren().addAll(hBoxTitle);

		HBox hBoxSave = new HBox(20);
		hBoxSave.setPadding(new Insets(20, 20, 20, 20));
		hBoxSave.setPrefWidth(Double.MAX_VALUE);

		HBox hBoxSaveAndAdd = new HBox(20);
		hBoxSaveAndAdd.setPadding(new Insets(20, 20, 20, 20));
		hBoxSaveAndAdd.setPrefWidth(Double.MAX_VALUE);

		Label lblSize = new Label("Size:");

		Label lblSeats = new Label("How many seats are available?");
		Label lblToilets = new Label("How many toilettes are available?");

		GridPane gridPane = new GridPane();
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		gridPane.add(lblSize, 0, 0);
		gridPane.add(txtSize, 0, 1);
		gridPane.add(lblSeats, 1, 0);
		gridPane.add(lblToilets, 2, 0);

		HBox toiletsCountHBox = new HBox(10);
		toiletsCountHBox.setAlignment(Pos.CENTER);
		toiletsCountHBox.getChildren().addAll(btnPlus1, lblToiletsCount, btnMinus1);

		gridPane.add(toiletsCountHBox, 2, 1);

		HBox seatsCountHBox = new HBox(10);
		seatsCountHBox.setAlignment(Pos.CENTER);
		seatsCountHBox.getChildren().addAll(btnPlus, lblSeatsCount, btnMinus);

		gridPane.add(seatsCountHBox, 1, 1);

		this.disableMinusButton();
		this.disableMinusButton1();
		this.btnSave.setFont(Font.font(18));
		hBoxSave.setAlignment(Pos.CENTER);
		btnSave.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		hBoxSave.getChildren().addAll(gridPane, btnSave, btnSaveAndAdd);

		VBox vBoxMain = new VBox(50);
		vBoxMain.setPadding(new Insets(20, 20, 20, 20));
		vBoxMain.getChildren().addAll(hBoxSave, hBoxSaveAndAdd);

		borderPane.setTop(hBoxTop);
		borderPane.setCenter(vBoxMain);

		Scene scene = new Scene(borderPane, 1200, 800);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public void disableMinusButton() {

		this.btnMinus.setDisable(true);

	}

	public void disableMinusButton1() {

		this.btnMinus1.setDisable(true);

	}

	public void enableMinusButton() {

		this.btnMinus.setDisable(false);

	}

	public void enableMinusButton1() {

		this.btnMinus1.setDisable(false);

	}

	public void addPlusHanlder(EventHandler<ActionEvent> addHandler) {

		this.btnPlus.setOnAction(addHandler);

	}

	public void addMinusHanlder(EventHandler<ActionEvent> minusHandler) {

		this.btnMinus.setOnAction(minusHandler);

	}

	public void addPlusHanlder1(EventHandler<ActionEvent> addHandler1) {

		this.btnPlus1.setOnAction(addHandler1);

	}

	public void addMinusHanlder1(EventHandler<ActionEvent> minusHandler1) {

		this.btnMinus1.setOnAction(minusHandler1);

	}

	public void addSaveListener(EventHandler<ActionEvent> saveHandler) {

		this.btnSave.setOnAction(saveHandler);

	}

	public void addSaveAndAddListener(EventHandler<ActionEvent> saveAndAddHandler) {

		this.btnSaveAndAdd.setOnAction(saveAndAddHandler);

	}
}
