import com.sun.jdi.Value;

import java.io.*;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FlightScheduler {

  private static FlightScheduler instance;
  private String flight_info;
  private String location_info;
  private final HashMap<Integer, Flight> flights = new HashMap<Integer, Flight>();
  private final ArrayList<Flight> flights_list = new ArrayList<Flight>();
  private final HashMap<String, Location> locations = new HashMap<String, Location>();
  private String[] loc_name;
  public static final List<String> week_list = List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Saturday", "Sunday");
  public static final List<String> short_week = List.of("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");


  public static void main(String[] args) {
      instance = new FlightScheduler(args);
      instance.run();
  }

  public static FlightScheduler getInstance() {
      return instance;
  }

  public FlightScheduler(String[] args) {}

  public void run() {
      // Do not use System.exit() anywhere in your code,
      // otherwise it will also exit the auto test suite.
      // Also, do not use static attributes otherwise
      // they will maintain the same values between testcases.

      // START YOUR CODE HERE
    while(true){
      Scanner scan = new Scanner(System.in);
      String input = scan.nextLine();
      String [] command = input.split(" ");

      // Print out user command;
      System.out.printf("User: %s\n", input);

      // Handle user commands
      int returnValue = 0;
      if (command[0].equalsIgnoreCase("flight")){
        returnValue = flightCommand(command);
      }
      else if (command[0].equalsIgnoreCase("flights")){
        System.out.println("Flights");
        System.out.println(String.join("",Collections.nCopies(55,"-")));
        System.out.println("ID Departure Arrival Source --> Destination");
        System.out.println(String.join("",Collections.nCopies(55,"-")));
        returnValue = viewFlights(command);
      }
      else if (command[0].equalsIgnoreCase("location")){
        returnValue = locationCommand(command);
      }
      else if (command[0].equalsIgnoreCase("locations")){
        returnValue = viewLocations(command);
      }
      else if (command[0].equalsIgnoreCase("schedule")){
        returnValue = viewSchedule(command);
      }
      else if (command[0].equalsIgnoreCase("departures")){
        returnValue = viewDepartures(command);
      }
      else if (command[0].equalsIgnoreCase("arrivals")){
        returnValue = viewArrivals(command);
      }
      else if (command[0].equalsIgnoreCase("travel")){
        returnValue = travelCommand(command);
      }
      else if (command[0].equalsIgnoreCase("help")){
        returnValue = viewHelp();
      }
      else if (command[0].equalsIgnoreCase("exit")){
        System.out.println("User: exit\nApplication closed.");
        break;
      } else returnValue = -100; // Error 100 -- Invalid command: "Invalid command. Type 'help' for a list of commands."

        // status message handling
      if (returnValue > 0){
        normalStatus(returnValue, command);
      }else errorStatus(returnValue, command);
      System.out.println();

    }
  }


    // All commands start with "flight" will be handled below
  public int flightCommand(String[] command){
    int status = 0;
    if (command.length == 1){
      status = -12; // Error 12 -- Flight: no parameters given
    }
    else{
        // FLIGHT ADD <departure time> <from> <to> <capacity> - add a flight
      if (command[1].equalsIgnoreCase("add")){
        if (command.length >= 7){
          status = addFlight(command[2], command[3], command[4], command[5], command[6],0 );
        }else status = -8; // Error 8 -- Not enough command arguments given: Usage of flight add
      }
        // FLIGHT IMPORT <filename> - import/export flights to csv file
      else if (command[1].equalsIgnoreCase("import")){
        importFlights(command);
      }
        // FLIGHT EXPORT <filename> - import/export flights to csv file
      else if (command[1].equalsIgnoreCase("export")){
        exportFlights(command);
      }
        // Flight <id> commands
      else{
        if (isValidInteger(command[1])){
            // pass flight id to f_id
          int f_id = Integer.parseInt(command[1]);
          if (flights.containsKey(f_id)){
              // FLIGHT <id> BOOK <num>
            if (command[2].equalsIgnoreCase("book")){
              if (isValidInteger(command[3])){
                int book_need = Integer.parseInt(command[3]);
                if (book_need > 0){
                  if (flights.get(f_id).isFull()){
                    status = -10; // Error 10 -- Capacity is Full: "Flight is now full."
                  }else{ // flight is not full
                    if (book_need + flights.get(f_id).getBookNum() >= flights.get(f_id).getCapacity()){
                      status = 60; // Status 60 -- Will be full after booked: status 6 + Error 10
                    }else status = 6; // Status 6: Booked message
                  }
                }else status = -9; // Error 9 -- Not positive: "Invalid number of passengers to book."
              }else status = -9; // Error 9 -- Not a integer: "Invalid number of passengers to book."
            }
              // FLIGHT <id> REMOVE
            else if (command[2].equalsIgnoreCase("remove")){
              status = 7; // Status 7: Print the removed flight information
            }
              // FLIGHT <id> RESET
            else if (command[2].equalsIgnoreCase("reset")){
              status = 8; // Status 8: Print the reset flight information
            }
              // FLIGHT <id>
            else status = 5; // Status 5: Information about this flight.
          }else status = -11; // Error 11 -- Does not exist in the database: "Invalid Flight ID."
        }else status = -11; // Error 11 -- Not a number: "Invalid Flight ID."
      }
    }
    return status;
  }


    // All commands start with "flights" will be handled below
  public int viewFlights(String[] command){
    if (flights.size() == 0){
      return -15; // Error 15 -- No flights in database: "(None)"
    }else{
      // Store one copy of flights in flights database for easily sorting
      flights.forEach((key, value)->{
        flights_list.add(value);
      });
      // Sort flights by departure time
      Collections.sort(flights_list, new Comparator<Flight>() {
        @Override
        public int compare(Flight o1, Flight o2) {
          String [] o1_time = o1.getDepTime().split(" ");
          String [] o2_time = o2.getDepTime().split(" ");
          return dateToMinute(o1_time[0], o1_time[1]).compareTo(dateToMinute(o2_time[0], o2_time[1]));
        }
      });
      return 1; // Status 1: Print out all the flights information in database
    }
  }


    // All commands start with "location" will be handled below
  public int locationCommand(String[] command){
    return 0;
  }


    // All commands start with "locations" will be handled below
  public int viewLocations(String[] command){
    if (locations.size() == 0){
      return -17;
    }
    loc_name = locations.keySet().toArray(new String[0]);
    Arrays.sort(loc_name, String.CASE_INSENSITIVE_ORDER);
    return 9;
  }


    // All commands start with "schedule" will be handled below
  public int viewSchedule(String[] command){
    return 0;
  }


    // All commands start with "departures" will be handled below
  public int viewDepartures(String[] command){
    return 0;
  }


   // All commands start with "arrivals" will be handled below
  public int viewArrivals(String[] command){
    return 0;
  }


    // All commands start with "travel" will be handled below
  public int travelCommand(String[] command){
    return 0;
  }


    // Print help information of this program
  public int viewHelp(){
    FileInputStream fis = null;
    try {
      File file = new File("help.txt");
      fis = new FileInputStream(file);
      byte[] b = new byte[(int)file.length()];
      while (fis.read(b) != -1) {
      }
      System.out.println(new String(b));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  public void normalStatus(int nStatus, String[] command){
    switch (nStatus) {
      case 1:
        for (Flight flight : flights_list) {
          int i_id = flight.getFlightID();
          String shortDep = shortWeekday(flight.getDepTime());
          String shortArr = shortWeekday(flight.getDepTime());
          String i_start = flight.getSource();
          String i_end = flight.getDestination();
          System.out.printf("%4d %s   %s   %s --> %s\n", i_id, shortDep, shortArr, i_start, i_end);
        }
        break;
      case 2:
        System.out.println("Successfully added Flight " + flights.size());
        break;
      case 5:
        System.out.println(flights.get(Integer.parseInt(command[1])).toString());
        break;
      case 6:
        break;
      case 7:
        int f7_id = Integer.parseInt(command[1]);
        flights.remove(f7_id);
        String shortDep7 = shortWeekday(flights.get(f7_id).getDepTime());
        String f7_start = flights.get(f7_id).getSource();
        String f7_end = flights.get(f7_id).getDestination();
        System.out.printf("Removed Flight %d, %s %s --> %s, from the flight schedule.\n", f7_id, shortDep7, f7_start, f7_end);
        break;
      case 8:
        int f8_id = Integer.parseInt(command[1]);
        flights.get(f8_id).setBookNum(0);
        String shortDep8 = shortWeekday(flights.get(f8_id).getDepTime());
        String f8_start = flights.get(f8_id).getSource();
        String f8_end = flights.get(f8_id).getDestination();
        System.out.printf("Reset passengers booked to 0 for Flight %d, %s %s --> %s.\n", f8_id, shortDep8, f8_start, f8_end);
        break;
      case 9:
        System.out.print(loc_name[0]);
        for (int i = 1; i < loc_name.length; i++) {
          System.out.print(", " + loc_name[i]);
        }
        break;

      default:
        throw new IllegalStateException("Unexpected value: " + nStatus);
    }
  }


  public void errorStatus(int eStatus, String[] command){
    switch (eStatus) {
      case -1:
        System.out.println("Invalid departure time. Use the format <day_of_week> <hour:minute>, with 24h time.");
        break;
      case -2:
        System.out.println("Invalid starting location.");
        break;
      case -3:
        System.out.println("Invalid ending location.");
        break;
      case -4:
        System.out.println("Invalid positive integer capacity.");
        break;
      case -5:
        System.out.println("Source and destination cannot be the same place.");
        break;
      case -8:
        System.out.println("Usage: FLIGHT ADD <departure time> <from> <to> <capacity>\nExample: FLIGHT ADD Monday 18:00 Sydney Melbourne 120 ");
      case -9:
        System.out.println("Invalid number of passengers to book.");
        break;
      case -10:
        System.out.println("Flight is now full.");
        break;
      case -11:
        System.out.println("Invalid Flight ID.");
        break;
      case -12:
        System.out.println("Usage:\nFLIGHT <id> [BOOK/REMOVE/RESET] [num]\nFLIGHT ADD <departure time> <from> <to> <capacity>\nFLIGHT IMPORT/EXPORT <filename>");
        break;
      case -15:
        System.out.println("(None)");
        break;
      case -100:
        System.out.println("Invalid command. Type 'help' for a list of commands.");
        break;

      default:
        throw new IllegalStateException("Unexpected value: " + eStatus);
    }
  }


  // Add a flight to the database
  // handle error cases and return status negative if error
  // (different status codes for different messages)
  // do not print out anything in this function
  public int addFlight(String date1, String date2, String start, String end, String capacity, int booked) {
    int ret = 0;
    date1 = upperCapital(date1);
    start = upperCapital(start);
    end = upperCapital(end);
      // Invalid input handling
    boolean validCommand = false;
    while(!validCommand){
      if (!week_list.contains(date1) || isValidTime(date2)){
        ret = -1; // Error 1 -- Wrong format of time: Print the correct format of time
        break;
      }
      if (locations.size() == 0){
        ret = -15; // Error 15 -- No flights in database: "(None)"
        break;
      }else{
        if (locations.containsKey(start)) {
          ret = -2; // Error 2 -- Start in locations: "Invalid starting location."
          break;
        }
        if (locations.containsKey(end)){
          ret = -3; // Error 3 -- End in locations: "Invalid ending location."
          break;
        }
        if (Objects.equals(start, end)){
          ret = -5; // Error 5 -- Start and end are the same: "Source and destination cannot be the same place."
          break;
        }
      }
      if (isValidInteger(capacity)){
        if (Integer.parseInt(capacity) < 1){
          ret = -4; // Error 4 -- Not positive: "Invalid positive integer capacity."
          break;
        }
      }else{
        ret = -4;
        break; // Error 4 -- Not a Integer: "Invalid positive integer capacity."
      }

        // Type conversions of params to make sure the parsing process
        // Flight(int flightID, String depTime, Location source, Location destination, int capacity, int bookNum)
        // Create a new unchecked flight(flight conflict will be checked later
      int flightID;
      if (flights.size() == 0){
        flightID = 0;
      }else flightID = Collections.max(flights.keySet()) + 1;
      String depTime = date1 + " " + date2;
      Location source = locations.get(start);
      Location destination = locations.get(end);
      int capacityNum =  Integer.parseInt(capacity);
      Flight uncheckedFlight = new Flight(flightID, depTime, source, destination, capacityNum, booked);

      // Check if the flight has schedule conflict
        // Check flights conflicts in start
      if (isConflict(source, uncheckedFlight) == 0){
         // Check flights conflicts in end
        if ((isConflict(destination, uncheckedFlight) == 0)){
          ret = 2; // Status 2: Flight Add works successfully
            // Add valid flights in database
          flights.put(flightID, uncheckedFlight);
        }
        else ret = isConflict(destination, uncheckedFlight); // Error 6 or 7
      }else ret = isConflict(source, uncheckedFlight); // Error 6 or 7
      validCommand = true;
    }
    return ret;
  }

  // Add a location to the database
    // do not print out anything in this function
    // return negative numbers for error cases
  public int addLocation(String name, String lat, String lon, String demand) {
    int ret = 0;

    return ret;
  }

  //flight import <filename>
  public void importFlights(String[] command) {
    try {
      if (command.length < 3) throw new FileNotFoundException();
      BufferedReader br = new BufferedReader(new FileReader(new File(command[2])));
      String line;
      int count = 0;
      int err = 0;

      while ((line = br.readLine()) != null) {
        String[] lparts = line.split(",");
        if (lparts.length < 5) continue;
        String[] dparts = lparts[0].split(" ");
        if (dparts.length < 2) continue;
        int booked = 0;

        try {
          booked = Integer.parseInt(lparts[4]);

        } catch (NumberFormatException e) {
          continue;
        }

        int status = addFlight(dparts[0], dparts[1], lparts[1], lparts[2], lparts[3], booked);
        if (status < 0) {
          err++;
          continue;
        }
        count++;
      }
      br.close();
      System.out.println("Imported "+count+" flight"+(count!=1?"s":"")+".");
      if (err > 0) {
        if (err == 1) System.out.println("1 line was invalid.");
        else System.out.println(err+" lines were invalid.");
      }
    } catch (IOException e) {
      System.out.println("Error reading file.");
      return;
    }
  }

  //location import <filename>
  public void importLocations(String[] command) {
    try {
      if (command.length < 3) throw new FileNotFoundException();
      BufferedReader br = new BufferedReader(new FileReader(new File(command[2])));
      String line;
      int count = 0;
      int err = 0;

      while ((line = br.readLine()) != null) {
        String[] lparts = line.split(",");
        if (lparts.length < 4) continue;

        int status = addLocation(lparts[0], lparts[1], lparts[2], lparts[3]);
        if (status < 0) {
          err++;
          continue;
        }
        count++;
      }
      br.close();
      System.out.println("Imported "+count+" location"+(count!=1?"s":"")+".");
      if (err > 0) {
        if (err == 1) System.out.println("1 line was invalid.");
        else System.out.println(err+" lines were invalid.");
      }

    } catch (IOException e) {
      System.out.println("Error reading file.");
      return;
    }
  }

  public void exportFlights(String[] command) {
    return;
  }


  public void exportLocations(String[] command){
    return;
  }


    // Check the schedule conflict
  public static int isConflict(Location location, Flight f){
    if (location.hasRunwayDepartureSpace(f) == null){
      return -6; // Error 6 -- Has departure flights conflict in start or end: Conflicted flight info(id, departLoc, depTime)
    }
    else if (location.hasRunwayArrivalSpace(f) == null){
      return -7; // Scheduling conflict! This flight clashes with Flight <ID> arriving at <Location> on <DateTime>.
    }
    else return 0;
  }


    // Shorten the week day
  public static String shortWeekday(String fullDate){
    String [] dateList = fullDate.split(" ");
    String shortDay = short_week.get(week_list.indexOf(dateList[0]));
    return shortDay + " " + dateList[1];
  }

    // Check if the time format is valid or not
  public static boolean isValidTime(String checkTime){
    SimpleDateFormat df = new SimpleDateFormat("HH:mm");
    try {
      df.parse(checkTime);
      return true;
    } catch (ParseException e) {
      return false;
    }
  }


    // Check if the string can be converted into integer or not
  public static boolean isValidInteger(String checkInt){
    try {
      int cap = Integer.parseInt(checkInt);
      return true;
    }catch (NumberFormatException e) {
      return false;
    }
  }


    // Turn the first letter of string into upper case and the other letters into lower case
  public static String upperCapital(String name) {
    name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    return name;
  }

}
