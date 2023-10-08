public class Square{

    public static void main(String[] args){
        int num = Integer.parseInt(args[0]);
        String [][] square = new String [num+1][num*2+2];
        // Calculate the matrix
        for(int i = 0; i < square.length; i++){
            for(int j = 0; j < square[i].length; j++){
                // First line
                if(i == 0){
                    if(j == 0||j == square[i].length-1){
                        square[i][j] = " ";
                    }else{
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
}
