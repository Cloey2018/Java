public class Flower extends java.lang.Object{
  private String colour;
  private String species;
  private double age = 0;
  private int maxAge;
  private double maxHeight;

  public Flower(String colour, String species, int mAge, double mHeight){
    if (colour == null) {
      this.colour = colour;
    }else{this.colour = colour.toLowerCase();}

    if (species == null) {
      this.species = species;
    }else{this.species = species.toLowerCase();}

    if (maxAge < 0) {maxAge = 0;}else{maxAge = mAge;}
    if (maxHeight < 0) {maxHeight = 0.0;}else{maxHeight = mHeight;}
  }

  public void setAge(double agesetter){
    age = agesetter;
  }

  public boolean grow(){
    if (!isAlive()) {
      return false;
    }else{
      age ++;
      if (!isAlive()) {
        return false;
      }else{
        return true;
      }
    }
  }

  public boolean growInside(Pot p){
    double ageUnit = 1.0;
    int size = p.size();
    int count = p.count();
    if (!isAlive()) {
      return false;
    }else{
      if (size - count > 5) {
        ageUnit += 2.5;
        age += ageUnit;
      }else{
        ageUnit += 0.5*(size-count);
        age += ageUnit;
      }
      if (!isAlive()) {
        return false;
      }else{
        return true;
      }
    }
  }

  public boolean isAlive(){
    if (age < 0 || age > maxAge) {
      return false;
    }else{
      return true;
    }
  }

  public String getColour(){
    return colour;
  }

  public String getSpecies(){
    return species;
  }

  public double getAge(){
    return age;
  }

  public double getHeight(){
    double H = 0.0;
    if (maxHeight == 0.0) {
      return H;
    }else{
      if (age >= 0) {
        H = (2/Math.PI)*Math.atan(age/maxHeight)*maxHeight;
      }
      return H;
    }
  }

  public void die(){
    // flower dies due to external circumstances
    age = -1;
  }

  // Overrride equals()
  public boolean equals(Object o){
    // Drtermine memory address, whether the two Objects are same or not
    if (o == this) {
      return true;
    }
    // whether o is null or not
    if (o == null) {
      return false;
    }
    // Determine whether o is Flower or not
    if (o instanceof Flower) { // return true if o is Flower
      // Compulsory conversion from Object to Flower
      Flower f = (Flower) o;
      boolean colour_state = false;
      boolean species_state = false;
      // colour_state
      if (null == this.getColour()) {
        if (null == f.getColour()) {
          colour_state = true;
        }
      }else{
        colour_state = this.getColour().equals(f.getColour());
      }
      // species_state
      if (null == this.getSpecies()) {
        if (null == f.getSpecies()) {
          species_state = true;
        }
      }else{
        species_state = this.getSpecies().equals(f.getSpecies());
      }
      // Compare the attributes
      if (colour_state && species_state
      && Math.round(this.getAge())==Math.round(f.getAge())
      && (double) Math.round(Math.abs(this.getHeight()-f.getHeight())*1000)/1000 <= 0.001) {        
        return true;
      }
    }
    return false;
  }

  public Flower clone(){
    Flower f_copy;
    if (null == this) {
      f_copy = null;
    }else{
      f_copy = new Flower(this.getColour(), this.getSpecies(), this.maxAge, this.maxHeight);
      f_copy.setAge(this.getAge());
    }
    return f_copy;
  }

}
