import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.logging.Logger;

class ExceptionLogger{
	private static Logger logger = Logger.getLogger("ExceptionLogger");
	public static void log(Exception e){
		StringWriter trace = new StringWriter();
		e.printStackTrace(new PrintWriter(trace));
		logger.severe(trace.toString());
	}
}

public class WordsInFile {
    public static void main(String... args) {
        //File path
        final String FILE_PATH = "temp1.txt";
        //File line
        String fileLine = null;
        //Map for words and frequencies
        Map<String, Integer> countOfWords = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
    	boolean openFileFlag=false;
        do{
	        try{
	            //Open File
	            FileReader fileReader = new FileReader(FILE_PATH);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            openFileFlag=true;
	            try{
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
	            }catch(IOException e){
	                System.err.println("Error reading file");
	                ExceptionLogger.log(e);
	            }finally{
	            bufferedReader.close();
	            }
	        }catch (FileNotFoundException e){
	            System.err.println("Unable to find file");
	            ExceptionLogger.log(e);
	            try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					System.err.println("Thread sleeping issue");
					ExceptionLogger.log(e);
				}
	        }catch(IOException e){
	            System.err.println("Error reading file");
	            ExceptionLogger.log(e);
	        }
        }while(!openFileFlag);
        System.out.println(countOfWords);
    }
}
