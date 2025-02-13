package FBF;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class FBFController {
    private FBFModel model; // Modelo para la lógica de la aplicación
    private FBFView view;   // Vista para la interfaz gráfica

    // Constructor que inicializa el controlador
    public FBFController(FBFModel model, FBFView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    // Método para inicializar los listeners de los botones
    private void initController() {
        view.setVerifyButtonListener(new VerifyButtonListener()); // Listener para el botón "Verificar"
        view.setSymbolButtonListeners(new SymbolButtonListener()); // Listener para los botones de símbolos
    }

    // Clase interna para manejar el evento del botón "Verificar"
    class VerifyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String entrada = view.getInputText(); // Obtener la fórmula ingresada por el usuario
            if (entrada.isEmpty()) {
                view.showErrorMessage("Ingrese una fórmula"); // Mostrar error si no hay entrada
                return;
            }

            // Verificar si la fórmula es válida y encontrar el conectivo principal
            boolean esValida = model.esFBF(entrada);
            char conectivoPrincipal = model.encontrarConectivoPrincipal(entrada);
            String resultado = "Cadena: " + entrada + "\nEs FBF: " + (esValida ? "Sí" : "No") +
                    "\nConectivo Principal: " + (conectivoPrincipal != '\0' ? conectivoPrincipal : "No encontrado");

            view.setResultText(resultado); // Mostrar el resultado en la vista
        }
    }

    // Clase interna para manejar los eventos de los botones de símbolos
    class SymbolButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource(); // Obtener el botón presionado
            view.appendInputText(boton.getText()); // Agregar el símbolo al campo de texto
        }
    }
}