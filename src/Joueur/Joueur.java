import java.util.*;
import java.util.Random;


public class Joueur
{
	private int posX;
	private int posY;
	private int idJoueur;
	private boolean pelle;
	private boolean mousquet;
	private boolean armure;
	private static int nbJoueur;
	

//---------Constructeur---------//
	public Joueur(int id, int x, int y)
	{
		this.idJoueur=id;
		this.posX=x;
		this.posY=y;
		this.pelle=false;
		this.mousquet=false;
		this.armure=false;
		this.nbJoueur+=1;
		return;
	}

	public Joueur(String strJoueur) throws JoueurException
	{
		System.out.println(strJoueur);
		int compt=0;
		int pos=0;
		int posSuiv=0;
		String info="";
		while (compt<6)
		{
			
			pos=posSuiv;
			posSuiv=strJoueur.indexOf("|", pos+1);
			if (posSuiv==-1)
			{
				throw new JoueurException("Erreur de construction", this);
			}
			System.out.println("ici : posX=" + pos + " posY=" + posSuiv);
			if(compt==0)
			{
				info=strJoueur.substring(pos, posSuiv);
			}
			else
			{
				info=strJoueur.substring(pos+1, posSuiv);
			}
			System.out.println("info= " + info);
			switch(compt)
			{
				case 0:
				{
					
					try
					{
						idJoueur=Integer.parseInt(info);
					}
					catch(NumberFormatException e)
					{
						throw new JoueurException("Erreur de construction", this);
					}
					compt++;
					break;
				}
				case 1:
				{
					try
					{
						posX=Integer.parseInt(info);
					}
					catch(NumberFormatException e)
					{
						throw new JoueurException("Erreur de construction", this);
					}
					compt++;
					break;
				}
				case 2:
				{
					try
					{
						posY=Integer.parseInt(info);
					}
					catch(NumberFormatException e)
					{
						throw new JoueurException("Erreur de construction", this);
					}
					compt++;
					break;
				}
				case 3:
				{
					if(info.equals("false")==false && info.equals("true")==false)
					{
						throw new JoueurException("Erreur de construction", this);
					}
					pelle=Boolean.valueOf(info);
					compt++;
					break;
				}
				case 4:
				{
					if(info.equals("false")==false && info.equals("true")==false)
					{
						throw new JoueurException("Erreur de construction", this);
					}
					mousquet=Boolean.valueOf(info);
					compt++;
					break;
				}
				case 5:
				{
					if(info.equals("false")==false && info.equals("true")==false)
					{
						throw new JoueurException("Erreur de construction", this);
					}
					armure=Boolean.valueOf(info);
					compt++;
					break;
				}
			}
		}
		nbJoueur++;
	}

	public void display()
	{
		System.out.println(idJoueur + "|" + posX + "|" + posY + "|" + String.valueOf(pelle) + "|" + String.valueOf(mousquet) + "|" + String.valueOf(armure) + "|");
	}

	public String toString()
	{
		return idJoueur + "|" + posX + "|" + posY + "|" + String.valueOf(pelle) + "|" + String.valueOf(mousquet) + "|" + String.valueOf(armure) + "|";
	}


	//------------Get--------//
	public int getPosX()
	{
		return this.posX;
	}

	public int getPosY()
	{
		return this.posY;
	}

	public char getIdJoueur()
	{
		return Character.forDigit(idJoueur, 10);
	}

	public boolean getPelle()
	{
		return this.pelle;
	}

	public boolean getMousquet()
	{
		return this.mousquet;
	}

	public boolean getArmure()
	{
		return this.armure;
	}

	public int getNbJoueur()
	{
		return this.nbJoueur;
	}



	//------------Set--------//
	public void setPosX(int x)
	{
		this.posX=x;
		return;
	}
	
	public void setPosY(int y)
	{
		this.posY=y;
		return;
	}

	public void setIdJoueur(int id)
	{
		this.idJoueur = id;
		return;
	}

	public void setPelle(boolean p)
	{
		this.pelle = p;
		return;
	}

	public void setMousquet(boolean m)
	{
		this.mousquet = m;
		return;
	}

	public void setArmure(boolean a)
	{
		this.armure = a;
		return;
	}

