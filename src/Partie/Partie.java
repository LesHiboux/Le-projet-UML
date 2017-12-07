
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Partie implements Affichage;
{

	//Attributs
	private LinkedList<Joueur> fileJoueurs;
	private int nbJoueurs;
	private Joueur joueurCourant;

	private LinkedList<Pirate> filePirates;
	private int nbPirates;
	private Pirate pirateCourant;

	private LinkedList<Coffre> listeCoffres;
	private int nbCoffres;

	private int tresorPosX;
	private int tresorposY;

	//Fonctions
	public Partie()
	{
		//je te construis et dans le main appel :  Affichage jeu = new Partie();//
	}

	public boolean charger()
	{
		
	}

	public boolean sauvegarde()
	{

		//Création du dossier ./Sauvegardes/nomSave/
		String ligne;
		boolean creer;
		String name;
		String dossier="./Sauvegardes/";
		Scanner saisie = new Scanner(System.in);
		System.out.println("Saisissez le nom de la sauvegarde");
		name=saisie.readLine();
		dossier+=name;
		File dossierSave = new File(dossier);
		dossierSave.close();
		if(dossierSave.exists()==false)
		{
			creer=dossierSave.mkdirs();
		}
		//Création du fichier Joueur
		File fJoueur = new File(dossierSave + "/joueur.hib");
		try
		{
			creer=fJoueur.creatNewFile();
			fJoueur.close();
		}
		catch(IOException e)
		{
			System.out.println("erreur");
		}
		Joueur joueurActuel;
		BufferedWriter bw = new BufferedWriter(new FileWriter(dossierSave + "/joueur.hib"));
		PrintWriter pWriter = new PrintWriter(bw);

		for(int i=0; i<fileJoueurs.size(); i++)
		{
			joueurActuel=fileJoueurs.get(i);
			pWriter.println(joueurActuel.toString());
		}
		pWriter.close();


		//Création fichier pirate
		File fPirate = new File(dossierSave + "/pirate.hib");
		try
		{
			creer=fPirate.creatNewFile();
			fPirate.close();
		}
		catch(IOException e)
		{
			System.out.println("erreur");
		}
		Pirate pirateActuel;
		BufferedWriter bw = new BufferedWriter(new FileWriter(dossierSave + "/pirate.hib"));
		PrintWriter pWriter = new PrintWriter(bw);
		for(int i=0; i<filePirates.size(); i++);
		{
			pirateActuel=filePirates.get(i);
			pWriter.println(pirateActuel.toString());
		}
		pWriter.close();

		//Création fichier coffre
		File fCoffre = new File(dossierSave + "/coffre.hib");
		try
		{
			creer=fCoffre.creatNewFile();
			fCoffre.close();
		}
		catch(IOException e)
		{
			System.out.println("erreur");
		}
		Coffre coffreCourant;
		BufferedWriter bw = new BufferedWriter(new FileWriter(dossierSave + "/coffre.hib"));
		PrintWriter pWriter = new PrintWriter(bw);
		for(int i=0; i<listeCoffres.size(); i++)
		{
			coffreCourant=listeCoffres.get(i);
			pWriter.println(coffreCourant.toString());
		}
		pWriter.close();

		//Création fichier trésor
		File fTresor = new File(dossierSave + "/tresor.hib");
		try
		{
			creer=fTresor.creatNewFile();
			fCoffre.close();
		}
		catch(IOException e)
		{
			System.out.println("erreur");
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(dossierSave + "/tresor.hib"));
		PrintWriter pWriter = new PrintWriter(bw);
		pWriter.println(tresorPosX + "|" + tresorposY + "|");
		pWriter.close();
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
