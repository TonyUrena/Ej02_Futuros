package org.futuros;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ComprimeZip {

    // Método principal para comprimir una ruta en un archivo zip
    public static void comprimeRuta(String ruta, String salida) throws IOException {

        // Crear un flujo de salida para el archivo zip
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(salida));
        File fileToZip = new File(ruta);

        // Llamada al método para comprimir el archivo o directorio
        ComprimeZip.comprimirArchivo(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
    }

    // Método recursivo para comprimir un archivo o directorio
    public static void comprimirArchivo(File archivoAComprimir, String nombreArchivo, ZipOutputStream zipSalida) throws IOException {

        // Verificar si el archivo está oculto y salir si es así
        if (archivoAComprimir.isHidden()) {
            return;
        }

        // Comprobar si es un directorio
        if (archivoAComprimir.isDirectory()) {
            // Manejar la entrada del directorio en el archivo zip
            if (nombreArchivo.endsWith("/")) {
                zipSalida.putNextEntry(new ZipEntry(nombreArchivo));
                zipSalida.closeEntry();
            } else {
                zipSalida.putNextEntry(new ZipEntry(nombreArchivo + "/"));
                zipSalida.closeEntry();
            }

            // Recorrer los archivos dentro del directorio y comprimirlos
            File[] hijos = archivoAComprimir.listFiles();
            for (File hijoArchivo : hijos) {
                comprimirArchivo(hijoArchivo, nombreArchivo + "/" + hijoArchivo.getName(), zipSalida);
            }
            return;
        }

        // Si es un archivo, comprimir su contenido
        FileInputStream fis = new FileInputStream(archivoAComprimir);
        ZipEntry entradaZip = new ZipEntry(nombreArchivo);
        zipSalida.putNextEntry(entradaZip);
        byte[] bytes = new byte[1024];
        int longitud;
        while ((longitud = fis.read(bytes)) >= 0) {
            zipSalida.write(bytes, 0, longitud);
        }
        fis.close();
    }

    // Método para comprimir contenido web y agregarlo al archivo zip
    public static void comprimeWebs(List<String> listaWebs, String nombreZip) throws ExecutionException, InterruptedException, IOException {

        try (ZipOutputStream zipSalida = new ZipOutputStream(new FileOutputStream(nombreZip))) {
            // Recorrer las URLs y agregar su contenido HTML al archivo zip
            for (String web : listaWebs) {
                agregarStringComoArchivo(HttpFuturos.getHttp(web), "Página_" + listaWebs.indexOf(web) + ".html", zipSalida);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para agregar un String como un archivo al archivo zip
    public static void agregarStringComoArchivo(String contenido, String nombreArchivo, ZipOutputStream zipSalida) throws IOException {
        byte[] bytes = contenido.getBytes(StandardCharsets.UTF_8);
        InputStream inputStream = new ByteArrayInputStream(bytes);
        zipSalida.putNextEntry(new ZipEntry(nombreArchivo));
        int longitud;
        while ((longitud = inputStream.read(bytes)) >= 0) {
            zipSalida.write(bytes, 0, longitud);
        }
        zipSalida.closeEntry();
    }
}

