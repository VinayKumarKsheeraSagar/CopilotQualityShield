package com.github.vinaykumarksheerasagar.copilotqualityshield;

import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DocumentListener implements EditorFactoryListener {
    @Override
    public void editorCreated(@NotNull EditorFactoryEvent event) {
        event.getEditor().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void documentChanged(@NotNull DocumentEvent event) {
                Project project = event.getDocument().getProject();
                if (project != null) {
                    ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow("Code Quality");
                    if (toolWindow != null) {
                        CodeQualityPanel panel = (CodeQualityPanel) toolWindow.getContentManager().getContent(0).getComponent();
                        String codeSnippet = event.getDocument().getText();
                        CodeQualityValidator validator = new CodeQualityValidator();
                        SecurityScanner scanner = new SecurityScanner();
                        List<String> qualityIssues = validator.validate(codeSnippet);
                        List<String> securityIssues = scanner.scan(codeSnippet);
                        List<String> allIssues = new ArrayList<>(qualityIssues);
                        allIssues.addAll(securityIssues);
                        panel.displayIssues(allIssues);
                    }
                }
            }
        });
    }
}
