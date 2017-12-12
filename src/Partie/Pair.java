import java.util.*;

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

    public int getY()
    {
      return y;
    }

    public boolean inside(ArrayList arr)
    {
       for (int i=0; i<arr.size(); i++)
       {
          if (this.x == (arr.get(i)).getX() && this.y == (arr.get(i)).getY()) return true;
       }
       return false;
    }

}
