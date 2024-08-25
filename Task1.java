import java.util.*;
import java.util.Random;

public class Task1 {
    public static void main(String[] args) {
        final String RESET = "\033[0m";
        final String RED = "\033[31m";
        final String PURPLE = "\033[35m";
        final String YELLOW = "\33[33m";
        Scanner sc = new Scanner(System.in);
        int tryouts = 10;
        int point = 0;
        int num = 0;
        Random random = new Random();
        boolean playAgain = true;
        System.out.println( PURPLE + "**********************************************************************************" + RESET);
        System.out.println("Rules : ");
        System.out.println( RED + "1. Guess your Number between 1 to 100.");
        System.out.println("2. Using Number lower than 1 and greater than 100 is strictly Prohibited.");
        System.out.println("3. You have only " + tryouts + " tryouts to win this game." + RESET);
        System.out.println("Point Distribution as follows : ");
        System.out.println("Try1:10\nTry2:9\nTry3:8\nTry4:7\nTry5:6\nTry6:5\nTry7:4\nTry8:3\nTry9:2\nTry10:1");
        System.out.println( PURPLE + "**********************************************************************************" + RESET);
        int round = 0;
        int score = 0;

        while (playAgain) {
            int randomNumber = random.nextInt(100) + 1;
            round++;
            System.out.println("Round " + round);
            boolean guess = false;
            for (int i = 1; i <= tryouts; i++) {
                System.out.println("tryout " + (i) + " Guess the Number : ");
                num = sc.nextInt();
                score++;
                if (num == randomNumber) {
                    guess = true;
                    System.out.println("You WON");
                    break;
                } else if (num > randomNumber) {
                    System.out.println("Too High");
                } else {
                    System.out.println("Too Low");
                }
            }
            if (num !=randomNumber) {
                score++;
            }
            switch (score) {
                case 1:
                    point = point + 10;
                    break;
                case 2:
                    point = point + 9;
                    break;
                case 3:
                    point = point + 8;
                    ;
                    break;
                case 4:
                    point = point + 7;
                    break;
                case 5:
                    point = point + 6;
                    break;
                case 6 :
                    point = point + 5;
                    break;
                case 7 :
                    point = point + 4;
                    break;
                case 8 :
                    point = point + 3;
                    break;
                case 9 :
                    point = point + 2;
                    break;
                case 10 :
                    point = point + 1;
                default:
                    point = point + 0;
            }
            if (guess == false) {
                System.out.println("You Lost the Game\n The Number is " + (randomNumber));
            }
            System.out.println("Do u wanna play Another game? (Yes / No) ");
            String mood = sc.next();
            if(mood.equalsIgnoreCase("Yes")){
                playAgain = true;
            }
            else{
                playAgain = false;
            }
            System.out.println("Thankyou for playing this game for entertainment!, Your Final Score is "  + point + "pts");
        }

    }
}