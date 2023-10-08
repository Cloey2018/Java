import java.util.Iterator;

public class Test {

    public static void main(String[] args) {
        House house1 = new House();
        System.out.print(house1.listFurniture());

        Chair c1 = new Chair(12, 10, 102);
        Table t1 = new Table(4, 5, "blue", 5);
        house1.insert(c1);
        house1.insert(t1);
//        System.out.print(house1.listFurniture());
//        System.out.print(house1.countChairs());

        Table t2 = new Table(4, 5, null, 4);
        Television tv1 = new Television(3, 60, 1080, 900);
        tv1.togglePowerState();
        house1.insert(t2);
        house1.insert(tv1);
        System.out.print(house1.listFurniture());
        System.out.print(house1.countChairs());

    }
}
