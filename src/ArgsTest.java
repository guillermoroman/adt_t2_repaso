import java.io.File;
import java.io.IOException;

public class ArgsTest {
    public static void main(String[] args) {
        String nombreArchivo = "default.txt";
        String nombreDirectorio = ".";
        if (args.length == 2){
            nombreArchivo = args[1];
            nombreDirectorio = args[0];
        }
        System.out.println(nombreDirectorio);
        System.out.println(nombreArchivo);

        crearArchivoEnDirectorio(nombreArchivo, nombreDirectorio);

    }

    public static void  crearArchivoEnDirectorio(String fileName, String directorio){
        File directory = new File(directorio);

        // Crear el directorio si no existe
        if (!directory.exists()) {
            boolean dirCreated = directory.mkdir();
            if (dirCreated) {
                System.out.println("Directorio 'archivos' creado.");
            } else {
                System.out.println("No se pudo crear el directorio 'archivos'.");
                return;
            }
        }

        // Crear un objeto File para el archivo dentro del directorio
        File file = new File(directory, fileName);

        try {
            // Crear el archivo si no existe
            boolean fileCreated = file.createNewFile();
            if (fileCreated) {
                System.out.println("Archivo 'prueba1.txt' creado en el directorio 'archivos'.");
            } else {
                System.out.println("El archivo " + fileName + " ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Se produjo un error al crear 'prueba1.txt': " + e.getMessage());
        }
    }
}
