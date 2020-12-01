package rpg;

abstract class Entity {
    protected int lp;
    protected int att;
    protected int defense;
    protected int mana;
    protected String ascii;
    protected String name;
    protected int x;
    protected int y;
    protected boolean alive;
    protected Item equipedWeapon;
    public Entity(String name,int lp, int att, int defense,int mana, String ascii, int y, int x, Item weapon) {
        this.lp = lp;
        this.equipedWeapon = weapon;
        this.att = att + equipedWeapon.dommage;
        this.defense = defense;
        this.mana = mana;
        this.ascii = ascii;
        this.x = x - 1;
        this.y = y -1;
        this.alive = true;
        this.name= name;
    }
    public void changeWeapons(Item weapons){
        this.att = att - equipedWeapon.dommage;
        this.equipedWeapon.equiped = false;
        this.equipedWeapon = weapons;
        this.equipedWeapon.equiped = true;
        this.att = att + equipedWeapon.dommage;

    }
    public void getHurt(int att) {
        this.lp -= att;
    }
    public void attackEntity(Entity target) {
        target.getHurt(this.att);
    } 
    public void magicEntity(Entity target,Player p1) {
        if(p1.mana>=5){
            
            target.getHurt(p1.magicAtt);
        }else{
            System.out.println("Pas assez de mana");
        }
    } 
   
}


