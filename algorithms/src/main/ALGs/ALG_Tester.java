package ALGs;

/*
Evan: ALG 1, 2, 4
Maryam: ALG 3, 5, 6
docker compose build && docker compose up
*/

public class ALG_Tester {
    public static void main(String[] args) {
        int[][] stockList1 = {
            {51,7,23,57,53,16},
            {52,10,54,84,7,19},
            {71,16,25,19,20,39},
            {56,72,1,81,34,21},
            {80,82,81,73,33,12},
            {64,66,82,14,7,3}
        };// answer is 80 (row 4: 81 - 1)

        int[][] stockList2 = {
            {51,7,23,57,53,16},
        };// answer is 50 (57 - 7)

        int[][] stockList3 = {
            {100}
        };// answer is 0

        int[][] stockList4 = {
            { 100 },
            {80, 23, 13, 3},
            {5, 160},
            {3, 6, 8, 10, 11}
        };// answer is 155 (row 3: 160 - 5)

        int[][] stockList5 = {
            { 15,76,85,176,192,154,131,90,36,163 }, // = 177
            { 144,116,64,78,131,14,171,193,129,66 }, // = 179
            { 97,148,134,34,119,135,1,160,78,123 }, // = 159
            { 77,23,47,149,72,78,108,10,25,33 }, // = 126
            { 116,46,123,199,75,188,103,172,153,152 }, // = 113
            { 32,134,162,23,103,17,4,113,63,104 }, // = 130
            { 170,153,53,154,136,31,94,125,127,21 }, // = 101
            { 102,167,120,131,86,55,17,13,11,139 }, // = 128
            { 56,179,60,129,152,89,131,100,193,102 }, // = 123
            { 57,100,60,142,179,68,30,11,69,34 } // = 122
        }; // answer is 179 (row 2: 193 - 14)

        int[][] copy = stockList5;

        // For quick sending of the same matrix, send copy and change the instance of copy's matrix
        // Otherwise, you can send individual matrixes to certain algorithms.
        System.out.println("============================");
        System.out.println(ALG1.run_ALG1(copy));
        System.out.println(ALG2.run_ALG2(copy));
        System.out.println(ALG3.run_ALG3(copy));
        System.out.println(ALG4.run_ALG4(copy));
        System.out.println(ALG5.run_ALG5(copy));
        System.out.println(ALG6.run_ALG6(copy));
        System.out.println("============================");
    }
}