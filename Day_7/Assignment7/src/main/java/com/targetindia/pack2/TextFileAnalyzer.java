package com.targetindia.pack2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class TextFileAnalyzer {

    // Method to find the longest line in the array of strings
    public static String findLongestLine(String[] lines) {
        String longestLine = "";
        for (String line : lines) {
            if (line.length() > longestLine.length()) {
                longestLine = line;
            }
        }
        return longestLine;
    }

    // Method to find the shortest line in the array of strings
    public static String findShortestLine(String[] lines) {
        String shortestLine = lines[0];
        for (String line : lines) {
            if (line.length() < shortestLine.length()) {
                shortestLine = line;
            }
        }
        return shortestLine;
    }

    // Method to count the number of words in each line and store them in an array
    public static int[] countWords(String[] lines) {
        int[] wordCounts = new int[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] words = lines[i].trim().split("\\s+");
            wordCounts[i] = words.length;
        }
        return wordCounts;
    }

    // Method to sort the array of word counts in descending order
    public static void sortWordCount (int[] wordCounts) {
        Arrays.sort(wordCounts);
        for (int i = 0; i < wordCounts.length / 2; i++) {
            int temp = wordCounts[i];
            wordCounts[i] = wordCounts[wordCounts.length - i - 1];
            wordCounts[wordCounts.length - i - 1] = temp;
        }
    }

    // Main method to execute the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the filename: ");
        String filename = scanner.nextLine();

        try {
            File file = new File(filename);
            Scanner fileScanner = new Scanner(file);

            // Read lines from the file and store them in an array
            StringBuilder contentBuilder = new StringBuilder();
            while (fileScanner.hasNextLine()) {
                contentBuilder.append(fileScanner.nextLine());
                contentBuilder.append("\n");
            }
            fileScanner.close();
            String fileContent = contentBuilder.toString();
            String[] lines = fileContent.split("\n");

            // Find longest and shortest lines
            String longestLine = findLongestLine(lines);
            String shortestLine = findShortestLine(lines);

            // Count words in each line
            int[] wordCounts = countWords(lines);

            // Sort word counts in descending order
            sortWordCount(wordCounts);

            // Display results
            System.out.println("Contents of the file:");
            for (int i = 0; i < lines.length; i++) {
                System.out.println("Line " + (i + 1) + ": " + lines[i]);
            }
            System.out.println("Longest line:");
            System.out.println("Line " + (Arrays.asList(lines).indexOf(longestLine) + 1) + ": " + longestLine);
            System.out.println("Shortest line:");
            System.out.println("Line " + (Arrays.asList(lines).indexOf(shortestLine) + 1) + ": " + shortestLine);
            System.out.println("Word count for each line:");
            for (int i = 0; i < lines.length; i++) {
                System.out.println("Line " + (i + 1) + ": " + wordCounts[i] + " words");
            }
            System.out.println("Sorted word count:");
            for (int count : wordCounts) {
                System.out.println(count + " words");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
