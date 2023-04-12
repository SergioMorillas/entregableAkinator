package proyecto.practicaentregable;

import java.util.Scanner;

/**
 * @author Sergio Morillas
 */
public class SE {

    private Nodo raiz;
    private String respuesta, pregunta;
    private Scanner s;

    public SE(Nodo raiz) {
        this.raiz = raiz;
        s = new Scanner(System.in);
    }

    public void juega(Nodo nodo) {
        if (nodo.getPregunta() != null) { // Comprobamos si la pregunta no es null
            System.out.println(nodo.getPregunta());
            respuesta = s.nextLine();
            comprobaciones(respuesta, nodo);
        } else { // Si no hay respuesta tratamos las que nos dan los nodos, si|no
            comprobaciones(respuesta, nodo);
        }
        System.out.println("¿Quieres volver a jugar?");
        if (s.nextLine().equalsIgnoreCase("si")) {
            this.juega(raiz);
        }
    }

    private void comprobaciones(String respuesta, Nodo nodo) {
        if (respuesta.equalsIgnoreCase("si")) { // Si el usuario responde si a la pregunte
            if (nodo.getRespuesta() != null) { // Si hay respuesta directa la mostramos
                System.out.println("Tu animal es " + nodo.getRespuesta());
            } else if (nodo.getNodoSi() != null) { // Si el nodoSi no es null jugamos
                juega(nodo.getNodoSi());
            } else {
                // Es un animal desconocido, hay que aprender
            }
        } else if (respuesta.equalsIgnoreCase("no")) { // El usuario responde no 
            if (nodo.getRespuesta() != null) { // Si hay respuesta directa la mostramos
                System.out.println("Tu animal es " + nodo.getRespuesta());
            } else if (nodo.getNodoNo() != null) { // Si el nodoSi no es null jugamos
                juega(nodo.getNodoNo());
            } else {
                // Es un animal desconocido, hay que aprender
            }
        } else {
            System.out.println("Te has equivocado, vamos a empezar otra vez a ver si te aclaras");
            juega(raiz);
        }
    }

}
