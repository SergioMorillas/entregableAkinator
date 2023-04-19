package proyecto.practicaentregable;

import java.io.IOException;
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
public class SEAnimales extends SEAbstracto {

    public SEAnimales(Nodo raiz) {
        super(raiz);
        s = new Scanner(System.in);
    }

    /**
     * Método que inicia el juego.
     *
     * @param nodo Nodo actual del árbol en el que nos encontramos.
     * @throws java.io.IOException
     */
    @Override
    public void comprobaciones(Nodo nodo) throws IOException {
        if (getRespuesta().equalsIgnoreCase("si")) { // Si el usuario responde si a la pregunta
            if (nodo.getRespuesta() != null) { // Si hay respuesta directa la mostramos
                System.out.println("Tu animal es " + nodo.getRespuesta());
                System.out.println("¿He acertado? (Si/No)");
                if (s.nextLine().equalsIgnoreCase("no")) {
                    aprender(nodo);
                }
            } else if (nodo.getNodoSi() != null) { // Si el nodoSi no es null jugamos
                juega(nodo.getNodoSi());
            }
        } else if (getRespuesta().equalsIgnoreCase("no")) { // El usuario responde no 
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
            juega(getRaiz());
        }
    }

    @Override
    public void aprender(Nodo nodo) {
        String respuestas[] = new String[3];
        if (nodo.getRespuesta() != null) {
            System.out.println("¿Que animal estabas pensando?");
            respuestas[0] = s.nextLine().toLowerCase();
            System.out.println("¿Que pregunta diferenciaría a tu animal del "
                    + nodo.getRespuesta() + "?");
            respuestas[1] = super.formatearPregunta(s.nextLine());
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

    @Override
    public String informacion() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bienvenido a la informacion del programa, aquí aprenderas a utilizarlo");
        sb.append("Este programa es un sistema experto el cual irá aprendiendo sobre ti hasta saber mas que tu mismo");
        sb.append("En este apartado especifico trataremos sobre animales\n");
        sb.append("Cuando el sistema no sepa la respuesta sobre un tema tendrás que enseñarsela, para ello tendras que seguir unas instrucciones");
        sb.append("1· Te preguntará por tu animal, deberas responder con tu animal en minusculas --> gato");
        sb.append("2· Te preguntará que diferencia a tu animal de uno especifico, deberas responder con una frase corta, de tres palabras maximo, tambien todo en minusculas --> es felino");
        sb.append("3· Te preguntará si tu animal responde afirmativamente o no a esa pregunta, deberas responder solamente si o no\n");
        sb.append("Muchas gracias por jugar y esperemos que te guste");
        return sb.toString();
    }

    /**
     * Metodo que guarda la informacion de los nodos en un fichero txt
     *
     * @throws IOException Si el fichero no existe lo creamos y añadimos la
     * información
     */
    @Override
    public void guardarInformacion() throws IOException {
        System.out.println("¿Quieres guardar la informacion de la partida?");
        if (s.nextLine().equalsIgnoreCase("si")) {
            try {
                guardarArbol(this.getRaiz(), "./src/main/resources/texto/animales.txt");
            } catch (IOException e) {
                Fichero.crearArchivo("./src/main/resources/texto", "animales.txt");
                guardarArbol(this.getRaiz(), "./src/main/resources/texto/animales.txt");
            }
        }
    }
}
