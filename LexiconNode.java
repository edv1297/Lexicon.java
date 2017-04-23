import structure5.*;
import java.util.Iterator;

class LexiconNode implements Comparable<LexiconNode> {
    protected char letter;
    protected boolean isWord;
    protected Vector<LexiconNode> children;

    /* TODO: a data structure for keeping track of the children of
       this LexiconNode */
    
    /**
     * TODO: Constructor
     */
    LexiconNode(char letter, boolean isWord) {
        this.letter = letter;
        this.isWord = isWord;
        children = new Vector<LexiconNode>();
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
	if(children.isEmpty()) children.add(ln);
	else if(ln.compareTo(children.get(children.size()-1))>0){
	    children.add(ln);
	}
	else{
	    int loc = binarySearch(ln);
	    children.add(loc, ln);
	}
    }

    /**
     * TODO: Get LexiconNode child for 'ch' out of child data structure
     */
    public LexiconNode getChild(char ch) {
	LexiconNode n = null;
	for(int i = 0; i < children.size(); ++i){
	    if(children.get(i).letter() == ch){
		n = children.get(i);
		break;
	    }
	}
	return n;
    }
    
    public Vector getChildren(){
	return children;
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
    
    private int binarySearch(LexiconNode ln){
	return binaryHelper(children, ln, 0, (children.size()-1)/2,children.size()-1);
    }
    private int binaryHelper(Vector n, LexiconNode ln, int l, int m, int h){
	//     if(h-l <=1){
	//       if(ln.compareTo(LexiconNode)n.get(h)>0){
	//     return h+1;
	//   }else{
	//     return m;
	//   }
	// }
	//  if(l==m && m == h){
	//    if(ln.compareTo((LexiconNode)n.get(m))<0) return l;
	//      if(ln.compareTo((LexiconNode)n.get(m))<0) return h;
	//    }
	/*if(ln.compareTo((LexiconNode)n.get(m)) <0){
	  binaryHelper(n, ln, l, (l+m)/2,m-1);
	  }else if(ln.compareTo((LexiconNode)n.get(m))>0){
	  binaryHelper(n,ln,m,(h+m)/2, h);
	  }
	  return h;*/
	
	
	if(l==h) return h;
	// if the node to be added is in the first half of the children (ln)
	else if (ln.compareTo((LexiconNode)n.get(m))<0){
	    return binaryHelper(n, ln, l, m/2,m);
	}
	//if the node to be added is in the second half of the children (ln)
	else if (ln.compareTo((LexiconNode)n.get(m))>0){
	    return binaryHelper(n,ln,m+1,(h+m)/2, h);
	}
	return m;
  }
}
