/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prog08practica;

/**
 *
 * @author Sergio
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Partida p = new Partida();
        Jugadores j = new Jugadores("sinNombre", 0);
        Preguntas pr = new Preguntas();
        
        pr.generarPreguntaLetras();
    }
}
    

