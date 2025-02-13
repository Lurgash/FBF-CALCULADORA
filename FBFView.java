package FBF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FBFView extends JFrame {
    private JTextField inputField; // Campo de texto para ingresar la fórmula
    private JTextArea resultArea;  // Área de texto para mostrar resultados
    private JButton verifyButton;  // Botón para verificar la fórmula
    private JButton[] symbolButtons; // Botones para insertar símbolos

    // Constructor para inicializar la vista
    public FBFView() {
        initComponents();
    }

    // Método para inicializar los componentes de la interfaz gráfica
    private void initComponents() {
        setTitle("Verificador de Fórmulas Lógicas"); // Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
        setSize(1280, 720); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Panel principal con un diseño de borde
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel para el campo de entrada y los botones de símbolos
        JPanel inputPanel = new JPanel(new BorderLayout(0, 5));
        JLabel inputLabel = new JLabel("Ingrese la fórmula:");
        inputField = new JTextField();

        // Panel para los botones de símbolos
        JPanel symbolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        String[] symbols = {"(", ")", "¬", "∧", "∨", "→", "↔"};
        symbolButtons = new JButton[symbols.length];
        for (int i = 0; i < symbols.length; i++) {
            symbolButtons[i] = new JButton(symbols[i]); // Crear botones para cada símbolo
            symbolButtons[i].setPreferredSize(new Dimension(45, 30)); // Tamaño de los botones
            symbolPanel.add(symbolButtons[i]); // Agregar botones al panel
        }

        // Agregar componentes al panel de entrada
        inputPanel.add(symbolPanel, BorderLayout.NORTH);
        inputPanel.add(inputLabel, BorderLayout.CENTER);
        inputPanel.add(inputField, BorderLayout.SOUTH);

        // Panel central con el botón "Verificar"
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        verifyButton = new JButton("Verificar");
        verifyButton.setPreferredSize(new Dimension(120, 35)); // Tamaño del botón
        centerPanel.add(verifyButton);

        // Panel para mostrar resultados
        JPanel resultPanel = new JPanel(new BorderLayout(0, 5));
        JLabel resultLabel = new JLabel("Resultado:");
        resultArea = new JTextArea();
        resultArea.setEditable(false); // El área de resultados no es editable
        resultArea.setLineWrap(true); // Ajustar texto al ancho del área
        resultArea.setWrapStyleWord(true); // Ajustar palabras completas
        JScrollPane scrollPane = new JScrollPane(resultArea); // Agregar barra de desplazamiento
        scrollPane.setPreferredSize(new Dimension(580, 200)); // Tamaño del área de resultados

        // Agregar componentes al panel de resultados
        resultPanel.add(resultLabel, BorderLayout.NORTH);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        // Ensamblar la interfaz gráfica
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(resultPanel, BorderLayout.SOUTH);

        add(mainPanel); // Agregar el panel principal a la ventana

        // Agregar márgenes a los componentes
        inputPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
    }

    // Métodos para configurar listeners y actualizar la vista
    public void setVerifyButtonListener(ActionListener listener) {
        verifyButton.addActionListener(listener);
    }

    public void setSymbolButtonListeners(ActionListener listener) {
        for (JButton button : symbolButtons) {
            button.addActionListener(listener);
        }
    }

    public String getInputText() {
        return inputField.getText().trim();
    }

    public void appendInputText(String text) {
        inputField.setText(inputField.getText() + text);
    }

    public void setResultText(String text) {
        resultArea.setText(text);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}