import java.text.DecimalFormat;

public class Ornament extends Furniture{
    protected double valuation;


    public Ornament(int x, int y, double value){
        super(x, y);
        if (value >= 0){
            valuation = value;
        }else System.out.println("The value of ornament must be positive.");
    }


    public String getName(){
        return "ornament";
    }


    public String display(){
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String or_info = null;
        or_info = String.format("An ornament worth $%s", df.format(valuation));
        return or_info;
    }

    public double getValuation(){
        return valuation;
    }
}

