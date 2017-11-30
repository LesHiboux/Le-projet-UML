public class Coffre
{
	private int posX;
	private int posY;
	private boolean pelle;
	private boolean mousquet;
	private boolean armure;
	private static int nbCoffre;

	public Coffre(String strCoffre) throws CoffreException
	{
		int compt=0;
		int pos=0;
		int posSuiv=0;
		String info="";
		while(compt < 5)
		{
			pos=posSuiv;
			posSuiv=strCoffre.indexOf("|", pos+1);
			if (posSuiv==-1)
			{
				throw new CoffreException("Erreur de construction", this);
			}
			if(compt==0)
			{
				info=strCoffre.substring(pos, posSuiv);
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
						throw new JoueurException("Erreur de construction", this);
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
						throw new JoueurException("Erreur de construction", this);
					}
					compt++;
					break;
				}
				case 2:
				{
					if(info.equals("false")==false && info.equals("true")==false)
					{
						throw new JoueurException("Erreur de construction", this);
					}
					pelle=Boolean.valueOf(info);
					compt++;
					break;
				}
				case 3:
				{
					if(info.equals("false")==false && info.equals("true")==false)
					{
						throw new JoueurException("Erreur de construction", this);
					}
					mousquet=Boolean.valueOf(info);
					compt++;
					break;
				}
				case 4:
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
	}

	public String toString()
	{
		return posX + "|" + posY + "|" + String.valueOf(pelle) + "|" + String.valueOf(mousquet) + "|" + String.valueOf(armure) + "|";
	}
}