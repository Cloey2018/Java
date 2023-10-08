import java.util.Arrays;
import java.util.ArrayList;

public class Pot extends java.lang.Object{
  private Flower[] flowers;
  private int numberOfFlowers;
  private String shape;


  public Pot(String shape, int capacity){
    if (shape == null) {
      this.shape = shape;
    }else{this.shape = shape.toLowerCase();}

    if (capacity <= 0) {
      flowers = new Flower[0];
    }else{flowers = new Flower[capacity];}
  }


  public String getShape(){
    return shape;
  }


  public boolean insert(Flower f){
    numberOfFlowers = count();
    // Whether the flower was successfully inserted
    if (f != null) {
      if (numberOfFlowers < flowers.length) {
        flowers[numberOfFlowers] = f;
        return true;
      }else{
        return false;
      }
    }else{
      return false;
    }
  }


  public boolean contains(Flower f){
    numberOfFlowers = count();
    boolean f_in_pot = false;
    if (f != null) {
      for (int i = 0; i < numberOfFlowers; i++) {
        if (f.equals(flowers[i])) {
          f_in_pot = true;
        }
      }
    }else{
      if (numberOfFlowers < flowers.length) {
        f_in_pot = true;
      }
    }
    return f_in_pot;
  }


  public boolean containsType(String type){
    numberOfFlowers = count();
    boolean t_in_pot = false;
    // make sure all type is lower case
    if (type != null) {type = type.toLowerCase();}
    // Check is there is any flowers matches the given species
    for (int i = 0; i < numberOfFlowers; i++) {
      if (null == type) {
        if (null == flowers[i].getSpecies()) {
          t_in_pot = true;
        }
      }else if (type.equals(flowers[i].getSpecies())) {
        t_in_pot = true;
      }
    }
    return t_in_pot;
  }


  public boolean containsColour(String colour){
    numberOfFlowers = count();
    boolean c_in_pot = false;
    // make sure all colur is lower case
    if (null != colour) {colour = colour.toLowerCase();}
    // Check is there any flowers mathces the given colour
    for (int i = 0; i < numberOfFlowers; i++) {
      if (null == colour) {
        if (null == flowers[i].getColour()) {
          c_in_pot = true;
        }
      }else if (colour.equals(flowers[i].getColour())) {
        c_in_pot = true;
      }
    }
    return c_in_pot;
  }

  public int size(){
    return flowers.length;
  }


  public int count(){
    numberOfFlowers = 0;
    // How many flowers are there in this pot
    for (int i = 0; i < flowers.length; i++ ) {
      if (flowers[i] != null) {
        numberOfFlowers ++;
      }
    }
    return numberOfFlowers;
  }


  public int countType(String type){
    numberOfFlowers = count();
    int t_num = 0;
    // make sure all type is lower case
    if (type != null) {type = type.toLowerCase();}
    // Check is there is any flowers matches the given species
    for (int i = 0; i < numberOfFlowers; i++) {
      if (null == type) {
        if (null == flowers[i].getSpecies()) {
          t_num ++;
        }
      }else if (type.equals(flowers[i].getSpecies())) {
        t_num ++;
      }
    }
    return t_num;
  }


  public int countColour(String colour){
    numberOfFlowers = count();
    int c_num = 0;
    // make sure all colur is lower case
    if (null != colour) {colour = colour.toLowerCase();}
    // Check is there any flowers mathces the given colour
    for (int i = 0; i < numberOfFlowers; i++) {
      if (null == colour) {
        if (null == flowers[i].getColour()) {
          c_num ++;
        }
      }else if (colour.equals(flowers[i].getColour())) {
        c_num ++;
      }
    }
    return c_num;
  }


  public String view(){
    numberOfFlowers = count();
    int act_colour = numberOfFlowers - countColour(null);
    String[] colours = new String [act_colour];
    numberOfFlowers = count();
    String colour_order = "";
    // Store the colours in a new array
    int a = 0;
    for (int i = 0; i < numberOfFlowers; i++) {
      if (null != flowers[i].getColour()) {
        colours[a] = flowers[i].getColour();
        // System.out.println(flowers[i].getColour());
        // System.out.println(i);
        // System.out.println(colours[a]);
        // System.out.println(a);
        a ++;
      }
    }
    //System.out.println(act_colour);
    // Reorder the array colours according to alphabetical order
    Arrays.sort(colours, String.CASE_INSENSITIVE_ORDER);
    loop: for (int i = 0; i < colours.length; i++) {
      if (i > 0) {
        for (int j = 0; j < i; j++) {
          if (colours[i].equals(colours[j])) {
            continue loop;
          }
        }
      }

      int colour_count = 0;
      for (int j = 0; j < colours.length; j++) {
        if (colours[i].equals(colours[j])) {
          colour_count ++;
        }
      }
      colour_order = colour_order + colour_count + "x " + colours[i] +"\n";
      // System.out.println(colour_order);
    }
    return colour_order;
  }


