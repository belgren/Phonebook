public class StringEditDistance {

  public int minimum(int first, int second, int third) {
    if (first < second && first < third) {
      return first;
    }
    else if (second < first && second < third) {
      return second;
    }
    else if (first == second && first < third)  {
      return first;
    }
    else {
      return third;
    }
  }

  public int stringEditDistance(String word1, String word2) {

    //base case if either word has no characters
    if (word1.length()==0)  {
      return word2.length();
    }
    else if (word2.length()==0) {
      return word1.length();
    }
    else  {
      ///if first chars match
      if (word1.charAt(0)==word2.charAt(0)) {
        return stringEditDistance(word1.substring(1), word2.substring(1));
      }
      //if first chars dont match
      else  {
        //different operations
        int a = stringEditDistance(word1.substring(1), word2.substring(1));
        int b = stringEditDistance(word1.substring(0), word2.substring(1));
        int c = stringEditDistance(word1.substring(1), word2.substring(0));
        return 1 + minimum(a,b,c);
      }
    }
  }
}
