import java.text.DecimalFormat;

public class Chair extends Furniture{
    private boolean occupied = false;
    private double comfortRating;


    public Chair(int x, int y, double comfort){
        super(x, y);
        if (comfort < 0){
            comfortRating = 0;
        }
        else if (comfort > 100){
            comfortRating = 100;
        }else comfortRating = comfort;
    }


    @Override
    public String getName() {
        return "chair";
    }


    @Override
    public String display() {
        String ch_info = null;
        DecimalFormat df = new DecimalFormat("##0.0#####");
        ch_info = String.format("A chair with comfort rating %s", df.format(comfortRating));
        return ch_info;
    }


    public double getComfortRating(){
        return comfortRating;
    }


    public boolean isOccupied(){
        return occupied;
    }


    public void occupy(Ornament o){
        if (o != null){
            if (o.getValuation() >= 100) {
                occupied = true;
            }
        }
    }
}

