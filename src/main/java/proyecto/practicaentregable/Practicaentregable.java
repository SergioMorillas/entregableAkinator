package proyecto.practicaentregable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author sergio
 */
public class Practicaentregable {

    public static void main(String[] args) throws IOException {
        System.out.println("""
                __________                                 
              .'----------`.                              
              | .--------. |                             
              | |########| |       __________              
              | |########| |      /__________\\             
     .--------| `--------' |------|    --=-- |-------------.
     |        `----,-.-----'      |o ======  |             | 
     |       ______|_|_______     |__________|             | 
     |      /  %%%%%%%%%%%%  \\                             | 
     |     /  %%%%%%%%%%%%%%  \\                            | 
     |     ^^^^^^^^^^^^^^^^^^^^                            | 
     +-----------------------------------------------------+
     ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^""");
        empezar(menuTema());

    }

    public static void empezar(int i) throws FileNotFoundException, IOException {
        Nodo miNodo;
        switch (i) {
            case 1 -> {
                juegaAnimales();
            }
            case 2 -> {
                juegaFamosos();
            }
            default -> {
                juegaClase();
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

    public static boolean menuArchivo() {
        Scanner s = new Scanner(System.in);
        int valor = 0;
        do {
            System.out.println("\t*******************************************************");
            System.out.println("\t* ¿Quieres jugar con un archivo guardado previamente? *");
            System.out.println("\t*******************************************************\n");
            System.out.print("╔═══════════════════════════════════════╗\n");
            System.out.println("║\t1· Si\t\t\t\t║");
            System.out.println("║\t2· No\t\t\t\t║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.print("\nIntroduce el numero: ");
            try {
                valor = s.nextInt();
            } catch (Exception e) {
                valor = -1;
                s.next(); //Para limpiar el buffer del scanner y que no se quede en un bucle infinito
            }
        } while (valor < 1 || valor > 2);
        return valor == 1;
    }

    private static void juegaAnimales() throws FileNotFoundException, IOException {
        Nodo miNodo;
        SEAnimales se;
        if (menuArchivo()) { //Si quiere cargar entramos aqui, sino else
            miNodo = SEAbstracto.cargarArbol(new FileReader("./src/main/resources/texto/animales.txt"));
            se = new SEAnimales(miNodo);
        } else {
            miNodo = new Nodo("¿Es mamifero?", null);
            miNodo.setNodoSi(new Nodo(null, "gato"));
            miNodo.setNodoNo(new Nodo(null, "salmon"));
            se = new SEAnimales(miNodo);
        }
        System.out.println("\t      c~~p ,---------. \n"
                + "\t ,---'oo  )           \\\n"
                + "\t( O O                  )/\n"
                + "\t `=^='                 /\n"
                + "\t       \\    ,     .   /\n"
                + "\t       \\\\  |-----'|  /\n"
                + "\t       ||__|    |_|__|");
        se.juega(miNodo);
    }

    private static void juegaFamosos() throws FileNotFoundException, IOException {
        Nodo miNodo;
        SEFamoso se;
        if (menuArchivo()) {
            miNodo = SEAbstracto.cargarArbol(new FileReader("./src/main/resources/texto/famoso.txt"));
            se = new SEFamoso(miNodo);
        } else {
            miNodo = new Nodo("¿Es real?", null);
            miNodo.setNodoSi(new Nodo(null, "Alex Corretja"));
            miNodo.setNodoNo(new Nodo(null, "Grogu viejo"));
            se = new SEFamoso(miNodo);
        }

        System.out.println("\t                    ____\n"
                + "\t                 _.' :  `._\n"
                + "\t             .-.'`.  ;   .'`.-.\n"
                + "\t    __      / : ___\\ ;  /___ ; \\      __\n"
                + "\t  ,'_ \"\"--.:__;\".-.\";: :\".-.\":__;.--\"\" _`,\n"
                + "\t  :' `.t\"\"--.. '<@.`;_  ',@>` ..--\"\"j.' `;\n"
                + "\t       `:-.._J '-.-'L__ `-- ' L_..-;'\n"
                + "\t         \"-.__ ;  .-\"  \"-.  : __.-\"\n"
                + "\t             L ' /.------.\\ ' J\n"
                + "\t              \"-.   \"--\"   .-\"\n"
                + "\t             __.l\"-:_JL_;-\";.__\n"
                + "\t          .-j/'.;  ;\"\"\"\"  / .'\\\"-.\n"
                + "\t        .' /:`. \"-.:     .-\" .';  `.\n"
                + "\t     .-\"  / ;  \"-. \"-..-\" .-\"  :    \"-.");
        se.juega(miNodo);
    }

    private static void juegaClase() throws FileNotFoundException, IOException {
        Nodo miNodo;
        SEClase se;
        if (menuArchivo()) {
            miNodo = SEAbstracto.cargarArbol(new FileReader("./src/main/resources/texto/clase.txt"));
            se = new SEClase(miNodo);
        } else {
            miNodo = new Nodo("¿Pierde combates de boxeo?", null);
            miNodo.setNodoSi(new Nodo(null, "Angel"));
            miNodo.setNodoNo(new Nodo(null, "Junchaya"));
            se = new SEClase(miNodo);
        }

        System.out.println("\t        .--'''''''''--.\n"
                + "\t     .'      .---.      '.\n"
                + "\t    /    .-----------.    \\\n"
                + "\t   /        .-----.        \\\n"
                + "\t   |       .-.   .-.       |\n"
                + "\t   |      /   \\ /   \\      |\n"
                + "\t    \\    | .-. | .-. |    /\n"
                + "\t     '-._| | | | | | |_.-'\n"
                + "\t         | '-' | '-' |\n"
                + "\t          \\___/ \\___/\n"
                + "\t       _.-'  /   \\  `-._\n"
                + "\t     .' _.--|     |--._ '.\n"
                + "\t     ' _...-|     |-..._ '\n"
                + "\t            |     |\n"
                + "\t            '.___.'\n"
                + "\t              | |\n"
                + "\t             _| |_");
        se.juega(miNodo);
    }
}
