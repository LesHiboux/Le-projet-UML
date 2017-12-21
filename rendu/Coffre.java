public class Coffre
{
	private int posX;
	private int posY;
	private boolean pelle;
	private boolean mousquet;
	private boolean armure;
	private static int nbCoffre;


	//--------------Costructeurs--------------//
	public Coffre(int x, int y, boolean p, boolean m, boolean a)
	{
		this.posX=x;
		this.posY=y;
		this.pelle=p;
		this.mousquet=m;
		this.armure=a;
		nbCoffre++;
	}

	public Coffre(String strCoffre) throws CoffreException
	{
		//Constructeur avec une string, utile los du chargement d'une sauvegarde
		// La chaine doit être de la forme: "posX|posY|pelle|mousquet|armure|"
		// Exemple: "10|4|true|false|false|"
		//pour récupérer les infos, on vas lire les block entre les ||
		int compt=0;
		int pos=0;	//position du début de la lecture d'une info
		int posSuiv=0;	//position du charactère | suivant dans la chaine
		String info="";
		while(compt < 5)	//Tant qu'on n'as pas récupérer toutes les infos:
		{
			pos=posSuiv;	//pos prends la valeur de la posSuiv précédente
			posSuiv=strCoffre.indexOf("|", pos+1);	//on récupère la position du prochain | dans la chaine
			if (posSuiv==-1)
			{
				//Cette vérif sert à vérifer que la structure est bonne et/ou q'il ne manque pas d'infos
				throw new CoffreException("Erreur de construction", this);
			}
			if(compt==0)
			{
				//pour la première info:
				info=strCoffre.substring(pos, posSuiv); //on lie la chaine de pos à posSuiv-1
			}
			else
			{
				info=strCoffre.substring(pos+1, posSuiv);
			}
			switch(compt)
			{
				case 0:
				{
					
					try
					{
						posX=Integer.parseInt(info);
					}
					catch(NumberFormatException e)
					{
						throw new CoffreException("Erreur de construction", this);
					}
					compt++;
					break;
				}
				case 1:
				{
					try
					{
						posY=Integer.parseInt(info);
					}
					catch(NumberFormatException e)
					{
						throw new CoffreException("Erreur de construction", this);
					}
					compt++;
					break;
				}
				case 2:
				{
					if(info.equals("false")==false && info.equals("true")==false)
					{
						throw new CoffreException("Erreur de construction", this);
					}
					pelle=Boolean.valueOf(info);
					compt++;
					break;
				}
				case 3:
				{
					if(info.equals("false")==false && info.equals("true")==false)
					{
						throw new CoffreException("Erreur de construction", this);
					}
					mousquet=Boolean.valueOf(info);
					compt++;
					break;
				}
				case 4:
				{
					if(info.equals("false")==false && info.equals("true")==false)
					{
						throw new CoffreException("Erreur de construction", this);
					}
					armure=Boolean.valueOf(info);
					compt++;
					break;
				}
			}
		}
		nbCoffre++;
	}


	//------Get-----//
	public int getPosX()
	{
		return posX;
	}

	public int getPosY()
	{
		return posY;
	}

	public boolean getPelle()
	{
		return pelle;
	}

	public boolean getMousquet()
	{
		return mousquet;
	}

	public boolean getArmure()
	{
		return armure;
	}


	//--------------Set-------------//
	public void setPosX(int x)
	{
		this.posX=x;
	}

	public void setPosY(int y)
	{
		this.posY=y;
	}

	public void setPelle(boolean p)
	{
		this.pelle=p;
	}

	public void setMousquet(boolean m)
	{
		this.mousquet=m;
	}

	public void setArmure(boolean a)
	{
		this.armure=a;
	}


	//--------------Fonctions------------//
	public String toString()
	{
		return posX + "|" + posY + "|" + String.valueOf(pelle) + "|" + String.valueOf(mousquet) + "|" + String.valueOf(armure) + "|";
	}

	public boolean vide()
	{
		//Retourne true si le coffre est vide
		//false sinon
		if(pelle==false && mousquet==false && armure==false)
		{
			return true;
		}
		return false;
	}
}