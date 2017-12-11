public class Flibustier extends Pirate {

	public Flibustier(String strFlib)
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
		System.out.println("le joueur "+j.getIdJoueur()+"est attaqué par un flibustier")
		if (j.getArmure()==true) {
			if (j.getMousquet()==true) {
				System.out.println("le joueur "+j.getIdJoueur()+"tue le flibustier")
				return false;
			}
			if (jet==10) {
				System.out.println("le joueur "+j.getIdJoueur()+"est décapité par le flibustier")
				fileJoueurs.remove(i);
				return true;
			}
			System.out.println("le joueur "+j.getIdJoueur()+"survit")
			return true;
		}
		if (j.getMousquet()==1) {
			if (jet<=5) {
				System.out.println("le joueur "+j.getIdJoueur()+"tue le flibustier")
				return false;
			}
		}
		System.out.println("le joueur "+j.getIdJoueur()+"est décapité par le flibustier")
		fileJoueurs.remove(j);
		return true;
	}
	
	public boolean attaquer(LinkedList<Joueur> fileJoueurs) {
		joueur j;
		for (int i=0;i<=fileJoueurs.size();i++) {
			j=fileJoueurs.get(i);
		}
		if (j.getPosX()==this.posX && j.getPosY()==this.posY) {
				if (combat(fileJoueurs,j)==false) return false; 
			}
			else {
				if (estAdjacent(j.getPosX(),j.getPosY(),this.posX,this.posY)==true) {
					if (combat(fileJoueurs,j)==false) return false;
				}
			}
	}
}