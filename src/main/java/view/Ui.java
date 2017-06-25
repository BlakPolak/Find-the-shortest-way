package view;


import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static void printText(String text) {
        System.out.println(text);
    }

    public static String prepareWelcomeText() {
        String welcomeText = "Find the way!\n Please provide cities and cost " +
                "of travelling from each city to another Example:\nWarsaw Cracow 30 etc." +
                "\nand confirm it by enter";
        return welcomeText;
    }

    public static String prepareStartAndDestinationText() {
        String welcomeText = "\nPlease provide start city and destination, and confirm it by enter";
        return welcomeText;
    }


    public static String prepareWrongArgumentText() {
        String wrongArgument = "Wrong arguments. Try again.";
        return wrongArgument;
    }


    public static String prepareEndText() {
        String playAgainText = "Would you like to check another journey? [y/n]";
        return playAgainText;
    }


    public static boolean takeUserCharInput() {
        boolean playAgainOrNot;
        Scanner read = new Scanner(System.in);
        String input = read.nextLine().replaceAll("\\s+","");
        char yesOrNo = input.charAt(0);
        if (input.length() == 1 &&
                (Character.toString(yesOrNo).equals("y") || (Character.toString(yesOrNo).equals("n")))) {
            playAgainOrNot = Character.toString(yesOrNo).equals("y");
        } else {
            throw new IllegalArgumentException();
        }
        return playAgainOrNot;
    }


    public static ArrayList<Integer> takeUserInput() {
        ArrayList<Integer> rowAndColumnList = new ArrayList<>();
        try {
            Scanner read = new Scanner(System.in);

            String inputs = read.nextLine().replaceAll("\\s+","");
            if ((inputs.length() == 2)) {
                Integer givenRow = Integer.parseInt(inputs.substring(0,1)) -1;
                Integer givenColumn = Integer.parseInt(inputs.substring(1,2)) -1;
                rowAndColumnList.add(givenRow);
                rowAndColumnList.add(givenColumn);
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            String textToPrint =prepareWrongArgumentText();
            printText(textToPrint);
        }
        return rowAndColumnList;
    }

}