package GeoHash;

import java.util.HashMap;
import java.util.Map;

public class MiniUber {

	public class Trip {
		public int id; // trip's id, primary key
		public int driver_id, rider_id; // foreign key
		public double lat, lng; // pick up location

		public Trip(int rider_id, double lat, double lng) {
		}
	}

	static class Helper {
		public static double get_distance(double lat1, double lng1, double lat2, double lng2) {
			return 1;
		}
	};

	class Driver {
		public int driverId;
		public double lat;
		public double lng;

		public Driver(int driverId, double lat, double lng) {
			this.driverId = driverId;
			this.lat = lat;
			this.lng = lng;
		}

		public void setLat(double lat) {
			this.lat = lat;
		}

		public void setLng(double lng) {
			this.lng = lng;
		}

		public double getLat() {
			return this.lat;
		}

		public double getLng() {
			return this.lng;
		}

		public int getId() {
			return this.driverId;
		}
	}

	// <Driver_id, Driver>
	private Map<Integer, Driver> drivers;
	// <Driver, Trip>
	private Map<Integer, Trip> trips;

	public MiniUber() {
		// initialize your data structure here.
		drivers = new HashMap<Integer, Driver>();
		trips = new HashMap<Integer, Trip>();
	}

	// @param driver_id an integer
	// @param lat, lng driver's location
	// return matched trip information if there have matched rider or null
	public Trip report(int driver_id, double lat, double lng) {
		if (drivers.containsKey(driver_id)) {
			Driver driver = drivers.get(driver_id);
			driver.setLat(lat);
			driver.setLng(lng);
		} else {
			Driver driver = new Driver(driver_id, lat, lng);
		}
		return trips.get(driver_id);
	}

	// @param rider_id an integer
	// @param lat, lng rider's location
	// return a trip
	public Trip request(int rider_id, double lat, double lng) {
		double minDis = Double.MAX_VALUE;
		int minDriverId = -1;
		for (Map.Entry<Integer, Driver> entry : drivers.entrySet()) {
			double dis = Helper.get_distance(entry.getValue().getLat(), entry.getValue().getLng(), lat, lng);
			if (dis < minDis && !trips.containsKey(entry.getKey())) {
				minDriverId = entry.getKey();
				minDis = dis;
			}
		}
		Trip trip = new Trip(rider_id, lat, lng);
		trip.driver_id = minDriverId;
		trip.id = minDriverId;
		trips.put(minDriverId, trip);
		return trip;
	}
}