
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

    public boolean inside(Arraylist arr)
    {
       for (int i=0; i<arr.size(); i++)
       {
          if (this.x == arr[i].getX() && this.y == arr[i].getY()) return True;
       }
       return False;
    }

}
