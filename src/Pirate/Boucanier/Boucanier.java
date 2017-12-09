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

	public boolean combat(LinkedList<Joueur> fileJoueurs, Joueur j) {//déroulé du combat
		Random x = new Random();
		int jet = x.nextInt(10)+1;
		//affiche le joueur j est attaqué par un boucanier
		if (j.getArmure()==true) {
			if (j.getMousquet()==true) {
				//affiche le joueur j tue le boucanier
				return false;
			}
			if (jet==10) {
				//affiche le joueur j est décapité par le boucanier
				fileJoueurs.remove(j);
				return true;
			}
			//le joueur j survit
			return true;
		}
		if (j.getMousquet()==true) {
			if (jet<=5) {
				//affiche le joueur j tue le boucanier
				return false;
			}
		}
		//le joueur j est décapité par le boucanier
		fileJoueurs.remove(j);
		return true;
	}

	public boolean attaquer(LinkedList<Joueur> fileJoueurs) {//determine si le pirate attaque ou non des joueurs et si oui lesquels puis lance le combat 
		Joueur j;
		for (int i=0;i<=fileJoueurs.size();i++) {
			j=fileJoueurs.get(i);
			if (j.getPosX()==this.posX && j.getPosY==this.posY) {
				if (combat(fileJoueurs,j)==false) return false; 
			}
			else {
				if(j.getMousquet()==true) {
					if (estAdjacent(j.getPosX(),j.getPosY(),this.posX,this.posY)==true) {
						if (combat(fileJoueurs,j)==false) return false;
					}
				}
			}
		}
		return true;
	}
}
