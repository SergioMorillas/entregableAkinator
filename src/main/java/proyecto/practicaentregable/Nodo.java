package proyecto.practicaentregable;

/**
 * Clase Nodo que representa un nodo en un árbol de decisiones.
 * 
 * @author Sergio Morillas Carmona
 */
public class Nodo {
 
    private String pregunta, respuesta;
    private Nodo nodoSi, nodoNo;

    /**
     * Constructor de la clase Nodo que recibe una pregunta y una respuesta.
     * 
     * @param pregunta  La pregunta asociada al nodo.
     * @param respuesta La respuesta asociada al nodo.
     */
    public Nodo(String pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    /**
     * Constructor de la clase Nodo que recibe dos nodos hijos.
     * 
     * @param nodoSi  El nodo hijo para la respuesta "Sí".
     * @param nodoNo  El nodo hijo para la respuesta "No".
     */
    public Nodo(Nodo nodoSi, Nodo nodoNo) {
        this.nodoSi = nodoSi;
        this.nodoNo = nodoNo;
        this.respuesta = "";
    }

    /**
     * Método para obtener la pregunta del nodo.
     * 
     * @return La pregunta del nodo.
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * Método para establecer la pregunta del nodo.
     * 
     * @param pregunta La pregunta a establecer.
     */
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * Método para obtener la respuesta del nodo.
     * 
     * @return La respuesta del nodo.
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Método para establecer la respuesta del nodo.
     * 
     * @param respuesta La respuesta a establecer.
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Método para obtener el nodo hijo para la respuesta "Sí".
     * 
     * @return El nodo hijo para la respuesta "Sí".
     */
    public Nodo getNodoSi() {
        return nodoSi;
    }

    /**
     * Método para establecer el nodo hijo para la respuesta "Sí".
     * 
     * @param nodoSi El nodo hijo a establecer para la respuesta "Sí".
     */
    public void setNodoSi(Nodo nodoSi) {
        this.nodoSi = nodoSi;
    }

    /**
     * Método para obtener el nodo hijo para la respuesta "No".
     * 
     * @return El nodo hijo para la respuesta "No".
     */
    public Nodo getNodoNo() {
        return nodoNo;
    }

    /**
     * Método para establecer el nodo hijo para la respuesta "No".
     * 
     * @param nodoNo El nodo hijo a establecer para la respuesta "No".
     */
    public void setNodoNo(Nodo nodoNo) {
        this.nodoNo = nodoNo;
    }
}
