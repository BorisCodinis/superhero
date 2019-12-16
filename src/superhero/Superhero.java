package superhero;
import java.util.UUID;
import java.util.Random;
import superhero.Superpower;
import java.util.Scanner;


public class Superhero {
	
	//Attribute
	private String uniqueId;
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getHealthPointsMax() {
		return healthPointsMax;
	}

	public void setHealthPointsMax(int healthPointsMax) {
		this.healthPointsMax = healthPointsMax;
	}

	public int getHealthPointsCurrent() {
		return healthPointsCurrent;
	}

	public void setHealthPointsCurrent(int healthPointsCurrent) {
		this.healthPointsCurrent = healthPointsCurrent;
	}

	public int getExperiencePoints() {
		return experiencePoints;
	}

	public void setExperiencePoints(int experiencePoints) {
		this.experiencePoints = experiencePoints;
	}

	public Superpower[] createPowers() {
		Superpower[] powers = new Superpower[3];
		for(int i = 0; i < 3; i++) {
			powers[i] = new Superpower();
		}
		return powers;
	}

	public void setPowers(Superpower[] powers) {
		this.powers = powers;
	}

	public Boolean getReadyToFight() {
		return readyToFight;
	}

	public void setReadyToFight(Boolean readyToFight) {
		this.readyToFight = readyToFight;
	}

	public boolean getInFight() {
		return inFight;
	}

	public void setInFight(boolean inFight) {
		this.inFight = inFight;
	}

	public boolean getAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}


	public String getUniqueId() {
		return uniqueId;
	}

	private String Name;
	private int healthPointsMax; 
	public int healthPointsCurrent;
	private int experiencePoints;
	private Superpower[] powers;
	private Boolean readyToFight;
	private boolean inFight;
	private boolean alive; 
	
	//Konstruktor 

	public Superhero(String name) {
		
		this.Name = name;
		this.healthPointsMax = 50;
		this.healthPointsCurrent = 50;
		this.uniqueId = UUID.randomUUID().toString();
		this.readyToFight = true;
		this.inFight = false;
		this.alive = true;
		this.powers = createPowers();
	}
	
	//Operatoren
	
	public void takeDmg(int dmg) {
		if(dmg > 0) {
			System.out.println("Der Held verliert " +
					Integer.toString(dmg) +
					" Lebenspunkte und hat noch " +
					(this.healthPointsCurrent - dmg));
			this.healthPointsCurrent -= dmg;
		
			if (this.healthPointsCurrent <= 0) {
				System.out.println("Der Held wurde besiegt");
				this.alive = false;
			}
		}else {
			System.out.println("Der Gegner trifft nicht.");
		}
	}
	
	public  void dance() {
		System.out.println(this.Name + " Tanzt Fabelhaft");
	}
	
	public int attack() {
		int powerNr = new Random().nextInt(3);
		Superpower power = powers[powerNr];
		
		int hitDmg = new Random().nextInt(5);
		int critProb = new Random().nextInt(100);
		if(hitDmg != 0) {
			if(critProb >= 2*this.experiencePoints ) {
				hitDmg = hitDmg * 2;
				System.out.println("Der Held trifft KRITISCH!!");
			}
			System.out.println("Der Held greift mit der Fähigkeit " + power.getName() + " an!");
			System.out.println(power.getDescription());
			return hitDmg;
		}else {
			System.out.println("Du bist zu schwach und machst keinen Schaden.");
			return 0;
		}			
	}
	
	private void showSuperpowers() {

		String menuItems[] = {"(1)\t " + this.powers[0].getName(),
				"(2)\t " + this.powers[1].getName(),
				"(3)\t " + this.powers[2].getName()
				};

		System.out.println("\nSuperpowers\n");
		for (int i = 1; i < menuItems.length; i++) {
			System.out.println(menuItems[i]);
		}
		
		

	}
	
	public  boolean flee() {
		int fleeProb = new Random().nextInt(100);
		if(fleeProb >= 20) {
			System.out.println("Der feige Held konnte fliehen.");
			return true;
		}else {
			System.out.println("Dem helden ist das Fliehen nicht gelungen");
			return false;
		}
	}
	
	public  void sleep() {

		if (this.healthPointsCurrent < this.healthPointsMax) {
			System.out.println("Der Held " +
						this.Name +
						" hat nur noch " +
						Integer.toString(this.healthPointsCurrent) +
					"Lebenspunkte. Er legt sich Schlafen" );
			this.healthPointsCurrent = this.healthPointsMax;
		}else {
			System.out.println("Dein Held ist noch bei vollem Bewusstsein. Lass ihn kämpfen!!");
		}
	}

}