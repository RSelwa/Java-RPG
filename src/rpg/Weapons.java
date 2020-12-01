
package rpg;
abstract class Weapons extends Item {
  protected boolean equiped = false;
    public Weapons(String name, int price, int d, boolean f){
      super(name, price, d,f);
      // this.dommage = d;
    }
}
