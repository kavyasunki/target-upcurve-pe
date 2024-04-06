package com.targetindia.pack1;
public class WordReversal {

    public static String reverseWords(String input) {
        String[] words = input.trim().split("\\s+");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--)
        {
            reversed.append(words[i]);
            if (i > 0) {
                reversed.append(" ");
            }
        }
        return reversed.toString();
    }

    public static void main(String[] args) {
        reverseWords("Hello! World");
    }
}
