package rpg;
import java.util.ArrayList;
import java.util.Scanner;

abstract class PNJ{
    protected ArrayList<Item> stock = new ArrayList();
    protected String ascii = "$";
    protected int x;
    protected int y;
    public PNJ( int y, int x) {
      this.x = x -1 ;
      this.y = y -1;
    }
    public void addStock(Item... items){
      for (Item item : items) {
        this.stock.add(item);
    }
    }


    public  void marchand( Player p1) {
      System.out.println("Bienvenue chez le marchand");
      System.out.println("Vous avez "+p1.money+" © ");
      
      afficherStock();
      System.out.println("If you want to Exit, type 'exit'  ");
      Scanner clavier = new Scanner(System.in);
      String nextMove = clavier.nextLine();
      int nextInt;  
      switch(nextMove){
        case "1":
         nextInt = Integer.parseInt(nextMove);  
          System.out.println(
            "vous voulez acheter " +
            this.stock.get(nextInt - 1).name +
            " à " +
            this.stock.get(nextInt - 1).price +
            " © ?  (y/n)"
          );
          nextMove = clavier.nextLine();
          switch(nextMove){
            case "y":
              if (p1.money >= this.stock.get(nextInt - 1).price) {
                p1.money -= this.stock.get(nextInt - 1).price;
                p1.inventory.add(this.stock.get(nextInt - 1));
                this.stock.remove(this.stock.get(nextInt - 1));
                System.out.println(
                  "Vous avez acheté : " +
                  p1.inventory.get(p1.inventory.size() - 1).name
                  );
                System.out.println("Bonne journée");
              } else {
                System.out.println("Vous n'avez pas assez d'argent, bonne journée");
              }
              break;
              case "n":
              System.out.println("D'accord, nous vous souhaitons une bonne journée");
              break;
              default:
              System.out.println("Entrez un resultat valide svp, sortez puis re-rentrez dans le magasin");
              break;  
          }
          break;
        case "2":
        nextInt = Integer.parseInt(nextMove);  

        System.out.println(
            "vous voulez acheter " +
            this.stock.get(nextInt - 1).name +
            " à " +
            this.stock.get(nextInt - 1).price +
            " © ?  (y/n)"
          );
          nextMove = clavier.nextLine();
          switch(nextMove){
            case "y":
            if (p1.money >= this.stock.get(nextInt - 1).price) {
                p1.money -= this.stock.get(nextInt - 1).price;
                p1.inventory.add(this.stock.get(nextInt - 1));
                this.stock.remove(this.stock.get(nextInt - 1));
                System.out.println(
                  "Vous avez acheté : " +
                  p1.inventory.get(p1.inventory.size() - 1).name
                );
                System.out.println("Bonne journée");
              } else {
                System.out.println("Vous n'avez pas assez d'argent, bonne journée");
              }
              break;
              case "n":
              System.out.println("D'accord, nous vous souhaitons une bonne journée");
              break;
            default:
            System.out.println("Entrez un resultat valide svp, sortez puis re-rentrez dans le magasin");
            break;  
          }
          break;
          default:
            System.out.println("pas compris");
          break;
        }
         nextMove = clavier.nextLine();
        switch(nextMove){
          default:
            break;  
        }
        // if (clavier.hasNext("exit") || clavier.hasNext("EXIT")) {
          //   // if(exit.compareTo("exit") ==0||exit.compareTo("exit") ==0){
            //   return;
      // }
      // int res = clavier.nextInt();
  
      // if (res >= 0 && res <= this.stock.size()) {
      //   System.out.println(
      //     "vous voulez acheter " +
      //     this.stock.get(res).name +
      //     " à " +
      //     this.stock.get(res).price +
      //     " © ?  (y/n)"
      //   );
      //   String resStr = clavier.next();
      //   if (resStr.compareTo("y") == 0 || resStr.compareTo("Y") == 0) {
      //     if (p1.money >= this.stock.get(res).price) {
      //       p1.money -= this.stock.get(res).price;
      //       p1.inventory.add(this.stock.get(res));
      //       this.stock.remove(this.stock.get(res));
      //       System.out.println(
      //         "Vous avez acheté : " +
      //         p1.inventory.get(p1.inventory.size() - 1).name
      //       );
      //       System.out.println("Bonne journée");
      //     } else {
      //       System.out.println("Vous n'avez pas assez d'argent, bonne journée");
      //       System.out.println("(tapez 'exit')");
      //     }
      //   } else if (resStr.compareTo("n") == 0 || resStr.compareTo("N") == 0) {
      //     System.out.println("D'accord, nous vous souhaitons une bonne journée");
      //     System.out.println("(tapez 'exit')");
      //   } else {
      //     System.out.println(
      //       "Entrez un resultat valide svp, sortez puis re-rentrez dans le magasin"
      //     );
      //     System.out.println("(tapez 'exit')");
      //   }
      // } else {
      //   System.out.println(
      //     "Entrez un resultat valide svp, sortez puis re-rentrez dans le magasin"
      //   );
      //   System.out.println("(tapez 'exit')");
      // }
      // if (clavier.hasNext("exit") || clavier.hasNext("EXIT")) {
      //   // if(exit.compareTo("exit") ==0||exit.compareTo("exit") ==0){
      //   return;
      // }


      // while(clavier.hasNext("exit")||clavier.hasNext("EXIT"))
  
    }
    public  void afficherStock() {
      System.out.println("Selectionner un Item à acheter : ");
      for (int i = 0; i < this.stock.size(); i++) {
        System.out.println(
          "(" +
          (i+1) +
          ") - " +
          this.stock.get(i).name +
          " :  " +
          this.stock.get(i).price +
          "©"
        );
      }
    }
    // public  void marchand( Player p1) {
    //   System.out.println("Bienvenue chez le marchand");
    //   Scanner clavier = new Scanner(System.in);
  
