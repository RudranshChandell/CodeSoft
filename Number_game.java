import java.util.*;
public class Number_game {
    public static Scanner sc = new Scanner(System.in);
    public static void game() {
        Scanner sc = new Scanner(System.in);
        int number_of_attempt = 0;
        boolean hasfound = false;
        int total_number_of_attempt = 5;
        while (!hasfound && number_of_attempt < total_number_of_attempt) {
            Random random = new Random();
            int rand_num = random.nextInt(100);
            System.out.println("Guess The number.");
            int num = sc.nextInt();
            if (num > rand_num)
                System.out.println("Number is too High");
            else if (num < rand_num)
                System.out.println("Number is too Low");
            else {
                System.out.println("Correct !!! YAY,You won the Game");
                hasfound = true;
            }
            number_of_attempt++;}
            if (hasfound == false) {
                System.out.println("Sorry! You lose.\n Type y to yes and n to No");
            }
        }


        public static void main (String[]args){
            String line = "Hello, This game is a number guessing game." +
                    "The only attempt you got is 5." +
                    "Well,game begin." +
                    "Type y to play or anything  to refuse";
            System.out.println(line);
            boolean game_on=true;
            while(game_on){
            if(sc.nextLine().equals("y")){
                game();
            }
            else{
                game_on=false;
            }

        }
    }
}