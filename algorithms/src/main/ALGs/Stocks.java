package ALGs;

public class Stocks {
    public static void main(String[] args){
        switch(args[1]){
            case "1":
                // Run ALG1
                ALG1.task1();
                break;
            case "2":
                ALG2.task2();
                break;
            case "3a":
                ALG3.task3a();
                break;
            case "3b":
                ALG3.task3b();
                break;
            case "4":
                ALG4.task4();
                break;
            case "5":
                ALG5.task5();
                break;
            case "6":
                ALG6.task6();
                break;
            case "7":
                ALG7.task7();
                break;
            case "8":
                ALG8.task8();
                break;
            case "9":
                ALG9.task9();
                break;
            default:
                System.exit(0);
        }
    }
}
