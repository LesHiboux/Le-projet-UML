public class Flibustier extends Pirate {

	public Flibustier(String strFlib)
	{
		super(strFlib);
		nbFlib++;
	}

	public String toString()
	{
		return "1|" + posX + "|" + posY + "|";
	}
}