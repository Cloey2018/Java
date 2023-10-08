public class Circle{

  public static void main(String[] args){
    int num = Integer.parseInt(args[0]);
    String [][] circle = new String [num*2+1][num*4+1];
    double eps = 0;
    if(num <=2){
      eps = 0.4;
    }else{
      eps = 0.4999;
    }

    for(int i = 0; i < circle.length; i++){
      for(int j = 0; j < circle[i].length; j++){
        double cir_state = Math.sqrt(4*Math.pow((i-num),2) + Math.pow((j-2*num),2));
        //long cir_state = Math.round(Math.sqrt(4*Math.pow((i-num),2) + Math.pow((j-2*num),2)));

        if(cir_state <= 2*(num+eps) && cir_state >= 2*(num+eps-1)){
          circle[i][j] = "X";
        }
        else if(cir_state > 2*(num+eps) && j > 2*num){
          circle[i][j] = "";
        }
        else{
          circle[i][j] = " ";
        }
      }
    }

    // Print circle matrix
    for(int j = 0; j < circle.length; j++){
        for(int k = 0; k < circle[j].length; k++){
            System.out.print(circle[j][k]);
        }
        System.out.println();
    }
  }
}
