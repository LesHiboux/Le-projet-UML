
public class Pair<L,R> {

  private int L left;
  private int R right;

  public Pair(int left, int right) {
    this.left = left;
    this.right = right;
  }

  public int getLeft()
  {
    return left;
  }
  
  public int getRight()
  {
    return right;
  }

  public boolean equality(Object o)
  {
    if (!(o instanceof Pair)) return false;
    Pair pairo = (Pair) o;
    return this.left.equality(pairo.getLeft()) && this.right.equality(pairo.getRight());
  }

}
