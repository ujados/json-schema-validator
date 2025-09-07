package org.json.json_schema_validator;

import com.networknt.schema.ValidationMessage;
import org.json.json_schema_validator.service.Validator;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Set;

public class JsonValidatorApplication {
    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("JSON Validator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        // Panel para los campos de entrada
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel schemaLabel = new JLabel("Schema Path:");
        JTextField schemaField = new JTextField();
        JLabel fileLabel = new JLabel("File Path:");
        JTextField fileField = new JTextField();
        JCheckBox ignoreDatesCheckBox = new JCheckBox("Ignore Date Errors", true);

        inputPanel.add(schemaLabel);
        inputPanel.add(schemaField);
        inputPanel.add(fileLabel);
        inputPanel.add(fileField);
        inputPanel.add(new JLabel()); // Espacio vacío
        inputPanel.add(ignoreDatesCheckBox);

        // Área de texto para mostrar resultados
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Botón para ejecutar la validación
        JButton validateButton = new JButton("Validate");
        validateButton.addActionListener(e -> {
            String schemaPath = schemaField.getText().trim();
            String filePath = fileField.getText().trim();
            boolean ignoreDates = ignoreDatesCheckBox.isSelected();

            // Imprimir los valores de entrada en la consola
            System.out.println("Schema Path: " + schemaPath);
            System.out.println("File Path: " + filePath);
            System.out.println("Ignore Dates: " + ignoreDates);

            if (schemaPath.isEmpty() || filePath.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in both paths.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Set<ValidationMessage> validationResult = Validator.jsonValidator(schemaPath, filePath, ignoreDates);
                if (validationResult.isEmpty()) {
                    resultArea.setText("Validation successful: No errors found.");
                } else {
                    StringBuilder result = new StringBuilder("Validation failed with the following errors:\n");
                    validationResult.forEach(message -> result.append("- ").append(message.getMessage()).append("\n"));
                    resultArea.setText(result.toString());
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error reading files: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Validation failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Agregar componentes a la ventana
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(validateButton, BorderLayout.SOUTH);

        // Mostrar la ventana
        frame.setVisible(true);
    }
}