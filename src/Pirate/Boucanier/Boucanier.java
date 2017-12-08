import java.util.LinkedList;
import java.util.Random;
public class Boucanier extends Pirate {
	Boucanier(String strBouc)
	{
		super(strBouc);
		nbBouc++;
	}

	public String toString()
	{
		return "2|" + posX + "|" + posY + "|";
	}

	public boolean combat(LinkedList<Joueur> fileJoueurs, Joueur i) {//déroulé du combat
		Random x = new Random();
		int jet = x.nextInt(10)+1;
		//affiche le joueur i est attaqué par un boucanier
		if (j.getArmure()==1) {
			if (j.getMousquet()==1) {
				//affiche le joueur i tue le boucanier
				return false;
			}
			if (jet==10) {
				//affiche le joueur i est décapité par le boucanier
				fileJoueurs.remove(i);
				return true;
			}
			//le joueur i survit
			return true;
		}
		if (j.getMousquet()==1) {
			if (jet<=5) {
				//affiche le joueur i tue le boucanier
				return false;
			}
		}
		//le joueur i est décapité par le boucanier
		fileJoueurs.remove(i);
		return true;
	}

	public boolean attaquer(LinkedList<Joueur> fileJoueurs) {//determine si le pirate attaque ou non des joueurs et si oui lesquels puis lance le combat 
		Joueur j;
		for (int i=0;i<=fileJoueurs.size();i++) {
			j=fileJoueurs.get(i);
			if (j.getPosX()==this.posX && j.getPosY==this.posY) {
				if (combat(fileJoueurs,i)==false) return false; 
			}
			else {
				if(i.getMousquet()==true) {
					if (estAdjacent(j.getPosX(),j.getPosY(),this.posX,this.posY)==true) {
						if (combat(fileJoueurs,j)==false) return false;
					}
				}
			}
		}
		return true;
	}
}
