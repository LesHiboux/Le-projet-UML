
import java.util.Scanner;
import java.io.File;

public class Partie;
{

	//Attributs

	private Carte carte;
	
	private LinkedList<Joueur> fileJoueurs;
	private int nbJoueurs;
	
	private LinkedList<Pirate> filePirates;
	private int nbPirates;

	private LinkedList<Coffre> listeCoffres;
	private int nbCoffres;

	private int tresorPosX;
	private int tresorposY;

	//Fonctions
	public boolean sauvegarde()
	{
		String name;
		String dossier="./Sauvegardes/";
		Scanner saisie = new Scanner(System.in);
		System.out.println("Saisissez le nom de la sauvegarde");
		name=saisie.readLine();
		dossier+=name;
		File dossierSave = new File(dossier);
		if(dossierSave.exists()==false)
		{
			dossierSave.mkdirs();
		}
	}
}
