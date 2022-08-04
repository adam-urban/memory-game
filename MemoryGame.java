import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class MemoryGame {
    /* Attributes */
    private int chances;
    private boolean end;
    private MatrixOfFields matrix;
    private String difficulty;



    /* Constructors */
    MemoryGame(Difficulty DIFFICULTY){
        ArrayList<String> listOfWords = new ArrayList<>();

        try {
            File f = new File("Words.txt");
            Scanner fReader = new Scanner(f);
            while(fReader.hasNextLine()){
                listOfWords.add(fReader.nextLine());
            }
            fReader.close();
            
        } catch (Exception e) {
            System.out.println("Brak pliku Words.txt");
            waitForEnter();
            System.exit(0);
        }
        
        this.difficulty = DIFFICULTY.difficulty;
        this.chances = DIFFICULTY.chances;
        this.end = false;
        String[] words = new String[DIFFICULTY.numberOfPairs];
        int i = DIFFICULTY.numberOfPairs-1;
        Random r = new Random();
        while(i>=0){
            int randomIndex = r.nextInt(listOfWords.size());
            String word = listOfWords.get(randomIndex);
            listOfWords.remove(randomIndex);
            words[i--] = word; 
        }
        matrix = new MatrixOfFields(words);
    }

    /* Methods */
    public void play(){
        String[] coordinates = new String[2];      // Stores coordinates of one turn ( to moves)
        
        while(!end && chances>0){
            /* two moves in for loop*/
            for(int i=0; i<2; i++){                                                 
                printMenu();
                String move = i==0? "first" : "second";
                System.out.println("Choose " + move + " coordinates eg.A1: ");
                
                coordinates[i] = PlayerMove();

                /* if fail to make coordinates break for loop */
                if(coordinates[i].isEmpty()){
                    System.out.println("Invalid move. You are loosing one chance");
                    waitForEnter();
                    this.chances--;
                    break;
                }

                setUnCover(coordinates[i]);

            }
            
            /* If coordninates are invalid continue */
            if(coordinates[0].isEmpty()) continue;
            if(coordinates[1].isEmpty()){
                setCover(coordinates[0]);   // if the second coorinates are empty, cover the first word
                continue;
            }

            printMenu();
            
            /* If words don't match cover then */
            if(!this.getWord(coordinates[0]).equals(this.getWord(coordinates[1]))){
                waitForEnter();
                for(int i=0; i<2; i++){
                    this.setCover(coordinates[i]);
                }
            }

            /* If win prompt */
            if(matrix.isMatrixUnCover()){
                end = true;
                System.out.println("Congratulation! You won!");
            }

            chances--;


        }
        
        /* If lose prompt */
        if(chances <= 0 && !end){
            end = true;
            clearConsole();
            System.out.println(output());
            System.out.println("You lost!"); 
        }
        

    }
    
    /* return String Coordinates of the word in the matrix if making coordinates fails return empty String */
    public String PlayerMove(){
        Scanner scan = new Scanner(System.in);
        String coordinates = "";
        boolean valid = false;
            try {
                while(!valid){
                    scan = new Scanner(System.in);
                    if(!scan.hasNext("([ab]|[AB])\\d")) throw new Exception();
                    coordinates = scan.nextLine().toUpperCase();

                    int y = getY(coordinates);
        
                    if(y<0 || y>this.matrix.getColumns()) throw new Exception();

                    if(!isCover(coordinates)){
                        System.out.println("You choose uncover word! Choose again");
                        continue;
                    }
                    valid = true;
                }
    
            }catch (Exception e) {
                coordinates = ""; 
                valid = true;
            }
        return coordinates;
                    
    }
    
    public void setUnCover(String coordinates){
        int x = getX(coordinates);
        int y = getY(coordinates);
        
        matrix.setUnCover(x, y);
         
    }

    public boolean isCover(String coordinates){
        int x = getX(coordinates);
        int y = getY(coordinates);

        return this.matrix.isCover(x, y);
    }

    public void setCover(String coordinates){
        int x = getX(coordinates);
        int y = getY(coordinates);

        matrix.setCover(x, y);   
    }

    public int getX(String coordinates){
        return (int)coordinates.charAt(0) - (int)'A';
    }
    
    public int getY(String coordinates){
        return (int)coordinates.charAt(1) - (int)'1';
    }

    public String getWord(String coordinates){
        int x = getX(coordinates);
        int y = getY(coordinates);

        return matrix.getWord(x, y);
    }

    public String getDifficulty(){ return "Level: " + this.difficulty;}
    
    public String getChances(){ return "Chances: " + this.chances;}

    public String output(){
        String bar = "------------------------------";
        String indent = "     ";

        StringBuffer output = new StringBuffer();
        output.append(bar+"\n");
        output.append(indent+getDifficulty()+"\n");
        output.append(indent+getChances()+"\n\n");
        output.append(indent+matrix.header() + "\n");
        for(int i=0; i<2; i++){
            output.append(indent + matrix.rowToString(i)+"\n");
        }
        output.append(bar + "\n");
        return output.toString();
    }

    public void clearConsole(){ System.out.println("\033[H\033[2J");}

    public void printMenu(){
        clearConsole();
        System.out.println(this.output());
    }

    public void waitForEnter(){
        Scanner s = new Scanner(System.in);
        System.out.println("Press Enter");
        s.nextLine();
    }


}
