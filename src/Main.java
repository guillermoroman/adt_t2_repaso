import java.io.*;


public class Main {
    public static void main(String[] args) {


        //crearArchivoEnDirectorio("prueba1.txt", "archivos");
        //renombrarArchivo("archivos/prueba1.txt", "test1.txt");
        //borrarArchivo("archivos/test1.txt");
        //crearArchivoAutoConWriters();


        // append a test1.txt
        String contenido1 = "El\nespacio\nno\nfunciona,\nasí\nque\nescribo\nen\nlíneas";
        String contenido2 = "Ya está arreglado\nNo te preocupes";

        //anadirLineaConFileWriter("archivos/test1.txt", contenido1);
        //anadirLineaConBufferedWriter("archivos/test1.txt", contenido2);


        //lectura
        //leerCaracterACaracter("archivos/test1.txt");
        //leerLineaALinea("archivos/test1.txt");

        //Copiado de archivos
        copiarArchivo("archivos/image.png", "archivos/mario.png");
    }

    // Crear directorio "archivos" si no existe y archivo "test1.txt" si no existe
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
    public static void renombrarArchivo(String rutaActual, String nuevoNombre) {
        File archivoActual = new File(rutaActual);
        if (!archivoActual.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }

        String nuevaRuta = archivoActual.getParent() + File.separator + nuevoNombre;
        File nuevoArchivo = new File(nuevaRuta);

        boolean exito = archivoActual.renameTo(nuevoArchivo);
        if (exito) {
            System.out.println("Archivo renombrado exitosamente a: " + nuevoNombre);
        } else {
            System.out.println("No se pudo renombrar el archivo.");
        }
    }
    public static void borrarArchivo(String ruta){
        File archivo = new File(ruta);
        archivo.delete();
    }
    public static void crearArchivoAutoConWriters(){
        //Un FileWriter crea automáticamente un archivo al escribir si no existe.
        try {
            FileWriter fw = new FileWriter("archivos/fwAuto.txt");
            //Se guarda creando el FileWriter. No hace falta escribir.
            fw.write("Texto de prueba con FileWriter.");
            fw.close(); // Necesario para guardar los cambios
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Un BufferedWriter también.
        // Usando un try-with-resources no necesitamos cerrar el Writer
        try (BufferedWriter bw = new BufferedWriter( new FileWriter("archivos/bwAuto.txt"))){
            bw.write("Texto de prueba con BufferedWriter.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void anadirLineaConFileWriter(String rutaArchivo, String contenido) {
        try (FileWriter writer = new FileWriter(rutaArchivo, true)) {
            writer.write(contenido + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void anadirLineaConBufferedWriter(String rutaArchivo, String contenido) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            bw.write(contenido + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void leerCaracterACaracter(String rutaArchivo) {
        try (FileReader reader = new FileReader(rutaArchivo)) {
            int caracter;
            while ((caracter = reader.read()) != -1) {
                char letra = (char) caracter;
                System.out.print(letra);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void leerLineaALinea(String rutaArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copiarArchivo(String origen, String destino) {
        try (FileInputStream fis = new FileInputStream(origen);
             FileOutputStream fos = new FileOutputStream(destino)) {

            byte[] buffer = new byte[1024];
            int cantidadLeida;
            int totalLeido = 0;

            while ((cantidadLeida = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, cantidadLeida);
                //totalLeido+=cantidadLeida;
            }

            System.out.println("Archivo copiado con éxito.");
            //System.out.println("Cantidad leída:" + totalLeido);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}