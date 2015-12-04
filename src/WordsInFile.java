import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordsInFile {
    public static void main(String... args) {
        //File path
        final String FILE_PATH = "temp.txt";
        //File line
        String fileLine = null;
        //Map for words and frequencies
        Map<String, Integer> countOfWords = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
        try{
            //Open File
            FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //Reading line by line
            while((fileLine = bufferedReader.readLine()) != null) {
                //Converting line to list
                List<String> fileLineList = new ArrayList<String>(Arrays.asList(fileLine.split(" ")));
                //For each word in the line converted into list
                for (String word:fileLineList) {
                    Integer freq = countOfWords.get(word);
                    countOfWords.put(word,freq==null?1:freq+1);
                }
            }
            bufferedReader.close();
        }catch (FileNotFoundException e){
            System.out.println("Unable to find file");
        }catch(IOException e){
            System.out.println("Error reading file");
        }
        System.out.println(countOfWords);
    }
}
