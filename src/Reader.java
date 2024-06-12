import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * reads a book and builds a word frequency map
 * then allows users to query/find words
 * @author s-stojima
 *
 */
public class Reader {
	public static final String BOOK = "mobydick.txt";
	
	public static void main(String[] args) throws FileNotFoundException {
		File f = new File(BOOK);
		Scanner scan = new Scanner(f);
		Scanner console = new Scanner(System.in);
		Map<String, Integer> bookMap = buildMap(scan);
		print500(bookMap);
		
		//System.out.println(filter("helloA,"));
		
		String question = "";
		while(!question.equals("quit")) {
			System.out.println("enter word. 'quit' to exit");
			question = filter(console.nextLine());
			if(bookMap.containsKey(question)) {
				System.out.println(question + " appears " + bookMap.get(question) + " times");
			}else {
				System.out.println(question + " does not appear");
			}
		}
		
		
		scan.close();
		console.close();
	}
	
	public static void print500(Map<String, Integer> map) {
		for(String key: map.keySet()) {
			int freq = map.get(key);
			if(freq >= 500) {
				System.out.println(key + " : " + map.get(key));
			}
		}
	}
	
	public static Map<String, Integer> buildMap(Scanner s){
		Map<String, Integer> map = new TreeMap<String, Integer>();
		while (s.hasNext()){
			String currentWord = filter(s.next());
			if(currentWord.length() >= 1) {
				int frequency = 1;
				if(map.containsKey(currentWord)) {
					frequency += map.get(currentWord);
				}
				map.put(currentWord, frequency);
				
			}
		}
		return map;
	}
	
	/**
	 * returns a filtered version of str with only lowercase alphaet chars
	 * @param str
	 * @return
	 */
	public static String filter(String str) {
		str = str.toLowerCase();
		String result = "";
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(c>='a' && c<='z') {
				result += c;
			}
		}
		return result;
	}
}
