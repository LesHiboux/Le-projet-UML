public class Boucanier extends Pirate {
	public Flibustier(String strBouc)
	{
		super(strBouc);
		nbBouc++;
	}

	public String toString()
	{
		return "2|" + posX + "|" + posY + "|";
	}
}