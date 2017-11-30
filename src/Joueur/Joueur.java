

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
						System.out.println("la");
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

	public int getIdJoueur()
	{
		return this.idJoueur;
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
	public boolean deplacer(int choix)
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
				if(posX-1<0 || posY+1>11)
				{
					System.out.println("Déplacement impossible");
					return false;
				}
				else
				{
					posX=posX-1;
					posY=posY+1;
					return true;
				}
			}

			case 2:
			{
				if(posY+1>11)
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

			case 3:
			{
				if(posX+1>11 || posY+1>11)
				{
					System.out.println("Déplacement impossible");
					return false;
				}
				else
				{
					posX=posX+1;
					posY=posY+1;
					return true;
				}
			}

			case 4:
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

			case 6:
			{
				if(posX+1 > 11)
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

			case 7:
			{
				if(posX-1 <0 || posY-1 <0)
				{
					System.out.println("Déplacement impossible");
					return false;
				}
				else
				{
					posX = posX-1;
					posY=posY-1;
					return true;
				}
			}

			case 8:
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

			case 9:
			{
				if(posX+1>11 || posY-1<0)
				{
					System.out.println("Déplacement impossible");
					return false;
				}
				else
				{
					posX=posX+1;
					posY=posY-1;
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

}
