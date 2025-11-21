
/** Functions for checking if a given string is an anagram. */
import java.util.Random;

public class Anagram {
    public static void main(String args[]) {
        // Tests the isAnagram function.
        System.out.println(isAnagram("silent","listen"));  // true
        System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
        System.out.println(isAnagram("Madam Curie","Radium came")); // true
        System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

        // Tests the preProcess function.
        System.out.println(preProcess("What? No way!!!"));

        // Tests the randomAnagram function.
        System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");

        // Performs a stress test of randomAnagram 
        String str = "1234567";
        Boolean pass = true;
        for (int i = 0; i < 10; i++) {
            String randomAnagram = randomAnagram(str);
            System.out.println(randomAnagram);
            pass = pass && isAnagram(str, randomAnagram);
            if (!pass) break;
        }
        System.out.println(pass ? "test passed" : "test Failed");
    }  

    // Returns true if the two given strings are anagrams, false otherwise.
    public static boolean isAnagram(String str1, String str2) {
        String s1 = preProcess(str1);
        String s2 = preProcess(str2);
        if (s1.length() != s2.length()) return false;

        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            int index = s2.indexOf(c);
            if (index == -1) return false;
            s2 = s2.substring(0, index) + s2.substring(index + 1);
        }
        return s2.isEmpty();
    }

    // Returns a preprocessed version of the given string
    public static String preProcess(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetter(c)) {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    } 

    // Returns a random anagram of the given string
    public static String randomAnagram(String str) {
        StringBuilder sb = new StringBuilder(str);
        StringBuilder result = new StringBuilder();
        Random rand = new Random();

        while (sb.length() > 0) {
            int index = rand.nextInt(sb.length());
            result.append(sb.charAt(index));
            sb.deleteCharAt(index);
        }
        return result.toString();
    }
}
