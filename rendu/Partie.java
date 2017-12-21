

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

	private int nMap=12;
	private int nTour=1;

	//Fonctions
	public Partie()
	{
		//je te construis et dans le main appel :  Affichage jeu = new Partie();//
		this.fileJoueurs =new LinkedList<Joueur>();
		this.filePirates =new LinkedList<Pirate>(); 
		this.listeCoffres =new LinkedList<Coffre>();


		boolean choix1=false;
		boolean choix2=false;
		Scanner saisieMode = new Scanner(System.in);
		while(choix1==false && choix2==false)
		{
			afficherAccueil();
			String choix=saisieMode.nextLine();
			switch(choix)
			{
				case "1":
				{
					choix1=true;
					break;
				}
				case "2" :
				{
					choix2=charger();
					break;
				}
				case "q":
				{
					System.exit(0);
				}
				case "Q":
				{
					System.exit(0);
				}
			}
		}
		if (choix2) return;

		//if(charger()) return;

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
				nx = jet.nextInt(nMap);
				ny = jet.nextInt(nMap);
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
			int nx = -1; int ny = -1;
			Pair ncoord = new Pair(nx, ny);
			while (ncoord.inside(coords))
			{
				Random jet= new Random();
				nx = jet.nextInt(nMap);
				ny = jet.nextInt(nMap);
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
					break;
				}
				case 1:
				{
					Pirate nPirate = new Flibustier(ncoord.getX(), ncoord.getY());
					coords.add(ncoord);
					filePirates.addFirst(nPirate);
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
					nx = jet.nextInt(nMap);
					ny = jet.nextInt(nMap);
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
		this.tresorPosX = jet.nextInt(nMap);
		this.tresorPosY = jet.nextInt(nMap);
	}


public boolean charger()
	{
		//Récupération du dossier de sauvegarde
		String nomSave="./Sauvegardes/";
		String nomDossier;
		Scanner saisie = new Scanner(System.in);
		System.out.println("Saisissez le nom de la sauvegarde");
		nomDossier = saisie.nextLine();
		nomSave+=nomDossier;
		File dossierSave= new File(nomSave);
		if(dossierSave.exists()==false)
		{
			System.out.println("erreur la sauvegarde n'existe pas");
			return false;
		}
		
		//Récupération du fichier joueur
		//File fJoueur = new File(nomDossier + "/joueur.hib");
		try
		{
			Joueur newJoueur;
			File fJoueur= new File(nomSave + "/joueur.hib");
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
			fJoueur.delete();
		}
		catch(IOException eIO)
		{
			System.out.println("Erreur fichier joueur");
			return false;
		}
		catch(JoueurException eJ)
		{
			System.out.println("Erreur construction joueur");
			return false;
		}

		//Récupération du fichier coffre
		try
		{
			Coffre newCoffre;
			File fCoffre=new File(nomSave + "/coffre.hib");
			InputStream ipsCoffre = new FileInputStream(nomSave + "/coffre.hib");
			InputStreamReader ipsrCoffre = new InputStreamReader(ipsCoffre);
			BufferedReader brCoffre = new BufferedReader(ipsrCoffre);
			String ligneCoffre;
			while((ligneCoffre=brCoffre.readLine())!=null)
			{
				newCoffre=new Coffre(ligneCoffre);
				listeCoffres.addLast(newCoffre);
			}
			fCoffre.delete();
			brCoffre.close();
		}
		catch(CoffreException eC)
		{
			System.out.println("erreur");
			return false;
		}
		catch(IOException eIO)
		{
			System.out.println("Erreur Coffre");
			return false;
		}

		//Récupération du fichier trésor
		try
		{
			File fTresor=new File(nomSave + "/tresor.hib");
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
				while (compt<4)
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
								System.out.println("Erreur construction tresor");
								return false;
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
								System.out.println("Erreur construction tresor");
								return false;
								//throw new JoueurException("Erreur de construction", this);
							}
							compt++;
							break;
						}
						case 2:
						{
							try
							{
								nMap=Integer.parseInt(info);
							}
							catch(NumberFormatException e)
							{
								System.out.println("Erreur construction taille Map");
								return false;
								//throw new JoueurException("Erreur de construction", this);
							}
							compt++;
							break;
						}
						case 3:
						{
							try
							{
								nTour=Integer.parseInt(info);
							}
							catch(NumberFormatException e)
							{
								System.out.println("Erreur construction nombre de tours");
								return false;
								//throw new JoueurException("Erreur de construction", this);
							}
							compt++;
							break;
						}
					}
				}
			}
			fTresor.delete();
			brTresor.close();

		}
		catch(IOException eIO)
		{
			System.out.println("Erreur Tresor");
			return false;
		}

		//Récupération du fichier pirates
		try
		{
			File fPirate=new File(nomSave + "/pirate.hib");
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
			fPirate.delete();
			brPirate.close();
			dossierSave.delete();
		}
		catch(PirateException eP)
		{
			System.out.println("Erreur construction Pirate");
			return false;
		}
		catch(IOException eIO)
		{
			System.out.println("Erreur Pirate");
			return false;
		}
		return true;
	}

	public boolean sauvegarde()
	{
		try
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
			else 
			{
				System.out.println("La sauvegarde existe déjà");
				return false;
			}

			//Création du fichier Joueur
			File fJoueur = new File(dossier + "/joueur.hib");
			creer=fJoueur.createNewFile();

			//écrire dans le fichier Joueur
			Joueur joueurActuel;
			bw = new BufferedWriter(new FileWriter(dossier + "/joueur.hib"));
			pWriter = new PrintWriter(bw);

			pWriter.print(joueurCourant.toString());
			for(int j = 0; j<fileJoueurs.size(); j++)
			{
				pWriter.println();
				joueurActuel=(Joueur) fileJoueurs.get(j);
				pWriter.print(joueurActuel.toString());
			}

			pWriter.close();


			//Création fichier pirate
			File fPirate = new File(dossier + "/pirate.hib");
			creer=fPirate.createNewFile();

			//écriture dans le fichier Pirate
			Pirate pirateActuel;
			bw = new BufferedWriter(new FileWriter(dossier + "/pirate.hib"));
			pWriter = new PrintWriter(bw);
			for(int p = 0; p<filePirates.size(); p++)
			{
				if(p>0) pWriter.println();
				pirateActuel=(Pirate) filePirates.get(p);
				pWriter.print(pirateActuel.toString());
			}
			pWriter.close();

			//Création fichier coffre
			File fCoffre = new File(dossier + "/coffre.hib");
			creer=fCoffre.createNewFile();
			Coffre coffreCourant;

			//écriture dans le fichier Coffre
			bw = new BufferedWriter(new FileWriter(dossier + "/coffre.hib"));
			pWriter = new PrintWriter(bw);
			for(int i=0; i<listeCoffres.size(); i++)
			{
				if(i>0) pWriter.println();
				coffreCourant=listeCoffres.get(i);
				pWriter.print(coffreCourant.toString());
			}
			pWriter.close();

			//Création fichier trésor
			File fTresor = new File(dossier + "/tresor.hib");

			creer=fTresor.createNewFile();

			//écriture dans le fichier tresor
			bw = new BufferedWriter(new FileWriter(dossier + "/tresor.hib"));
			pWriter = new PrintWriter(bw);
			pWriter.print(tresorPosX + "|" + tresorPosY + "|" + nMap + "|" + nTour + "|");
			pWriter.close();
		}
		catch(IOException eIO)
		{
			System.out.println("erreur de sauvegarde");
			return false;
		}
		return true;
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

	public void afficherCarte()
	{
		//generation de la carte a afficher
		char carte[][]=new char [nMap][nMap];
		char id;
		int xx;
		int yy;
		
		for (int row=0; row<nMap; row++)
		{
			for (int col=0; col<nMap; col++)
			{
				carte[row][col]='+';
			}
		}
		
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
		for (int x=0; x<nMap; x++)
		{
			for (int y=0; y<nMap; y++)
			{
				System.out.print(carte[x][y]);
			}
			System.out.println();
		}
		System.out.println("-------------------------");
	}
	
	
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
				//System.out.println("tour du pirate:" + pirateCourant.toString());
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
			nTour++;
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
		pirateCourant.deplacer(nMap);
		if(pirateCourant instanceof Boucanier)
		{
			tmpB=(Boucanier) pirateCourant;
			//System.out.println("pirate combat Bouc?");
			vivant=tmpB.attaquer(fileJoueurs, nTour);
			//System.out.println("pirate fin combatBouc2?");
			
		}
		else if(pirateCourant instanceof Flibustier)
		{
			tmpF=(Flibustier) pirateCourant;
			//System.out.println("pirate combat Flib?");
			vivant=tmpF.attaquer(fileJoueurs, nTour);
			//System.out.println("pirate fin combat Flib2?");
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
			System.out.println("Au tour du joueur: " + joueurCourant.getIdJoueur());
			joueurCourant.afficheEquipement();
			System.out.println("Choisissez une direction 1 2 3 4 6 7 8 9 s q");
			String choix=saisie.nextLine();
			if(choix.equals("s"))
			{
				boolean saveValide=sauvegarde();
			}
			else if(choix.equals("q"))
			{
				System.exit(0);
			}
			else if(choix.equals("1") || choix.equals("2") || choix.equals("3") || choix.equals("4") || choix.equals("6") || choix.equals("7") || choix.equals("8") || choix.equals("9"))
			{
				deplacer=joueurCourant.deplacer(Integer.parseInt(choix), nMap);
			}
		}

			//Combat
		boolean vivant=joueurCourant.combat(filePirates, nTour);
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
}
