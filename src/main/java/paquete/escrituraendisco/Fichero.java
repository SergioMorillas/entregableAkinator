package paquete.escrituraendisco;
import java.io.*;
/**
 * La clase Fichero proporciona metodos para crear, eliminar, leer y escribir en
 * archivos.
 *
 * @version 1.0
 * @author Sergio
 */
public class Fichero {
    // <editor-fold defaultstate="collapsed" desc="Crear archivos">
    /**
     * Crea un nuevo archivo con el nombre y ruta especificados.
     *
     * @param ruta La ruta del archivo.
     * @param nombre El nombre del archivo.
     * @return El objeto File que representa el archivo creado.
     * @throws IOException Si ocurre un error al crear el archivo.
     */
    public static File crearArchivo(String ruta, String nombre) throws IOException {
        File archivo = new File(ruta, nombre);
        boolean resultado = true;
        if ((!archivo.exists())) {
            try {
                resultado = archivo.createNewFile();
            } catch (IOException e) {
                resultado = false;
                System.out.println("ExcepciÃ³n:\t" + e.getMessage());
            }
        } else {
            System.out.println("El fichero ya existÃ­a");
            resultado = false;
        }
        return archivo;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Eliminar archivos">
    /**
     * Elimina el archivo con el nombre y ruta especificados.
     *
     * @param ruta La ruta del archivo.
     * @param nombre El nombre del archivo.
     * @return true si el archivo se elimina correctamente, false en caso
     * contrario.
     */
    public static boolean eliminarArchivo(String ruta, String nombre) {
        File archivo = new File(ruta, nombre);
        archivo.delete();
        return true;
    }

    /**
     * Elimina el archivo especificado.
     *
     * @param archivo El archivo a eliminar.
     * @return true si el archivo se elimina correctamente, false en caso
     * contrario.
     */
    public static boolean eliminarArchivo(File archivo) {
        archivo.delete();
        return true;
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Leer archivos">
    /**
     * Lee el contenido del archivo con el nombre y ruta especificados.
     *
     * @param ruta La ruta del archivo.
     * @param nombre El nombre del archivo.
     * @return El contenido del archivo como una cadena de caracteres.
     * @throws FileNotFoundException Si el archivo no se encuentra en la ruta
     * especificada.
     */
    public static String leerArchivo(String ruta, String nombre) throws FileNotFoundException {
        File archivo = new File(ruta, nombre);
        StringBuilder sb = new StringBuilder();
        String linea;
        try ( BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return sb.toString();
    }

    /**
     * Lee el contenido del archivo especificado.
     *
     * @param archivo El archivo a leer.
     * @return El contenido del archivo como una cadena de caracteres.
     * @throws FileNotFoundException Si el archivo no se encuentra en la ruta
     * especificada.
     */
    public static String leerArchivo(File archivo) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        String linea;
        try ( BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return sb.toString();
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Escribir archivos">
    /**
     * Escribe el mensaje especificado en el archivo con el nombre y ruta
     * especificados sobreescribiendo el texto que hubiese anteriormente.
     *
     * @param ruta La ruta del archivo.
     * @param nombre El nombre del archivo.
     * @param mensaje El mensaje a escribir en el archivo.
     * @return true si el mensaje se escribe correctamente en el archivo, false
     * en caso contrario.
     */
    public static boolean escribirArchivo(String ruta, String nombre, String mensaje) {
        File archivo = new File(ruta, nombre);
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(mensaje);
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        }
    }

    /**
     * Escribe el mensaje especificado en el archivo especificado,
     * sobreescribiendo el texto que hubiese anteriormente.
     *
     * @param archivo El archivo en el que se escribira el mensaje.
     * @param mensaje El mensaje a escribir en el archivo.
     * @return true si el mensaje se escribe correctamente en el archivo, false
     * en caso contrario.
     */
    public static boolean escribirArchivo(File archivo, String mensaje) {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(mensaje);
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        }
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Anadir texto">
    /**
     * Escribe el mensaje especificado en el archivo con el nombre y ruta
     * especificados, manteniendo el texto que hubiese previamente
     *  
     * @param ruta La ruta del archivo.
     * @param nombre El nombre del archivo.
     * @param mensaje El mensaje a escribir en el archivo.
     * @return true si el mensaje se escribe correctamente en el archivo, false
     * en caso contrario.
     */
    public static boolean anadirTexto(String ruta, String nombre, String mensaje) throws FileNotFoundException {
        String textAntiguo = leerArchivo(ruta, nombre);
        File archivo = new File(ruta, nombre);
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(textAntiguo);
            bw.write(mensaje);
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        }
    }

    /**
     * Escribe el mensaje especificado en el archivo especificado, manteniendo
     * el texto que hubiese previamente
     *
     * @param archivo El archivo en el que se escribira el mensaje.
     * @param mensaje El mensaje a escribir en el archivo.
     * @return true si el mensaje se escribe correctamente en el archivo, false
     * en caso contrario.
     */
    public static boolean anadirTexto(File archivo, String mensaje) throws FileNotFoundException {
        String textAntiguo = leerArchivo(archivo);
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(textAntiguo);
            bw.write(mensaje);
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        }
    }
    //</editor-fold>
}