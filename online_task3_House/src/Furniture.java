public abstract class Furniture {
    protected int x;
    protected int y;


    public Furniture(int x, int y){
        this.x = x;
        this.y = y;
    }


    public abstract String display ();


    public abstract String getName ();


    public int getX (){
        return x;
    }


    public int getY (){
        return y;
    }
}


