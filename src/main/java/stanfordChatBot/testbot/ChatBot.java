package stanfordChatBot.testbot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class ChatBot {

	StanfordCoreNLP stanfordCoreNLP = null;
	static long start = System.nanoTime();

	ChatBot() {
		stanfordCoreNLP = Pipeline.getPipeline();
	}

	public void talk(String text) {
		
		CoreDocument coreDocument = new CoreDocument(text);
		stanfordCoreNLP.annotate(coreDocument);
		List<CoreLabel> sentence = coreDocument.tokens();
		analyse(sentence);
	}

	private static void analyse(List<CoreLabel> sentence) {

		Utility made = new Utility();

		List convo = made.makeArrayList();
		List numbers = made.makeArrayList();

		for (CoreLabel token : sentence) {

			String word = null;
			String POS = (String) token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
			if (POS.equals("NN") || POS.equals("VBG") || POS.equals("VB") || POS.equals("IN") || POS.equals("JJ") || POS.equals("NNS") || POS.equals("DT")) {
				word = token.get(CoreAnnotations.TextAnnotation.class);
				convo.add(word);

			}
			if (POS.equals("CD") || token.get(CoreAnnotations.TextAnnotation.class).equals("lakh") || token.get(CoreAnnotations.TextAnnotation.class).equals("lakhs")) {
				word = token.get(CoreAnnotations.TextAnnotation.class);
				numbers.add(word);
			}
		}

		try {
			chooser(convo);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		System.out.println(convo);
		System.out.println(numbers);
	}
	
	private static void chooser(List keyWords) throws FileNotFoundException, IOException, ParseException {
		
		Utility made = new Utility();
		
		JSONParser jsonparser = new JSONParser();

		Object obj = jsonparser.parse(new FileReader(".\\jsonFile\\convoData.json"));
		JSONObject keyWordsList = (JSONObject) obj;

		List<String> elements = new ArrayList<>(keyWordsList.keySet());
		
		switch(made.switcher(elements, keyWords)) {
		case "buy":System.out.println("buy");
		break;
		case "sell":System.out.println("sell");
		break;
		case "looking":System.out.println("looking");
		break;
		case "between":System.out.println("between");
		break;
		case "price":System.out.println("price");
		break;
		default:System.out.println("Sorry, i think we dont have that here");
		}
		
		
	}

	private static void checkList(List keyWords) throws IOException, ParseException {

		JSONParser jsonparser = new JSONParser();

		Object obj = jsonparser.parse(new FileReader(".\\jsonFile\\convoData.json"));
		JSONObject keyWordsList = (JSONObject) obj;

		List<String> elements = new ArrayList<>(keyWordsList.keySet());

		for (int i = 0; i < elements.size(); i++) {

			JSONObject array = (JSONObject) keyWordsList.get(elements.get(i));

			List<String> layers = new ArrayList<>(array.keySet());

			for (String key : layers) {

				JSONArray jsonArray = (JSONArray) array.get(key);

				List<String> internal = jsonArray;
				
				if(keyWords.containsAll(internal)) {
					System.out.println("true or false: ");
				}
			}
		}
	}
}

class Utility {

	private static List sender = null;

	public List makeArrayList() {
		sender = new ArrayList();
		return sender;
	}

	public String switcher(List<String> choices, List<String> text) {
		String option = null;

		for (String compare : choices) {
			if (text.contains(compare)) {
				if (text.contains("looking") && text.contains("between")) {
					option = "between";
				} else {
					option = compare;
				}
			}
		}

		if (option != null) {
			return option;
		} else {
			return "no";
		}
	}
}
