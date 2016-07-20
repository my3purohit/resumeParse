package com.programcreek.resumeParse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *author maithripurohit
 *
 */

//TODO: structure it into methods 
//TODO: write output into file
//TODO: suggest adjectives to add to the resume
public class App1
{
    public static void main( String[] args )
    {
    	BufferedReader br = null;

		try {
			TreeMap<String,Integer> map = new TreeMap<String,Integer>();
			String sCurrentLine;
			String[] stopwords = {"a", "as", "able", "about", "above", "according", "You", "you","accordingly", "across", "actually", "after", "afterwards", "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero"};
			Set<String> stopWordSet = new HashSet<String>(Arrays.asList(stopwords));
			
			BufferedReader br_verb = new BufferedReader(new FileReader("C:\\Users\\my3pu\\workspace\\resumeParse\\src\\main\\java\\com\\programcreek\\resumeParse\\verbs.txt")); 
			String verbLine;
			
			
			
			br = new BufferedReader(new FileReader("C:\\Users\\my3pu\\workspace\\resumeParse\\src\\main\\java\\com\\programcreek\\resumeParse\\input.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				String[] wordsOnLine = sCurrentLine.split("\\s");
				for(String x : wordsOnLine){
					if(x.length()<2 || stopWordSet.contains(x)){
						continue;
					}
					if(map.containsKey(x.toLowerCase())){
						map.put(x.toLowerCase(),map.get(x.toLowerCase())+1);
					}
					else
						map.put(x.toLowerCase(), 1);
				}
				
				while ((verbLine = br_verb.readLine()) != null) {
					if(map.containsKey(verbLine.toLowerCase()))
						map.remove(verbLine);
				}
				
			}

			BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\my3pu\\workspace\\resumeParse\\src\\main\\java\\com\\programcreek\\resumeParse\\resume.txt"));
			String rLine;
			TreeSet<String> result = new TreeSet<String>();
			while ((rLine = br1.readLine()) != null) {
				String[] resumeWords = rLine.split(" ");
				for(String word: resumeWords){
					if(map.containsKey(word.toLowerCase())){
						map.remove(word);
					}
				}
			}
			TreeSet<String> adj = checkAdjective(map,result);
			
			PrintWriter out = null;
			try  {
				out = new PrintWriter("filename.txt");
				for(String val:map.keySet()){
					 out.println(val+" "+map.get(val));
					 out.flush();
					 System.out.println(val);
				}
				System.out.println("Suggested adjectives");
				System.out.println(adj);
			}
			finally {
			    if (out != null) {
			        out.close();
			    }
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

    	
    	
    }

	private static TreeSet<String> checkAdjective(TreeMap<String, Integer> map, TreeSet<String> result) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\my3pu\\workspace\\resumeParse\\src\\main\\java\\com\\programcreek\\resumeParse\\adjectives.txt"));
		String line;
		TreeSet<String> adj = new TreeSet<String>();
		while ((line = br1.readLine()) != null) {
			if(map.containsKey(line)){
				if(result.contains(line)){
					continue;
				}
				else
					adj.add(line);
			}
		}
		return adj;
	}
}

//open input.txt
//read a line
//split words
//if word is a verb
//if word is an adjective
