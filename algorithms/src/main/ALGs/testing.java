package ALGs;

import java.util.*;
import java.io.*;

public class testing {
    public static int[][] timeAlg() {
        try {
        File file = new File("algorithms/src/main/ALGs/matrix.txt");
        Scanner scanner = new Scanner(file);
        String lineOne = scanner.nextLine();
        String[] lineOneParts = lineOne.split("\\s+");
        int m = Integer.valueOf(lineOneParts[0]);
        int n = Integer.valueOf(lineOneParts[1]);
        int[][] newMatrix = new int[m][n];
        for (int i = 0; i < m; i++){
            String temp = scanner.nextLine();
            String[] tempParts = temp.split("\\s+");

            for (int j = 0; j < n; j++){
                newMatrix[i][j] = Integer.valueOf(tempParts[j]);
                //System.out.print(newMatrix[i][j]);
            }
        }
        scanner.close();
        return newMatrix;
    }
    catch (FileNotFoundException e) {
        System.out.println("File did not open properly.");
    }
    int[][] errorM = new int[0][0];
    return errorM;
    }
}
