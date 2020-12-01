package rpg;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

// import sun.security.util.ArrayUtil;

public class Main {

  public static void main(String[] args) {
    //! ======================================================================
    System.out.println("Bienvenue dans le RPG");
    System.out.println("Vous allez Créer votre personnage");
    System.out.println("Choisissez votre nom : ");
    Scanner scanner = new Scanner(System.in);
    String name = scanner.nextLine();
    System.out.println("Choisissez votre classe : ");
    System.out.println("0 : Mage, 1 : Elfe, 2 : Guerrier, 3: Pretre");
    int caste = scanner.nextInt();
    while (caste != 0 && caste != 1 && caste != 2 && caste != 3) {
      System.out.println("Saisissez un nombre correct");
      caste = scanner.nextInt();
    }
    Player p1 = new Player(name, caste) {};
    
    System.out.println(
      "Voici votre personnage : " +
      "\n" +
      "Nom : " +
      p1.name +
      "\n" +
      "Classe : " +
      p1.caste +
      "\n" +
      p1.playerChar[caste][1] +
      "\n" +
      "Lp : " +
      p1.lp +
      "\n" +
      "Att : " +
      p1.att +
      "\n" +
      "Def : " +
      p1.defense +
      "\n" +
      "Mana : " +
      p1.mana +
      "\n" +
      "Argent : " +
      p1.money
    );
    //! ======================================================================

    //*Matrice de la map
    int l = 10, h = 6; // definition de la longueur et la largeur de la map
    final ArrayList<Mob> mobList = new ArrayList();
    final String[][] map = new String[h][l];
    //*Crea PNJ et shop
    PNJ marchand = new PNJ(1, 10) {};
    Heal heal = new Heal(1, 1) {};
    marchand.addStock(
      new Weapons("Hache de la mort", 55, 15,false) {},
      new Weapons("Arc de la Terreur", 10, 5,false) {}
    );
    while (p1.lp>0) {
      deplacement(p1, map, mobList, marchand, heal);
    } ;
    if(p1.lp<0){
      System.out.println("GAME OVER");
    }
  }

