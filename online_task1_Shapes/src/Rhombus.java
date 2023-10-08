import java.lang.Math;

public class Rhombus{

  public static void main(String[] args){
    int num = Integer.parseInt(args[0]);
    int row = num + 2;
    int col = (int)Math.round(Math.sqrt(5)*(num+1))+row;
    String [][] rhombus = new String [row][col];

    for(int i = 0; i < row; i++){
      for(int j = 0; j < col; j++){
        // First line
        if(i == 0 && j > num){
          rhombus[i][j] = "_";
        }
        // Last line
        else if(i == row -1 && j > 0 && j <= (col-row)){
          rhombus[i][j] = "_";
        }
        // Middle
        else if(i == -j+row-1 || i == -j+col){
          rhombus[i][j] = "/";
        }
        else{
          rhombus[i][j] = " ";
        }
      }
    }

    // Print Rhombus matrix
    for(int j = 0; j < row; j++){
      for(int k = 0; k < col; k++){
        System.out.print(rhombus[j][k]);
      }
      System.out.println();
    }
  }
}
