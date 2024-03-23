import java.util.Scanner;

public class ReverseSentenceByWords {
    public static String reverseByWords(String sentence) {
        String[] words = sentence.split("\\s+");
        StringBuilder reversedSentence = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversedSentence.append(words[i]);
            if (i != 0) {
                reversedSentence.append(" ");
            }
        }
        return reversedSentence.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String inputSentence = scanner.nextLine();
        scanner.close();
        
        String reversedSentence = reverseByWords(inputSentence);
        System.out.println("Reversed sentence: " + reversedSentence);
    }
}
