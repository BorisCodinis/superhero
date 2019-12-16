package superhero;

import superhero.Villain;
import java.util.Scanner;
import java.util.Set;
import java.awt.print.Printable;
import java.util.ArrayList;
import superhero.Superhero;
import java.util.Random;

/**
* @author vemaj
*
*/
public class SuperheroApp {

	
	static ArrayList<Superhero> heroes = new ArrayList<Superhero>();
	private static Scanner scannerMenu = new Scanner(System.in);
 
	/**
	 * @param args mainklasse
	 */
	public static void main(String[] args) {

		while (true) {
			
			showMenu();
			int choice = readUserInput();
			handle(choice);
			
			System.out.println("====================");
		}
	}

	/**
	 * 
	 * @return
	 */
	private static int readUserInput() {
		System.out.print("\nBitte, geben Sie die Nummer des gewaehlten Menueeintrags ein:\t");
		int choiceInternal = scannerMenu.nextInt();

		return choiceInternal;
	}

	/**
	 * 
	 * @param choice
	 */
	private static void handle(int choice) {
		switch (choice) {
		case 1: // add hero
			createSuperhero();
			break;
		case 2: // list single hero detail
			if (heroes.size() == 0) {
				System.out.println("Keine Helden vorhanden. Erstelle zuerst welche!");
				break;
			}
			printSingleHeroDetail();
			break;
		case 3: // list all hero detail
			if (heroes.size() == 0) {
				System.out.println("Keine Helden vorhanden. Erstelle zuerst welche!");
				break;
			}
			printAllHeroDetails();
			break;
		case 4: // remove hero
			removeHero();
			break;
		case 5: // start game
			startAdventure();
			break;
		case 6: // exit
			//exitGame();
			
			break;
		default: {
			System.out.println("Ungueltige Eingabe. Bitte überpruefen Sie Ihre Eingabe");
			showMenu();
		}
			break;

		}
	}



	/**
	 * 
	 */
	private static void showMenu() {

		String menuItems[] = { "",
				"(1)\t Superheld anlegen",
				"(2)\t Daten eines Superhelden ausgeben",
				"(3)\t Daten aller Superhelden ausgeben",
				"(4)\t Superheld aus dem Team nehmen",
				"(5)\t Ein Abenteuer bestreiten",
				"(6)\t Beenden"};

		System.out.println("\nSuperheroes 1.0\n");
		for (int i = 1; i < menuItems.length; i++) {
			System.out.println(menuItems[i]);
		}

	}
	
	
	private static void showAdventureMenu() {

		String menuItems[] = { "",
				"(1)\t Erkunden",
				"(2)\t Tanzen",
				"(3)\t Schlafen",
				"(4)\t Abenteuer beenden"};

		System.out.println("\nAdventure MenÜ\n");
		for (int i = 1; i < menuItems.length; i++) {
			System.out.println(menuItems[i]);
		}

	}
	
	private static void showFightMenu() {

		String menuItems[] = { "",
				"(1)\t Kämpfen",
				"(2)\t Fliehen"};

		System.out.println("\nKampf MenÜ\n");
		for (int i = 1; i < menuItems.length; i++) {
			System.out.println(menuItems[i]);
		}

	}
	
	private static void startAdventure() {
		
		boolean inAdventure = true;
		while (inAdventure) {
			showAdventureMenu();
			int choice = scannerMenu.nextInt();
			inAdventure = handleAdventure(choice);
		}
	}
	

	
	private static boolean handleAdventure(int choice) {
		
		boolean inAdventure = true;
		switch(choice) {
		case 1:
			explore();
			break;
		case 2: 
			tanzen();
			break;
		case 3:
			schlafen();
			break;
		case 4:
			inAdventure = false;
			break;
		default:
			System.out.println("Ungueltige Eingabe. Bitte überpruefen Sie Ihre Eingabe");
			showAdventureMenu();
		}
		return inAdventure;
	}
	
