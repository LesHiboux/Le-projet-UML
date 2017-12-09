
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
		this.fileJoueurs =new LinkedList<Joueur>();
		this.filePirates =new LinkedList<Pirate>(); 
		this.fileCoffres =new LinkedList<Coffre>(); 
		
		//Inputs
		Scanner saisie = new Scanner(System.in);
		string askJoueur = "0";
		while (askJoueur < "1")
		{
			System.out.println("combien de joueur ?");
			askJoueur = saisie.readLine();			
		}
		string askPirate = "-1";
		while (askPirate < "0")
		{
			System.out.println("combien de pirate ?");
			askPirate = saisie.readLine();			
		}
		this.nbJoueurs = Integer.parseInt(askJoueur);
		this.nbPirates = Integer.parseInt(askPirate);
		
		//Coords generator
		ArrayList<Pair> coords = new ArrayList<Pair>();
		Pair nul = new Pair(-1, -1);
		coords.add(nul);
			//Players
		while (askJoueur != 0)
		{
			int nx = -1, int ny = -1;
			Pair ncoord = new Pair(nx, ny);
			while (ncoord.inside(coords))
			{
				Random jet= new Random();
				int nx = jet.nextInt(11);
				int ny = jet.nextInt(11);
				ncoord = new Pair(nx, ny);

			}
			Joueur nJoueur = new Joueur(askJoueur, ncoord.getX(), ncoord.getY());
			coords.add(ncoord);
			fileJoueurs.addFirst(nJoueur);
			askJoueur = askJoueur-1;
		}
			//Pirates
		while (askPirate != 0)
		{
			int nx = -1, int ny = -1;
			Pair ncoord = new Pair(nx, ny);
			while (ncoord.inside(coords))
			{
				Random jet= new Random();
				int nx = jet.nextInt(11);
				int ny = jet.nextInt(11);
				ncoord = new Pair(nx, ny);

			}
			Random FouB = new Random();
			int yolo = FouB.nextInt(1);
			switch (yolo)
			{
				case 0:
				{
					Pirate nPirate = new Boucanier(ncoord.getX(), ncoord.getY());
					coords.add(ncoord);
					filePirate.addFirst(nPirate);

				}
				case 1:
				{
					Pirate nPirate = new Flibustier(ncoord.getX(), ncoord.getY());
					coords.add(ncoord);
					filePirate.addFirst(nPirate);

				}
			}
			askPirate = askPirate-1;
		}
			//Chests
		
		for (j=0; j<nbJoueurs; j++)
		{
			for (c=0; c<3; c++)
			{
				int nx = -1, int ny = -1;
				Pair ncoord = new Pair(nx, ny);
				while (ncoord.inside(coords))
				{
					Random jet= new Random();
					int nx = jet.nextInt(11);
					int ny = jet.nextInt(11);
					ncoord = new Pair(nx, ny);

				}
				
				if (c==0)
				{
					Coffre nCoffre = new Coffre(ncoord.getX(), ncoord.getY(), True, False, False);
					coords.add(ncoord);
					fileCoffres.addFirst(nCoffre);
				}
				else if (c==1)
				{
					Coffre nCoffre = new Coffre(ncoord.getX(), ncoord.getY(), False, True, False);
					coords.add(ncoord);
					fileCoffres.addFirst(nCoffre);
				}
				else if (c==2)
				{
					Coffre nCoffre = new Coffre(ncoord.getX(), ncoord.getY(), False, False, True);
					coords.add(ncoord);
					fileCoffres.addFirst(nCoffre);
				}
			}
		}
			//Treasure
		Random jet= new Random();
		this.tresorPosX = jet.nextInt(11);
		this.tresorPosy = jet.nextInt(11);
		
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
		System.out.println("           Welcome to:            ");
		System.out.println("----------TRESURE HUNTER----------");
		System.out.println("*** Type 1 to start a new game ***");
		System.out.println("*** Type 2 to load an old game ***");
		System.out.println("******** Type q/Q to quit ********");
		System.out.println("----------------------------------");
		
	}

	public void afficherCarte(LinkedList fileJoueurs, LinkedList filePirates, LinkedList fileCoffres)
	{
		char carte[][] = new char[12][12];
		int id;
		int xx;
		int yy;
		for (int j = nbJoueurs; j!=0; j++)
		{
			Joueur temp = fileJoueurs.get(j-1);
			xx = temp.getX();
			yy = temp.getY();
			id = temp.getId();
			carte[xx][yy] = 
		}
	}
}
