

import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Partie
{

	//Attributs
	private LinkedList<Joueur> fileJoueurs;
	private int nbJoueurs;
	private Joueur joueurCourant;

	private LinkedList<Pirate> filePirates;
	private int nbPirates;
	private Pirate pirateCourant;
	private Flibustier flibCourant;
	private Boucanier boucCourant;
	

	private LinkedList<Coffre> listeCoffres;
	private int nbCoffres;

	private int tresorPosX;
	private int tresorPosY;

	private int n=12;

	//Fonctions
	public Partie()
	{
		//je te construis et dans le main appel :  Affichage jeu = new Partie();//
		this.fileJoueurs =new LinkedList<Joueur>();
		this.filePirates =new LinkedList<Pirate>(); 
		this.listeCoffres =new LinkedList<Coffre>(); 
		
		//Inputs
		Scanner saisie = new Scanner(System.in);
		int askJoueur = 0;
		while (askJoueur < 1)
		{
			System.out.println("combien de joueur ?");
			askJoueur = Integer.parseInt(saisie.nextLine());			
		}
		int askPirate = -1;
		while (askPirate < 0)
		{
			System.out.println("combien de pirate ?");
			askPirate = Integer.parseInt(saisie.nextLine());			
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
			int nx = -1; int ny = -1;
			Pair ncoord = new Pair(nx, ny);
			while (ncoord.inside(coords))
			{
				Random jet= new Random();
				nx = jet.nextInt(11);
				ny = jet.nextInt(11);
				ncoord = new Pair(nx, ny);

			}
			Joueur nJoueur = new Joueur(askJoueur, ncoord.getX(), ncoord.getY());
			System.out.println("condtr joueur" + nJoueur.getIdJoueur());
			coords.add(ncoord);
			fileJoueurs.addFirst(nJoueur);
			askJoueur = askJoueur-1;
		}
			//Pirates
		while (askPirate != 0)
		{
			int nx = -1; int ny = -1;
			Pair ncoord = new Pair(nx, ny);
			while (ncoord.inside(coords))
			{
				Random jet= new Random();
				nx = jet.nextInt(11);
				ny = jet.nextInt(11);
				ncoord = new Pair(nx, ny);

			}
			Random FouB = new Random();
			int yolo = FouB.nextInt(2);
			switch (yolo)
			{
				case 0:
				{
					Pirate nPirate = new Boucanier(ncoord.getX(), ncoord.getY());
					coords.add(ncoord);
					filePirates.addFirst(nPirate);
					System.out.println("Constr Bouc" + nPirate.toString());
					break;
				}
				case 1:
				{
					Pirate nPirate = new Flibustier(ncoord.getX(), ncoord.getY());
					coords.add(ncoord);
					filePirates.addFirst(nPirate);
					System.out.println("Constr Flib" + nPirate.toString());
					break;
				}
			}
			askPirate = askPirate-1;
		}
			//Chests
		
		for (int j=0; j<this.nbJoueurs; j++)
		{
			for (int c=0; c<3; c++)
			{
				int nx = -1; int ny = -1;
				Pair ncoord = new Pair(nx, ny);
				while (ncoord.inside(coords))
				{
					Random jet= new Random();
					nx = jet.nextInt(11);
					ny = jet.nextInt(11);
					ncoord = new Pair(nx, ny);

				}
				
				if (c==0)
				{
					Coffre nCoffre = new Coffre(ncoord.getX(), ncoord.getY(), true, false, false);
					coords.add(ncoord);
					listeCoffres.addFirst(nCoffre);
				}
				else if (c==1)
				{
					Coffre nCoffre = new Coffre(ncoord.getX(), ncoord.getY(), false, true, false);
					coords.add(ncoord);
					listeCoffres.addFirst(nCoffre);
				}
				else if (c==2)
				{
					Coffre nCoffre = new Coffre(ncoord.getX(), ncoord.getY(), false, false, true);
					coords.add(ncoord);
					listeCoffres.addFirst(nCoffre);
				}
			}
		}
			//Treasure
		Random jet= new Random();
		this.tresorPosX = jet.nextInt(11);
		this.tresorPosY = jet.nextInt(11);
		
	}


//------------------Partie Delfeil---------------------------------//
/*public boolean charger()
	{
		//Récupération du dossier de sauvegarde
		String nomSave;
		String nomDossier="./Sauvegardes/";
		Scanner saisie = new Scanner(System.in);
		System.out.println("Saisissez le nom de la sauvegarde");
		nomSave = saisie.nextLine();
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
		BufferedWriter bw;
		PrintWriter pWriter;

		//Création du dossier ./Sauvegardes/nomSave/
		String ligne;
		boolean creer;
		String name;
		String dossier="./Sauvegardes/";
		Scanner saisie = new Scanner(System.in);
		System.out.println("Saisissez le nom de la sauvegarde");
		name=saisie.nextLine();
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
			creer=fJoueur.createNewFile();
		}
		catch(IOException e)
		{
			System.out.println("erreur");
		}
		Joueur joueurActuel;
		bw = new BufferedWriter(new FileWriter(dossier + "/joueur.hib"));
		pWriter = new PrintWriter(bw);

		for(int j = 0; j<fileJoueurs.size(); j++)
		{
			joueurActuel=fileJoueurs.get(j);
			pWriter.println(joueurActuel.toString());
		}
		pWriter.close();


		//Création fichier pirate
		File fPirate = new File(dossier + "/pirate.hib");
		try
		{
			creer=fPirate.createNewFile();
		}
		catch(IOException e)
		{
			System.out.println("erreur");
		}
		Pirate pirateActuel;
		int P;
		bw = new BufferedWriter(new FileWriter(dossier + "/pirate.hib"));
		pWriter = new PrintWriter(bw);
		for(P = 0; P<filePirates.size(); P++);
		{
			pirateActuel=filePirates.get(P);
			pWriter.println(pirateActuel.toString());
		}
		pWriter.close();

		//Création fichier coffre
		File fCoffre = new File(dossier + "/coffre.hib");
		try
		{
			creer=fCoffre.createNewFile();
		}
		catch(IOException e)
		{
			System.out.println("erreur");
		}
		Coffre coffreCourant;
		bw = new BufferedWriter(new FileWriter(dossier + "/coffre.hib"));
		pWriter = new PrintWriter(bw);
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
			creer=fTresor.createNewFile();
		}
		catch(IOException e)
		{
			System.out.println("erreur");
		}
		bw = new BufferedWriter(new FileWriter(dossier + "/tresor.hib"));
		pWriter = new PrintWriter(bw);
		pWriter.println(tresorPosX + "|" + tresorPosY + "|");
		pWriter.close();
		return true;
	}*/

//------------------ Fin Partie Delfeil---------------------------------//
	
	public void afficherAccueil()
	{
		System.out.println("           Welcome to:            ");
		System.out.println("----------TRESURE HUNTER----------");
		System.out.println("*** Type 1 to start a new game ***");
		System.out.println("*** Type 2 to load an old game ***");
		System.out.println("******** Type q/Q to quit ********");
		System.out.println("----------------------------------");
		
	}

	public void afficherCarte()
	{
		//generation de la carte a afficher
		char carte[][] = { {'+','+','+','+','+','+','+','+','+','+','+','+'},
					{'+','+','+','+','+','+','+','+','+','+','+','+'},
					{'+','+','+','+','+','+','+','+','+','+','+','+'},
					{'+','+','+','+','+','+','+','+','+','+','+','+'},
					{'+','+','+','+','+','+','+','+','+','+','+','+'},
					{'+','+','+','+','+','+','+','+','+','+','+','+'},
					{'+','+','+','+','+','+','+','+','+','+','+','+'},
					{'+','+','+','+','+','+','+','+','+','+','+','+'},
					{'+','+','+','+','+','+','+','+','+','+','+','+'},
					{'+','+','+','+','+','+','+','+','+','+','+','+'},
					{'+','+','+','+','+','+','+','+','+','+','+','+'},
					{'+','+','+','+','+','+','+','+','+','+','+','+'} };
		char id;
		int xx;
		int yy;
		
		for (int c = listeCoffres.size()-1; c>=0; c--)
		{
			Coffre tempC = listeCoffres.get(c);
			xx = tempC.getPosX();
			yy = tempC.getPosY();
			carte[xx][yy] = 'C';
		}
		
		for (int p = filePirates.size()-1; p>=0; p--)
		{
			Pirate tempP = (Pirate) filePirates.get(p);
			xx = tempP.getPosX();
			yy = tempP.getPosY();
			if (tempP instanceof Boucanier) id = 'B';
			else id = 'F';
			carte[xx][yy] = id;
		}
		
		for (int j = fileJoueurs.size()-1; j>=0; j--)
		{
			Joueur tempJ =(Joueur) fileJoueurs.get(j);
			xx = tempJ.getPosX();
			yy = tempJ.getPosY();
			id =  tempJ.getIdJoueur();
			carte[xx][yy] = id;
		}
		xx=joueurCourant.getPosX();
		yy=joueurCourant.getPosY();
		id =  joueurCourant.getIdJoueur();
		carte[xx][yy] = id;
		
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
		boolean victoire=false;
		boolean defaite=false;
		while(victoire==false || defaite==false)
		{
			if(fileJoueurs.size()==0)
			{
				//Si plus aucuns joueurs dans la file de joueurs défaite
				defaite=true;
			}
			for(int i=0; i<fileJoueurs.size(); i++)
			{
				joueurCourant=fileJoueurs.pollFirst();
				
				victoire=tourJoueur();
				if(victoire)
				{
					break;
				}
				if(joueurCourant!=null)
				{
					//Si le jouer a survécu à son tour (n'est pas mort pendant son combat)
					//On le replace à la fin de la file de joueurs
					fileJoueurs.addLast(joueurCourant);
				}
				else
				{
					i=i-1;
				}
			}
			if(victoire || defaite)
			{
				break;
			}

			for(int j=0; j<filePirates.size(); j++)
			{
				pirateCourant=filePirates.pollFirst();
				System.out.println("tour du pirate:" + pirateCourant.toString());
				if(pirateCourant!=null)
				{
					//Si il reste des pirates,
					//on lance le tour du pirate courant
					tourPirate();
					//Lors du tour du pirate, si celui ci est mort, pirateCourant à été reset à NULL.
					if(pirateCourant!=null)
					{
						//Si le pirateCourant n'as pas été supprimé, il est rajouté en fin de file.
						filePirates.addLast(pirateCourant);
					}
					else
					{
						j=j-1;
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
		Boucanier tmpB;
		Flibustier tmpF;
		boolean vivant=true;
		pirateCourant.deplacer();
		if(pirateCourant instanceof Boucanier)
		{
			tmpB=(Boucanier) pirateCourant;
			System.out.println("pirate combat Bouc?");
			vivant=tmpB.attaquer(fileJoueurs);
			System.out.println("pirate fin combatBouc2?");
			
		}
		else if(pirateCourant instanceof Flibustier)
		{
			tmpF=(Flibustier) pirateCourant;
			System.out.println("pirate combat Flib?");
			vivant=tmpF.attaquer(fileJoueurs);
			System.out.println("pirate fin combat Flib2?");
		}
		
		if(vivant == false)
		{
			//Si le pirate est mort lors du combat, on suprime le pirate courant
			System.out.println("Pirate mort");
			pirateCourant=null;
		}
		return;
	}

	public boolean tourJoueur()
	{
		//Retourne true si victoire, false sinon
			//Déplacement
		boolean deplacer=false;
		afficherCarte();
		Scanner saisie = new Scanner(System.in);
		while(deplacer==false)
		{
			System.out.println("Choisissez une direction 1 2 3 4 6 7 8 9 s q");
			String choix=saisie.nextLine();
			System.out.println(choix);
			/*if(choix=="s")
			{
				boolean saveValide=sauvegarde();
			}*/
			/*else if(choix=="q")
			{

			}*/
			if(choix.equals("1") || choix.equals("2") || choix.equals("3") || choix.equals("4") || choix.equals("6") || choix.equals("7") || choix.equals("8") || choix.equals("9"))
			{
				System.out.println("la");
				deplacer=joueurCourant.deplacer(Integer.parseInt(choix), n);
			}
		}

			//Combat
		boolean vivant=joueurCourant.combat(filePirates);
		if(vivant==false)
		{
			joueurCourant=null;
			return false;
		}
		//Si le joueur est vivant après son combat
			//Ouverture du coffre
		joueurCourant.ouvrirCoffre(listeCoffres);

			//Vérif si le  joueur a la pelle et si le coffre au trésor est sur sa position
		if(joueurCourant.getPelle()==true)
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
