public class Television extends Furniture implements Electric{
    private boolean on = false;
    private int height;
    private int width;

    public Television (int x, int y, int width, int height){
        super(x, y);
        if (width > 0){
            this.width = width;
        }else System.out.println("The width of television must be a positive integer.");

        if (height > 0){
            this.height = height;
        }else System.out.println("The height of television must be a positive integer.");
    }

    public String display(){
        String tv_info = null;
        String off_string;
        if (on){
            off_string = "on";
        }else off_string = "off";
        tv_info = String.format("A %dx%d television in the %s state.", width, height, off_string);
        return tv_info;
    }


    public String getName(){
        return "television";
    }

    @Override
    public void togglePowerState (){
        if (!on) on = true;
        else on = false;
    }


    @Override
    public boolean getPowerState() {
        return on;
    }


    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
