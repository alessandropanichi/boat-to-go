package logic.model.bookingState;

import logic.bean.BookingBean;
import logic.model.Booking;
import logic.model.dao.BookingDAOImpl;

/**
 *         This class represent the accepted state. A booking can be accepted by
 *         the owner after its submit by the user.
 */
public class AcceptedState implements BookingState {

	@Override
	public void accept(Booking context) {
		/*
		 * This method is empty because a booking in accepted state remains in accepted
		 * state if you call accept operation.
		 */
	}

	@Override
	public void delete(Booking context) {

		context.setState(new DeletedState());

		BookingBean bean = new BookingBean();
		bean.setCheckIn(context.getCheckIn());
		bean.setCheckOut(context.getCheckOut());
		bean.setState(StateEnum.DELETED);

		new BookingDAOImpl().updateBooking(bean);

	}

	@Override
	public void resubmit(Booking context) {
		/*
		 * This method is empty because a booking accepted state cannot be resubmitted..
		 */
	}

	@Override
	public StateEnum getEnumValueOfState() {
		return StateEnum.ACCEPTED;
	}

}
