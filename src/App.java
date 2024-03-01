

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


public class App {
    public static List<String> currentStack = new ArrayList<>();
    public static Map <Integer, List<String>> retrievedStacksMap = new HashMap<>();
    public static FileService fileService = new FileService();
    public static  String pokemonFilePath = "";

    public static void main(String[] args) throws Exception {
        clearConsole();

        if (args.length == 1) {
            pokemonFilePath = args[0];
            System.out.println("[Your request] file from directory: " + pokemonFilePath);

        } else {
            System.out.println("Please input the directory Pokemon stack!");
        }

        //putting into structure Map <Integer, List<String>> 
        List<String> pokemonArrList = fileService.ReadCSV(pokemonFilePath);

        // System.out.println(pokemonArrList.get(1));
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
            pressAnyKeyToContinue();
                break;
            case "3":
            //Find next 5 stars Pokemon occurrence
            String pokemonInput = cons.readLine("Which Pokemon would you like to search? >");
            printNext5StarsPokemon(pokemonInput);
         
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

            case "5":
            //Print top10 pokemons
            printPokemonCardCount();
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
        System.out.println("Press any key to continue...");
        Scanner s = new Scanner(System.in);
        s.nextLine();
    }

    // Task 1
    public static void printHeader() {

        // Task 1 - your code here
        System.out.println("Welcome to Pokemon Gaole Legend 4 Rush 2\n");
        System.out.println("(1) View whole list of Pokemon in all stacks");
        System.out.println("(2) View unique list of Pokemon in the selected stack");
        System.out.println("(3) Find next 5 stars Pokemon occurrence");
        System.out.println("(4) Create new Pokemon stack and save (append) to csv file");
        System.out.println("(5) Print distinct Pokemons and cards count");
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
        if (retrievedStacksMap.get(stack) == null){
            System.out.println("This stack is empty. You may select option (4) to add a stack.");
        } else {
            String oneStack = retrievedStacksMap.get(stack).get(0).replaceAll(" ","");
            String[] oneStackSplit = oneStack.split(",");
            for (int i = 0; i < oneStackSplit.length; i++) {
                System.out.println((i+1) + " => " + oneStackSplit[i]);
            }
            
        }
    }

    // Task 2
    public static void printNext5StarsPokemon(String enteredPokemon) {
        // Task 2 - your code here
        String[] enteredArr = enteredPokemon.split("\\*");
        String enteredStars = enteredArr[0];
        String enteredName = enteredArr[1].trim();
        System.out.println("Searching for 5 stars " + enteredName + "...");

        for (Map.Entry <Integer, List<String>> entry : retrievedStacksMap.entrySet()) { 
            System.out.println("Set " + entry.getKey());
            String oneStack = entry.getValue().get(0).replaceAll(" ","");
            String[] oneStackSplit = oneStack.split(",");

            boolean fiveStarsPokeExist = false;
            boolean pokemonExist = false;
            int cardsCounted = 0;
           
            for (int i = 0; i < oneStackSplit.length; i++) {
                String[] onePokemon = oneStackSplit[i].split("\\*");
                String pokeStars = onePokemon[0].trim();
                String pokeName = onePokemon[1].trim();
             
                cardsCounted ++;

            //    System.out.println("Searched " + "Name: " + pokeName + " Stars: " + pokeStars);
                if (pokeStars.equals(Integer.toString(5)) && pokeName.equalsIgnoreCase(enteredName))  {
                    System.out.println("5* " + pokeName + ">>>" +  (oneStackSplit.length-cardsCounted) + " cards to go. "+cardsCounted+ " cards counted." );
                    fiveStarsPokeExist = true;
                } 

                if (pokeName.equalsIgnoreCase(enteredName)) {
                    pokemonExist = true;
                }
             
    } 
        
    if (pokemonExist==true && fiveStarsPokeExist==true) {
        break;
    } else if (pokemonExist==false) {
            System.out.println(enteredStars + "* " + enteredName + " not found in this set.");
        }  else if (pokemonExist==true) {
            System.out.println("No 5 stars " + enteredName +" found subsequently in this stack.");
        } 

        fiveStarsPokeExist = false;
        pokemonExist = false;
        cardsCounted=0;
    };


    }

    // Task 2
    public static void printPokemonCardCount() {
        // Task 2 - your code here

        List<String> pokemonArrList = fileService.ReadCSV(pokemonFilePath);
        Map<String, Integer> pokeCount = new HashMap<>();

        for (String stack : pokemonArrList) {
           String cleanStack = stack.replaceAll(" ","");
            String[] oneStackArr = cleanStack.split(",");
            for (String pokemon : oneStackArr) {
                 // key is null, first word does not exist
                 if (!pokeCount.containsKey(pokemon)) {
                    pokeCount.putIfAbsent(pokemon, 1);
                } else {
                    int frequency = pokeCount.get(pokemon);
                    pokeCount.put(pokemon, frequency + 1); //updates if the key already exists
                }
            
        }
    }
   Map<String,Integer> topTen =
    pokeCount.entrySet().stream()
       .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
       .limit(10)
       .collect(Collectors.toMap(
          Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
                  
         int rank = 1;
            for (Map.Entry<String,Integer> entry : topTen.entrySet()) { 
            String pokemon = entry.getKey(); 
            int count = entry.getValue();
            
            System.out.println("Pokemon " + rank + ": " + pokemon + ", " + "Cards cout: " + count);
            rank ++;
        };
            }


    }

