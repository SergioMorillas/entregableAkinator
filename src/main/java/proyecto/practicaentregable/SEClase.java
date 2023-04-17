package proyecto.practicaentregable;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * Esta clase representa el juego "Adivina la persona". Permite al usuario
 * pensar en un animal y el programa tratará de adivinarlo mediante preguntas
 * que el usuario deberá responder con "Si" o "No". Si no lo adivina, el
 * programa aprenderá una nueva pregunta que diferencie al animal del que estaba
 * pensando y la agregará a su base de conocimientos.
 *
 * @author Sergio Morillas
 */
public class SEClase extends SEAbstracto {

    /**
     * <b>Constructor</b> que permite crearse objetos de tipo SE utilizando un
     * nodo raiz
     *
     * @param raiz Nodo creado en la clase que contiene el main para trabajar
     * sobre el sistema experto
     */
    public SEClase(Nodo raiz) {
        super(raiz);
        s = new Scanner(System.in);
    }

    /**
     * Método que inicia el juego.
     *
     * @param nodo Nodo actual del árbol en el que nos encontramos.
     */
    @Override
    public void comprobaciones(Nodo nodo) throws IOException {
        if (respuesta.equalsIgnoreCase("si")) { // Si el usuario responde si a la pregunta
            if (nodo.getRespuesta() != null) { // Si hay respuesta directa la mostramos
                System.out.println("Tu persona es " + nodo.getRespuesta());
                System.out.println("¿He acertado? (Si/No)");
                if (s.nextLine().equalsIgnoreCase("no")) {
                    aprender(nodo);
                }
            } else if (nodo.getNodoSi() != null) { // Si el nodoSi no es null jugamos
                juega(nodo.getNodoSi());
            }
        } else if (respuesta.equalsIgnoreCase("no")) { // El usuario responde no 
            if (nodo.getRespuesta() != null) { // Si hay respuesta directa la mostramos
                System.out.println("Tu persona es " + nodo.getRespuesta());
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

    @Override
    public void aprender(Nodo nodo) {
        String respuestas[] = new String[3];
        if (nodo.getRespuesta() != null) {
            System.out.println("¿Que persona estabas pensando?");
            respuestas[0] = super.formatearPersona(s.nextLine());
            System.out.println("¿Que pregunta diferenciaría a tu persona de "
                    + nodo.getRespuesta() + "?");
            respuestas[1] = formatearPregunta(s.nextLine());
            System.out.println("¿Tu persona responde de manera "
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
            System.out.println("¿Que persona estabas pensando?");
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
        sb.append("En este apartado especifico trataremos sobre compañeros de esta clase\n");
        sb.append("Cuando el sistema no sepa la respuesta sobre un tema tendrás que enseñarsela, para ello tendras que seguir unas instrucciones");
        sb.append("1· Te preguntará por tu compañero, deberas responder con tu compañero en minusculas --> iñigo");
        sb.append("2· Te preguntará que diferencia a tu compañero de uno especifico, "
                + "deberas responder con una frase corta, de tres palabras maximo, tambien todo en minusculas --> es listo");
        sb.append("3· Te preguntará si tu animal responde afirmativamente o no a esa pregunta, deberas responder solamente si o no\n");
        sb.append("Muchas gracias por jugar y esperemos que te guste");
        return sb.toString();
    }

}