	private static void explore() {
		
		Random attackProb = new Random();
		if(attackProb.nextInt(100) >= 50) {
			startFight();
		}else {
			System.out.println("Die Helden haben ohne Angriffe ein neues Gebiet erforscht.");
		}
		
		
	}
	
	private static void startFight() {
		Scanner scanner = new Scanner(System.in);
		Villain gegner = new Villain();
		System.out.println("Ein Gegner greift Ihr Team an. Welcher Held soll gegen ihn antreten?");
		listHeroes();
		String heroname = scanner.nextLine();
		Superhero hero = getHero(heroname);
		if (hero.getReadyToFight()) {
			System.out.println("Der Held " + hero.getName() + " wird gegen den Gegner antreten");
			fight(hero);
		}
		
	}
	
	public static void fight(Superhero hero) {
		showFightMenu();
		
		Villain gegner = new Villain();
		while(gegner.getAlive() && hero.getAlive()) {
			int choice = readUserInput();
			boolean flee = false;
			switch (choice) {
			case 1:
					boolean fleeVillain = gegner.flee();
					if(!fleeVillain) {
						hero.takeDmg(gegner.attack()); 
					}
					gegner.takeDamage(hero.attack());
					if(!gegner.getAlive())
						hero.setExperiencePoints(hero.getExperiencePoints() + 1);
				break;
			case 2:
				flee = hero.flee();
				if(!flee)
					fight(hero);
				else
					break;
			default:
				System.out.println("Ungueltige Eingabe. Bitte überpruefen Sie Ihre Eingabe");
				showFightMenu();
			}
			if(flee){
				break;
			}
		}
		
	}
	
	private static void createSuperhero() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Wie soll der Name des Spielers lauten?");
		String heroname = scanner.nextLine();
		Superhero hero = new Superhero(heroname);
		if(heroes.size() <= 4) {
			heroes.add(hero);
			System.out.println("Hero " + hero.getName() + " zu deinen Helden hinzugefügt" );
		}else
			System.out.println("Du hast schon 5 Helden. Lösche einen, um einen hinzuzufügen");
	}

	private static void printAllHeroDetails() {
		for (Superhero hero: heroes) {
			showHeroDetail(hero.getName());
		}
	}
	
	private static void removeHero() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcher Held soll gefeuert werden?");
		listHeroes();
		String heroname = scanner.nextLine();
		heroes.remove(getHero(heroname));
		
	}
	
	private static void printSingleHeroDetail() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Zu welchem Spieler möchten sie Informationen haben?");
		listHeroes();
		String heroname = scanner.nextLine();
		showHeroDetail(heroname);
	}
	
	private static void listHeroes() {
		for(Superhero hero: heroes) {
			System.out.println(hero.getName());
		}
		
	}
	
	private static void showHeroDetail(String name) {
		Superhero currentHero = getHero(name);
		System.out.println("Name: " + currentHero.getName() + "\n" + 
		"Max Lebenspunkte: " + currentHero.getHealthPointsMax() + "\n" +
		"Erfahrungspunkte: " + currentHero.getExperiencePoints()); //TODO: show all attributes, pretty print
		
	}
	
	private static void tanzen() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welchen Helden wollen sie tanzen lassen?");
		listHeroes();
		String heroname = scanner.nextLine();
		Superhero hero = getHero(heroname);
		hero.dance();
		
	}
		
	private static void schlafen() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcher Held soll sich eine Runde schalfen legen?");
		listHeroes();
		String heroname = scanner.nextLine();
		Superhero hero = getHero(heroname);
		hero.sleep();
	}
	
	private static Superhero getHero(String name) {
		for(int i = 0 ; i < heroes.size() ; i++) {
			if(heroes.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
				return heroes.get(i);
			}
			
		}
		System.out.println("Kein Held mit dem Namen \"" + name + "\" vorhanden");
		return null; //TODO exception
		
	}
}

	
	


