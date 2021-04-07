package logic.model.bookingState;

import logic.model.Booking;

/**
 *         This class represent the inactive state, the last state of the
 *         booking life cycle.
 */
public class InactiveState implements BookingState {

	@Override
	public void accept(Booking context) {
		/*
		 * This method is empty because a booking in inactive state cannot perform any
		 * operation
		 */
	}

	@Override
	public void delete(Booking context) {
		/*
		 * This method is empty because a booking in inactive state cannot perform any
		 * operation
		 */
	}

	@Override
	public void resubmit(Booking context) {
		/*
		 * This method is empty because a booking in inactive state cannot perform any
		 * operation
		 */
	}

	@Override
	public StateEnum getEnumValueOfState() {
		return StateEnum.INACTIVE;
	}

}
