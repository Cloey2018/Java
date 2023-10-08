import java.util.Arrays;
import java.util.ArrayList;

public class Garden{

  public static void main(String[] args){

    // public String testnum(int output, int expected){
    //   String t_num = "";
    //   String = "Expected: " + expected + " Output: " + output + "\n";
    //   return t_num;
    // }

    Pot p1 = new Pot("Square", 12);
		Pot p2 = new Pot("Circle", 12);
		Flower f = new Flower("yellow", "dandelion", 12, 23);
		Flower f2 = new Flower("white", "orchid", 2, 53.4);
		Flower f3 = new Flower("green", "parsely", 2, 53.4);
		Flower f4 = new Flower("orange", "tulip", 6, 53.4);
		Flower f5 = new Flower("red", "rose", 15, 53.4);
		Flower f6 = new Flower("yellow", "dandelion", 12, 23);
		Flower f7 = new Flower("white", "rose", 4, 53.4);
		Flower f8 = new Flower(null, "rose", 46, 34);
		Flower f9 = new Flower(null, null, 46, 34);
		p2.insert(f);
		p2.insert(f2);
		p2.insert(f3);
		p2.insert(f4);
		p2.insert(f5);
		p2.insert(f6);
		p2.insert(f7);
		p2.insert(f8);
		p2.insert(f9);
		for (int i = 0; i < 10; i++) {
			p1.insert( new Flower("yellow", "dandelion", 12, 23));
		}

		Pot np = Pot.combine(p1, p2); // 5
    System.out.println(np.count()); // 5
    System.out.println(np.size()); // 5
    System.out.println(np.contains(null)); // false
    System.out.println(np.getShape()); // square
    System.out.println(np.countColour("yellow")); // 1
    System.out.println(np.countColour(null)); // 0


		// assertEquals(np.count(), 5);
		// assertEquals(np.size(), 5);
		// assertFalse(np.contains(null));
		// assertEquals(np.getShape(), p1.getShape());
		// assertEquals(np.countColour("yellow"), 1);
		// assertEquals(np.countColour("white"), 1);
		// assertEquals(np.countColour("green"), 1);
		// assertEquals(np.countColour("orange"), 1);
		// assertEquals(np.countColour("red"), 1);
		// assertEquals(np.countColour(null), 0);

		p1.water();
    System.out.println(np.averageAge()); // 0
		// assertEquals(np.averageAge(), 0, 0.0001);

    // Flower f = new Flower("yellow", "dandelion", 12, 23);
    // Flower f2 = new Flower("white", "orchid", 2, 53.4);
    // Flower f3 = new Flower("yELlow", "dandelion", 12, 23);
    // Flower f4 = null;
    // Flower f5 = new Flower("red", "rose", 1, 23);
    //
    // Pot p = new Pot("circle", 10);
    // p.insert(f);
    // p.insert(f2);
    // p.insert(f3);
    // p.insert(f4);
    // System.out.println(p.count()); // 3
    // System.out.println(p.replaceColour("yELloW", f5)); // 2
    // System.out.println(p.count()); // 3
    // System.out.println(p.replaceColour("white", null)); // 1
    // System.out.println(p.count()); // 2
    // System.out.println(p.replaceColour(null, null)); // 0
    // System.out.println(p.count()); // 2
    // System.out.println(p.replaceColour(null, f5)); // 0
    // System.out.println(p.count()); // 2

		// assertEquals(p2.averageAge(), 0,0.0001);
		// assertEquals(p2.countColour("green"), 2);


    // System.out.println(p.count());
    // System.out.println(p.view());

     // String[] colours = new String[]{"yellow", "white", "red", "yellow", "yellow", "red", null};
     // ArrayList<String> colour_list = new ArrayList<String> ();
     // for (int i = 0; i < colours.length; i ++) {
     //   if (!colour_list.contains(colours[i]) && null != colours[i] ){
     //     colour_list.add(colours[i]);
     //   }
     // }
     //
     // System.out.println(colour_list.size());
    // String colour_order = "";
    //
    // loop: for (int i = 0; i < colours.length; i++) {
    //   if (i > 0) {
    //     for (int j = 0; j < i; j++) {
    //       if (colours[i].equals(colours[j])) {
    //         continue loop;
    //       }
    //     }
    //   }
    //
    //   int colour_count = 0;
    //   for (int j = 0; j < colours.length; j++) {
    //     if (colours[i].equals(colours[j])) {
    //       colour_count ++;
    //     }
    //   }
    //   colour_order = colour_order + colour_count + "x " + colours[i] +"\n";
    //   System.out.println(colour_order);
    // }

    // Pot p2 = p.filterColour(null);
    // System.out.println(p.averageAge());
    // System.out.println(p2.averageAge());
    // System.out.println(p.count());
    // System.out.println(p2.count());
    //
    // p2.water();
    // System.out.println(p.averageAge());
    // System.out.println(p2.averageAge());
    //
    // Pot p3 = p.filterType(null);
    // System.out.println(p.averageAge());
    // System.out.println(p3.averageAge());
    // System.out.println(p.count());
    // System.out.println(p3.count());
    //
    // p3.water();
    // System.out.println(p.averageAge());
    // System.out.println(p3.averageAge());

    // String[] array = { "e", "b", "c", "a", "d" };
    // Arrays.sort(array, String.CASE_INSENSITIVE_ORDER);
    // String array_order = "";
    // for (int i = 0; i < array.length; i++) {
    //   array_order = array_order + array[i] + "\n";
    // }
    // System.out.println(array_order);


    // System.out.println(p.containsType("orchid")); // true
    // System.out.println(p.containsColour("White")); // true
    // System.out.println(p.containsColour(null)); // true
    // System.out.println(p.containsColour("yellow")); // true

    // Pot p = new Pot("circle", 23);
    // p.insert(new Flower("yellow", "dandelion", 12, 23));
    // System.out.println(p.size()); // 23
    // System.out.println(p.count()); // 1
    // p.insert(new Flower("white", "rose", 4, 53.4));
    // System.out.println(p.size()); // 23
    // System.out.println(p.count()); // 2
    //
    // System.out.println(p.insert(null));


    // Flower rose = new Flower("Red", "rOSE", 1, 4.55);
    // System.out.println(rose.getColour()); // red
    // System.out.println(rose.getSpecies()); // rose
    // rose.die();
    // System.out.println(rose.grow()); // false
    // System.out.println(rose.getAge()); // -1.0
    //
    // Flower lily = new Flower(null, "LILY", -1, -4.55);
    // System.out.println(lily.getColour()); // null
    // System.out.println(lily.getSpecies()); // lily
    // System.out.println(lily.getAge()); // 0.0
    // System.out.println(lily.isAlive()); // true
    // System.out.println(lily.getHeight()); // 0.0



    // System.out.println(rose.getAge()); // 0.0
    // System.out.println(rose.grow()); // true
    // System.out.println(rose.getAge()); // 1.0
    // System.out.println(rose.getHeight()); // age
    // System.out.println(rose.isAlive()); // true
    //
    // System.out.println(rose.grow()); // true
    // System.out.println(rose.getAge()); // 2.0
    // System.out.println(rose.getHeight()); // max
    // System.out.println(rose.isAlive()); // false
    //
    // System.out.println(rose.grow()); // false
    // System.out.println(rose.getAge()); // 2.0
    // System.out.println(rose.getHeight()); // max
    // System.out.println(rose.isAlive()); // false
    //
    // rose.die();
    // System.out.println(rose.isAlive()); // false
    // System.out.println(rose.getAge()); // -1.0
    // System.out.println(rose.grow()); // false
    // System.out.println(rose.getAge()); // -1.0
    // System.out.println(rose.getHeight()); // 0.0
    // System.out.println(rose.isAlive()); // false
    //System.out.println();
  }
}
