
package rpg;
abstract class Weapons extends Item {
  protected int dommage;
  protected boolean equiped = false;
    public Weapons(String name, int price, int d){
      super(name, price);
      this.dommage = d;
    }
}
