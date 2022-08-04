import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        System.out.println("Choose difficulty number:");
        System.out.println("1: Easy");
        System.out.println("2: Hard");

        Scanner scan = new Scanner(System.in);
        while(!scan.hasNext("[12]")){
            System.out.println("Wrong input. Choose again");
            scan.nextLine();
        }

        int i = scan.nextInt();
        Difficulty difficulty;
        if(i==1)    difficulty = Difficulty.EASY;
        else difficulty = Difficulty.HARD;
     
        MemoryGame m = new MemoryGame(difficulty);

        m.play();

        scan.close();
    }
    
}
