
import java.util.Scanner;
import java.io.File;

public class Partie implements Affichage;
{

	//Attributs
	
	private LinkedList<Joueur> fileJoueurs;
	private int nbJoueurs;
	
	private LinkedList<Pirate> filePirates;
	private int nbPirates;

	private LinkedList<Coffre> listeCoffres;
	private int nbCoffres;

	private int tresorPosX;
	private int tresorposY;

	//Fonctions
	
	public Partie()
	{
		//je te construis et dans le main appel :  Affichage jeu = new Partie();//
	}
	
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
	
	public void afficherAccueil()
	{
		System.out.println("Welcome to :");
		System.out.println("TRESURE HUNTER");
	}

	public void afficherCarte(LinkedList fileJoueurs, LinkedList filePirates, LinkedList fileCoffres)
	{
		char carte[][] = new char[12][12];
		int nbJoueur = fileJoueurs.size();
		int nbPirate = filePirates.size();
		int nbCoffre = fileCoffres.size();
		
		/*for (i -> size)
			recup joueur/pirate/coffre 
			recup coord -> mettre dans carte
		faire pour chaque file
		
		afficher le tout*/
	}
}
