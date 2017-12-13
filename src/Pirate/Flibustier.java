import java.util.*;
public class Flibustier extends Pirate {

	public Flibustier(String strFlib) throws PirateException
	{
		super(strFlib);
	}

	public Flibustier(int x, int y) {
		super(x,y);
	}
	
	public String toString()
	{
		return "1|" + posX + "|" + posY + "|";
	}
	
	public boolean combat(LinkedList<Joueur> fileJoueurs, Joueur j) {//déroulé du combat
		Random x = new Random();
		int jet = x.nextInt(10)+1;
		System.out.println("le joueur "+j.getIdJoueur()+" est attaqué par un flibustier");
		if (j.getArmure()==true) {
			if (j.getMousquet()==true) {
				System.out.println("le joueur "+j.getIdJoueur()+" tue le flibustier");
				return false;
			}
			if (jet==10) {
				System.out.println("le joueur "+j.getIdJoueur()+" est tué par le flibustier");
				fileJoueurs.remove(j);
				return true;
			}
			System.out.println("le joueur "+j.getIdJoueur()+" survit");
			return true;
		}
		if (j.getMousquet()==true) {
			if (jet<=5) {
				System.out.println("le joueur "+j.getIdJoueur()+" tue le flibustier");
				return false;
			}
		}
		System.out.println("le joueur "+j.getIdJoueur()+" est tué par le flibustier");
		fileJoueurs.remove(j);
		j=null;
		return true;
	}

	/*public void deplacer()
	{
		super();
	}*/
	
	public boolean attaquer(LinkedList<Joueur> fileJoueurs, int nTour) {
		if (nTour==1) return true;
		Joueur j=new Joueur(0,0,0);
		for (int i=0;i<fileJoueurs.size();i++) {
			j=(Joueur) fileJoueurs.get(i);
		
			if (j.getPosX()==this.posX && j.getPosY()==this.posY) {
				if (combat(fileJoueurs,j)==false) return false; 
			}
			else {
				if (estAdjacent(j.getPosX(),j.getPosY(),this.posX,this.posY)==true) {
					if (combat(fileJoueurs,j)==false) return false;
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