  //!GENERATION DE LA MAP
  public static void Map(
    String[][] map,
    Player p1,
    PNJ marchand,
    Heal heal,
    ArrayList<Mob> mobList,
    String grass
  ) {

    map[marchand.y][marchand.x] = marchand.ascii;
    map[heal.y][heal.x] = heal.ascii;
    for (Mob el : mobList) {
      map[el.y][el.x] = el.ascii;
    }
    
    
    map[p1.y][p1.x] = p1.ascii;

    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == null) {
          map[i][j] = grass;
        }
      }
    }
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        System.out.print(map[i][j] + " ");
      }
      System.out.println();
    }
    //*légende
    System.out.println("\n");
   
    System.out.println(
      p1.ascii +
      " = Joueur, " +
      marchand.ascii +
      " = Marchand, " +
      " ᴥ/♠/♣  = MOB," +
      "♥  = HEAL"+
      "\n" +
      "Déplacer vous avec Z, Q, S, D | i pour info | e pour equipement (puis appuyez sur entrée):"
    );
  }

  //!FIGHT
  public static void Fight(Player p1, Mob mob, String[][] map, ArrayList<Mob> mobList) {
    System.out.println("You're on fight now against " + mob.name);
    while (mob.lp > 0) {
      System.out.println("---------------------");
      System.out.println(mob.skin);
      System.out.println(mob.name + " LP : " + mob.lp);
      System.out.println("---------------------");
      System.out.println(p1.name + "sdf :");
      System.out.println("  lp : " + p1.lp);
      System.out.println("  Att : " + p1.att);
      System.out.println("---------------------");
      System.out.println("What do you want to do (Attack: a,Magie : m, Nothing : n): ");
      Scanner clavier = new Scanner(System.in);
      String nextMove = clavier.nextLine();
      switch(nextMove){
        case "a":
          System.out.println(" Vous attaquez " + mob.name+" avec "+p1.att+" deg");
          p1.attackEntity(mob);
          System.out.println(mob.name + "lp : " + mob.lp);
          System.out.println(mob.name + " vous attaque"+" avec "+mob.att+" deg");
          mob.attackEntity(p1);
          System.out.println("\n");
          System.out.println(p1.name + "  lp : " + p1.lp);
          break;
        case "m":
        System.out.println("ce sort vous coute 5 mana");
        System.out.println(" Vous attaquez " + mob.name+" avec "+p1.magicAtt+" deg");
        p1.magicEntity(mob,p1);
          System.out.println(mob.name + "lp : " + mob.lp);
          System.out.println(mob.name + " vous attaque"+" avec "+mob.att+" deg");
          mob.attackEntity(p1);
          System.out.println("\n");
          System.out.println(p1.name + "  lp : " + p1.lp);
          break;
        case "n":
          System.out.println(mob.name + " vous attaque"+" avec "+mob.att);
          mob.attackEntity(p1);
          System.out.println("\n");
          System.out.println(p1.name + "  lp : " + p1.lp);
          break;
        default:
          System.out.println("Entrez une action valide");
      }
      
      if (mob.lp <= 0) { //win fight
        
        System.out.println("Le " + mob.name + " a été vaincu");
        p1.money += mob.loot;
        System.out.println("Vous remportez " + mob.loot + " ©");
        System.out.println();
        mob.alive = false;
      }
      if(p1.lp<0){
        return;
      }
    }
    mobList.remove(mob);
  }

  //! DEPLACEMENT
  public static void deplacement(
    Player p1,
    String[][] map,
    ArrayList<Mob> mobList,
    PNJ marchand,
    Heal heal
  ) {
    String grass ="□";
    Map(map, p1, marchand,heal, mobList, grass);

    mobGenerator(mobList, map, p1);
    //* enleve la trace
    map[p1.y][p1.x] = null;

    Scanner clavier = new Scanner(System.in);
    String nextMove = clavier.nextLine();
    System.out.println(nextMove);

    switch (nextMove) {
      case "z":
        if (p1.y == 0) {
          System.out.println("Out of Bounds");
        } else {

          p1.y -= 1; //y-1
        }
        break;
      case "q":
        if (p1.x == 0) {
          System.out.println("Out of Bounds");
        } else {

          p1.x -= 1; //y-1
        }
        break;
      case "s":
        if (p1.y + 1 == map.length) {
          System.out.println("Out of Bounds");
        } else {

          p1.y += 1; //y+1
        }
        break;
      case "d":
        if (p1.x + 1 == map[0].length) {
          System.out.println("Out of Bounds");
        } else {

          p1.x += 1; //x+1
        }
        break;
      case "i":
        info(p1);
        break;
      case "e":
        equipement(p1);
        break;
      default:
        System.out.println("Entrez un deplacement valide");
        
      }
          if (p1.y == marchand.y && p1.x == marchand.x) {
            marchand.marchand(p1);
          }
          if (p1.y == heal.y && p1.x == heal.x) {
            heal.heal(p1);
          }
  }
  //! INFOS
  public static void info(Player p1){
  System.out.println("Voici vos stats : \n");
  System.out.println(
    "Nom : " +
    p1.name +
    "\n" +
    "Classe : " +
    p1.caste +
    "\n" +
    "Lp : " +
    p1.lp +
    "\n" +
    "Att : " +
    p1.att +
    "\n" +
    "Def : " +
    p1.defense +
    "\n" +
    "Mana : " +
    p1.mana +
    "\n" +
    "Argent : " +
    p1.money+
    "\n"
  );
  for(Item el : p1.inventory){
    System.out.println(el.name+" : "+el.dommage +" deg");
  }
}
  //! EQUIPREMENT
  public static void equipement(Player p1){
    System.out.println("Voulez-vous changez d'equipement ?");
    if(p1.inventory.size()==0){
      System.out.println("Vous n'avez aucune arme");
    }
    for(int i = 0; i< p1.inventory.size();i++){
      if(p1.inventory.get(i).equiped==false){
        System.out.println("("+(i+1)+") "+p1.inventory.get(i).name+" : "+p1.inventory.get(i).dommage +" deg");
        }
      }
        Scanner clavier = new Scanner(System.in);
        String nextMove =clavier.nextLine();
   
    switch(nextMove){
      case "1":
      int nexInt = Integer.parseInt(nextMove);
      p1.changeWeapons(p1.inventory.get(nexInt -1));
      break;
      case "2":
      nexInt = Integer.parseInt(nextMove);
      p1.changeWeapons(p1.inventory.get(nexInt -1));
      break;
      default:
      System.out.println("Entrez une réponse valide");
    }
  }  
//! MOB GENERATOR
  public static void mobGenerator(ArrayList<Mob> mobList, String[][] map,Player p1) {
    if (mobList.size() < 5) {
      int m = randomeInt(0, 3);
      int spawn = randomeInt(0, 10);
      if (spawn < 3) {
        int h = randomeInt(0, map.length), l = randomeInt(0, map[0].length);
        if (map[h][l].compareTo("□") == 0) {
          Mob mob = new Mob(m, h+1, l+1) {};
          mobList.add(mob);
        }
      }
    }
    
    for(int i =0; i<mobList.size();i++){
      if (p1.y == mobList.get(i).y && p1.x == mobList.get(i).x) {
          Fight(p1, mobList.get(i), map, mobList);
        }
    }
  }

  public static int randomeInt(int min, int max) {
    int result = ThreadLocalRandom.current().nextInt(min, max);
    return result;
  }
}