  public void water(){
    numberOfFlowers = count();
    for (int i = 0; i < numberOfFlowers; i++) {
      flowers[i].growInside(this);
    }
  }


  public int rearrange(){
    numberOfFlowers = count();
    int die_num = 0;
    // Whether flowers is alive or not
    for (int i = 0; i < numberOfFlowers; i++) {
      if (!flowers[i].isAlive()) {
        flowers[i] = null;
        die_num ++;
      }
    }

    // new flowers array
    Flower[] flowers_copy = flowers.clone();
    int alive_num = 0;
    for (int i = 0; i < numberOfFlowers; i++) {
      flowers[i] = null;
      if (flowers_copy[i] != null) {
        flowers[alive_num] = flowers_copy[i];
        alive_num ++;
      }
    }

    return die_num;
  }


  public double averageAge(){
    numberOfFlowers = count();
    int alive_num = 0;
    double age_av = -1.0;
    double age_sum = 0.0;

    // whether the flower is alive or not
    if (numberOfFlowers > 0) { // the pot is not empty
      // Calculate the alive flowers and their age_sum
      for (int i = 0; i < numberOfFlowers; i++) {
        if (flowers[i].isAlive()) {
          age_sum += flowers[i].getAge();
          alive_num ++;
        }
      }
      if (alive_num > 0) { // there are alive flowers inside the pot
        age_av = age_sum/alive_num;
      } // else: the flowers are all dead
    } // else: the pot is empty
    return age_av;
  }


  public double averageHeight(){
    numberOfFlowers = count();
    int alive_num = 0;
    double height_av = -1.0;
    double height_sum = 0.0;

    // whether the flower is alive or not
    if (numberOfFlowers > 0) { // the pot is not empty
      // Calculate the alive flowers and their age_sum
      for (int i = 0; i < numberOfFlowers; i++) {
        if (flowers[i].isAlive()) {
          height_sum += flowers[i].getHeight();
          alive_num ++;
        }
      }
      if (alive_num > 0) { // there are alive flowers inside the pot
        height_av = height_sum/alive_num;
      } // else: the flowers are all dead
    } // else: the pot is empty
    return height_av;
  }


  public Pot filterType(String t){
    Pot pot_f_t = new Pot(this.getShape(), this.size());
    Flower flower_copy;
    numberOfFlowers = count();
    // make sure all type is lower case
    if (t != null) {t = t.toLowerCase();}
    // Check is there is any flowers matches the given species
    for (int i = 0; i < numberOfFlowers; i++) {
      if (null == t) {
        if (null == flowers[i].getSpecies()) {
          flower_copy = flowers[i].clone();
          pot_f_t.insert(flower_copy);
        }
      }else if (t.equals(flowers[i].getSpecies())) {
        flower_copy = flowers[i].clone();
        pot_f_t.insert(flower_copy);
      }
    }
    return pot_f_t;
  }


  public Pot filterColour(String t){
    Pot pot_f_t = new Pot(this.getShape(), this.size());
    Flower flower_copy;
    numberOfFlowers = count();
    // make sure all colur is lower case
    if (null != t) {t = t.toLowerCase();}
    // Check is there any flowers mathces the given colour
    for (int i = 0; i < numberOfFlowers; i++) {
      if (null == t) {
        if (null == flowers[i].getColour()) {
          flower_copy = flowers[i].clone();
          pot_f_t.insert(flower_copy);
        }
      }else if (t.equals(flowers[i].getColour())) {
        flower_copy = flowers[i].clone();
        pot_f_t.insert(flower_copy);
      }
    }
    return pot_f_t;
  }


