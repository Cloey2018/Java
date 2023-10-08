public class Triangle{

  public static void main(String[] args){
    int num = Integer.parseInt(args[0]);
    String [][] triangle = new String [num+1][num*2+2];

    for(int i = 0; i < triangle.length; i++){
      for(int j = 0; j < triangle[i].length; j++){
        // Last line
        if(i == triangle.length-1&&j != 0&&j != triangle[i].length-1){
          triangle[i][j] = "_";
        }
        // Right part
        else if (i == j-triangle[i].length/2) {
          triangle[i][j] = "\\";
        }
        // Left part
        else if (i == -j+num) {
          triangle[i][j] = "/";
        }
        // Spaces
        else{
            triangle[i][j] = " ";
        }
        }
    }
    // Print triangle matrix
    for(int j = 0; j < triangle.length; j++){
        for(int k = 0; k < triangle[j].length; k++){
            System.out.print(triangle[j][k]);
        }
        System.out.println();
    }
  }
}
