
public class Main {
    public static final char[][] field = new char[12][12];

    public static void main(String[] args) {
        System.out.println(SystemColors.RED + "RED COLORED" + SystemColors.RESET + " NORMAL");
        System.out.println(SystemColors.RED_BACKGROUND + "RED BACK" + SystemColors.RESET + " NORMAL");
        Field.setField(field);
        Drawer.setField(field);

        Field.initField();
        Drawer.draw();
        Field.putShips();
    }
}
