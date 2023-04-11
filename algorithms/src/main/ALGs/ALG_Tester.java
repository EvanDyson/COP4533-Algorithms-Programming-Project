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
               //day   1    2   3    4
       /*stock 1*/   { 68,  5,  1,   150 }, // = 149 (150 - 1)
             /*2*/   { 105, 56, 101, 47 }, // = 45 (101 - 56)
             /*3*/   { 1,   42, 6,   137 }, // = 136,     41 (42 - 1)
             /*4*/   { 95,  99, 141, 50 } // = 46 (141-95)
        }; // answer is 195 for k = 2 (149 + 46)
           // answer is 235 for k = 3 (41(0-1) + 45(1-2) + 149(2-3))
           /*
           first stock 3 buy 1 sell 2
           second stock 2 buy 2 sell 3
           third stock 1 buy 3 sell 4 
           3 1 2
           2 2 3
           1 3 4
            */

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
                { 14, 8, 55, 7, 60 }, // 53 (60 - 7)    53-8=47
                { 59, 73, 77, 63, 34 } // 14 = (73 - 59)
        }; // k = 2 answer is 146
           // k = 3 answer is 170

        int[][] stockList11 = {
            {10, 12, 15, 13, 11},
            {8, 9, 10, 11, 12},
            {11, 10, 12, 14, 13},
            {15, 14, 13, 16, 18}
        };

        int [][] stockList12 = {
            {5, 2, 6, 15}, // 2-6 = 4,  2-15 = 13,  6-15 = 9
            {1, 4, 2, 8},  // 1-4 = 3,  2-8 = 6
            {2, 5, 11, 12} // 2-5 = 3,  2-11 = 9,  5-11 = 6,  2-12 = 10
        };
        
        // Creating matrixes for timing ===============
        // 1 - 3
        //      need variable n and fixed m

        //      need variable m and fixed n

        // 4 - 6
        //      need variable n and fixed m and k
        //      need variable m and fixed n and k
        //      need variable k and fixed m and n

        // 7 - 9
        //      need variable n and fixed m and c
        //      need variable m and fixed n and c

        // 8 and 9
        //      need variable n and fixed m and c
        //      need variable m and fixed n and c


        int[][] copy = stockList10;
        int k = 10;
        int c = 3;



        // ===========================================
        // test printing out the matrix
        /*{
            System.out.println();
            System.out.println("test printing out the matrix");
            for (int i = 0; i < copy.length; i++) {
                for (int j = 0; j < copy[i].length; j++) {
                    if (j == copy[i].length - 1)
                        System.out.println(copy[i][j]);
                    else
                        System.out.print(copy[i][j] + ",\t");
                }
            }
        }*/
        
        // For quick sending of the same matrix, send copy and change the instance of copy's matrix
        // Otherwise, you can send individual matrixes to certain algorithms.
        System.out.println("\n============================");
        //testing.timeAlg();
        //System.out.println("|| Algorithm 1: " + ALG1.run_ALG1(testing.timeAlg()));
        // ALG1.task1();
        //System.out.println("|| Algorithm 2: " + ALG2.run_ALG2(testing.timeAlg()));
        // ALG2.task2();
        //System.out.println("|| Algorithm 3A: " + ALG3.run_ALG3A(testing.timeAlg()));
        //System.out.println("|| Algorithm 3B: " + ALG3.run_ALG3B(testing.timeAlg()));
        //ALG3.run_ALG3B(testing.timeAlg());
        // ALG3.task3();
        //System.out.println("|| Algorithm 4: " + ALG4.run_ALG4(testing.timeAlg(), k));
        // ALG4.task4();
        //System.out.println("|| Algorithm 5: " + ALG5.run_ALG5(testing.timeAlg(), k));
        // ALG5.task5();
        //System.out.println("|| Algorithm 6: " + ALG6.run_ALG6(testing.timeAlg(), k));
        // ALG6.task6();
        //System.out.println("|| Algorithm 7: " + ALG7.run_ALG7(testing.timeAlg(), c));
        // ALG7.task7();
        //System.out.println("|| Algorithm 8: " + ALG8.run_ALG8(testing.timeAlg(), c));
        // ALG7.task8();
        //System.out.println("|| Algorithm 9: " + ALG9.run_ALG9(testing.timeAlg(), c));
        // ALG7.task9();
        //System.out.println("|| Answer is " + answer);
        System.out.println("============================\n");
    }
}