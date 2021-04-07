package logic.view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.bean.BoatBean;

public class BoatShopView extends MainView {

	private Button btnBack = new Button("Back");
	private Button btnBook = new Button("Book");

	private Label name = new Label();
	private Label address = new Label();
	private Label information = new Label();

	private VBox vBoxLeft;

	private List<BoatSelector> boatSelectors;

	private Text txtErr = new Text("You have to select seats that \ncan contain how much people you are.");

	@Override
	public void start(Stage primaryStage) throws Exception {
		super.start(primaryStage);

		HBox hBoxCenter = new HBox(10);
		hBoxCenter.setPadding(new Insets(0, 20, 20, 20));
		this.name.setFont(Font.font(20));
		this.address.setFont(Font.font(20));
		this.information.setFont(Font.font(20));
		hBoxCenter.getChildren().addAll(this.name, this.address, this.information);

		this.vBoxLeft = new VBox(10);
		this.txtErr.setFill(Color.RED);
		this.txtErr.setVisible(false);
		vBoxLeft.getChildren().addAll(this.btnBack, this.txtErr);

		HBox hBoxBottom = new HBox(10);
		hBoxBottom.setAlignment(Pos.CENTER_RIGHT);
		hBoxBottom.getChildren().add(this.btnBook);

		borderPane.setLeft(vBoxLeft);
		borderPane.setCenter(hBoxCenter);
		borderPane.setBottom(hBoxBottom);

	}

	public void addBackHandler(EventHandler<ActionEvent> backHandler) {
		this.btnBack.setOnAction(backHandler);
	}

	public void addBookHandler(EventHandler<ActionEvent> bookHandler) {
		this.btnBook.setOnAction(bookHandler);
	}

	public void setErrVisible(boolean value) {
		this.txtErr.setVisible(value);
	}

	public void createBoatSelector(List<BoatBean> boatBeans, EventHandler<ActionEvent> plusHandler,
			EventHandler<ActionEvent> minusHandler) {

		this.boatSelectors = new ArrayList<>();

		for (int i = 0; i < boatBeans.size(); i++) {

			BoatBean boatBean = boatBeans.get(i);

			this.boatSelectors.add(new BoatSelector(String.valueOf(boatBean.getSeats()),
					String.valueOf(boatBean.getAvailability()), i, plusHandler, minusHandler));

		}

		this.vBoxLeft.getChildren().addAll(this.boatSelectors);

	}

	public BoatSelector getBoatSelector(int index) {
		return this.boatSelectors.get(index);
	}

	public List<BoatSelector> getAllBoatSelectors() {
		return this.boatSelectors;
	}

	public void setName(String name) {
		this.name.setText(name);
	}

	public void setAddress(String address) {
		this.address.setText(address);
	}

	public void setInformation(String information) {
		this.information.setText(information);
	}

	public class BoatSelector extends HBox {

		private Label lblSeats = new Label();

		private Label lblAvailability = new Label();

		private Button btnPlus = new Button("+");

		private Label lblBoatChoise = new Label();

		private Button btnMinus = new Button("-");

		public BoatSelector(String seats, String availability, int index, EventHandler<ActionEvent> plusHandler,
				EventHandler<ActionEvent> minusHandler) {

			this.setSpacing(10);

			this.lblSeats.setText(seats);

			this.lblAvailability.setText(availability);

			this.lblBoatChoise.setText("0");

			Label lblStringSeats = new Label("#");

			Label lblStringAvailability = new Label("Availability :");

			this.getChildren().addAll(lblStringSeats, this.lblSeats, lblStringAvailability, this.lblAvailability,
					this.btnPlus, this.lblBoatChoise, this.btnMinus);

			this.btnMinus.setOnAction(minusHandler);

			this.btnPlus.setOnAction(plusHandler);

			this.btnMinus.setId(String.valueOf(index));

			this.btnPlus.setId(String.valueOf(index));

			this.disableMinusButton();

		}

		public String getBoatAvailability() {
			return this.lblAvailability.getText();
		}

		public void setBoatChoise(String choise) {
			this.lblBoatChoise.setText(choise);
		}

		public String getBoatChoise() {
			return this.lblBoatChoise.getText();
		}

		public String getNumberOfSeats() {
			return this.lblSeats.getText();
		}

		public void disableMinusButton() {
			this.btnMinus.setDisable(true);
		}

		public void disablePlusButton() {
			this.btnPlus.setDisable(true);
		}

		public void enableMinusButton() {
			this.btnMinus.setDisable(false);
		}

		public void enablePlusButton() {
			this.btnPlus.setDisable(false);
		}

	}

}