  public int replace(Flower f1, Flower f2){
    int replace_num = 0;
    numberOfFlowers = count();
    //int test_num = 0;
    // Calculate the number of replacement
    if (null != f1) {
      // Replace f1 with f2_notnull or f2_null
      for (int i = 0; i < numberOfFlowers; i++) {
        if(f1.equals(flowers[i])){
          //System.out.println(i);
          replace_num ++;
          flowers[i] = null;
          //System.out.println(replace_num);
        }
      }
    }else if (null == f1 && null != f2) {
      // Fill the empty space with f2
      replace_num = flowers.length - numberOfFlowers;
    }

    // new flowers array
    Flower[] flowers_copy = flowers.clone();
    int alive_num = 0;
    for (int i = 0; i < numberOfFlowers; i++) {
      flowers[i] = null;
      if (flowers_copy[i] != null) {
        flowers[alive_num] = flowers_copy[i];
        alive_num ++;
      }
    }
    // insert f2_notnull into pot
    // this.rearrange();
    if (null != f2) {
      for (int i = 0; i < replace_num; i++) {
        this.insert(f2.clone());
      }
    }
    return replace_num;
  }


  public int replaceType(String type, Flower f){
    int replace_t_num = 0;
    numberOfFlowers = count();
    // Make sure type is lowercase
    if (null != type) {type = type.toLowerCase();}
      // Calculate the number of replacement
      for (int i = 0; i < numberOfFlowers; i++) {
        if (type == flowers[i].getSpecies()) {
          flowers[i] = null;
          replace_t_num ++;
        }
      }
    // new flowers array
    Flower[] flowers_copy = flowers.clone();
    int alive_num = 0;
    for (int i = 0; i < numberOfFlowers; i++) {
      flowers[i] = null;
      if (flowers_copy[i] != null) {
        flowers[alive_num] = flowers_copy[i];
        alive_num ++;
      }
    }

    // insert f2_notnull into pot
    // this.rearrange();
    if (null != f) {
      for (int i = 0; i < replace_t_num; i++) {
        this.insert(f.clone());
      }
    }
    return replace_t_num;
  }


  public int replaceColour(String colour, Flower f){
    int replace_c_num = 0;
    numberOfFlowers = count();
    // Make sure type is lowercase
    if (null != colour) {colour = colour.toLowerCase();}
      // Calculate the number of replacement
      for (int i = 0; i < numberOfFlowers; i++) {
        if (colour == flowers[i].getColour()) {
          flowers[i] = null;
          replace_c_num ++;
        }
      }

      // new flowers array
      Flower[] flowers_copy = flowers.clone();
      int alive_num = 0;
      for (int i = 0; i < numberOfFlowers; i++) {
        flowers[i] = null;
        if (flowers_copy[i] != null) {
          flowers[alive_num] = flowers_copy[i];
          alive_num ++;
        }
      }

      // insert f2_notnull into pot
      //this.rearrange();
      if (null != f) {
        for (int i = 0; i < replace_c_num; i++) {
          this.insert(f.clone());
        }
      }
    return replace_c_num;
  }


  public static Pot combine(Pot p1, Pot p2){
    //ArrayList<Flower> flowers_combined = new ArrayList<Flower> ();
    ArrayList<String> colour_list = new ArrayList<String> ();
    Pot p_combined;
    //int fc_count = 0;

    if (null == p1 && null == p2) {
      p_combined = null;
    }else if (null == p1 && null != p2) {
      p_combined = p2;
    }else if (null != p1 && null == p2) {
      p_combined = p1;
    }else{
      // Calculate the size of new pot
      for (int i = 0; i < p1.count(); i ++) {
        if (!colour_list.contains(p1.flowers[i].getColour())
        && null != p1.flowers[i].getColour()){
          colour_list.add(p1.flowers[i].getColour());
        }
      }
      for (int i = 0; i < p2.count(); i ++) {
        if (!colour_list.contains(p2.flowers[i].getColour())
        && null != p2.flowers[i].getColour()){
          colour_list.add(p2.flowers[i].getColour());
        }
      }
      // new Pot
      p_combined = new Pot(p1.getShape(), colour_list.size());
      // Insert flowers in first pot
      for (int i = 0; i < p1.count(); i++) {
        if (!p_combined.containsColour(p1.flowers[i].getColour())) {
          p_combined.insert(p1.flowers[i].clone());
          p1.flowers[i].setAge(-1.0);
        }
      }
      p1.rearrange();

      // Insert flowers in second pot
      for (int i = 0; i < p2.count(); i++) {
        if (!p_combined.containsColour(p2.flowers[i].getColour())) {
          p_combined.insert(p2.flowers[i].clone());
          p2.flowers[i].setAge(-1.0);
        }
      }
      p2.rearrange();
    }
    return p_combined;
  }

}
