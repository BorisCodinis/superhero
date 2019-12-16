package superhero;
import java.util.UUID;
import java.util.Random;

public class Superpower {

	private static String[] superpowers = {"Eispfeil","Roundhousekick","Bananengeschoss","Blitz"};
	private static String[] descriptions = {"Schieﬂt einen eisigen Eispfeil zwischen die Augen des Zieles",
			"Trifft das Ziel mit Schwung am Hinterkopf",
			"Im Eifer des Gefechts merkt das Ziel nicht, dass die Banane eine Pistole ist. Die Kugel trifft den linken kleinen Zeh.",
			"Zeusartig Donnern enorme Blitze auf das Ziel ein"};

	private String uniqueId;
	
	private String name;
	private String beschreibung;
	
	private void selectPowers() {
		int index = new Random().nextInt(superpowers.length);
		this.name = superpowers[index];
		this.beschreibung = descriptions[index];
	}
	
	public Superpower() {
		this.uniqueId = UUID.randomUUID().toString();
		this.selectPowers();
	
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.beschreibung;
	}
}
