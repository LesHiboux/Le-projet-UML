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
      Pair comp;
       for (int i=0; i<arr.size(); i++)
       {
         comp = arr.get(i)
          if (this.x == comp.getX() && this.y == comp.getY()) return true;
       }
       return false;
    }

}
