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
public class App 
{
	HashMap<String, Integer> suggestedWords;
	
	TreeSet<String> verbs;
	TreeSet<String> adjectives;
	TreeSet<String> resumeList;
	
	String[] stopwords = {"a", "as", "able", "about", "above", "according", "You", "you","accordingly", "across", "actually", "after", "afterwards", "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero"};
	Set<String> stopWordSet;
	String job;
	String resume;
	
	public App(String resume, String job, String verbsFile, String adjFile) throws IOException {
		// TODO Auto-generated constructor stub	
		this.resume = resume;
		this.job = job;
		resumeList = new TreeSet<String>();
		verbs =  new TreeSet<String>();
		adjectives = new TreeSet<String>();
		suggestedWords = new HashMap<String, Integer>();
		
		stopWordSet = new HashSet<String>(Arrays.asList(stopwords));
		verbs = readVerbs(verbsFile);
		adjectives = readAdjectives(adjFile);
	}


	private TreeSet<String> readVerbs(String verbsFile) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br_verb = new BufferedReader(new FileReader(verbsFile));
		String verbLine;
		while ((verbLine = br_verb.readLine()) != null) {
			if(!verbs.contains(verbLine)){
				verbs.add(verbLine);
			}
		}
		return verbs;
	}
		
		private TreeSet<String> readAdjectives(String adjFile) throws IOException {
			// TODO Auto-generated method stub
			BufferedReader br_adj = new BufferedReader(new FileReader(adjFile));
			String adjLine;
			while ((adjLine = br_adj.readLine()) != null) {
				if(!adjectives.contains(adjLine)){
					adjectives.add(adjLine);
				}
			}
		return adjectives;
	}


	private HashMap<String, Integer> getWordsCount(String job) throws IOException {
		// TODO Auto-generated method stub
		job = this.job;
		
		
		BufferedReader br_job = new BufferedReader(new FileReader(job));
		
		String job_line;
		while ((job_line = br_job.readLine()) != null){
			String[] wordsInJob = job_line.split("\\s|,|;|:|!");
				for(String wordInJob: wordsInJob){
					if(suggestedWords.containsKey(wordInJob.toLowerCase())){
						if(isVerb(wordInJob.toLowerCase()) || isStopWord(wordInJob.toLowerCase())){
							suggestedWords.remove(wordInJob.toLowerCase());
						}
						else
							suggestedWords.put(wordInJob.toLowerCase(),suggestedWords.get(wordInJob.toLowerCase())+1);
					}
					else
						suggestedWords.put(wordInJob.toLowerCase(),1);
				}
		}
		br_job.close();
		return suggestedWords;
	}


	private boolean isStopWord(String wordInJob) throws IOException {
		// TODO Auto-generated method stub
		
		return stopWordSet.contains(wordInJob)?true:false;
	}

	private boolean isVerb(String wordInJob) throws IOException {
		// TODO Auto-generated method stub
		
		return verbs.contains(wordInJob)?true:false;
	}
	
	private boolean isAdjective(String wordInJob) throws IOException {
		// TODO Auto-generated method stub
		
		return adjectives.contains(wordInJob)?true:false;
	}

	private void recommend(String resume) throws IOException {
		// TODO Auto-generated method stub
		
		suggestedWords = getWordsCount(job);
		
		TreeSet<String> resumeResult = new TreeSet<String>();
		
		System.out.println("****************************************");
		System.out.println("Recommended words");
		System.out.println("****************************************");
		
		BufferedReader br_resume = new BufferedReader(new FileReader(resume));
		String resumeLine;
		while ((resumeLine = br_resume.readLine()) != null) {
			
			String[] resumeWords = resumeLine.split("\\s|,|;|:|!");
			for(String word: resumeWords){
				if( !resumeList.contains(word.toLowerCase()) ){
					resumeList.add(word.toLowerCase());
				}
			}
		}
		
		
		for(String jobWord: suggestedWords.keySet()){
			if(!resumeList.contains(jobWord)){
				System.out.println(jobWord);
			}
		}
		System.out.println();
		System.out.println("****************************************");
		System.out.println("Recommended adjectives");
		System.out.println("****************************************");
		
		for(String jobWord: suggestedWords.keySet()){
			if(isAdjective(jobWord)){
				if(!resumeList.contains(jobWord)){
					System.out.println(jobWord);
				}
			}
			
		}
		
	}
    public static void main( String[] args ) throws IOException
    {
    	//input: resume, job descr
    	//output: words in job descr and not in resume. suggested list of adjectives
    	
    	String resume = args[1];
    	String job = args[2];
    	String verbsFile = "verbs.txt";
    	String adjFile ="adjectives.txt";
    	
    	
    	App app = new App(resume, job, verbsFile, adjFile);
    	app.recommend(resume);
    	
    	
    }

}
    