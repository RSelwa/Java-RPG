package rpg;
import java.util.ArrayList;

abstract class PNJ{
    protected ArrayList<Item> stock = new ArrayList();
    protected String ascii = "$";
    protected int x;
    protected int y;
    public PNJ( int y, int x) {
      this.x = x -1 ;
      this.y = y -1;
    }
    public void addStock(Item... items){
      for (Item item : items) {
        this.stock.add(item);
    }
    }
}
//♦