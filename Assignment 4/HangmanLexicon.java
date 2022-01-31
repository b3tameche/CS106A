import java.io.*;
import java.util.ArrayList;

public class HangmanLexicon {
	ArrayList<String> words;
	
	public HangmanLexicon() throws IOException{
		try {
			BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			words = new ArrayList<String>();
			while(rd.readLine() != null) {
				words.add(rd.readLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		int count = words.size();
		return count;
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		String word = words.get(index);
		return word;
	}
}

