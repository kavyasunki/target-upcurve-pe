package com.targetindia.pack2;

import java.io.*;
import java.util.*;

public class StudentGradeAnalyzer {

    public static void main(String[] args) {
        List<String[]> grades = readCSV("grades.csv");

        double averageGrade = calculateAverageGrade(grades);
        int highestGrade = calculateHighestGrade(grades);
        int lowestGrade = calculateLowestGrade(grades);
        int numPassed = countPassedStudents(grades);
        int numFailed = grades.size() - numPassed;

        System.out.println("Statistics:");
        System.out.println("- Average grade: " + averageGrade);
        System.out.println("- Highest grade: " + highestGrade);
        System.out.println("- Lowest grade: " + lowestGrade);
        System.out.println("- Number of students passed: " + numPassed);
        System.out.println("- Number of students failed: " + numFailed);
    }

    private static List<String[]> readCSV(String fileName) {
        List<String[]> grades = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                grades.add(parts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return grades;
    }

    private static double calculateAverageGrade(List<String[]> grades) {
        return grades.stream()
                .mapToInt(grade -> Integer.parseInt(grade[1]))
                .average()
                .orElse(0.0);
    }

    private static int calculateHighestGrade(List<String[]> grades) {
        return grades.stream()
                .mapToInt(grade -> Integer.parseInt(grade[1]))
                .max()
                .orElse(0);
    }

    private static int calculateLowestGrade(List<String[]> grades) {
        return grades.stream()
                .mapToInt(grade -> Integer.parseInt(grade[1]))
                .min()
                .orElse(0);
    }

    private static int countPassedStudents(List<String[]> grades) {
        return (int) grades.stream()
                .filter(grade -> Integer.parseInt(grade[1]) >= 60)
                .count();
    }
}
