package proyecto.practicaentregable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.exit;
import java.util.Scanner;
import proyecto.escrituraendisco.Fichero;

/**
 *
 * Esta clase representa el juego "Adivina el animal". Permite al usuario pensar
 * en un animal y el programa tratará de adivinarlo mediante preguntas que el
 * usuario deberá responder con "Si" o "No". Si no lo adivina, el programa
 * aprenderá una nueva pregunta que diferencie al animal del que estaba pensando
 * y la agregará a su base de conocimientos.
 *
 * @author Sergio Morillas
 */
public class SE {

    private Nodo raiz;
    private String respuesta, pregunta;
    private final Scanner s;

    /**
     * <b>Constructor</b> que permite crearse objetos de tipo SE utilizando un
     * nodo raiz
     *
     * @param raiz Nodo creado en la clase que contiene el main para trabajar
     * sobre el sistema experto
     */
    public SE(Nodo raiz) {
        this.raiz = raiz;
        s = new Scanner(System.in);
    }

    public void juega(Nodo nodo) throws IOException {
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
            System.out.println(Fichero.leerArchivo("./src/main/resources/texto", "guardaArbol.txt"));
            
            guardarInformacion();
            exit(0);
        }
    }

    /**
     * Método que inicia el juego.
     *
     * @param nodo Nodo actual del árbol en el que nos encontramos.
     */
    private void comprobaciones(Nodo nodo) throws IOException {
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
            respuestas[0] = s.nextLine().toLowerCase();
            System.out.println("¿Que pregunta diferenciaría a tu animal del "
                    + nodo.getRespuesta() + "?");
            respuestas[1] = formatearPregunta(s.nextLine());
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

    /**
     * Metodo que formatea las preguntas para que el usuario las pueda
     * introducir como quiera (mayusculas, minusculas...) y sin signos de
     * interrogación
     *
     * @param frase Pregunta que ha señalizado el usuario
     * @return la frase ya formateada
     */
    private String formatearPregunta(String frase) {
        char primero = frase.toUpperCase().charAt(0);
        String resto = frase.substring(1, frase.length()).toLowerCase();
        return "¿" + primero + resto + "?";
    }

    /**
     * Método que recorre el árbol binario y lo guarda en un archivo de texto.
     *
     * @param nodo Nodo a partir del cual se comienza a recorrer el árbol.
     * @param archivo Ruta del archivo de texto en el que se guardará el árbol.
     * @throws IOException Si se produce un error al escribir en el archivo.
     */
    public void guardarArbol(Nodo nodo, String archivo) throws IOException {
        try (PrintWriter escritor = new PrintWriter(new BufferedWriter(new FileWriter(archivo)))) {
            guardarNodo(nodo, escritor);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Método auxiliar que guarda un nodo y sus subnodos en el archivo de texto.
     *
     * @param nodo Nodo a guardar.
     * @param escritor Escritor que se encargará de escribir en el archivo de
     * texto.
     */
    private void guardarNodo(Nodo nodo, PrintWriter escritor) {
        if (nodo == null) {
            return;
        }
        if (nodo.getPregunta() != null) {
            escritor.println("P:" + nodo.getPregunta());
            guardarNodo(nodo.getNodoSi(), escritor);
            guardarNodo(nodo.getNodoNo(), escritor);
        } else {
            escritor.println("R:" + nodo.getRespuesta());
        }
    }

    private void cargarNodo(Nodo nodo, PrintWriter escritor){

    }
    
    /**
     * Metodo que guarda la informacion de los nodos en un fichero txt
     *
     * @throws IOException Si el fichero no existe lo creamos y añadimos la
     * información
     */
    private void guardarInformacion() throws IOException {
        System.out.println("¿Quieres guardar la informacion de la partida?");
        if (s.nextLine().equalsIgnoreCase("si")) {
            try {
                guardarArbol(this.raiz, "./src/main/resources/texto/guardaArbol.txt");
            } catch (Exception e) {
                Fichero.crearArchivo("./src/main/resources/texto", "guardaArbol.txt");
                guardarArbol(this.raiz, "./src/main/resources/texto/guardaArbol.txt");
            }
        }
    }
}
