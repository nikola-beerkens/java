package taxi;

/**
 * Trains bring a number of passengers to a station in the Taxi simulation
 */
public class Train implements Runnable {
	private static final int TRANSPORTATION_TIME = 100;
	private static final int TRAIN_TRIPS = 10;
	private int nrOfPassengers;
	private final Station station;
	private int nrOfTrips = 0;

	public Train(Station station) {
		this.station = station;
		this.nrOfPassengers = 0;
	}

	/**
	 * Populate this train with number nrOfPassengers
	 *
	 * @param number the number of passengers of this train
	 */
	public void loadPassengers(int number) {
		nrOfPassengers = number;
	}

	/**
	 * empties this train and augment the number of Passengers at the station with
	 * this nrOfPassenegers
	 */
	public void unloadPassengers() {
		nrOfTrips += 1;
		station.enterStation(nrOfPassengers);
	}

	@Override
	public void run() {
		try {
			while (nrOfTrips < TRAIN_TRIPS) {
				station.waitForNoPassengers();
				loadPassengers(Util.getRandomNumber(Simulation.MIN_TRAVELLERS, Simulation.MAX_TRAVELLERS));
				Thread.sleep(TRANSPORTATION_TIME);
				unloadPassengers();
			}
			station.waitForNoPassengers();
			station.close();
		} catch (InterruptedException exception) {
			station.close();
			Thread.currentThread().interrupt();
		}
	}

	public void closeStation() {
		station.close();
	}

	public int getNrOfTrips() {
		return nrOfTrips;
	}
}