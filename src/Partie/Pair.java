
public class Pair
  {

    private int x;
    private int y;

    public Pair(int x, int y)
    {
      this.x = x;
      this.y = y;
    }

    public int getX()
    {
      return x;
    }

    public int gety()
    {
      return y;
    }

    public boolean equality(Object o)
    {
      if (!(o instanceof Pair)) return false;
      Pair pairo = (Pair) o;
      return this.x.equality(pairo.getX()) && this.y.equality(pairo.getY());
    }

}
