package bullscows;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);

        System.out.println("Please, enter the secret code's length:");
        int sizeCode = 0;
        try {
            sizeCode = scanner.nextInt();

            if (sizeCode > 36) {
                System.out.println(String.format("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", sizeCode));
                return;
            } else if (sizeCode <= 0) {
                System.out.println(String.format("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", sizeCode));
                return;
            }
        } catch (RuntimeException e) {
            System.out.println("Error: \"abc 0 -7\" isn't a valid number.");
            return;
        }

        System.out.println("Input the number of possible symbols in the code:");

        int numCharacters = 0;

        try {
            numCharacters = scanner.nextInt();

            if (numCharacters > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                return;
            } else if (numCharacters < sizeCode) {
                System.out.println("Error: it's not possible to generate a code with a length of 6 with 5 unique symbols.");
                return;
            }
        } catch (RuntimeException e) {
            System.out.println("Error: \"abc 0 -7\" isn't a valid number.");
            return;
        }

        System.out.print("The secret is prepared: ");

        display(sizeCode, numCharacters);

        System.out.println("Okay, let's start a game!");

        String secretCode = "";
        secretCode = codeGenerator(sizeCode, secretCode, numCharacters);

        int bull=0;
        int count=1;
        while(bull != secretCode.length()){
            System.out.println("Turn "+count+":");
            bull = guessTheNumber(secretCode);
            count++;}
        System.out.println("Congratulations! You guessed the secret code.");
    }

    private static int guessTheNumber(String secretCode) {
        Scanner scanner=new Scanner(System.in);
        String guess=scanner.next();
        int bull=0;
        int cow=0;

        for (int i = 0; i< secretCode.length(); i++){
            if(guess.contains(secretCode.charAt(i)+""))
                cow++;
            if(guess.charAt(i)== secretCode.charAt(i)){
                bull++;
                cow--;
            }
        }

        if(bull==0){
            if(cow==1)
                System.out.println("Grade: 1 cow");
            else System.out.println("Grade: "+cow+" cows");
        }
        else if(cow==0){
            if(bull==1)
                System.out.println("Grade: 1 bull");
            else System.out.println("Grade: "+bull+" bulls");
        }
        else if(cow==1){
            if(bull==1)
                System.out.println("Grade: 1 bull and 1 cow");
            else System.out.println("Grade: "+bull+" bulls and 1 cow");}
        else if(bull==1){
            if(cow==1)
                System.out.println("Grade: 1 bull and 1 cow");
            else
                System.out.println("Grade: 1 bull and "+cow+" cows");}
        else if(cow>1&&bull>1)
            System.out.println("Grade: "+bull+" bulls and "+cow+" cows");
        else
            System.out.println("Grade: None.");
        return bull;
    }

    private static String codeGenerator(int n, String secretCode, int numCharacters) {
        Random random = new Random();
        int res = 0;

        int[] temp = new int[n];

        boolean flag = true;
        for (int i = 0; i < n; i++) {
            flag = true;
            res = random.nextInt(numCharacters + 1);
            for (int j = 0; j < i; j++) {
                if (temp[j] == res) {
                    flag = false;
                    i--;
                    break;
                }
            }
            if (flag) {
                temp[i] = res;
                char temp1 = 0;
                if (res > 10) {
                    res += 86;
                    temp1 = (char)res;
                } else if (res != 0) {
                    res--;
                }
                if (i == 0) {
                    if (res > 10) {
                        secretCode = String.valueOf(temp1);
                    } else {
                        secretCode = String.valueOf(res);
                    }
                } else {
                    if (res > 10) {
                        secretCode += String.valueOf(temp1);
                    } else {
                        secretCode += String.valueOf(res);
                    }
                }
            }
        }
        return secretCode;
    }

    private static boolean contains(char[] gu, char c) {
        boolean res=false;
        for (char x:gu)
            if(x==c){
                res =true;
                break;}

        return res;
    }

    private static void display(int sizeCode, int numCharacters) {
        for (int i = 0; i < sizeCode; i++) {
            System.out.print("*");
        }
        System.out.print(" (0-9");
        if (numCharacters > 10) {
            if (numCharacters == 11) {
                System.out.print(", a");
            } else {
                System.out.print(String.format(", a-%c", numCharacters + 86));
            }
        }
        System.out.println(")");
    }
}
