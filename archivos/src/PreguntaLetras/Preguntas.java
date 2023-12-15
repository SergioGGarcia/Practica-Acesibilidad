/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PreguntaLetras;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Sergio
 */
public class Preguntas {
    
    // ATRIBUTO
    String palabra;
    
    /**
     * Método que lee una palabra aleatoria del fichero diccionario.txt
     * @return 
     */
    public String leerPalabra() {
        
        // Variable donde se guardará la palabra
        String linea = "";
        
        try {
            
            // Objeto de la clase RandomAccesFile para leer linea aleatoria
            RandomAccessFile fichero = new RandomAccessFile("diccionario.txt", "r");
            
            // Guardamos la longitud del fichero
            long longFichero = fichero.length();
            // Guardamos una posición aleatoria
            long posicionA = (long) (Math.random() * longFichero);
            
            // Nos posicionamos en la ubicación aleatoria generada
            fichero.seek(posicionA);
            
            // Saltamos todos los caracteres hasta el siguiente salto de linea
            if (posicionA > 0) {
                fichero.readLine();
            }
            
            // Guardamos lo que a leído en una variable
            linea = fichero.readLine();
            // Cerramos el fichero
            fichero.close();
           
                
        } catch (IOException e) {
            System.out.println ("No se puede leer el fichero: " + e.getMessage());
        }
        
        return linea;
    }
    
    /**
     * Método que genera la pregunta
     */
    public void generarPreguntaLetras() {
        
        // Variable para la palabra
        this.palabra = leerPalabra();
        System.out.println(palabra);
        char [] arrayPalabra = palabra.toCharArray();
        
        int asteriscos = (int) arrayPalabra.length / 3;
        
        for (int i=0; i < asteriscos; i++) {
            arrayPalabra [(int) (i + Math.random() * (arrayPalabra.length-i))] = '*';
        }
        
        for (int i=0; i < arrayPalabra.length; i++) {
            System.out.print(arrayPalabra[i]);
        }
        System.out.println();
    }
    
    /**
     * Método para evaluar la pregunta generada
     * @param respuesta
     * @return 
     */
    public String evaluarPreguntaLetras (String respuesta) {
        
        if (respuesta.equals(this.palabra)) { 
            System.out.println("!Enhorabuena, has acertado¡");
            return this.palabra;
            
        } else
            return this.palabra;
    }
}
