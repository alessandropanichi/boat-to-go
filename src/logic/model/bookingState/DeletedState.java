package logic.model.bookingState;

import java.time.LocalDate;

import logic.bean.BookingBean;
import logic.model.Booking;
import logic.model.dao.BookingDAOImpl;

/**
 *         This class represent the deleted state of a booking.
 */
public class DeletedState implements BookingState {

	@Override
	public void accept(Booking context) {
		/*
		 * This method is empty because a booking in deleted state cannot be accepted.
		 */
	}

	/**
	 * A deleted booking can't be deleted yet.
	 */
	@Override
	public void delete(Booking context) {
		/*
		 * This method is empty because a booking in deleted state can't be deleted yet.
		 */
	}

	/**
	 * A booking can be resubmitted if the check-in date is before today.
	 */
	@Override
	public void resubmit(Booking context) {

		if (context.getCheckIn().isAfter(LocalDate.now())) {
			context.setState(new SubmittedState());
			BookingBean bean = new BookingBean();
			bean.setBookingId(context.getId());
			bean.setState(StateEnum.SUBMITTED);

			new BookingDAOImpl().updateBooking(bean);
		}

	}

	@Override
	public StateEnum getEnumValueOfState() {
		return StateEnum.DELETED;
	}

}
