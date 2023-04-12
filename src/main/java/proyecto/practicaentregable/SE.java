package proyecto.practicaentregable;

import static java.lang.System.exit;
import java.util.Scanner;

/**
 * @author Sergio Morillas
 */
public class SE {

    private Nodo raiz;
    private String respuesta, pregunta;
    private final Scanner s;

    public SE(Nodo raiz) {
        this.raiz = raiz;
        s = new Scanner(System.in);
    }

    public void juega(Nodo nodo) {
        if (nodo.getPregunta() != null) { // Comprobamos si la pregunta no es null
            System.out.println(nodo.getPregunta());
            respuesta = s.nextLine();
            comprobaciones(nodo);
        } else { // Si no hay respuesta tratamos las que nos dan los nodos, si|no
            comprobaciones(nodo);
        }
        System.out.println("¿Quieres continuar jugando? (Si o No)");
        if (s.nextLine().equalsIgnoreCase("si")) {
            this.juega(raiz);
        } else {
            exit(0);
        }
    }

    private void comprobaciones(Nodo nodo) {
        if (respuesta.equalsIgnoreCase("si")) { // Si el usuario responde si a la pregunta
            if (nodo.getRespuesta() != null) { // Si hay respuesta directa la mostramos
                System.out.println("Tu animal es " + nodo.getRespuesta());
                System.out.println("¿He acertado? (Si/No)");
                if (s.nextLine().equalsIgnoreCase("no")) {
                    aprender(nodo);
                }
            } else if (nodo.getNodoSi() != null) { // Si el nodoSi no es null jugamos
                juega(nodo.getNodoSi());
            }
        } else if (respuesta.equalsIgnoreCase("no")) { // El usuario responde no 
            if (nodo.getRespuesta() != null) { // Si hay respuesta directa la mostramos
                System.out.println("Tu animal es " + nodo.getRespuesta());
                System.out.println("¿He acertado? (Si/No)");
                if (s.nextLine().equalsIgnoreCase("no")) {
                    aprender(nodo);
                }
            } else if (nodo.getNodoNo() != null) { // Si el nodoSi no es null jugamos
                juega(nodo.getNodoNo());
            }
        } else {
            System.out.println("Te has equivocado, vamos a empezar otra vez a ver si te aclaras");
            juega(raiz);
        }
    }

    private void aprender(Nodo nodo) {
        String respuestas[] = new String[3];
        if (nodo.getRespuesta() != null) {
            System.out.println("¿Que animal estabas pensando?");
            respuestas[0] = s.nextLine();
            System.out.println("¿Que pregunta diferenciaría a tu animal del "
                    + nodo.getRespuesta() + "?");
            respuestas[1] = s.nextLine();
            System.out.println("¿Tu animal responde de manera "
                    + "afirmativa a la pregunta? (Si/No)");
            respuestas[2] = s.nextLine();           
            if (respuestas[2].equalsIgnoreCase("no")) {
                nodo.setNodoSi(new Nodo(null, nodo.getRespuesta()));
                nodo.setNodoNo(new Nodo(null, respuestas[0]));
            } else {
                nodo.setNodoSi(new Nodo(null, respuestas[0]));
                nodo.setNodoNo(new Nodo(null, nodo.getRespuesta()));
            }
            nodo.setRespuesta(null);
            nodo.setPregunta(respuestas[1]);
        } else {
            System.out.println("¿Que animal estabas pensando?");
            respuestas[0] = s.nextLine();
            System.out.println("Vale, volveremos a intentarlo");
            nodo.setNodoSi(new Nodo(null, respuestas[0]));
        }
    }
}