package proyecto.practicaentregable;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author sergio
 */
public class Practicaentregable {

    public static void main(String[] args) throws IOException {
        int digitoControl;

        digitoControl = menuTema();
        switch (digitoControl) {
            case 1 -> {
                Nodo miNodo = SEAnimales.cargarArbol(new FileReader("./src/main/resources/texto/animales.txt"));
                SEAbstracto se = new SEAnimales(miNodo);
                se.limpiaPantalla();
                System.out.println(se.informacion());
                se.juega(miNodo);
            }
            case 2 -> {
                Nodo miNodo = new Nodo("¿Es real?", null);
                miNodo.setNodoSi(new Nodo(null, "Alex Corretja"));
                miNodo.setNodoNo(new Nodo(null, "Don Quijote"));
                SEAbstracto se = new SEFamoso(miNodo);
                se.limpiaPantalla();
                System.out.println(se.informacion());
                se.juega(miNodo);
            }
            case 3 -> {
                Nodo miNodo = new Nodo("¿Pierde combates de boxeo?", null);
                miNodo.setNodoSi(new Nodo(null, "Angel"));
                miNodo.setNodoNo(new Nodo(null, "Junchaya"));
                SEAbstracto se = new SEClase(miNodo);
                se.limpiaPantalla();
                System.out.println(se.informacion());
                se.juega(miNodo);
            }
        }
    }

    public static int menuTema() {
        Scanner s = new Scanner(System.in);
        int valor = 0;
        do {
            System.out.println("\t\t*****************");
            System.out.println("\t\t* Elige el modo *");
            System.out.println("\t\t*****************\n");
            System.out.print("╔═══════════════════════════════════════╗\n");
            System.out.println("║\t1· Adivina el animal\t\t║");
            System.out.println("║\t2· Adivina el famoso\t\t║");
            System.out.println("║\t3· Adivina el compañero\t\t║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.print("\nIntroduce el numero: ");
            try {
                valor = s.nextInt();
            } catch (Exception e) {
                valor = -1;
                s.next(); //Para limpiar el buffer del scanner y que no se quede en un bucle infinito
            }
        } while (valor < 1 || valor > 3);
        return valor;
    }
}
