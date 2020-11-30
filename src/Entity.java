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
    protected Weapons equipedWeapon = new Weapons("Hands",0,2){};
    public Entity(String name,int lp, int att, int defense,int mana, String ascii, int y, int x) {
        this.lp = lp;
        this.att = att + equipedWeapon.dommage;
        this.defense = defense;
        this.mana = mana;
        this.ascii = ascii;
        this.x = x - 1;
        this.y = y -1;
        this.alive = true;
        this.name= name;
    }
    public void getHurt(int att) {
        this.lp -= att;
    }
    public void attackEntity(Entity target) {
        target.getHurt(this.att);
    } 
    public void magicEntity(Entity target,Player from) {
        target.getHurt(from.att);
    } 
   
}


