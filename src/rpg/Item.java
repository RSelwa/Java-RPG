package rpg;

abstract class Item {
    protected int price; 
    protected String name;
    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
