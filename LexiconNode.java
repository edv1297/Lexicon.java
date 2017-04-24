import structure5.*;
import java.util.Iterator;

class LexiconNode implements Comparable<LexiconNode> {

    public boolean isWord;
    protected char letter;
    protected OrderedVector<LexiconNode> children;

    /* TODO: a data structure for keeping track of the children of
       this LexiconNode */
    
    /**
     * TODO: Constructor
     */
    LexiconNode(char letter, boolean isWord) {
        this.letter = letter;
        this.isWord = isWord;
        children = new OrderedVector<LexiconNode>();
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
	children.add(ln);
    }

    /**
     * TODO: Get LexiconNode child for 'ch' out of child data structure
     */
    public LexiconNode getChild(char ch) {
	LexiconNode n = null;
	Iterator<LexiconNode> iter = children.iterator();
	while(iter.hasNext()){
	    LexiconNode next = iter.next();
	    if(next.letter() == ch){
		n = next;
		break;
	    }
	}
	return n;
    }
    
    public OrderedVector<LexiconNode> getChildren(){
	return children;
    }
    
    /**
     * Remove LexiconNode child for 'ch' from child data structure
     */
    public void removeChild(char ch) {
	Iterator<LexiconNode> iter = children.iterator();
	while(iter.hasNext()){
	    LexiconNode next = iter.next();
	    if(next.letter() == ch){
		children.remove(next);
	    }
	}
    }

    /**
     * TODO: create an Iterator that iterates over children in alphabetical order
     */
    public Iterator<LexiconNode> iterator() {
	return children.iterator();
    }
    
    /* private int binarySearch(LexiconNode ln){
	return binaryHelper(children, ln, 0, (children.size()-1)/2,children.size()-1);
    }
    private int binaryHelper(Vector n, LexiconNode ln, int l, int m, int h){
	
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
	}*/
}
