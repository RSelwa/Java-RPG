package rpg;

abstract class Item {
    protected int price; 
    protected int dommage; 
    protected String name;
    protected boolean equiped;
    public Item(String name, int price, int dommage, boolean f) {
        this.name = name;
        this.price = price;
        this.dommage = dommage;
        this.equiped = f;
    }
}
