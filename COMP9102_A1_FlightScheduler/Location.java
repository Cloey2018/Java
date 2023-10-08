import java.util.ArrayList;
import java.util.LinkedList;

public class Location {
	public static final double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
	private String name;
	private double latitude;
	private double longitude;
	private double demand;
	private ArrayList<String> arrList;
	private ArrayList<String> depList;

	public Location(String name, double lat, double lon, double demand) {
	this.name = name;
	latitude = lat;
	longitude = lon;
	this.demand = demand;
	}

    //Implement the Haversine formula - return value in kilometres
    public static double distance(Location l1, Location l2) {
		double latSin = Math.sin((l1.latitude - l2.latitude)*0.5);
		double lonSin = Math.sin((l1.longitude - l2.longitude)*0.5);

		double l = latSin * lonSin + (Math.cos(l1.latitude) * Math.cos(l2.latitude) * latSin * lonSin);
		return AVERAGE_RADIUS_OF_EARTH_KM * 2 * Math.atan2(Math.sqrt(l), Math.sqrt(1 - l));
    }

    public double getDemand(){
		return demand;
	}

    public void addArrival(Flight f) {
		arrList = new ArrayList<String>();
	}

	public void addDeparture(Flight f) {
		depList = new ArrayList<String>();
	}

	/**
	 * Check to see if Flight f can depart from this location.
	 * If there is a clash, the clashing flight string is returned, otherwise null is returned.
	 * A conflict is determined by if any other flights are arriving or departing at this location within an hour of this flight's departure time.
	 * @param f The flight to check.
	 * @return "Flight <id> [departing/arriving] from <name> on <clashingFlightTime>". Return null if there is no clash.
	 */
	public String hasRunwayDepartureSpace(Flight f) {
		return null;
    }

    /**
	 * Check to see if Flight f can arrive at this location.
	 * A conflict is determined by if any other flights are arriving or departing at this location within an hour of this flight's arrival time.
	 * @param f The flight to check.
	 * @return String representing the clashing flight, or null if there is no clash. Eg. "Flight <id> [departing/arriving] from <name> on <clashingFlightTime>"
	 */
	public String hasRunwayArrivalSpace(Flight f) {
		return null;
    }


    public static int dateToMinute(String weekday, String time){
		int minutes = 0;
		String [] timeList = time.split(":");
		minutes = (Integer.parseInt(timeList[0]) * 60) + Integer.parseInt(timeList[1]);

		for (int i = 0; i < week_list.size(); i ++){
			if (weekday.equals(week_list.get(i))){
				minutes += i * 24 * 60;
				break;
			}
		}
		return minutes;
	}


    public String toString(){
		String l_string;
		l_string = "Location:      " + this.name + "\n"
				+ "Latitude:     " + this.latitude + "\n"
				+ "Longitude:    " + this.longitude + "\n"
				+ "Demand:       " + this.demand + "\n";
		return l_string;
	}
}
