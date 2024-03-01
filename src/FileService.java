

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileService {
    
    public List<String> ReadCSV(String fullPathFilename) {
        // Task 1 - your code here
        File pokemonSampleFile = new File(fullPathFilename);
        
        List<String> pokemonArrList = new ArrayList<>();


        try (Scanner scan = new Scanner(pokemonSampleFile)) {
      
            while (scan.hasNextLine()) {
                String pokemonInfo = scan.nextLine();
    
                pokemonArrList.add(pokemonInfo);
            }

        } catch (Exception e) {
        e.printStackTrace();
        }

        return pokemonArrList;
    }

    public void writeAsCSV(String pokemons, String fullPathFilename) {
        // Task 1 - your code here
        // create a new file and put in the path provided
        File file = new File(fullPathFilename);

        //create a new file if file does not exist
        
        try {
            // // Create directories if necessary
            // Path directoryPath = Paths.get(fullPathFilename).getParent();
            // if (directoryPath != null) {
            //     Files.createDirectories(directoryPath);
            // }

            // Write the content to the file (append if file exists, create new file otherwise)
            try (FileOutputStream fos = new FileOutputStream(file, file.exists())) {
                fos.write(System.lineSeparator().getBytes());// Optional: add a new line after appending
                fos.write(pokemons.getBytes());
            }

            if (file.exists()) {
                System.out.println("Successfully appended to existing file.");
            } else {
                System.out.println("Successfully created and saved to new file.");
            }

        
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        
    } 
    
}
