import java.util.ArrayList;
import java.util.Iterator;

public class House {
    private ArrayList<Furniture> furniture = new ArrayList<Furniture>();

    public String listFurniture(){
        StringBuilder houseInside = null;
        houseInside = new StringBuilder("This house contains:");
        if (!furniture.isEmpty()){
            for (Furniture f : furniture){
                houseInside.append("\n").append(f.display());
            }
        }
        return houseInside.toString();
    }


    public void insert(Furniture item){
        if (item != null){
            furniture.add(item);
        }
    }


    public boolean containsTelevision(){
        for (Furniture f : furniture){
            if (f.getName().equals("television")){
                return true;
            }
        }
        return false;
    }


    public int countChairs(){
        int chairNum = 0;
        for (Furniture f : furniture){
            if (f.getName().equals("chair")){
                chairNum ++;
            }else if (f.getName().equals("table")){
                Table t = (Table) f;
                Iterator it = t.iterator();
                while(it.hasNext()){
                    chairNum ++;
                    it.next();
                }
            }
        }
        return chairNum;
    }
}
