package com.company;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private final static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int game;
        System.out.println("Выберите игру:\n" +
                "1. Угадай число\n" +
                "2. Угадай слово");
        try {
            game = scan.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Вы ввели не число.");
            game = 0;
        }
        switch (game){
            case 1:
                System.out.println("Запускается игра \"Угадай число\"...");
                guessTheNumber();
                break;
            case 2:
                System.out.println("Запускается игра \"Угадай слово\"...");
                guessTheWord();
                break;
            default:
                System.out.println("Выбранной игры не существует!");
                break;
        }
        scan.close();

        String brokenString = "Предложение        один   Теперь    предложение   два   Тут  предложение   три";
        fixString(brokenString);
    }


    //task 1
    private static void guessTheNumber(){
        int number = (new Random()).nextInt(10);
        //System.out.println(number);
        int count = 0;
        int answer = -1;
        do{
            System.out.println("Введите число от 0 до 9:");
            try {
                answer = scan.nextInt();
                count++;
                if(checkNumber(number, answer)) {
                    count = 0;
                    break;
                }
            } catch (InputMismatchException e){
                System.out.println("Вы ввели не число, попробуйте еще раз.");
            }
        }while (count != 3);
        if (count == 3){
            System.out.println("Вы проиграли! Попробовать снова?\n" +
                    "1. Да\n" +
                    "2. Нет");
            try {
                answer = scan.nextInt();
                if (answer == 1)
                    guessTheNumber();
            } catch (InputMismatchException e){
                System.out.println("Вы ввели не число.");
            }
        }
    }

    private static boolean checkNumber(int number, int answer){
        if (number < answer)
            System.out.println("Загаданное число меньше");
        else if (number > answer)
            System.out.println("Загаданное число больше");
        else
            System.out.println("Вы выиграли!");
        return number == answer;
    }


    //task 2
    private static void guessTheWord(){
        String[] words = new String[]{"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pear", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        int wordNumber = (new Random()).nextInt(words.length - 1);
        String word = words[wordNumber];
        String answer;
        StringBuilder out = new StringBuilder("###############");
        //System.out.println(word);
        do {
            System.out.println("Введите загаданное слово:");
            answer = scan.next();
        } while (!checkWord(word, answer, out));
    }

    private static boolean checkWord(String word, String answer, StringBuilder out){
        if(word.equalsIgnoreCase(answer)){
            System.out.println("Вы выиграли! Загаданное слово " + word + "!");
            return true;
        } else {
            answer = answer.toLowerCase(Locale.ROOT);
            for (int i = 0; i < word.length() && i < answer.length(); i++) {
                char cWord = word.charAt(i);
                char cAnswer = answer.charAt(i);
                if (cWord == cAnswer && out.charAt(i) == '#'){
                    out.setCharAt(i, cWord);
                }
            }
        }
        System.out.println(out);
        return false;
    }

    //extra task
    private static void fixString(String brokenString){
        String[] words = brokenString.split(" +");
        StringBuilder fixedString = new StringBuilder();
        for (String word : words) {
            fixedString.append(word).append(" ");
        }
        for (int i = 1; i < fixedString.length(); i++) {
            if (fixedString.charAt(i) > 'А' && fixedString.charAt(i) < 'Я'){
                fixedString.insert(i-1, '.');
                i++;
            }
        }
        fixedString.replace(fixedString.length()-1,fixedString.length(),".");
        System.out.println(fixedString);
    }

    //useless comment for pull request
}
