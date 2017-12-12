import java.util.*;
public class Pirate {
	protected int posX;
	protected int posY;

	public Pirate (int x, int y) {
		posX=x;
		posY=y;
	}
	
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
				System.out.println("erreur");
				//throw new PirateException("Erreur de construction", this);
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
						System.out.println("erreur");
						//throw new PirateException("Erreur de construction", this);
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
						System.out.println("erreur");
						//throw new PirateException("Erreur de construction", this);
					}
					compt++;
					break;
				}
			}
		}
	}

	//public String toString(){}
	
	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}
	
	public void setPosX(int x) {
		this.posX=x;
		return;
	}
	
	public void setPosY(int y) {
		this.posY=y;
		return;
	}

	public boolean estAdjacent(int posX, int posY, int posX2, int posY2) { //fonction qui renvoie true si les coordonées (posX,posY) sont adjacente aux coordonées (posX2,posY2)
		if (posX==posX2) {
			if (posY==posY2+1 || posY==posY2-1) return true;
		}
		if (posY==posY2) {
			if (posX==posX+1 || posX==posX-1) return true;
		}
		if (posX==posX2-1 || posX==posX2+1) {
			if(posY==posY-1 || posY==posY+1) return true;
		}
		return false;
	}	
	public void deplacer() {
		int posX2=5;
		int posY2=5;
		boolean valide = false;
		//deplacement aleatoire
		while (valide == false) {
			valide=true;
			Random x = new Random();
			int direction = x.nextInt(9)+1;
			switch(direction) {
				case 1: posX2=posX+1;
					posY2=posY-1;
				case 2:
					posX2=posX+1;
				case 3:
					posX2=posX+1;
					posY2=posY+1;
				case 4:
					posY2=posY-1;
				case 5:
					valide=false;
				case 6:
					posY2=posY+1;
				case 7:
					posX2=posX-1;
					posY2=posY-1;
				case 8:
					posX2=posX-1;
				case 9:
					posX2=posX-1;
					posY2=posY+1;

			}
			//vérification direction non hors map
			if (posX2>=12 || posX2<0 || posY2>=12 || posY2<0) { 
				valide=false;
				posX2=posX;
				posY2=posY;
			}
		}
		//changement coordonées
		posX=posX2;
		posY=posY2;	  
	}

	//public boolean attaquer(LinkedList<Joueur> fileJoueurs){}
}
