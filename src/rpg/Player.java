package rpg;
import java.util.ArrayList;

abstract class Player extends Entity {
    //protected int mp;
    protected String caste;
    protected String[] optis = {"Vous êtes plutot bien équilibré entre sorts et attaque", "Vous êtes un tueur en attaque mais attention à votre défense", "Vous êtes plutôt un tank","Vous êtes un grand magicien"};
    protected int money = 70;
    protected int magicAtt;
    //lp atts def mana
    protected static String[][] playerChar= {{"Mage","Vous êtes plutot bien équilibré entre sorts et attaque"},{"Elfe","Vous êtes un tueur en attaque mais attention à votre défense"},{"Guerrier","Vous êtes plutôt un tank"},{"Pretre","Vous êtes un grand magicien"}};
    
    protected static int[][] playerStats = {{100,15,10,50,10},{80,25,10,0,0},{100,10,20,0,0},{120,5,20,100,20}};
    protected int lpMax;
    protected int manaMax;
    protected ArrayList<Item> inventory = new ArrayList();
    public Player(String n, int x) {
        super(n,playerStats[x][0], playerStats[x][1], playerStats[x][2], playerStats[x][3], "⬘", 6, 1,new Weapons("hands", 0,0,true){
        });
        this.name = n;
        this.magicAtt =  playerStats[x][4];
        this.caste = playerChar[x][0];
        this.lpMax = playerStats[x][0];
        this.manaMax = playerStats[x][3];
    }
}