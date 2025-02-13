package FBF;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FBFModel {

    // Método para verificar si una cadena es una Fórmula Bien Formada (FBF)
    public boolean esFBF(String cadena) {
        // Paso 1: Sustituir todas las variables proposicionales (p-z) por "0"
        cadena = cadena.replaceAll("[p-z]", "0");

        // Patrones para identificar negaciones y conectivos binarios
        Pattern negacion = Pattern.compile("¬0");
        Pattern binario = Pattern.compile("\\(0\\)|\\(0[∧∨→↔]0\\)");

        boolean cambio;
        do {
            cambio = false;

            // Reemplazar todas las negaciones (¬0) por "0"
            Matcher mNegacion = negacion.matcher(cadena);
            String nuevaCadena = mNegacion.replaceAll("0");
            if (!nuevaCadena.equals(cadena)) {
                cambio = true;
                cadena = nuevaCadena;
            }

            // Reemplazar todas las operaciones binarias (por ejemplo, (0∧0)) por "0"
            Matcher mBinario = binario.matcher(cadena);
            nuevaCadena = mBinario.replaceAll("0");
            if (!nuevaCadena.equals(cadena)) {
                cambio = true;
                cadena = nuevaCadena;
            }

        } while (cambio); // Repetir hasta que no haya más cambios

        // Si la cadena final es "0", entonces es una FBF
        return cadena.equals("0");
    }

    // Método para encontrar el conectivo principal de una fórmula
    public char encontrarConectivoPrincipal(String cadena) {
        // Si la fórmula comienza con negación, ese es el conectivo principal
        if (cadena.startsWith("¬")) {
            return '¬';
        }

        // Arreglo para almacenar los valores de los paréntesis
        int[] valores = new int[cadena.length()];
        int contador = 0;

        // Asignar valores a los paréntesis
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (c == '(') {
                contador++;
                valores[i] = contador; // Incrementar el contador para paréntesis izquierdo
            } else if (c == ')') {
                valores[i] = contador;
                contador--; // Decrementar el contador para paréntesis derecho
            } else {
                valores[i] = contador; // Asignar el valor actual a otros caracteres
            }
        }

        // Buscar el conectivo principal en el nivel más externo (valor 1)
        for (int i = 0; i < cadena.length(); i++) {
            if (valores[i] == 1) { // Solo nos interesan los conectivos en el nivel más externo
                char c = cadena.charAt(i);
                if (esConectivoBinario(c)) { // Verificar si es un conectivo binario
                    return c; // Devolver el conectivo principal
                }
            }
        }

        return '\0'; // Si no se encuentra ningún conectivo principal
    }

    // Método para verificar si un carácter es un conectivo binario
    private boolean esConectivoBinario(char c) {
        return c == '∧' || c == '∨' || c == '→' || c == '↔';
    }
}