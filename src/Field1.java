import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Field {
    public static char[][] playerField;
    public static char[][] enemiesField;


    public static void setField(char[][] field) {
        playerField = field;
        enemiesField = field.clone();
    }

    //public static Scanner sc = new Scanner(System.in);
    public static Scanner sc;

    static {
        try {
            sc = new Scanner(new File("input"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void initField() {
        for (int i = 0; i < playerField.length; i++) {
            for (int j = 0; j < playerField.length; j++) {
                playerField[i][j] = '*';
            }
        }
    }
    
    public static void putShips() {
        int len;
        for (int i = 0; i <= 3; i++) {
            len = 4-i;
            System.out.println("\nput  " + len + "'s ship");

            String dir;
            int letterPosition;
            int numberPosition;

            for (int i1 = 0; i1<i+1; i1++) {
                if (i!=3) {
                    do {
                        char posIString = sc.next().charAt(0);
                        String posJString = sc.next();
                        dir = sc.next();

                        letterPosition = posIString + 1 - 97;
                        numberPosition = Integer.parseInt(posJString);

                    } while (!checkLine(letterPosition, numberPosition, 4 - i, dir));
                } else {
                    dir = "";
                    do {
                        char posIString = sc.next().charAt(0);
                        String posJString = sc.next();

                        letterPosition = posIString + 1 - 97;
                        numberPosition = Integer.parseInt(posJString);

                    } while (!checkLine(letterPosition, numberPosition));
                }

                switch (dir) {
                    case "RIGHT" -> {
                        for (int j = 0; j < len; j++) {
                            playerField[letterPosition-1][numberPosition+j-1] = '0';
                        }
                    }
                    case "DOWN" -> {
                        for (int j = 0; j < len; j++) {
                            playerField[letterPosition+j-1][numberPosition-1] = '0';
                        }
                    }
                    case "" -> playerField[letterPosition-1][numberPosition-1] = '0';
                }
                Drawer.draw();
            }
        }
    }

    public static boolean checkLine(int stX, int stY, int len, String dir) {
        return checkBorders(stX, stY, len, dir) && checkNeighbours(stX, stY, len, dir);
    }

    public static boolean checkLine(int stX, int stY)
    {
        return checkBorders(stX, stY) && checkNeighbours(stX, stY);
    }

    public static boolean checkBorders(int stX, int stY, int len, String dir) {
        if (stX < 0 || stY < 0) return false;

        return switch (dir) {
            case "RIGHT" -> stY + len - 1 <= 10;
            case "DOWN" -> stX + len - 1 <= 10;
            default -> false;
        };
    }

    public static boolean checkBorders(int stX, int stY) {
        return stX >= 0 && stY >= 0 && (stX <= 10 && stY <= 10);
    }

    public static boolean checkNeighbours(int stX, int stY, int len, String dir) {
        int dX;
        int dY;
        int shiftX;
        int shiftY;
        switch (dir) {
            case "RIGHT" -> {
                dX = 0;
                dY = 1;
                for (int i = 0; i < len; i++) {
                    shiftX = 0;
                    shiftY = i * dY;
                    if (i == 0) // самая первая клетка (проверка на диагонали и лево\право)
                    {
                        if ((playerField[stX + shiftX][stY + shiftY - 1] != '*') || (playerField[stX + shiftX + 1][stY + shiftY - 1] != '*') || (playerField[stX + shiftX - 1][stY + shiftY - 1] != '*'))
                            return false;
                    }

                    //TODO fix
                    if ((playerField[stX + shiftX - 1][stY + shiftY] != '*') || (playerField[stX + shiftX + 1][stY + shiftY] != '*'))
                        return false;

                    if (i == len - 1) {
                        if ((playerField[stX + shiftX][stY + shiftY + 1] != '*') || (playerField[stX + shiftX - 1][stY + shiftY + 1] != '*') || (playerField[stX + shiftX + 1][stY + shiftY + 1] != '*'))
                            return false;
                    }
                }
            }
            case "DOWN" -> {
                dX = 1;
                dY = 0;
                for (int i = 0; i < len; i++) {
                    shiftX = i * dX;
                    shiftY = 0;
                    if (i == len - 1) // самая первая клетка (проверка на диагонали и верх\низ)
                    {
                        if ((playerField[stX + shiftX + 1][stY + shiftY - 1] != '*') || (playerField[stX + shiftX + 1][stY + shiftY] != '*') || (playerField[stX + shiftX + 1][stY + shiftY + 1] != '*'))
                            return false;
                    }

                    if ((playerField[stX + shiftX][stY + shiftY + 1] != '*') || (playerField[stX + shiftX][stY + shiftY - 1] != '*'))
                        return false;

                    if (i == 0) {
                        if ((playerField[stX + shiftX - 1][stY + shiftY - 1] != '*') || (playerField[stX + shiftX - 1][stY + shiftY] != '*') || (playerField[stX + shiftX - 1][stY + shiftY + 1] != '*'))
                            return false;
                    }
                }
            }
            default -> {return false;}
        }
        return true;
    }

    public static boolean checkNeighbours(int stX, int stY) {
        return playerField[stX-1][stY-1] == '*' &&
                playerField[stX-1][stY] == '*' &&
                playerField[stX-1][stY+1] == '*' &&
                playerField[stX][stY-1] == '*' &&
                playerField[stX][stY+1] == '*' &&
                playerField[stX+1][stY-1] == '*' &&
                playerField[stX+1][stY] == '*' &&
                playerField[stX+1][stY+1] == '*';

    }
}