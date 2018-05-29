import java.io.File;
import java.util.*;

public class Levenshtein {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("dictionary.txt"));
        List<String> dictionary = new ArrayList<String>();
        while(sc.hasNext()) {
            dictionary.add(sc.nextLine());
        }

        while (true) {
            System.out.println("Enter a Misspelled Word to get Suggestions");
            Scanner input = new Scanner(System.in);
            String misspelled = input.nextLine().toLowerCase();

            List<String> suggestions = new ArrayList<String>();
            for (String word:dictionary) {
                if (number_Edits(word, misspelled)<2) {
                    suggestions.add(word);
                }
            }

            for (String suggestion: suggestions) {
                System.out.println(suggestion);
            }
        }
    }

    public static int number_Edits(String A, String B) {
        int[][] arr = new int[A.length()][B.length()];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = -1;
            }
        }

        int ans = calculate_Edits(A, A.length()-1, B, B.length()-1, arr);

        /*
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
        */

        return ans;
    }

    private static int calculate_Edits(String A, int index_A, String B, int index_B, int[][] arr) {

        if (index_A<0) {
            return index_B + 1;
        }
        if (index_B<0) {
            return index_A + 1;
        }

        //no need to explore paths already explored
        if (arr[index_A][index_B]==-1) {
            if (A.charAt(index_A)==B.charAt(index_B)) {
                arr[index_A][index_B] = calculate_Edits(A, index_A-1, B, index_B-1, arr);
            }
            else {
                int add = calculate_Edits(A, index_A, B, index_B-1, arr);
                int subtract = calculate_Edits(A, index_A-1, B, index_B, arr);
                int substitute = calculate_Edits(A, index_A-1, B, index_B-1, arr);

                arr[index_A][index_B] = 1 + minThree(add, subtract, substitute);
            }

        }
        return arr[index_A][index_B];
    }

    private static int minThree(int x, int y, int z) {
        int a = x;
        if (a > y) {
            a = y;
        }
        if (a > z) {
            a = z;
        }
        return a;
    }
}
