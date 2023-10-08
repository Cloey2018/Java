import java.text.DecimalFormat;

public class Painting extends Ornament{
    private String painter;
    private int year;


    public Painting(int x, int y, double value, String painter, int year){
        super(x, y, value);
        this.painter = painter;
        if (year > 0){
            this.year = year;
        }else System.out.println("The year of painting must be positive integer.");
    }


    @Override
    public String getName() {
        return "painting";
    }


    @Override
    public String display() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String pt_info = null;
        pt_info = String.format("A painting by %s painted in %d, worth $%s", painter, year, df.format(valuation));
        return pt_info;
    }


    public int getYear() {
        return year;
    }


    public String getPainter(){
        return painter;
    }
}

