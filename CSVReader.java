import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.io.BufferedReader;


public class CSVReader {

  HashMap<String, String[]> phonebook = new HashMap(502);
  LinkedList<String> last_names = new LinkedList<String>();

  public HashMap<String, String[]> CSVReader(String file_name)  {

    BufferedReader br = null;
    String line = "";
    String word = ",";

    try {
      br = new BufferedReader(new FileReader(file_name));
      //reads through CSV file, separating words by ','
      //adds each line as a value in the hash map with the first word in each line (lastname) as the keys
      while ((line = br.readLine()) != null) {
        String[] person = line.split(word);
        last_names.add(person[0]);
        //add name and info to hashmap
        phonebook.put(person[0],person);

      }
    }
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    catch (IOException e) {
        e.printStackTrace();
    }
    catch (HashMapException e)  {
      System.out.println(e);
    }
    return phonebook;
  }
}