	//------------Fonctions--------//
	public boolean deplacer(int choix, int n)
	{
		//choix = 1, 2, 3, 4, 6, 7, 8, 9
		//bas-gauche, bas, bas-droite, gauche, droite, haut-gauche, haut, haut-droite

		//retourne true si le déplacement est fait, false sinon
		
		//int choix;

		//System.out.println("Sélectionnez une dirrection")

		switch(choix)
		{
			case 1:
			{
				if(posY-1<0 || posX+1>n-1)
				{
					System.out.println("Déplacement impossible");
					return false;
				}
				else
				{
					posY=posY-1;
					posX=posX+1;
					return true;
				}
			}

			case 2:
			{
				if(posX+1>n-1)
				{
					System.out.println("Déplacement impossible");
					return false;
				}
				else
				{
					posX=posX+1;
					return true;
				}

			}

			case 3:
			{
				if(posY+1>11 || posX+1>n-1)
				{
					System.out.println("Déplacement impossible");
					return false;
				}
				else
				{
					posY=posY+1;
					posX=posX+1;
					return true;
				}
			}

			case 4:
			{
				if(posY-1 < 0)
				{
					System.out.println("Déplacement impossible");
					return false;
				}
				else
				{
					posY=posY-1;
					return true;
				}
			}

			case 6:
			{
				if(posY+1 > n-1)
				{
					System.out.println("Déplacement impossible");
					return false;
				}
				else
				{
					posY=posY+1;
					return true;
				}
			}

			case 7:
			{
				if(posY-1 <0 || posX-1 <0)
				{
					System.out.println("Déplacement impossible");
					return false;
				}
				else
				{
					posY = posY-1;
					posX=posX-1;
					return true;
				}
			}

			case 8:
			{
				if(posX-1 < 0)
				{
					System.out.println("Déplacement impossible");
					return false;
				}
				else
				{
					posX=posX-1;
					return true;
				}
			}

			case 9:
			{
				if(posY+1>n-1 || posX-1<0)
				{
					System.out.println("Déplacement impossible");
					return false;
				}
				else
				{
					posY=posY+1;
					posX=posX-1;
					return true;
				}
			}

			default:
			{
				System.out.println("erreur de saisie");
				return false;
			}
		}

	}

	public void ouvrirCoffre(LinkedList<Coffre> listeCoffres)
	{
		Coffre coffreCourant;
		int cPosX, cPosY;
		boolean cPelle, cArmure, cMousquet;
		for(int i=0; i<listeCoffres.size(); i++)
		{
			coffreCourant=listeCoffres.get(i);
			cPosX=coffreCourant.getPosX();
			cPosY=coffreCourant.getPosY();
			//System.out.println(coffreCourant.toString());
			if(this.posX==cPosX && this.posY==cPosY)
			{
				//On regarde le contenu du coffre que si il est sur le même case que le joueur
				cPelle=coffreCourant.getPelle();
				cArmure=coffreCourant.getArmure();
				cMousquet=coffreCourant.getMousquet();
				if(cPelle && this.pelle==false)
				{
					//Si le coffre contiend une pelle, et que le joueur n'en as pas:
					this.pelle=cPelle;
					coffreCourant.setPelle(false);
					System.out.println("récup pelle");
				}
				if(cMousquet && this.mousquet==false)
				{
					//Si le coffre contiend un mousquet, et que le joueur n'en as pas:
					this.mousquet=cMousquet;
					coffreCourant.setMousquet(false);
					System.out.println("récup mousquet");
				}
				if(cArmure && this.armure==false)
				{
					//Si le coffre contiend une armure, et que le joueur n'en as pas:
					this.armure=cArmure;
					coffreCourant.setArmure(false);
					System.out.println("récup armure");
				}

				if(coffreCourant.vide()==true)
				{
					//Si le coffre est vide, on l'enlève de la liste de coffres
					listeCoffres.remove(i);
					return;
				}
				else if(coffreCourant.vide()==false)
				{
					//Si le coffre n'est pas vide
						//On enlève le coffre de la liste, 
						//Puis on rajoute le coffreCourant qui a été moifié précedement
					listeCoffres.remove(i);
					listeCoffres.add(coffreCourant);
					return;
				}
			}
		}
	}
	
