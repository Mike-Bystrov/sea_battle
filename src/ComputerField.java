import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class ComputerField extends Field {
    private static final String[] directions = {"RIGHT","DOWN"};
    private static int[] arr = new int[100];
    private static int numberOfTakenBlocks = 0;
    private static final Random random = new Random();

    // hello mike
    @Override
    //TODO проверить почему корабли становятся рядом!!!!
    public void putShips() {
        int pos = 0;
        int len;
        String direction="";
        // 0-99 numbers for field representation
        for (int i = 0; i < 99; i++) {
            arr[i] = i;
        }

        for (int i=0; i<4; i++){
            len = 4-i;
            for (int j=0; j<4-len+1; j++) {
                do {
                    //pos = 95;
                    pos = random.nextInt((99 - numberOfTakenBlocks) + 1) + numberOfTakenBlocks;
                } while (!checkDirections(pos, len).isDirectionValid);
                direction = checkDirections(pos, len).direction;

                // now we get free position (pos) and direction
                int x = convertNumberToCoordinates(pos)[0];
                int y = convertNumberToCoordinates(pos)[1];

                removeExcessBlocks(x, y, len, direction);

                initField(x, y, len, direction);

                drawer.draw();
            }
        }
    }

    private static int findFreePos() {
        // rand.nextInt((max - min) + 1) + min;
        // pos = [first free position, 99]
        int pos = random.nextInt((99 - numberOfTakenBlocks) + 1) + numberOfTakenBlocks;

        return arr[pos];
    }

    private static Pair checkDirections(int pos, int len) {

        Pair pair = new Pair(false, "");

        int x = convertNumberToCoordinates(pos)[0];
        int y = convertNumberToCoordinates(pos)[1];

        for(int i=0; i<=1; i++) {
            if (checkLine(x,y,len,directions[i])) {
                pair.setDirection(directions[i]);
                pair.setDirectionValid(true);
                return pair;
            }
        }
        return pair;
    }

    private void removeExcessBlocks(int x, int y, int len, String direction) {
        int firstCoord, secondCoord, thirdCoord;
        int numOfExcessBlocks = 0;
        if (direction.equals("RIGHT")) {
            for (int i=0; i<len+2; i++) {
                firstCoord = convertCoordinatesToInt(x-1,y-1+i);
                secondCoord = convertCoordinatesToInt(x, y-1+i);
                thirdCoord = convertCoordinatesToInt(x+1, y-1+i);
                if (firstCoord >0 && firstCoord <100) {
                    arr[firstCoord] = -1;
                    if(arr[firstCoord]!=-1)numOfExcessBlocks++;
                }
                if (secondCoord >0 && secondCoord <100) {
                    arr[secondCoord] = -1;
                    if(arr[secondCoord]!=-1)numOfExcessBlocks++;
                }
                if (thirdCoord >0 && thirdCoord <100) {
                    arr[thirdCoord] = -1;
                    if(arr[thirdCoord]!=-1)numOfExcessBlocks++;
                }
            }
        } else if (direction.equals("DOWN") || direction.equals("")) {
            for (int i=0; i<len+2; i++) {
                firstCoord = convertCoordinatesToInt(x-1+i,y-1);
                secondCoord = convertCoordinatesToInt(x-1+i, y);
                thirdCoord = convertCoordinatesToInt(x-1+i, y+1);

                if (firstCoord >0 && firstCoord <100) {
                    arr[firstCoord] = -1;
                    if(arr[firstCoord]!=-1)numOfExcessBlocks++;
                }
                if (secondCoord >0 && secondCoord <100) {
                    arr[secondCoord] = -1;
                    if(arr[secondCoord]!=-1) numOfExcessBlocks++;
                }
                if (thirdCoord >0 && thirdCoord <100) {
                    arr[thirdCoord] = -1;
                    if(arr[thirdCoord]!=-1) numOfExcessBlocks++;
                }
            }
        }

        numberOfTakenBlocks += numOfExcessBlocks;
        arr = Arrays.stream(arr).sorted().toArray();
    }

    private void initField(int x, int y, int len, String direction) {
        if (direction.equals("RIGHT")) {
            for (int i=0; i<len; i++) {
                field[x-1][y-1+i] = '0';
            }
        } else if (direction.equals("DOWN") || direction.equals("")) {
            for (int i=0; i<len; i++) {
                field[x-1+i][y-1] = '0';
            }
        }
    }

    private static int convertCoordinatesToInt(int x, int y) {
        return 10*(x-1) + y - 1;
    }

    private static int[] convertNumberToCoordinates(int num) {
        return new int[]{(num - num%10)/10+1,(num+1)%10};
    }

}
