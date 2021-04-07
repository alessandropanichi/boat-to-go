package logic.view;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.mydatecell.MyCallback;

public class MainMenuView extends MainView {
	private static final String ERR_MESSAGE = "You have to fill this field!";
	private Text txtErrCity = new Text(ERR_MESSAGE);
	private Text txtErrCheckIn = new Text(ERR_MESSAGE);
	private Text txtErrCheckOut = new Text(ERR_MESSAGE);
	private Text txtErrPersonCount = new Text("You have to select the number of people!");

	private TextField txtFieldCity = new TextField();

	private DatePicker dPickerCheckIn = new DatePicker();
	private DatePicker dPickerCheckOut = new DatePicker();

	private Label lblPersonCount = new Label("0");

	private Button btnSearch = new Button("Search");
	private Button btnGetStarted = new Button("Get Started");
	private Button btnPlus = new Button("+");
	private Button btnMinus = new Button("-");

	@Override
	public void start(Stage primaryStage) throws Exception {

		super.start(primaryStage);

		HBox hBoxSearch = new HBox(20);
		hBoxSearch.setPadding(new Insets(20, 20, 20, 20));
		hBoxSearch.setPrefWidth(Double.MAX_VALUE);

		Label lblcity = new Label("Where do you want to go?");
		Label lblCheckIn = new Label("Enter Check-In");
		Label lblCheckOut = new Label("Enter Check-Out");
		Label lblPerson = new Label("Select how many people you are");

		txtFieldCity.setPromptText("e.g. Rome");
		dPickerCheckIn.setPromptText("Pick a date");
		dPickerCheckIn.setDayCellFactory(MyCallback.getDayCellFactory());
		dPickerCheckIn.setEditable(false);

		dPickerCheckOut.setPromptText("Pick a date");
		dPickerCheckOut.setDayCellFactory(MyCallback.getDayCellFactory());
		dPickerCheckOut.setEditable(false);

		this.txtErrCity.setFill(Color.RED);
		this.txtErrCity.setVisible(false);
		this.txtErrCheckIn.setFill(Color.RED);
		this.txtErrCheckIn.setVisible(false);
		this.txtErrCheckOut.setFill(Color.RED);
		this.txtErrCheckOut.setVisible(false);
		this.txtErrPersonCount.setFill(Color.RED);
		this.txtErrPersonCount.setVisible(false);

		GridPane gridPane = new GridPane();
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		gridPane.add(lblcity, 0, 0);
		gridPane.add(txtFieldCity, 0, 1);
		gridPane.add(lblCheckIn, 1, 0);
		gridPane.add(dPickerCheckIn, 1, 1);
		gridPane.add(lblCheckOut, 2, 0);
		gridPane.add(dPickerCheckOut, 2, 1);
		gridPane.add(lblPerson, 3, 0);
		gridPane.add(this.txtErrCity, 0, 2);
		gridPane.add(this.txtErrCheckIn, 1, 2);
		gridPane.add(this.txtErrCheckOut, 2, 2);
		gridPane.add(this.txtErrPersonCount, 3, 2);

		HBox personCountHBox = new HBox(10);
		personCountHBox.setAlignment(Pos.CENTER);
		personCountHBox.getChildren().addAll(btnPlus, lblPersonCount, btnMinus);

		gridPane.add(personCountHBox, 3, 1);

		this.disableMinusButton();
		this.btnSearch.setFont(Font.font(18));
		hBoxSearch.setAlignment(Pos.CENTER);
		btnSearch.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		hBoxSearch.getChildren().addAll(gridPane, btnSearch);

		VBox vBoxGetStarted = new VBox(10);
		vBoxGetStarted.setPadding(new Insets(20, 20, 20, 20));
		vBoxGetStarted.setAlignment(Pos.CENTER);

		Text txtGetStarted = new Text(
				"Do you need a boat, but don't know where to find it?" + "\nDon't worry, we will find it for you!");
		txtGetStarted.setFont(Font.font("Arial", FontWeight.MEDIUM, 18));
		btnGetStarted.setFont(new Font(20));

		vBoxGetStarted.getChildren().addAll(txtGetStarted, btnGetStarted);

		VBox vBoxMain = new VBox(50);
		vBoxMain.setPadding(new Insets(20, 20, 20, 20));
		vBoxMain.getChildren().addAll(hBoxSearch, vBoxGetStarted);

		super.borderPane.setCenter(vBoxMain);

	}

	public void setVisibleErrCityField(boolean value) {
		this.txtErrCity.setVisible(value);
	}

	public void setVisibleErrCheckInField(boolean value) {
		this.txtErrCheckIn.setVisible(value);
	}

	public void setVisibleErrCheckOutField(boolean value) {
		this.txtErrCheckOut.setVisible(value);
	}

	public void setVisibleErrPersonCount(boolean value) {
		this.txtErrPersonCount.setVisible(value);
	}

	public void addSearchListener(EventHandler<ActionEvent> searchHandler) {
		this.btnSearch.setOnAction(searchHandler);
	}

	public void addGetStartedListener(EventHandler<ActionEvent> getStartedHandler) {
		this.btnGetStarted.setOnAction(getStartedHandler);
	}

	public void enableMinusButton() {
		this.btnMinus.setDisable(false);
	}

	public void disableMinusButton() {
		this.btnMinus.setDisable(true);
	}

	public void addMinusHanlder(EventHandler<ActionEvent> minusHandler) {
		this.btnMinus.setOnAction(minusHandler);
	}

	public void addPlusHanlder(EventHandler<ActionEvent> addHandler) {
		this.btnPlus.setOnAction(addHandler);
	}

	public String getPersonCount() {
		return this.lblPersonCount.getText();
	}

	public void setPersonCountText(String value) {
		this.lblPersonCount.setText(value);
	}

	public String getCityField() {
		return this.txtFieldCity.getText();
	}

	public void resetPersonCount() {
		this.lblPersonCount.setText("0");
	}

	public LocalDate getCheckOutDate() {
		return this.dPickerCheckOut.getValue();
	}

	public LocalDate getCheckInDate() {
		return this.dPickerCheckIn.getValue();
	}

}
