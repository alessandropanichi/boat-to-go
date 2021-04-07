package logic.mydatecell;

import java.time.LocalDate;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

/**
 *         This class provide the implementation of the Callback<T, T>
 *         interface. It needs to disable all the past days in a datepicker.
 * 
 *         Example: DatePicker picker = new DatePicker();
 *         picker.setDayCellFactory(MyCallback.getDayCellFactory);
 */
public class MyCallback implements Callback<DatePicker, DateCell> {

	@Override
	public DateCell call(final DatePicker param) {
		return new DateCell() {
			@Override
			public void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);

				if (item.isBefore(LocalDate.now())) {
					setDisable(true);

				}
			}
		};
	}

	public static MyCallback getDayCellFactory() {

		return new MyCallback();

	}

}
