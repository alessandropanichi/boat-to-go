package logic.model.bookingState;

import java.time.LocalDate;

import logic.bean.BookingBean;
import logic.model.Booking;
import logic.model.dao.BookingDAOImpl;

/**
 *         This class represent the submitted state in which a booking can be
 *         just after it's creation.
 */
public class SubmittedState implements BookingState {

	/**
	 * A booking in submitted state can be accepted by the owner of a rental
	 * shop.
	 */
	@Override
	public void accept(Booking context) {

		if (context.getCheckIn().isBefore(LocalDate.now()))
			context.setState(new AcceptedState());

	}

	/**
	 * A booking in submitted state can be deleted by both owner and user.
	 */
	@Override
	public void delete(Booking context) {

		context.setState(new DeletedState());
		BookingBean bean = new BookingBean();
		bean.setBookingId(context.getId());
		bean.setState(StateEnum.DELETED);

		new BookingDAOImpl().updateBooking(bean);

	}

	@Override
	public void resubmit(Booking context) {
		/*
		 * Nothing can happen if you call resubmit and the booking is in submitted
		 * state.
		 */
	}

	@Override
	public StateEnum getEnumValueOfState() {
		return StateEnum.SUBMITTED;
	}

}
