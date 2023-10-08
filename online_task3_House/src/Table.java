import java.text.DecimalFormat;
import java.util.Iterator;

public class Table extends Furniture implements Iterable<Chair> {
    private Chair[] chairSet;
    private String tableclothColour;


    public Table(int x, int y, String colour, int chairs) {
        super(x, y);
        tableclothColour = colour;
        if (colour != null){
            if (chairs > 0) {
                chairSet = new Chair[chairs];
                for (int i = 0; i < chairs; i++) {
                    String i_comfort = colour + i;
                    Chair i_chair = new Chair(x, y, Math.abs(i_comfort.hashCode() % 101));
                    chairSet[i] = i_chair;
                }
            } else {
                chairSet = new Chair[0];
            }
        }
        else{
            if (chairs > 0) {
                chairSet = new Chair[chairs];
                for (int i = 0; i < chairs; i++) {
                    Chair i_chair = new Chair(x, y, 0.0);
                    chairSet[i] = i_chair;
                }
            } else {
                chairSet = new Chair[0];
            }
        }

    }


    @Override
    public String getName() {
        return "table";
    }


    @Override
    public String display() {
        StringBuilder t_info = null;
        DecimalFormat df = new DecimalFormat("##0.0#####");
        if (tableclothColour != null){
            t_info = new StringBuilder(String.format("A table with a %s tablecloth", tableclothColour));
        }else         t_info = new StringBuilder("A table with no tablecloth");
        if (chairSet.length != 0) {
            for (Chair i : chairSet) {
                String i_info = String.format(" - A chair with comfort rating %s", df.format(i.getComfortRating()));
                t_info.append("\n").append(i_info);
            }
        }
        return t_info.toString();
    }


    // iterator
    public Iterator<Chair> iterator() {
        return new MyIterator<Chair>(chairSet);
    }
}


class MyIterator<Chair> implements Iterator<Chair>{
    private int index = -1;
    private Chair[] chairs;
    public MyIterator(Chair[] chair_set){
        this.chairs = chair_set;
    }

    @Override
    public boolean hasNext() {
        return this.index+1 < chairs.length;
    }

    @Override
    public Chair next() {
        this.index ++;
        Chair nextElement = chairs[index];
        return nextElement;
    }
}

