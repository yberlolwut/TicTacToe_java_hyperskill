package tictactoe;
import java.util.Scanner;

public class Main {

    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] field = new char[3][3];
        boolean oTurn = false;
        fillArray(field);
        drawField(field);
        do {
          oTurn = userInputToField(field,oTurn);
        }while(analyzeState(field));
    }

    static void drawField(char[][] field) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    System.out.print("| " + field[i][j] + " ");
                } else if (j == 2) {
                    System.out.println(field[i][j] + " |");
                } else {
                    System.out.print(field[i][j] + " ");
                }
            }
        }
        System.out.println("---------");
    }

    static void fillArray(char[][] field) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    field[i][j] = ' ';
                }
            }
    }

    static boolean analyzeState(char[][] field) {
        if (analyzeValidGame(field)) {
            System.out.println("Impossible");
            return false;
        } else if (analyzeRows(field) != 'F') {
            System.out.println(analyzeRows(field) + " wins");
            return false;
        } else if (analyzeColumns(field) != 'F') {
            System.out.println(analyzeColumns(field) + " wins");
            return false;
        } else if (analyzeDiagonals(field) != 'F') {
            System.out.println(analyzeDiagonals(field) + " wins");
            return false;
        } else if (analyzeGameFinished(field)) {
            System.out.println("Game not finished");
            return true;
        } else {
            System.out.println("Draw");
            return false;
        }


    }

    static char analyzeRows(char[][] field) {
        int counter = 0;
        char value = '0';
        for (int i = 0; i < 3; i++) {
            char temp1 = field[i][0];
            char temp2 = field[i][1];
            char temp3 = field[i][2];
            if (temp1 == temp2 && temp2 == temp3 && temp1 != ' ') {
                counter++;
                value = temp1;
            }
        }
        if (counter == 1) {
            return value;
        } else if (counter > 1) {
            return 'T';
        } else {
            return 'F';
        }

    }

    static char analyzeColumns(char[][] field) {
        int counter = 0;
        char value = '0';
        for (int i = 0; i < 3; i++) {
            char temp1 = field[0][i];
            char temp2 = field[1][i];
            char temp3 = field[2][i];
            if (temp1 == temp2 && temp2 == temp3 && temp1 != ' ') {
                counter++;
                value = temp1;
            }
        }
        if (counter == 1) {
            return value;
        } else if (counter > 1) {
            return 'T';
        } else {
            return 'F';
        }
    }

    static char analyzeDiagonals(char[][] field) {
        char temp1 = field[0][0];
        char temp2 = field[1][1];
        char temp3 = field[2][2];
        if (temp1 == temp2 && temp2 == temp3 && temp1 != ' ') {
            return temp1;
        }
        temp1 = field[0][2];
        temp3 = field[2][0];
        if (temp1 == temp2 && temp2 == temp3 && temp1 != ' ') {
            return temp1;
        }
        return 'F';
    }

    static boolean analyzeValidGame(char[][] field) {
        int xCounter = 0;
        int oCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char temp = field[i][j];
                if (temp == 'X') {
                    xCounter++;
                } else if (temp == 'O') {
                    oCounter++;
                }
            }
        }
        if (Math.abs(xCounter - oCounter) > 1) {
            return true;
        }
        char temp1 = analyzeColumns(field);
        char temp2 = analyzeDiagonals(field);
        char temp3 = analyzeRows(field);
        return temp1 != 'F' && temp2 != 'F' || temp1 != 'F' && temp3 != 'F' || temp2 != 'F' && temp3 != 'F' || temp1 == 'T' || temp3 == 'T';
    }

    static boolean analyzeGameFinished(char[][] field) {
        int _Counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char temp = field[i][j];
                if (temp == ' ') {
                    _Counter++;
                }
            }
        }
        return _Counter > 0;
    }

    static boolean userInputToField(char[][] field,boolean oTurn) {
        boolean temp;
        boolean output = oTurn;
        do {
            System.out.println("Enter move:");
            int input1 = scanner.nextInt() - 1;
            int input2 = scanner.nextInt() - 1;
            if (-1 < input1 && input1 <= 2 && -1 < input2 && input2 <= 2) {
                if (field[input1][input2] ==  ' '){
                    if(oTurn){
                        field[input1][input2] = 'O';
                        output = false;
                    }
                    else {
                        field[input1][input2] = 'X';
                        output = true;
                    }
                    temp = false;
                }else {
                    temp = true;
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
                temp = true;
            }
        }while(temp);
        drawField(field);
        return output;
    }
}
