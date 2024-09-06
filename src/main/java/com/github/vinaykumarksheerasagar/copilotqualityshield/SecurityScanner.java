package com.github.vinaykumarksheerasagar.copilotqualityshield;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SecurityScanner {
    public List<String> scan(String codeSnippetPath) {
        List<String> issues = new ArrayList<>();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("snyk", "test", codeSnippetPath);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                issues.add(line);
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return issues;
    }
}