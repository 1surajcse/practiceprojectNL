package com.mycompany.app;

import com.mycompany.app.constants.MatrixInputs;

import java.io.*;
import java.util.*;

import static com.mycompany.app.constants.MatrixInputs.*;

/**
 * The idea of the application consists in matrices of 0's and 1's representing letters.
 * Make your own algorithm to guess the correct letters or return X when there is no answer.
 * 
 * We'll value not only the algorithm, also code simplicity, structure, comments, etc.
 * 
 * Test cases are welcome if you have some spare time.
 */
public class App{

    private final String message = "There we go...";

    public App() {}

    public static void main(String[] args) throws IOException {



        App app = new App();

        System.out.println(app.getMessage());
        System.out.println(app.letters);

        //Continue here or with other classes...
        //Using third-party libraries is allowed

        int input[][]=INPUT_X;
        String result=patternMatchingImplemenation(app,input);
        System.out.println("Expected Output :"+result);

    }

    private static String patternMatchingImplemenation(App app,int[][]input) throws IOException {
        List<String>list1=new ArrayList<>();
        List<String>list2=new ArrayList<>();
if(input[0].length<10||input.length<10){
    return "X";
}

        for (int i = 0; i < input.length; i++) {

            for (int j = 0; j < input.length; j++) {
                list2.add(String.valueOf ((input[i][j])));
            }
            System.out.println();

        }
        for (String key: app.letters.keySet()) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(app.letters.get(key)));
            int k;

            while ((k = bufferedReader.read()) != -1) {

                if (Character.isDigit((char) k)) {
                    list1.add(String.valueOf((char) k).trim());
                }


            }

            if (list1.equals(list2)) {
                System.out.println("The Given Matrix contains the pattern for :"+key);
                return key;

            } else {
               list1.clear();
            }
        }

        System.out.println("The Given Matrix does not contains any pattern hence returning  :X");
   return "X";
    }

    private final HashMap<String, File> letters = loadLetters();

    private final HashMap<String, File> loadLetters() {
        File CFile = new File("C.txt");
        File IFile = new File("I.txt");
        File LFile = new File("L.txt");
        File OFile = new File("O.txt");
        File TFile = new File("T.txt");
        File XFile = new File("X.txt");
        
        assert CFile.exists();
        assert IFile.exists();
        assert LFile.exists();
        assert OFile.exists();
        assert TFile.exists();
        assert XFile.exists();

        HashMap<String, File> map = new HashMap<String, File>();
        map.put("C", CFile);
        map.put("I", IFile);
        map.put("L", LFile);
        map.put("O", OFile);
        map.put("T", TFile);
        map.put("X", XFile);
        return map;
    }

    private final String getMessage() {
        return message;
    }

    public static boolean isRectangle(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return false;
        int columns = matrix[0].length;

        Map<Integer, Set<Integer>> table = new HashMap<>();

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns - 1; ++j) {
                for (int k = j + 1; k < columns; ++k) {
                    if (matrix[i][j] == 1 && matrix[i][k] == 1) {
                        if (table.containsKey(j) && table.get(j).contains(k))
                            return true;
                        table.computeIfAbsent(j, key -> new HashSet<>()).add(k);
                    }
                }
            }
        }
        return false;
    }

}