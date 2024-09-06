package com.github.vinaykumarksheerasagar.copilotqualityshield;

import org.sonarsource.sonarlint.core.client.api.common.analysis.Issue;
import org.sonarsource.sonarlint.core.client.api.common.analysis.AnalysisResults;
import org.sonarsource.sonarlint.core.client.api.common.analysis.AnalysisParameters;
import org.sonarsource.sonarlint.core.client.api.standalone.StandaloneSonarLintClient;
import java.util.List;
import java.util.stream.Collectors;

public class CodeQualityValidator {
    private final StandaloneSonarLintClient client;

    public CodeQualityValidator() {
        client = new StandaloneSonarLintClient();
    }

    public List<String> validate(String codeSnippet) {
        AnalysisResults results = client.analyze(codeSnippet, new AnalysisParameters());
        return results.getIssues().stream()
                .map(Issue::getMessage)
                .collect(Collectors.toList());
    }
}