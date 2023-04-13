package proyecto.practicaentregable;

import java.io.IOException;

/**
 * @author sergio
 */
public class Practicaentregable {
    public static void main(String[] args) throws IOException {
        Nodo miNodo = new Nodo("¿Es mamifero?", null);
        miNodo.setNodoSi(new Nodo(null,"gato"));
        miNodo.setNodoNo(new Nodo(null, "paloma"));
        SE se = new SE(miNodo);
        se.juega(miNodo);
    }
}
