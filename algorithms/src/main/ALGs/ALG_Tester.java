package ALGs;

/*
Evan: ALG 1, 2, 4
Maryam: ALG 3, 5, 6
docker compose build && docker compose up
*/

public class ALG_Tester {
    public static void main(String[] args) {
        int[][] stockList1 = {
                { 51, 7, 23, 57, 53, 16 },
                { 52, 10, 54, 84, 7, 19 },
                { 71, 16, 25, 19, 20, 39 },
                { 56, 72, 1, 81, 34, 21 },
                { 80, 82, 81, 73, 33, 12 },
                { 64, 66, 82, 14, 7, 3 }
        };// answer is 80 (row 4: 81 - 1)

        int[][] stockList2 = {
                { 51, 7, 23, 57, 53, 16 },
        };// answer is 50 (57 - 7)

        int[][] stockList3 = {
                { 100 }
        };// answer is 0

        int[][] stockList4 = {
                { 100 },
                { 80, 23, 13, 3 },
                { 5, 160 },
                { 3, 6, 8, 10, 11 }
        };// answer is 155 (row 3: 160 - 5)

        int[][] stockList5 = {
                { 15, 76, 85, 176, 192, 154, 131, 90, 36, 163 }, // = 177
                { 144, 116, 64, 78, 131, 14, 171, 193, 129, 66 }, // = 179
                { 97, 148, 134, 34, 119, 135, 1, 160, 78, 123 }, // = 159
                { 77, 23, 47, 149, 72, 78, 108, 10, 25, 33 }, // = 126
                { 116, 46, 123, 199, 75, 188, 103, 172, 153, 152 }, // = 113
                { 32, 134, 162, 23, 103, 17, 4, 113, 63, 104 }, // = 130
                { 170, 153, 53, 154, 136, 31, 94, 125, 127, 21 }, // = 101
                { 102, 167, 120, 131, 86, 55, 17, 13, 11, 139 }, // = 128
                { 56, 179, 60, 129, 152, 89, 131, 100, 193, 102 }, // = 123
                { 57, 100, 60, 142, 179, 68, 30, 11, 69, 34 } // = 122
        }; // answer is 179 (row 2: 193 - 14)

        //problem 2 tests ==================================================
        int[][] stockList6 = {
                { 68, 5, 1, 150 }, // = 149 (150 - 1), 82 (), 5
                { 105, 56, 101, 47 }, // = 45 (101 - 56)
                { 1, 42, 6, 137 }, // = 136, 41
                { 95, 99, 141, 50 } // = 46 (141-95)
        }; // answer is 195 for k = 2 (149 + 46)
           // answer is 235 for k = 3 (149(2-3) + 41(0-1) + 45(1-2))

        int[][] stockList7 = {
                { 1, 150, 1, 80 } // = 149 (150 - 1), 79 (80-1)
        }; // answer is 228 (149 + 79)

        int[][] stockList8 = {
                { 1, 150, 1, 80 }, // = 149 (150 - 1), 79 (80-1)
                { 90, 4, 1, 200 } // = 199 (200 - 1)
        }; // answer is 348 (149 + 199)

        int[][] stockList9 = {
                { 1, 150, 200, 300 }, // = 299
                { 1, 251, 1, 201 } // = 250 + 200 = 450
        }; // answer is 348 (149 + 199)

        int[][] stockList10 = {
                { 92, 85, 5, 75, 3 }, // = 70 (75 - 5)
                { 67, 7, 12, 100, 89 }, // 93 (100 - 7)
                { 14, 8, 55, 7, 60 }, // 53 (60 - 7)
                { 59, 73, 77, 63, 34 } // 14 = (73 - 59)
        }; // k = 2 answer is 146
           // k = 3 answer is 160

        int[][] copy = stockList10;
        int k = 2;
        int answer = 146;

        /* 
        // test printing out the matrix
        {
            System.out.println();
            System.out.println("test printing out the matrix");
            for (int i = 0; i < copy.length; i++) {
                for (int j = 0; j < copy[i].length; j++) {
                    if (j == copy[i].length - 1)
                        System.out.println(copy[i][j]);
                    else
                        System.out.print(copy[i][j] + ",  ");
                }
            }
        }
        */

        // For quick sending of the same matrix, send copy and change the instance of copy's matrix
        // Otherwise, you can send individual matrixes to certain algorithms.
        System.out.println("\n============================\n");
        // part4.part4();

        //System.out.println("|| Algorithm 1: " + ALG1.run_ALG1(copy));
        //System.out.println("|| Algorithm 2: " + ALG2.run_ALG2(copy));
        //System.out.println("|| Algorithm 3: " + ALG3.run_ALG3(copy));
        System.out.println("|| Algorithm 4: " + ALG4.run_ALG4(copy, k));
        //System.out.println("|| Algorithm 5: " + ALG5.run_ALG5(copy, k));
        //System.out.println("|| Algorithm 6: " + ALG6.run_ALG6(copy, k));

        System.out.println("|| Answer is  : " + answer);
        System.out.println("\n============================\n");
    }
}