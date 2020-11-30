public  class Bow extends Weapon {
    private static final double DAMAGE = 10;
    public Bow() {
    super(DAMAGE);
    }
public String ascii_art(){
    return 
    "(             \n"+
    "    \\         \n"+
    "     )        \n"+
    "##-------->   \n"+ 
    "     )        \n"+
    "    /         \n"+
    "   (          \n";
    
}
}