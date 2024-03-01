

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class App {
    public static List<String> currentStack = new ArrayList<>();
    public static Map <Integer, List<String>> retrievedStacksMap = new HashMap<>();
    public static FileService fileService = new FileService();
    public static  String pokemonFilePath = "";

    public static void main(String[] args) throws Exception {

        if (args.length == 1) {
            pokemonFilePath = args[0];
            System.out.println("[Your request] file from directory: " + pokemonFilePath);

        } else {
            System.out.println("Please input the directory Pokemon stack!");
        }

        //putting into structure Map <Integer, List<String>> 
        List<String> pokemonArrList = fileService.ReadCSV(pokemonFilePath);

        System.out.println(pokemonArrList.get(1));
for (int i = 0; i < pokemonArrList.size(); i++) {
    List<String> oneStack = new ArrayList<>();
    oneStack.add(pokemonArrList.get(i));
    retrievedStacksMap.put(i+1,oneStack);
}
 
     printHeader();

      boolean keepGameRunning = true; 
      Console cons = System.console();

      while (keepGameRunning) {
        String input = cons.readLine("Enter your selection >");

        switch (input) {
            case "q":
            printExitMessage();
                keepGameRunning = false;
                clearConsole();
                break;
            case "1":
           //View list of Pokemon in the selected stack
           for (Map.Entry <Integer, List<String>> entry : retrievedStacksMap.entrySet()) { 
            int key = entry.getKey(); 
            List<String> value = entry.getValue(); 
            System.out.println(key + ": " + value);
        };

                break;
            case "2":
            //View unique list of Pokemon in the selected stack
            int stackChoice = Integer.parseInt(cons.readLine("Display the list of unique Pokemon stack (1-8) >"));
            if (stackChoice > 8 || stackChoice < 0) {
                System.out.println("Please input choice between 1-8 only");
            } else {
                printUniquePokemonStack(stackChoice);
            }
                break;
            case "3":
            //Find next 5 stars Pokemon occurrence

         
                break;
            case "4":
             //Create new Pokemon stack and save (append) to csv file
             String stackInput = cons.readLine("Create a new Pokemon stack and save to a new file >");
             // Save unique stack globally

               String[] stackInputArray = stackInput.split(",");
                pokemonArrList = Arrays.asList(stackInputArray);
                currentStack = Arrays.asList(stackInputArray);
             String saveToPath = cons.readLine("Enter filename to save (e.g. path/filename.csv) >");

             // Save unique stack to CVS
           savePokemonStack(stackInput, saveToPath);

                break;
          
            default:
                break;
        }

      }

  
    }

    public static void clearConsole() throws IOException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Task 1
    public static void pressAnyKeyToContinue() {
        // your code here
    }

    // Task 1
    public static void printHeader() {

        // Task 1 - your code here
        System.out.println("Welcome to Pokemon Gaole Legend 4 Rush 2\n");
        System.out.println("(1) View whole list of Pokemon in all stacks");
        System.out.println("(2) View unique list of Pokemon in the selected stack");
        System.out.println("(3) Find next 5 stars Pokemon occurrence");
        System.out.println("(4) Create new Pokemon stack and save (append) to csv file");
        System.out.println("(q) to exit the program\n");
    }

    // Task 1
    public static void printExitMessage() {

        // Task 1 - your code here
        System.out.println("Thank you for using the program...");
        System.out.println("Hope to see you soon...");
    }

    // Task 1
    public static void savePokemonStack(String pokemonStack, String filename) {
        // Task 1 - your code here
        fileService.writeAsCSV(pokemonStack, filename);
    }

    // Task 2
    public static void printUniquePokemonStack(Integer stack) {
        // Task 2 - your code here
        if (currentStack.size() == 0){
            System.out.println("This stack is empty. You may select option (4) to add a stack.");
        } else {
            for (String pokemon : currentStack) {
                System.out.println(pokemon.trim());
               }
    
        }
    }

    // Task 2
    public static void printNext5StarsPokemon(String enteredPokemon) {
        // Task 2 - your code here
        String[] enteredArr = enteredPokemon.split("*");
        // String enteredStars = enteredArr[0];
        String enteredName = enteredArr[1].trim();
        
        List<String> pokemonArrList = fileService.ReadCSV(pokemonFilePath);

        int index = 0;

        for (String pokemon : pokemonArrList) {
         System.out.println(pokemon);
         String[] pokemonArr = enteredPokemon.split("*");
        int pokeStars = Integer.parseInt(pokemonArr[0]);
        String pokeName = pokemonArr[1].trim();
       
        if (pokeStars == 5 && pokeName == enteredName)  {
            System.out.println("5 stars" + pokeName + "found.\n" + index + " cards to go (searched)." );
        }

        index ++;
     }

    //  if (index == )


    //  if (fiveStarsPoke.size() == 0) {
    //     System.out.println(enteredName + "not found in this set.");
    // } 
    
    // else if () {
    //     System.out.println("No 5 stars " + enteredName +" found subsequently in this stack.");
    // }

    }

    // Task 2
    public static void printPokemonCardCount() {
        // Task 2 - your code here
        
    }

}
