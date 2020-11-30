package rpg;


abstract class Heal {
    protected String ascii = "♥";
    protected int x;
    protected int y;
    public Heal(int y, int x) {
      this.x = x -1 ;
      this.y = y -1;
    }
    public void heal(Player p1){
        while(p1.lp<p1.lpMax){
            p1.lp +=1;
        }
        while(p1.mana<p1.manaMax){
            p1.mana +=1;
        }
        System.out.println("Vos stats ont été réstauré");
    }
}
