package proyecto.practicaentregable;

/**
 *
 * @author sergio
 */
public class Nodo {

    private String pregunta, respuesta;
    private Nodo nodoSi, nodoNo;

    public Nodo(String pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public Nodo(Nodo nodoSi, Nodo nodoNo) {
        this.nodoSi = nodoSi;
        this.nodoNo = nodoNo;
        this.respuesta = "";
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Nodo getNodoSi() {
        return nodoSi;
    }

    public void setNodoSi(Nodo nodoSi) {
        this.nodoSi = nodoSi;
    }

    public Nodo getNodoNo() {
        return nodoNo;
    }

    public void setNodoNo(Nodo nodoNo) {
        this.nodoNo = nodoNo;
    }
}
