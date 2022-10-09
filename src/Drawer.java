import java.awt.*;
import java.io.IOException;

public class Drawer {
    public static char[][] field;

    public static void setField(char[][] field) {
        Drawer.field = field;
    }

     //   System.out.println(SystemColors.RED + "RED COLORED" + SystemColors.RESET + " NORMAL");
     //   System.out.println(SystemColors.RED_BACKGROUND + "RED BACK" + SystemColors.RESET + " NORMAL");
     public static void clearScreen() {
         System.out.print("\033[H\033[2J");
         System.out.flush();
     }

    public static void draw() {
        try {
//            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception ignored) {

        }
        clearScreen();
        char i = 'a';
        System.out.print("   ");
        for (int j = 0; j < 10; j++) {
            System.out.print(j+1 + "   ");
        }
        System.out.println();
        for (int j =0; j<10; j++) {
            System.out.print(i  + "  ");
            for (int k = 0; k<10; k++) {
                if (field[j][k] == '0') {
                    System.out.print(SystemColors.GREEN + field[j][k] + SystemColors.RESET+ "   ");
                }
                else System.out.print(SystemColors.BLACK + field[j][k] + SystemColors.RESET+ "   ");
            }
            i++;
            System.out.println();
        }
    }
}
