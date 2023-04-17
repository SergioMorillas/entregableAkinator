package proyecto.practicaentregable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.exit;
import java.util.Scanner;
import proyecto.escrituraendisco.Fichero;

/**
 * @author Sergio Morillas
 */
/*

 */
public abstract class SEAbstracto {

    protected Nodo raiz;
    protected String respuesta, pregunta;
    protected Scanner s;

    /**
     * <b>Constructor</b> que permite crearse objetos de tipo SE utilizando un
     * nodo raiz
     *
     * @param raiz Nodo creado en la clase que contiene el main para trabajar
     * sobre el sistema experto
     */
    public SEAbstracto(Nodo raiz) {
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
     *
     *
     * /**
     *
     * @param nodo
     * @throws IOException
     */
    public abstract void comprobaciones(Nodo nodo) throws IOException;

    public abstract void aprender(Nodo nodo);

    /**
     * Metodo que formatea las preguntas para que el usuario las pueda
     * introducir como quiera (mayusculas, minusculas...) y sin signos de
     * interrogación
     *
     * @param frase Pregunta que ha señalizado el usuario
     * @return la frase ya formateada
     */
    public String formatearPregunta(String frase) {
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
    public void guardarNodo(Nodo nodo, PrintWriter escritor) {
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

    /**
     * Metodo que guarda la informacion de los nodos en un fichero txt
     *
     * @throws IOException Si el fichero no existe lo creamos y añadimos la
     * información
     */
    public void guardarInformacion() throws IOException {
        System.out.println("¿Quieres guardar la informacion de la partida?");
        if (s.nextLine().equalsIgnoreCase("si")) {
            try {
                guardarArbol(this.raiz, "./src/main/resources/texto/guardaArbol.txt");
            } catch (IOException e) {
                Fichero.crearArchivo("./src/main/resources/texto", "guardaArbol.txt");
                guardarArbol(this.raiz, "./src/main/resources/texto/guardaArbol.txt");
            }
        }
    }

}
