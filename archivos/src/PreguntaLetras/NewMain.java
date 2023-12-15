/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PreguntaLetras;

import java.io.IOException;

/**
 *
 * @author Sergio
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Preguntas p = new Preguntas();
        
        p.leerPalabra();
        p.generarPreguntaLetras();
    }
    
}