	public boolean combat(LinkedList<Pirate> filePirates)	//Return true si vivant, false si joueur mort
	{
		Pirate pirateCourant;
		int pPosX, pPosY;
		for(int i=0; i<filePirates.size(); i++)
		{
			//On récupère les infos du pirate à la position i dans la liste
			pirateCourant=filePirates.get(i);
			pPosX=pirateCourant.getPosX();
			pPosY=pirateCourant.getPosY();
			if((this.posX == pPosX-1 && this.posY == pPosY-1) || (this.posX == pPosX && this.posY == pPosY-1) || (this.posX == pPosX+1 && this.posY == pPosY-1) || (this.posX == pPosX-1 && this.posY == pPosY) || (this.posX==pPosX && this.posY==pPosY) || (this.posX == pPosX+1 && this.posY == pPosY) || (this.posX == pPosX-1 && this.posY == pPosY+1) || (this.posX == pPosX && this.posY == pPosY+1) || (this.posX == pPosX+1 && this.posY == pPosY+1))
			{
				System.out.println("Combat joueur: "+ this.toString() + " vs: " + pirateCourant.toString());
				Random x=new Random();		//Génération d'un nombre aléatoire
				int jet=x.nextInt(10)+1;	//entre 1(inclu) et 10 -> 11(exclu)
				//Si le pirate est proche du joueur
				if(pirateCourant instanceof Boucanier)
				{
					//Combat avec un boucanier
					if(this.mousquet)
					{
						//Si le joueur a le mousquet combat
						if(this.armure)
						{
							//Si le perso a l'armure et le mousquet, il gagne -> pirate mort, joueur vivant
							//Affiche joueur a tué le boucanier
							filePirates.remove(i);
							pirateCourant=null;
						}
						else
						{
							//Si le joueur a seulement le mousquet: 50% de chance de tuer le pirate
							if(jet<=5)
							{
								//Affiche joueur a tué le boucanier
								filePirates.remove(i);
								pirateCourant=null;
							}
							else
							{
								//Affiche joueur décapité par boucanier
								return false;
							}
						}
					}
					else
					{
						//Si le joueur n'as pas le mousquet, combat seulement si ils sont sur la meme case
						if(this.posX==pPosX && this.posY==pPosY)
						{
							if(this.armure==false)
							{
								//Si le joueur n'as pas de mousquet et d'armure, il est mort...
								//Affiche joueur décapité par boucanier
								return false;
							}
							else
							{
								//Si le joueur n'as pas de mousquet mais une armure, il a 10% de chances de mourir7
								if(jet==10)
								{
									//Affiche joueur décapité par boucanier
									return false;
								}
								//Affiche joueur a survécu au boucanier
							}
						}
					}
				}
				else if(pirateCourant instanceof Flibustier)
				{
					//Si le pirate est un flibustier combat
					if(this.mousquet == false && this.armure==false)
					{
						//Si le joueur n'as ni le mousquet ni l'armure, il est mort
						//Affiche joueur tué par le flibustier
						return false;
					}
					else if(this.mousquet==false && this.armure==true)
					{
						//Si le joueur n'as que l'armure, il a 10% de chances de mourir
						if(jet==10)
						{
							//Affiche joueur tué par le flibustier
							return false;
						}
						//Affiche joueur a survécu au flibustier
					}
					else if(this.mousquet==true && this.armure==false)
					{
						//Si le joueur n'as que le mousquet, il a 50% de chances de tuer le pirate
						if(jet<=5)
						{
							//Affiche joueur a tué le flibustier
							filePirates.remove(i);
							pirateCourant=null;
						}
						else
						{
							//Affiche joueur tué par le flibustier
							return false;
						}
					}
					else if(this.mousquet==true && this.armure==true)
					{
						//Si le joueur a l'armure et le mousquet il gagne
						//Affiche joueur a tué le flibustier
						filePirates.remove(i);
					}
				}
			}
			if(pirateCourant==null)
			{
				i--;
			}
		}
		//Le joueur a survécu à toutes les attaques de pirates.
		return true;
	}

}

