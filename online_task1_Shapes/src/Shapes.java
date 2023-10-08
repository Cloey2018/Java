public class Shapes{

    public static void main(String[] args){
        String shapes = new String();
        int size = 0;

        try{
            shapes = args[0];
            size = Integer.parseInt(args[1]);
        }catch(NumberFormatException e){
            System.out.println("Invalid size - it must be a positive integer.");
            System.exit(0);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Usage: java Shapes <shape> <size>");
            System.exit(0);
        }

        if(size <= 0){
          System.out.println("Invalid size - it must be a positive integer.");
        }
        else{
          switch(shapes){

            case "square":
                //System.out.println("square");
                Square(size);
                break;
            case "triangle":
                //System.out.println("triangle");
                Triangle(size);
                break;
            case "rhombus":
                //System.out.println("rhombus");
                Rhombus(size);
                break;
            case "circle":
                //System.out.println("circle");
                Circle(size);
                break;
            default:
                System.out.println("Invalid shape, must be either square, triangle, circle or rhombus.");
          }
        }

    }

    // Square
    public static void Square(int num){
      String [][] square = new String [num+1][num*2+2];
      // Calculate the matrix
      for(int i = 0; i < square.length; i++){
          for(int j = 0; j < square[i].length; j++){
              // First line
              if(i == 0){
                  if(j == 0){
                    square[i][j] = " ";
                  }
                  else if(j == square[i].length-1){
                    square[i][j] = "";
                  }
                  else{
                    square[i][j] = "_";
                  }
              }
              // Last line
              else if(i == square.length-1){
                  if(j == 0||j == square[i].length-1){
                      square[i][j] = "|";
                  }else{
                      square[i][j] = "_";
                  }
              }
              // Middle
              else{
                  if(j == 0||j == square[i].length-1){
                      square[i][j] = "|";
                  }else{
                      square[i][j] = " ";
                  }
              }
          }
      }
      // Print square matrix
      for(int j = 0; j < square.length; j++){
          for(int k = 0; k < square[j].length; k++){
              System.out.print(square[j][k]);
          }
          System.out.println();
      }
    }
    // Triangle
    public static void Triangle(int num){
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
          else if(i < j-triangle[i].length/2){
            triangle[i][j] = "";
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

    // Rhombus
    public static void Rhombus(int num){
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
          else if(i > -j+col){
            rhombus[i][j] = "";
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

    // Circle
    public static void Circle(int num){
      String [][] circle = new String [num*2+1][num*4+1];
      double eps = 0;
      if(num <=2){
        eps = 0.4;
      }else{
        eps = 0.5;
      }

      for(int i = 0; i < circle.length; i++){
        for(int j = 0; j < circle[i].length; j++){
          double cir_state = Math.sqrt(4*Math.pow((i-num),2) + Math.pow((j-2*num),2));

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
