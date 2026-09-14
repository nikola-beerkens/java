package taxi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that holds the number of persons arriving by train at the station and
 * waiting for a taxi
 */
public class Station {

	private int nrOfPassengersAtStation = 0;
	private int totalNrOfPassengers = 0;
	private boolean isClosed = false;
	private final Lock lock = new ReentrantLock();
	private final Condition passengers = lock.newCondition();
	private final Condition noPassengers = lock.newCondition();

	public void enterStation(int nrOfPassengers) {
		lock.lock();
		try {
			nrOfPassengersAtStation += nrOfPassengers;
			totalNrOfPassengers += nrOfPassengers;
			System.out.println(nrOfPassengers + " passengers arrived at station");
			passengers.signalAll();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Ask for nrOfPassengers Passengers to leave the station
	 *
	 * @param requestedNrOfPassengers
	 * @return number of passengers actually leaving
	 */
	public int leaveStation(int requestedNrOfPassengers) throws InterruptedException {
		lock.lockInterruptibly();
		try {
			while (nrOfPassengersAtStation == 0 && !isClosed) {
				passengers.await();
			}
			if (isClosed) {
				return 0;
			}
			int actuallyLeaving = Math.min(requestedNrOfPassengers, nrOfPassengersAtStation);
			nrOfPassengersAtStation -= actuallyLeaving;
			if (nrOfPassengersAtStation == 0) {
				noPassengers.signal();
			}
			return actuallyLeaving;
		} finally {
			lock.unlock();
		}
	}

	public void waitForNoPassengers() throws InterruptedException {
		lock.lockInterruptibly();
		try {
			while (nrOfPassengersAtStation > 0) {
				noPassengers.await();
			}
		} finally {
			lock.unlock();
		}
	}

	public int waitingPassengers() {
		lock.lock();
		try {
			return nrOfPassengersAtStation;
		} finally {
			lock.unlock();
		}
	}

	public void close() {
		lock.lock();
		try {
			isClosed = true;
			passengers.signalAll();
			noPassengers.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public boolean isClosed() {
		lock.lock();
		try {
			return isClosed;
		} finally {
			lock.unlock();
		}
	}

	public int getTotalNrOfPassengers() {
		lock.lock();
		try {
			return totalNrOfPassengers;
		} finally {
			lock.unlock();
		}
	}
}