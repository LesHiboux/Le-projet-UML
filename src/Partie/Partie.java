
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
		int askJoueur = 0;
		while (askJoueur < 1)
		{
			System.out.println("combien de joueur ?");
			askJoueur = Integer.parseInt(saisie.readLine());			
		}
		int askPirate = -1;
		while (askPirate < 0)
		{
			System.out.println("combien de pirate ?");
			askPirate = Integer.parseInt(saisie.readLine());			
		}
		this.nbJoueurs = askJoueur;
		this.nbPirates = askPirate;
		
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


//------------------Partie Delfeil---------------------------------//
public boolean charger()
	{
		//Récupération du dossier de sauvegarde
		String nomSave;
		String nomDossier"./Sauvegardes/";
		Scanner saisie = new Scanner(System.in);
		System.out.println("Saisissez le nom de la sauvegarde");
		nomSave = Scanner.readLine();
		nomDossier+=nomSave;
		File dossierSave= new File(nomDossier);
		if(dossierSave.exists()==false)
		{
			System.out.println("erreur la sauvegarde n'existe pas");
			return false;
		}
		
		//Récupération du fichier joueur
		//File fJoueur = new File(nomDossier + "/joueur.hib");
		Joueur newJoueur;
		InputStream ipsJoueur = new FileInputStream(nomSave + "/joueur.hib");
		InputStreamReader ipsrJoueur = new InputStreamReader(ipsJoueur);
		BufferedReader brJoueur = new BufferedReader(ipsrJoueur);
		String ligneJoueur;
		while((ligneJoueur=brJoueur.readLine())!=null)
		{
			newJoueur=new Joueur(ligneJoueur);
			fileJoueurs.addLast(newJoueur);
		}
		brJoueur.close();

		//Récupération du fichier coffre
		Coffre newCoffre;
		InputStream ipsCoffre = new FileInputStream(nomSave + "/coffre.hib");
		InputStreamReader ipsrCoffre = new InputStreamReader(ipsCoffre);
		BufferedReader brCoffre = new BufferedReader(ipsrCoffre);
		String ligneCoffre;
		while((ligneCoffre=brCoffre.readLine())!=null)
		{
			newCoffre=new Coffre(ligneCoffre);
			listeCoffres.addLast(newCoffre);
		}
		brCoffre.close();

		//Récupération du fichier trésor
		InputStream ipsTresor = new FileInputStream(nomSave + "/tresor.hib");
		InputStreamReader ipsrTresor = new InputStreamReader(ipsTresor);
		BufferedReader brTresor = new BufferedReader(ipsrTresor);
		String ligneTresor;
		while((ligneTresor=brTresor.readLine())!=null)
		{
			int compt=0;
			int pos=0;
			int posSuiv=0;
			String info="";
			while (compt<2)
			{
				pos=posSuiv;
				posSuiv=ligneTresor.indexOf("|", pos+1);
				if(compt==0)
				{
					info=ligneTresor.substring(pos, posSuiv);
				}
				else
				{
					info=ligneTresor.substring(pos+1, posSuiv);
				}
				switch(compt)
				{
					case 0:
					{
						try
						{
							tresorPosX=Integer.parseInt(info);
						}
						catch(NumberFormatException e)
						{
							//throw new JoueurException("Erreur de construction", this);
						}
						compt++;
						break;
					}
					case 1:
					{
						try
						{
							tresorPosY=Integer.parseInt(info);
						}
						catch(NumberFormatException e)
						{
							//throw new JoueurException("Erreur de construction", this);
						}
						compt++;
						break;
					}
				}
			}
		}
		brTresor.close();

		//Récupération du fichier pirates
		InputStream ipsPirate = new FileInputStream(nomSave + "/pirate.hib");
		InputStreamReader ipsrPirate = new InputStreamReader(ipsPirate);
		BufferedReader brPirate = new BufferedReader(ipsrPirate);
		String lignePirate;
		while((lignePirate=brPirate.readLine())!=null)
		{
			Pirate newPirate;
			int compt=0;
			int pos=0;
			int posSuiv=0;
			String info="";
			while (compt<1)
			{
				pos=posSuiv;
				posSuiv=lignePirate.indexOf("|", pos+1);
				info=lignePirate.substring(pos, posSuiv);
				switch(compt)
				{
					case 0:
					{
						switch(info)
						{
							case "1":
							{
								newPirate=new Flibustier(lignePirate);
								filePirates.addLast(newPirate);
								break;
							}
							case "2":
							{
								newPirate=new Boucanier(lignePirate);
								filePirates.addLast(newPirate);
								break;
							}
						}
						compt++;
						break;
					}
				}
			}
		}
		brPirate.close();
		return true;
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
		if(dossierSave.exists()==false)
		{
			creer=dossierSave.mkdirs();
		}
		//Création du fichier Joueur
		File fJoueur = new File(dossier + "/joueur.hib");
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
		BufferedWriter bw = new BufferedWriter(new FileWriter(dossier + "/joueur.hib"));
		PrintWriter pWriter = new PrintWriter(bw);

		for(int i=0; i<fileJoueurs.size(); i++)
		{
			joueurActuel=fileJoueurs.get(i);
			pWriter.println(joueurActuel.toString());
		}
		pWriter.close();


		//Création fichier pirate
		File fPirate = new File(dossier + "/pirate.hib");
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
		BufferedWriter bw = new BufferedWriter(new FileWriter(dossier + "/pirate.hib"));
		PrintWriter pWriter = new PrintWriter(bw);
		for(int i=0; i<filePirates.size(); i++);
		{
			pirateActuel=filePirates.get(i);
			pWriter.println(pirateActuel.toString());
		}
		pWriter.close();

		//Création fichier coffre
		File fCoffre = new File(dossier + "/coffre.hib");
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
		BufferedWriter bw = new BufferedWriter(new FileWriter(dossier + "/coffre.hib"));
		PrintWriter pWriter = new PrintWriter(bw);
		for(int i=0; i<listeCoffres.size(); i++)
		{
			coffreCourant=listeCoffres.get(i);
			pWriter.println(coffreCourant.toString());
		}
		pWriter.close();

		//Création fichier trésor
		File fTresor = new File(dossier + "/tresor.hib");
		try
		{
			creer=fTresor.creatNewFile();
			fCoffre.close();
		}
		catch(IOException e)
		{
			System.out.println("erreur");
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(dossier + "/tresor.hib"));
		PrintWriter pWriter = new PrintWriter(bw);
		pWriter.println(tresorPosX + "|" + tresorposY + "|");
		pWriter.close();
		return true;
	}

