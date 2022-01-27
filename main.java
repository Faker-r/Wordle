import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class main
{
    public static ArrayList<String> words;
    public static void main(String[] args)
    {
        words = new ArrayList<String>();
        System.out.println("Guess the Wordle in 6 tries.");
        System.out.println("Each Guess must be a valid 5 letter word.");
        System.out.println("After each guess, a symbol will appear next to each letter to show how close the guess was to the word");
        System.out.println("* means correct letter in correct place");
        System.out.println("# means the letter is in the word but in another location");
        System.out.println("! means the letter is not in the word\n");
        System.out.println("Good luck!\n\n");
        Scanner s = new Scanner(System.in);
        String guess;
        int tries = 6;
        loadWords("words.txt");
        String word = words.get((int)(Math.random()*words.size())); 
        int numCorrect;
        for(int i =0; i<6; i++){
            guess = s.nextLine(); 
            
            numCorrect = 0;
            System.out.println();
            if(!words.contains(guess)) {
                System.out.println("Error: Must Guess an Actual Word");
                i--;
            }
            else{
            for(int j = 0; j<5; j++){
                if(word.charAt(j)==guess.charAt(j)) {
                    System.out.print(guess.charAt(j) + "* ");
                    numCorrect++;
                }
                else if(word.indexOf(guess.substring(j,j+1))>=0) System.out.print(guess.charAt(j) + "# ");
                else System.out.print(guess.charAt(j) + "! ");
                
            }
            tries--;
            if(numCorrect == 5){
                System.out.println("YOU WIN!");
                i = 6;
            }
            else if(i==5){
                System.out.println("YOU LOSE!!!");
                System.out.println("The Word Was " + word);
            }
            else if(tries == 1) System.out.println("You have 1 more try");
            else System.out.println("You have " + tries + " more tries");
            System.out.println();
        }
        }
        
        
    }
    public static void loadWords(String filename){
        File wordfile = new File(filename);
        try{
            Scanner fileScanner = new Scanner(wordfile);
            while (fileScanner.hasNextLine()){
                String w = fileScanner.nextLine();
                if(w.length()==5 && !Character.isUpperCase(w.charAt(0))) {
                    words.add(w);
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println(e);
        }
    }
    
}
