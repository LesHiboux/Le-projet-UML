import java.util.LinkedList;
import java.util.Random;
public class Boucanier extends Pirate {
	public Boucanier(String strBouc)
	{
		super(strBouc);
	}

	public Boucanier(int x, int y) {
		super(x,y);
	}
	
	public String toString()
	{
		return "2|" + posX + "|" + posY + "|";
	}

	public boolean combat(LinkedList<Joueur> fileJoueurs, Joueur j) {//déroulé du combat
		Random x = new Random();
		int jet = x.nextInt(10)+1;
		System.out.println("le joueur "+j.getIdJoueur()+"est attaqué par un boucanier");
		if (j.getArmure()==true) {
			if (j.getMousquet()==true) {
				System.out.println("le joueur "+j.getIdJoueur()+"tue le boucanier");
				return false;
			}
			if (jet==10) {
				System.out.println("le joueur "+j.getIdJoueur()+"est décapité par le boucanier");
				fileJoueurs.remove(j);
				return true;
			}
			System.out.println("le joueur "+j.getIdJoueur()+"survit");
			return true;
		}
		if (j.getMousquet()==true) {
			if (jet<=5) {
				System.out.println("le joueur "+j.getIdJoueur()+"tue le boucanier");
				return false;
			}
		}
		System.out.println("le joueur "+j.getIdJoueur()+"est décapité par le boucanier");
		fileJoueurs.remove(j);
		j=null;
		return true;
	}

	public boolean attaquer(LinkedList<Joueur> fileJoueurs) {//determine si le pirate attaque ou non des joueurs et si oui lesquels puis lance le combat 
		Joueur j;
		for (int i=0;i<fileJoueurs.size();i++) {
			j=fileJoueurs.get(i);
			System.out.println("erreur atak Bouc: " + j.toString());
			if (j.getPosX()==this.posX && j.getPosY()==this.posY) {
				if (combat(fileJoueurs,j)==false) return false; 
			}
			else {
				if(j.getMousquet()==true) {
					if (estAdjacent(j.getPosX(),j.getPosY(),this.posX,this.posY)==true) {
						if (combat(fileJoueurs,j)==false) return false;
					}
				}
			}
			if(j==null)
			{
				i--;
			}
		}
		return true;
	}
}
