public class Carte
{
	private int carte = new int[12][12];

	public Carte()
	{
		int nbJoueur;
		int nbCoffre;
		//se renseigner sur les tuples de java

		for(int i = 0; i <= 11; i++)
		{
			for(int i = 0; i <= 11; i++)
			{
				carte[i][j] = 0;
			}	
		}
		
		nbJoueur = Joueur.getNbJoueur();
		nbCoffre = nbJoueur * 3;
		
	}
}