//------------------Partie Delfeil---------------------------------//
	
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
		//generation de la carte a afficher
		char carte[][] = new char[12][12];
		int id;
		int xx;
		int yy;
		
		for (int c = nbCoffres; c!=0; c--)
		{
			Coffre temp = fileCoffres.get(p-1);
			xx = temp.getX();
			yy = temp.getY();
			carte[xx][yy] = "C";
		}
		
		for (int p = nbPirates; p!=0; p--)
		{
			Pirate temp = filePirates.get(p-1);
			xx = temp.getX();
			yy = temp.getY();
			if (temp instanceof Boucanier) id = "B";
			else id = "F";
			carte[xx][yy] = id;
		}
		
		for (int j = nbJoueurs; j!=0; j--)
		{
			Joueur temp = fileJoueurs.get(j-1);
			xx = temp.getX();
			yy = temp.getY();
			id =  Character.forDigit(temp.getId(), 10);
			carte[xx][yy] = id;
		}
		
		for (int x=0; x<12; x++)
		{
			for (int y=0; y<12; y++)
			{
				if (carte[x][y] == "") carte[x][y] = "+";
			}
		}
		//affichage
		System.out.println("----------Carte----------");
		for (int x=0; x<12; x++)
		{
			for (int y=0; y<12; y++)
			{
				System.out.print(carte[x][y]);
			}
			System.out.println();
		}
		System.out.println("-------------------------");
	}
	
	
	
//------------------Partie Delfeil---------------------------------//
	public void deroulePartie()
	{
		boolean victoire==false;
		boolean defaite==false;
		while(victoier==false || defaite==false)
		{
			for(int i=0; i<fileJoueurs.size(); i++)
			{
				joueurCourant=fileJoueurs.pollFirst();
				if(joueurCourant==NULL)
				{
					//Si plus aucuns joueurs dans la file de joueurs défaite
					defaite=true;
					break;
				}
				else
				{
					victoire=tourJoueur();
					if(victoire)
					{
						break;
					}
					if(joueurCourant!=NULL)
					{
						//Si le jouer a survécu à son tour (n'est pas mort pendant son combat)
						//On le replace à la fin de la file de joueurs
						fileJoueurs.addLast(joueurCourant);
					}
				}
			}
			if(victoire || defaite)
			{
				break;
			}

			for(int j=0; j<filePirates.size(); j++)
			{
				pirateCourant=filePirates.pollFirst();
				if(pirateCourant!=NULL)
				{
					//Si il reste des pirates,
					//on lance le tour du pirate courant
					tourPirate();
					//Lors du tour du pirate, si celui ci est mort, pirateCourant à été reset à NULL.
					if(pirateCourant!=NULL)
					{
						//Si le pirateCourant n'as pas été supprimé, il est rajouté en fin de file.
						filePirates.addLast(pirateCourant);
					}
				}
			}
		}
		
		if(defaite)
		{
			System.out.println("Git Gud");
		}
		else if(victoire)
		{
			System.out.println("Bravo");
		}
	}

	public void tourPirate()
	{
		pirateCourant.deplacer();
		boolean vivant=pirateCourant.attaquer(fileJoueurs);
		if(vivant == false)
		{
			//Si le pirate est mort lors du combat, on suprime le pirate courant
			pirateCourant=NULL;
		}
		return;
	}

	public boolean tourJoueur()
	{
		//Retourne true si victoire, false sinon
			//Déplacement
		Scanner saisie = new Scanner(System.in);
		System.out.println("Choisissez une direction 1 2 3 4 6 7 8 9 s q");
		String choix=saisie.readLine();
		if(choix=="s")
		{
			boolean saveValide=sauvegarde();
		}
		while(!joueurCourant.deplacer(choix))
		{
			System.out.println("Choisissez une direction 1 2 3 4 6 7 8 9 s q");
			choix=saisie.readLine();
			if(choix=="s")
			{
				boolean saveValide=sauvegarde();
			}
			/*else if(choix=="q")
			{

			}*/
		}

			//Combat
		boolean vivant=joueurCourant.combat(filePirates);
		if(vivant==false)
		{
			joueurCourant=NULL;
			return false;
		}
		//Si le joueur est vivant après son combat
			//Ouverture du coffre
		joueurCourant.ouvrirCoffre(listeCoffres);

			//Vérif si le  joueur a la pelle et si le coffre au trésor est sur sa position
		if(joueurCourant.getPelle()=true)
		{
			int jPosX=joueurCourant.getPosX();
			int jPosY=joueurCourant.getPosY();
			if(tresorPosX==jPosX && tresorPosY==jPosY)
			{
				return true;
			}
		}
		return false;
	}

//------------------Fin Partie Delfeil---------------------------------//
}
