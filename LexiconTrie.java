import structure5.*;
import java.util.Iterator;

public class LexiconTrie implements Lexicon {

  LexiconNode root = new LexiconNode(' ',false);

  public boolean addWord(String word) {
    //PRE: give a word to be added into the Lexicon
    //POST: return true if the word is new, or false if the world already existed

    boolean result = false;
    LexiconNode n = root;

    for(int i = 0; i<word.length();++i){
      if(n.getChild(word.charAt(i)) == null){  //gives us access to the child node
        if(i < word.length()-1){
          n.addChild(new LexiconNode(word.charAt(i),false));
          n=n.getChild(word.charAt(i));
          result= true;
        }
        else{

          n.addChild(new LexiconNode(word.charAt(i),true));
          n=n.getChild(word.charAt(i));
          result = true;
        }
      }else{
        n = n.getChild(word.charAt(i));
      }
    } return result;
}


  public int addWordsFromFile(String filename) {






  }
  public boolean removeWord(String word) { return true; }
  public int numWords() { return 0; }
  public boolean containsWord(String word) { return true; }
  public boolean containsPrefix(String prefix){ return true; }
  public Iterator<String> iterator() { return null; }
  public Set<String> suggestCorrections(String target, int maxDistance) { return null; }
  public Set<String> matchRegex(String pattern){ return null; }

  public static void main(String args[]){

    LexiconTrie n = new LexiconTrie();

    boolean baseWord = n.addWord(args[0]);
    boolean checkWordSame = n.addWord(args[1]);
    boolean checkWordDifferent = n.addWord(args[2]);

    System.out.println("<added> " + args[0] + " to LexiconTrie and got " + baseWord);

    System.out.println("<added> " + args[1]+ " to LexiconTrie and expected false. Actual: " + checkWordSame);

    System.out.println("<added> " + args[2]+ " to LexiconTrie and expected true. Actual: " + checkWordDifferent);
  }
}