    //   afficherStock();
    //   System.out.println("If you want to Exit, type 'exit'  ");
    //   //----------------
    //   if (clavier.hasNext("exit") || clavier.hasNext("EXIT")) {
    //     // if(exit.compareTo("exit") ==0||exit.compareTo("exit") ==0){
    //     return;
    //   }
    //   int res = clavier.nextInt();
  
    //   if (res >= 0 && res <= this.stock.size()) {
    //     System.out.println(
    //       "vous voulez acheter " +
    //       this.stock.get(res).name +
    //       " à " +
    //       this.stock.get(res).price +
    //       " © ?  (y/n)"
    //     );
    //     String resStr = clavier.next();
    //     if (resStr.compareTo("y") == 0 || resStr.compareTo("Y") == 0) {
    //       if (p1.money >= this.stock.get(res).price) {
    //         p1.money -= this.stock.get(res).price;
    //         p1.inventory.add(this.stock.get(res));
    //         this.stock.remove(this.stock.get(res));
    //         System.out.println(
    //           "Vous avez acheté : " +
    //           p1.inventory.get(p1.inventory.size() - 1).name
    //         );
    //         System.out.println("Bonne journée");
    //       } else {
    //         System.out.println("Vous n'avez pas assez d'argent, bonne journée");
    //         System.out.println("(tapez 'exit')");
    //       }
    //     } else if (resStr.compareTo("n") == 0 || resStr.compareTo("N") == 0) {
    //       System.out.println("D'accord, nous vous souhaitons une bonne journée");
    //       System.out.println("(tapez 'exit')");
    //     } else {
    //       System.out.println(
    //         "Entrez un resultat valide svp, sortez puis re-rentrez dans le magasin"
    //       );
    //       System.out.println("(tapez 'exit')");
    //     }
    //   } else {
    //     System.out.println(
    //       "Entrez un resultat valide svp, sortez puis re-rentrez dans le magasin"
    //     );
    //     System.out.println("(tapez 'exit')");
    //   }
    //   if (clavier.hasNext("exit") || clavier.hasNext("EXIT")) {
    //     // if(exit.compareTo("exit") ==0||exit.compareTo("exit") ==0){
    //     return;
    //   }
    //   // while(clavier.hasNext("exit")||clavier.hasNext("EXIT"))
  
    // }
    // public  void afficherStock() {
    //   System.out.println("Selectionner un Item à acheter : ");
    //   for (int i = 0; i < this.stock.size(); i++) {
    //     System.out.println(
    //       "(" +
    //       i +
    //       ") - " +
    //       this.stock.get(i).name +
    //       " :  " +
    //       this.stock.get(i).price +
    //       "©"
    //     );
    //   }
    // }
}
//♦