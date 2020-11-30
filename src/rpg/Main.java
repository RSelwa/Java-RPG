package rpg;
import java.util.Scanner;
import java.util.ArrayList;

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
      p1.playerChar[caste][1]+
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
      "Argent : "+
      p1.money
    );
    //! ======================================================================
    //*Matrice de la map
    int l = 10, h = 6; // definition de la longueur et la largeur de la map
    final String[][] map = new String[h][l];
    String grass = "□";
    //*Crea PNJ
    PNJ marchand = new PNJ(1, 10) {};
    marchand.addStock(
      new Weapons("Hache de la mort", 55, 15) {},
      new Weapons("Arc de la Terreur", 10, 5) {}
    );
    //* Crea Mob
    Mob mob = new Mob(0, 2, 6) {};

    do {
      //position du joueur
      map[marchand.y][marchand.x] = marchand.ascii;
      if (p1.alive) {
        map[p1.y][p1.x] = p1.ascii;
      }
      if (mob.alive) {
        map[mob.y][mob.x] = mob.ascii;
      }
      //! faire un generateur de mob ici avec des random position et tout
      int[] mobList = new int[5];
      System.out.println(mobList.length);
      //*Map
    
      Map(map, p1, marchand, mob, grass);
      deplacement(p1, map);
      if (p1.y == mob.y && p1.x == mob.x) {
        Fight(p1, mob, map);
      }
      if (p1.y == marchand.y && p1.x == marchand.x) {
        marchand(marchand, p1);
      }
    } while (p1.alive);
  }
  //!GENERATION DE LA MAP
  public static void Map(String[][] map, Player p1, PNJ marchand, Mob mob, String grass) {
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
      mob.ascii +
      " = MOB"
      +"\n"+"Déplacer vous avec Z, Q, S, D (puis appuyez sur entrée):"
    );
  
    //* enleve l'empreinte du tour precedent
    map[p1.y][p1.x] = null;
  }

  
  //!FIGHT
  public static void Fight(Player p1, Mob mob, String[][] map) {
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
      String fightChoose;
      if (clavier.hasNext("a") || clavier.hasNext("A")) {
        System.out.println( " Vous attaquez "+mob.name );
        p1.attackEntity(mob);
        System.out.println(mob.name + "lp : " + mob.lp);
        System.out.println(mob.name + " vous attaque");
        mob.attackEntity(p1);
        System.out.println(p1.name + "lp : " + p1.lp);
      } else if (clavier.hasNext("n") || clavier.hasNext("N")) {
        System.out.println(mob.name + " vous attaque");
        mob.attackEntity(p1);
        System.out.println(p1.name + "lp : " + p1.lp);
      }
      else {
        System.out.println("Entrez une action valide");
      }
      if (mob.lp <= 0) { //win fight
        System.out.println("Le " + mob.name + " a été vaincu");
        p1.money += mob.loot;
        System.out.println("Vous remportez "+mob.loot + " ©");
        System.out.println();
        mob.alive = false;
      }
    }
    mob = null;
  }

  //!MARCHAND
  public static void marchand(PNJ marchand, Player p1) {
    System.out.println("Bienvenue chez le marchand");
    Scanner clavier = new Scanner(System.in);
    
    afficherStock(marchand);
      System.out.println("If you want to Exit, type 'exit'  ");
      //----------------
      if(clavier.hasNext("exit")||clavier.hasNext("EXIT")){
        // if(exit.compareTo("exit") ==0||exit.compareTo("exit") ==0){
          return;
        }
      int res = clavier.nextInt();

      if (res>=0 && res<=marchand.stock.size()) {
        System.out.println("vous voulez acheter "+marchand.stock.get(res).name+" à "+marchand.stock.get(res).price+" © ?  (y/n)");
        String resStr = clavier.next();
        if(resStr.compareTo("y") == 0|| resStr.compareTo("Y") == 0){
          if(p1.money>=marchand.stock.get(res).price){
            p1.money -= marchand.stock.get(res).price;
            p1.inventory.add(marchand.stock.get(res));
            marchand.stock.remove(marchand.stock.get(res));
            System.out.println("Vous avez acheté : "+p1.inventory.get(p1.inventory.size()-1).name);
            System.out.println("Bonne journée");
          }else{
            System.out.println("Vous n'avez pas assez d'argent, bonne journée");
            System.out.println("(tapez 'exit')");
          }
          
        }else if(resStr.compareTo("n") == 0|| resStr.compareTo("N") == 0){
          System.out.println("D'accord, nous vous souhaitons une bonne journée");
          System.out.println("(tapez 'exit')");
        }else{
          System.out.println("Entrez un resultat valide svp, sortez puis re-rentrez dans le magasin");
          System.out.println("(tapez 'exit')");
        }
      }else{
        System.out.println("Entrez un resultat valide svp, sortez puis re-rentrez dans le magasin");
        System.out.println("(tapez 'exit')");

      }
      if(clavier.hasNext("exit")||clavier.hasNext("EXIT")){
      // if(exit.compareTo("exit") ==0||exit.compareTo("exit") ==0){
        return;
      }
    // while(clavier.hasNext("exit")||clavier.hasNext("EXIT"))
    
  }
  public static void afficherStock(PNJ marchand){
    System.out.println("Selectionner un Item à acheter : ");
    for (int i = 0; i < marchand.stock.size(); i++) {
        System.out.println("("+i+") - "+marchand.stock.get(i).name +" :  "+ marchand.stock.get(i).price + "©");
    }
  }

  //! DEPLACEMENT
  public static void deplacement(Player p1, String[][] map) {
    Scanner clavier = new Scanner(System.in);
    if (clavier.hasNext("z") || clavier.hasNext("Z")) {
      if (p1.y == 0) {
        System.out.println("Out of Bounds");
      } else {
        p1.y -= 1; //y-1
      }
    } else if (clavier.hasNext("q") || clavier.hasNext("Q")) {
      if (p1.x == 0) {
        System.out.println("Out of Bounds");
      } else {
        p1.x -= 1; //y-1
      }
    } else if (clavier.hasNext("s") || clavier.hasNext("S")) {
      if (p1.y + 1 == map.length) {
        System.out.println("Out of Bounds");
      } else {
        p1.y += 1; //y+1
      }
    } else if (clavier.hasNext("d") || clavier.hasNext("D")) {
      if (p1.x + 1 == map[0].length) {
        System.out.println("Out of Bounds");
      } else {
        p1.x += 1; //x+1
      }
    } else if (clavier.hasNext("i") || clavier.hasNext("I")) {
System.out.println("Voici vos stats : \n");
System.out.println("Nom : " +
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
"Argent : "+
p1.money);
    }
    else {
      System.out.println("Entrez un deplacement valide");
    }
  }

}
