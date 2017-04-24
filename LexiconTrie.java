import structure5.*;
import java.util.Iterator;
import java.util.Scanner;

public class LexiconTrie implements Lexicon {

    protected int count = 0;
    static LexiconNode root = new LexiconNode(' ',false);

    //post: add word to lexicon
    public boolean addWord(String word) {
	//pre: give a word to be added into the Lexicon
	//post: return true if the word is new, or false if existing
	String lowerCaseWord = word.toLowerCase();
	boolean result = false;
	LexiconNode node = root;

	for(int i = 0; i<lowerCaseWord.length();++i){
	    //gives us access to the child node
	    char letter = lowerCaseWord.charAt(i);
	    if(node.getChild(letter) == null){
		if(i < lowerCaseWord.length()-1){
		    node.addChild(new LexiconNode(letter,false));
		}
		else{
		    node.addChild(new LexiconNode(letter,true));
		    count++;
		}
		node = node.getChild(letter);
		result = true;
	    }
	    // if the word is added within the branch of another word
	    // then mark the last node as a word and update count and result
	    else if(i == lowerCaseWord.length()-1 &&
		    !node.getChild(letter).isWord){
		node.getChild(letter).isWord = true;
		count++;
		result = true;
	    }
	    else{
		node = node.getChild(letter);
	    }
	}
	return result;
    }

    //pre: valid filename
    //post: scan each line and add the word contained
    public int addWordsFromFile(String filename) {
	Scanner scan = new Scanner(filename);
	while(scan.hasNextLine()){
	    addWord(scan.nextLine());
		}
	return 0;
    }

    // post: removes word if present and returns true or false it it was/wasn't found
    public boolean removeWord(String word) {
	return removeWordHelper(word.toLowerCase(), root);
    }

    private boolean removeWordHelper(String word, LexiconNode current){
	if(word.isEmpty() && current.isWord){
	    current.isWord = false;
	    count--;
	    return true;
	}
	LexiconNode nextNode = current.getChild(word.charAt(0));
	if(nextNode == null){
	    return false;
	}
	else{
	    if(removeWordHelper(word.substring(1), nextNode)){
		// only delete a node if its only child is a dead end now
		if(nextNode.getChildren().isEmpty() && !nextNode.isWord){
		    current.removeChild(nextNode.letter());
		}
	    }
	}
	return true;
    }

    //post: return number of words in LexiconTrie
    public int numWords() { return count; }


    //post: returns true if word is found and false if it isn't
    public boolean containsWord(String word) {
	return containsWordHelper(word.toLowerCase(), root);
    }

    private boolean containsWordHelper(String word, LexiconNode current){
	if(word.isEmpty()) return current.isWord;

	char nextChar = word.charAt(0);
	if(current.getChild(nextChar)==null) return false;

	else{
	    return containsWordHelper(word.substring(1),
				      current.getChild(nextChar));
	}
    }

    //post: returns true if prefix is found and false if it isn't
    public boolean containsPrefix(String prefix){
	LexiconNode current = root;
	boolean prefixFound = true;
	for(int i = 0; i < prefix.length(); i++){
	    // update current node as long as the next letter is a child of the node
	    if(current.getChild(prefix.charAt(i))==null){
		prefixFound = false;
		break;
	    }
	    else{
		current = current.getChild(prefix.charAt(i));
	    }
	}
	return prefixFound;
    }

    //post: return an iterator of all the words in order
    public Iterator<String> iterator() {

	Vector<String> words = new Vector<String>();
	IteratorHelper(root, "", words);
	return words.iterator();
    }

    private void IteratorHelper(LexiconNode current, String word, Vector<String> words){
	if(current.isWord){
	    words.add(word.substring(1)+current.letter());

	}
	Iterator iter = current.iterator();
	while(iter.hasNext()){
	    LexiconNode next = (LexiconNode)iter.next();
	    IteratorHelper(next,word+current.letter(), words);
	}
    }

    public Set<String> suggestCorrections(String target, int maxDistance){
	Set<String> corrections = new SetVector<String>();
	suggestCorrectionsHelper(target, maxDistance, root, "", corrections);
	return corrections;
    }

    private void suggestCorrectionsHelper(String target, int maxDistance, LexiconNode current,
					  String word, Set<String> set){
	if(target.isEmpty() && current.isWord && maxDistance>=0){
	    set.add(word);
	}
	Iterator iter = current.iterator();
	while(iter.hasNext() && !target.isEmpty()){
	    LexiconNode next = (LexiconNode)iter.next();

	    if(next.letter()==target.charAt(0)){
		suggestCorrectionsHelper(target.substring(1), maxDistance,
					 next, word+target.charAt(0), set);
	    }
	    else{
		suggestCorrectionsHelper(target.substring(1), maxDistance-1,
					 next, word+next.letter(), set);
	    }
	}
    }

    public Set<String> matchRegex(String pattern){

	Set<String> words = new SetVector<String>();
	
	matchRegexHelper(pattern, root, "", words);
	return words;
    }

    private void matchRegexHelper(String pattern, LexiconNode current,
				  String word, Set<String> set ){
	System.out.println(pattern);
	if(pattern.startsWith("*")){
	    System.out.println("has asterik");
	    if(current.isWord){
		set.add(word);
	    }
	    Iterator<LexiconNode> search = current.getChildren().iterator();
	    while(search.hasNext()){
		LexiconNode ln = (LexiconNode)search.next();
		word += ln.letter();
		System.out.println(word);
		if(ln.isWord){
	      
		    set.add(word);
		}
		matchRegexHelper(pattern, ln, word ,set);
	    }
	}

	Iterator<LexiconNode> iter = current.iterator();
	while(iter.hasNext()){
	    System.out.println("iter does have next");

	    LexiconNode nextNode = iter.next().getChild(pattern.charAt(0));
	    
	    if(nextNode!=null){
		System.out.println("nextNode is not null");
		matchRegexHelper(pattern.substring(1), nextNode, word+current.letter(), set);
	    }
	}
    }


    public static void main(String args[]){

	LexiconTrie n = new LexiconTrie();
	
	for(int j= 0; j<args.length; ++j){
	    n.addWord(args[j]);
	}

	for(int i =0 ; i < root.getChildren().size(); i++){
	    LexiconNode l = (LexiconNode)root.getChildren().get(i);
	    System.out.println(i + ": " + l.letter());
	    
	}

	System.out.println("Number of words: " + n.numWords());

	Iterator<String> lexicon = n.iterator();

	while(lexicon.hasNext()){
	    String word = lexicon.next();
	    System.out.println(word + " length is: " + word.length());
	}
	Set<String> s = n.matchRegex("a*");
	Iterator <String> iter = s.iterator();
	
	while(iter.hasNext()){
	    System.out.println("flag");
	    System.out.println(iter.next());
	}
	
    }
    
}
