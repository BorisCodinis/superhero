package superhero;
import java.util.UUID;
import java.util.Random;
import superhero.Superpower;

public class Villain {

	private static String[] creatures = {"Totengräber", "Majuntke", "Stanierowski", "Kobold"};
	
	public String getUniqueId() {
		return uniqueId;
	}
	
	public int getHealthPointsCurrent() {
		return healthPointsCurrent;
	}

	public void setHealthPointsCurrent(int healthPointsCurrent) {
		this.healthPointsCurrent = healthPointsCurrent;
	}

	public Superpower getPower() {
		return power;
	}

	public void createPower(Superpower power) {
		
	}

	public boolean getAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	private String createCreatureType() {
		int index = new Random().nextInt(creatures.length);
		this.creatureType = creatures[index];
		return this.creatureType;
	}
	
	private String uniqueId;
	private int healthPointsCurrent;
	private Superpower power;
	private boolean alive;
	private String creatureType;
	
	
	public Villain() {
		this.uniqueId = UUID.randomUUID().toString();
		this.healthPointsCurrent = 20;
		this.alive = true;
		this.creatureType = this.createCreatureType();
		this.power = new Superpower();
		
	}
	
	public void takeDamage(int dmg) {
		
		this.healthPointsCurrent -= dmg;
		System.out.println(this.creatureType + " bekommt " + Integer.toString(dmg) + " Schaden");
		if(this.healthPointsCurrent <= 0) {
			this.alive = false;
			System.out.println(this.creatureType + " wurde besiegt!");
		}
	}
	
	public int attack() {
		
		int hitProb = new Random().nextInt(100);
		System.out.println("Der Gegner greift mit " + this.power.getName() + " an");
		System.out.println(this.power.getDescription());
		if (hitProb >= 40) {
			System.out.println("Der Gegner trifft mit 5 Schaden");	
			return 5;
		}else {
			System.out.println(this.creatureType + " verfehlt dich aber.");	
			return 0;
		}
		

	}
	
	public boolean flee() {
		
		int fleeProb = new Random().nextInt(100);
		
		if (fleeProb >= 20) {
			return false;
		}else {
			System.out.println(this.creatureType + " ist erfolgreich geflohen");
			return true;
		}	
	}
	
}
