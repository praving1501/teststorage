package stanfordChatBot.testbot;

import java.util.ArrayList;

public class QueryGenerator {
	
	private static String getData = "select";
	private static String updateData = "update";
	private static String table = "from";
	private static String limit = "where in (";
	private static String carTable = "carwale.car_info";
	private static String clientTable = "carwale.client_info";
	private static String availability = "availablity = true";
	
	
	public static void onlyNames(String showCase) {
		System.out.println("Generating Query");
		String qry= getData+" * "+table+" "+carTable+" where "+availability;
		SQLConnector.dataRequest(qry);
	}
	public static void allDetails(int whichData) {
		String qry = null;
		switch (whichData) {
		case 1:
			qry = getData + " * " + table + " " + carTable;
			break;
		case 2:
			qry = getData + " brand, model " + table + " " + clientTable;
		}
	}
	public static void parDetails(ArrayList<String> a) {
		
	}
	public static void range() {
		
		
	}
	public static void buy() {
		String serialNo = null;
		String qry = null;
		qry = updateData+" "+carTable+" set availablity = true where serialno in (10004)";
		SQLConnector.dataRequest(qry);
		
	}
	public static void sell() {
		
	}
}











