package stanfordChatBot.testbot;

import java.util.Scanner;

public class Showroom {
	
	static boolean convo = true;
	
	public static void enter() {
		Showroom.showroomEmployee();
	}
	
	public static void showroomEmployee() {
		
		ChatBot talk = new ChatBot();
		Scanner sc = new Scanner(System.in);
		System.out.println("How can i help you today?");
		while(convo==true) {
			talk.talk(sc.nextLine());
		}
	}
}
