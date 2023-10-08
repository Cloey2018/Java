public class Flight {
  private int flightID;
  private String depTime;
  private Location source;
  private Location destination;
  private int capacity;
  private double ticketPrice;
  private int bookNum;

  public Flight(int flightID, String depTime, Location source, Location destination, int capacity, int bookNum) {
    this.flightID = flightID;
    this.depTime = depTime;
    this.source = source;
    this.destination = destination;
    this.capacity = capacity;
    this.bookNum = bookNum;
  }


  public int getFlightID(){
    return flightID;
  }

    // Get the departure time
  public String getDepTime() {
    return depTime;
  }

  //get the number of minutes this flight takes (round to nearest whole number)
  public int getDuration() {
    double durationHour = this.getDistance()/720;
    return (int) Math.round(durationHour * 60);
  }


  public String calculateArrTime(String depTime, int duration){
    String [] dep = depTime.split(" ");
    String arrDate1;
    String arrDate2;
    int arrMinute = dateToMinute(dep[0], dep[1]) + duration;
    if (arrMinute >= 10080){
      arrMinute -= 10080;
    }
    arrDate1 = week_list.get((arrMinute/60)/24);
    arrDate2 = String.format("%02d", (arrMinute/60)%24) + ":" + String.format("%02d", arrMinute%60);

    return (arrDate1 + " " + arrDate2);
  }


  public String getArrTime() {
      String arrTime = this.calculateArrTime(this.depTime, this.getDuration());
      return arrTime;
    }


    public Location getSource(){
      return this.source;
    }


    public Location getDestination(){
      return this.destination;
    }

    public int getCapacity(){
      return capacity;
    }


    public int getBookNum(){
      return bookNum;
    }


    //implement the ticket price formula
    public double getTicketPrice() {
      double bookrate = (double) bookNum/capacity;
      double multiplier;
      if (bookrate > 0 && bookrate <= 0.5) {
        multiplier = -0.4 * bookrate + 1;
      }else if (bookrate <= 0.7) {
        multiplier = bookrate + 03;
      }else{
        multiplier = (0.2/Math.PI) * Math.atan(20 * bookrate) + 1;
      }
      ticketPrice = multiplier * (this.getDistance()/100) * (30+4*(destination.getDemand()-source.getDemand()));
      return ticketPrice;
    }


  public void setBookNum(int bookNum) {
    this.bookNum = bookNum;
  }

  //book the given number of passengers onto this flight, returning the total cost
    public double book(int num) {
      return 0.0;
    }

    //return whether or not this flight is full
    public boolean isFull() {
    boolean full = false;
      if (this.bookNum == this.capacity) {
        full = true;
      }
      return full;
	}

    //get the distance of this flight in km
    public double getDistance() {
      Location src = this.source;
      return src.distance(this.source, this.destination);
	}

    //get the layover time, in minutes, between two flights
    public static int layover(Flight x, Flight y) {
      return 0;
    }


    public String toString(){
      String f_string;
      f_string = "Flight " + this.flightID + "\n"
               + "Departure:      " + this.source + "\n"
               + "Arrival:        " + this.destination + "\n"
               + "Distance:       " + this.getDistance() + "\n"
               + "Duration:       " + this.getDuration() + "\n"
               + "Ticket Cost:    $" + this.ticketPrice + "\n"
               + "Passengers:     " + this.bookNum + "/" + this.capacity + "\n";
      return f_string;
    }
}
