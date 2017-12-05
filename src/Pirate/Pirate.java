import java.util.Random;
public class Pirate {
	private int posx;
	private int posy;
	private boolean vivant;
}
  
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

