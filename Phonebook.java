import java.util.Scanner;

///Returnes contact information based on user-inputed lastname
//Autocorrects input to closest name in phonebook, if input is not in phonebook
//Time complexity is O(n) if name is correct and O(n^2) if incorrect
public class Phonebook {
  CSVReader reader = new CSVReader();
  StringEditDistance test = new StringEditDistance();
  int minSED;
  String closest_word;
  HashMap<String, String[]> map = new HashMap(502);
  String[] result;
  String[] labels;

  //corrects the incorrectly spelled last name based on LinkedList of last names
  //uses StringEditDistance class
  public String spellCheck(String input_word, LinkedList<String> names) {
    minSED = test.stringEditDistance(names.getFirst().getElement(), input_word);
    while(!names.isEmpty()) {
      String nextname = names.getFirst().getElement();
      names.removeFirst();
      int sed = test.stringEditDistance(nextname, input_word);
      if (sed < minSED) {
        minSED = sed;
        closest_word = nextname;
      }
    }
    return closest_word;
  }


  //finds value(Array) in hashmap based on key (input_word)
  //then prints each string cooresponding to name
  public void retrieve(String input_word, String file_name, HashMap<String, String[]> map)  {
      try {
      labels = map.get("last_name");
      result = map.get(input_word);
    }
    catch (HashMapException e) {
        System.out.println(e);
    }

    for (int n=0; n<12; n++){
      System.out.print(labels[n]+ ": ");
      System.out.println(result[n]);
    }
    System.out.println();
  }

  //compares input name to all names in phonebook file
  //if the name is correct, it calls the method to find the information
  //if there is a typo, it autocorrects the input based on a linked list of all
  //names in file and calls retrieve method on corrected word
  public void getInfo(String input, String file_name) {
    map = reader.CSVReader(file_name);
    if (reader.last_names.contains(input)) {
      retrieve(input,file_name,map);
    }
    else {
      System.out.print("Input name is not in directory, '");
      String new_word = spellCheck(input, reader.last_names);
      System.out.println(input + "' has been corrected to: " + new_word);
      System.out.println();
      System.out.println("Contact Information:");
      retrieve(new_word, file_name, map);
    }
  }

  public static void main(String[] args) {
    Phonebook book = new Phonebook();
    Scanner sc = new Scanner(System.in);
    System.out.println("Input a last name: ");
    String word = sc.nextLine();
    System.out.println();
    book.getInfo(word,"phone_book_data.csv");
  }
}
