package proyecto.practicaentregable;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Sergio Morillas
 */
/*

 */
public class SEFamoso extends SEAbstracto {

    public SEFamoso(Nodo raiz) {
        super(raiz);
        s = new Scanner(System.in);
    }

    @Override
    public void comprobaciones(Nodo nodo) throws IOException {
        if (respuesta.equalsIgnoreCase("si")) { // Si el usuario responde si a la pregunta
            if (nodo.getRespuesta() != null) { // Si hay respuesta directa la mostramos
                System.out.println("Tu famoso es " + nodo.getRespuesta());
                System.out.println("¿He acertado? (Si/No)");
                if (s.nextLine().equalsIgnoreCase("no")) {
                    aprender(nodo);
                }
            } else if (nodo.getNodoSi() != null) { // Si el nodoSi no es null jugamos
                juega(nodo.getNodoSi());
            }
        } else if (respuesta.equalsIgnoreCase("no")) { // El usuario responde no 
            if (nodo.getRespuesta() != null) { // Si hay respuesta directa la mostramos
                System.out.println("Tu famoso es " + nodo.getRespuesta());
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
            System.out.println("¿Que famoso estabas pensando?");
            respuestas[0] = super.formatearPersona(s.nextLine());
            System.out.println("¿Que pregunta diferenciaría a tu famoso de "
                    + nodo.getRespuesta() + "?");
            respuestas[1] = formatearPregunta(s.nextLine());
            System.out.println("¿Tu famoso responde de manera "
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
            System.out.println("¿Que famoso estabas pensando?");
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
        sb.append("En este apartado especifico trataremos sobre famosos\n");
        sb.append("Cuando el sistema no sepa la respuesta sobre un tema tendrás que enseñarsela, para ello tendras que seguir unas instrucciones");
        sb.append("1· Te preguntará por tu famoso, deberas responder con tu famoso en minusculas (Nombre o nombre y apellido) --> messi");
        sb.append("2· Te preguntará que diferencia a tu famoso de uno especifico, deberas responder con una frase corta, de tres palabras maximo, tambien todo en minusculas --> es futbolista");
        sb.append("3· Te preguntará si tu famoso responde afirmativamente o no a esa pregunta, deberas responder solamente si o no\n");
        sb.append("Muchas gracias por jugar y esperemos que te guste");
        return sb.toString();
    }
}
