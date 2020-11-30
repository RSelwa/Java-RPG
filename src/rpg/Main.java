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
    //*Crea PNJ
    PNJ marchand = new PNJ(1, 10) {};
    Heal heal = new Heal(1, 1) {};
    marchand.addStock(
      new Weapons("Hache de la mort", 55, 15) {},
      new Weapons("Arc de la Terreur", 10, 5) {}
    );
    //* Crea Mob
    while (p1.alive) {
      deplacement(p1, map, mobList, marchand, heal);
    } ;
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
      " ᴥ/♠/♣  = MOB" +
      "\n" +
      "Déplacer vous avec Z, Q, S, D (puis appuyez sur entrée):"
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
      System.out.println("What do you want to do (Attack: a, Nothing : n): ");
      Scanner clavier = new Scanner(System.in);
      if (clavier.hasNext("a") || clavier.hasNext("A")) {
        System.out.println(" Vous attaquez " + mob.name);
        p1.attackEntity(mob);
        System.out.println(mob.name + "lp : " + mob.lp);
        System.out.println(mob.name + " vous attaque");
        mob.attackEntity(p1);
        System.out.println(p1.name + "lp : " + p1.lp);
      } else if (clavier.hasNext("n") || clavier.hasNext("N")) {
        System.out.println(mob.name + " vous attaque");
        mob.attackEntity(p1);
        System.out.println(p1.name + "lp : " + p1.lp);
      } else {
        System.out.println("Entrez une action valide");
      }
      if (mob.lp <= 0) { //win fight
        System.out.println("Le " + mob.name + " a été vaincu");
        p1.money += mob.loot;
        System.out.println("Vous remportez " + mob.loot + " ©");
        System.out.println();
        mob.alive = false;
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
    p1.money
  );
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
