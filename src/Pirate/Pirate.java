import java.util.Random;
public class Pirate {
	private int posX;
	private int posY;


	public Pirate(String strPirate)
	{
		System.out.println(strPirate);
		int compt=0;
		int pos=0;
		int posSuiv=0;
		String info="";
		while(compt<3)
		{
			pos=posSuiv;
			posSuiv=strPirate.indexOf("|", pos+1);
			if (posSuiv==-1)
			{
				throw new JoueurException("Erreur de construction", this);
			}
			if(compt==0)
			{
				info=strPirate.substring(pos, posSuiv);
			}
			else
			{
				info=strPirate.substring(pos+1, posSuiv);
			}
			switch(compt)
			{
				case 0:
				{
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
						throw new PirateException("Erreur de construction", this);
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
						throw new PirateException("Erreur de construction", this);
					}
					compt++;
					break;
				}
			}
		}
		nbPirate++;
	}

	abstract public String toString();

public void deplacer() {
	Random x = new Random();
	int direction = x.nextInt(8)+1;
	int posx2;
	int posy2;
	boolean valide = false;
	//deplacement aleatoire
	while (valide == false) {
		valid==true;
		switch(direction) {
			case 1: posx2=posx+1;
				posy2=posy-1;
			case 2:
				posx2=posx+1;
			case 3:
				posx2=posx+1;
				posy2=posy+1;
			case 4:
				posy2=posy-1;
			case 5:
				valide=false;
			case 6:
				posy2=posy+1;
			case 7:
				posx2=posx-1;
				posy2=posy-1;
			case 8:
				posx2=posx-1;
			case 9:
				posx2=posx-1;
				posy2=posy+1;

		}
		//vérification direction non hors map
		if (posx2>=12 || posx2<0 || posy2>=12 || posy2<0) { 
			valide=false;
			posx2=posx:
			posy2=posy;
		}
	}
	//changement coordonées
	posx=pox2;
	posy=posy2;	  
}

public void attaquer() {

	}
}
