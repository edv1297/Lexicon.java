import structure5.*;
import java.util.Iterator;
import java.util.ArrayList;

class LexiconNode implements Comparable<LexiconNode> {
    char letter;
    boolean isWord;
    ArrayList<LexiconNode> children;

    /* TODO: a data structure for keeping track of the children of
	   this LexiconNode */

    /**
	 * TODO: Constructor
	 */
    LexiconNode(char letter, boolean isWord) {
        this.letter = letter;
        this.isWord = isWord;
        children = new ArrayList<LexiconNode>();
    }

    /**
	 * TODO: Compare this LexiconNode to another.
	 * (You should just compare the characters stored at the Lexicon Nodes.)
     */
    public int compareTo(LexiconNode o) {

      return this.letter - o.letter;

     }

    /**
	 * TODO: Return letter stored by this LexiconNode
	 */
    public char letter() {
      return this.letter; }

    /**
	 * TODO: Add LexiconNode child to correct position in child data structure
	 */
    public void addChild(LexiconNode ln) {
      int loc = children.binarySearch(children, ln );
      children.add(loc, ln);
    }

    /**
	 * TODO: Get LexiconNode child for 'ch' out of child data structure
	 */
    public LexiconNode getChild(char ch) {
      LexiconNode n = null;
     for(int i = 0; i < children.size(); ++i){
       if(children.get(i).letter() == ch){
          n = children.get(i);
       }
     }return n;
     }

    /**
	 * Remove LexiconNode child for 'ch' from child data structure
	 */
    public void removeChild(char ch) {
      for(int i = 0; i < children.size(); ++i){
        if(children.get(i).letter() == ch){
          children.remove(i);
        }
      }
      }

    /**
	 * TODO: create an Iterator that iterates over children in alphabetical order
	 */
    public Iterator<LexiconNode> iterator() {
      return children.iterator();
    }

    private int binarySearch()
      return binaryHelper(children, ln, 0, n.size()/2,n.size());

    private int binaryHelper(ArrayList n, LexiconNode ln, int l, int m, int h){

      if(ln.compareTo(n[m] <0){
        return binarySearch(n, ln, l, m/2,m-1);
      }else if(ln.compareTo(n[m]>0)){
        return binarySearch(n,ln,m+1,h+m/2, h)
      }else{
        return m;
      }


    }


}
