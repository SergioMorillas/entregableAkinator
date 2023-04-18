package proyecto.practicaentregable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.exit;
import java.util.Scanner;

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

    public abstract void comprobaciones(Nodo nodo) throws IOException;

    public abstract void aprender(Nodo nodo);

    public abstract String informacion();

    public abstract void guardarInformacion() throws IOException;

    public String formatearPersona(String persona) {
        char primero = persona.toUpperCase().charAt(0);
        String resto = persona.substring(1, persona.length()).toLowerCase();
        return primero + resto;
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
            guardarInformacion();
            exit(0);
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
        try ( PrintWriter escritor = new PrintWriter(new BufferedWriter(new FileWriter(archivo)))) {
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
    
    public void limpiaPantalla() {
        try {
            new ProcessBuilder("cmd", "/c",
        "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
        }
    }

    public static void cargarNodo(Nodo nodo, BufferedReader br) throws IOException {
        String linea = br.readLine();
        String tipo = linea.substring(0, 1);
        String texto = linea.substring(2).trim();

        if (tipo.equals("P")) {
            nodo.setPregunta(texto);
            System.out.println(tipo);
            System.out.println(texto);
            nodo.setNodoSi(new Nodo("", null));
            nodo.setNodoNo(new Nodo("", null));
            cargarNodo(nodo.getNodoSi(), br);
            cargarNodo(nodo.getNodoNo(), br);
        } else if (tipo.equals("R")) {
            System.out.println(tipo);
            nodo.setRespuesta(texto);
            System.out.println(texto);
        } else {
            System.err.println("El archivo estaba mal formulado");
        }
    }
}
