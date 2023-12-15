/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog08practica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author madrid
 */
public class Partida{
    
    // ATRIBUTOS
    private int rondas;
    private int numJugadores;
    ArrayList<Jugadores> jugadores = new ArrayList();
    
    
    // GETTERS Y SETTERS
    /**
     * @param numJugadores the numJugadores to set
     */
    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }
    
    
    // MÉTODOS
    
    /**
     * Método que muestra el ranking de la partida
     */
    public void ranking() {
        // Conexiones
        Connection con;
        Statement sentencia;
        String sql;
        
        String url = "jdbc:mysql://localhost:3306/concurso";
        
        try {
            con = DriverManager.getConnection(url, "root", "1234");
            sentencia = con.createStatement();
            
            // Sentencia para la base de datos
            sql = "select * from ranking";
            
            // Ejecutamos la sentencia
            ResultSet rs = sentencia.executeQuery(sql);
            System.out.println("----RANKING----");
            
            // Mostramos los datos recogidos
            while(rs.next()) {
                String nombre = rs.getString("nombre");
                int total = rs.getInt("total");
                System.out.println("Nombre: " + nombre + " | Preguntas acertadas: " + total);
            }
            
            // Cerramos conexiones
            con.close();
            sentencia.close();
            
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algún error. ERROR:" + e.getMessage());
        }
    }
    
    
    
    /**
     * Método que muestra el histórico de la partida
     */
    public void historico() {
        // Conexiones
        Connection con;
        Statement sentencia;
        String sql;
        
        String url = "jdbc:mysql://localhost:3306/concurso";
        
        try {
            con = DriverManager.getConnection(url, "root", "1234");
            sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            // Sentencia para la base de datos
            sql = "select id_partida, jugador, puntuacion from juegan";
            
            // Ejecutamos la sentencia
            ResultSet rs = sentencia.executeQuery(sql);
            System.out.println("----HISTÓRICO----");
            
            // Mostramos los datos recogidos           
            while (rs.next()) {
                int numColumnas = rs.getMetaData().getColumnCount(); // Obtenemos el nº de columnas
                
                // Imprimimos columna por columna
                for (int i = 1; i <= numColumnas; i++) {
                    String nombreColumna = rs.getMetaData().getColumnName(i);
                    String valorColumna = rs.getString(i);

                    System.out.print(nombreColumna + ": " + valorColumna + " | ");
                }
                
                // Guardamos el id de la partida junto con el del siguiente registro
                // para compararlos después y ver si imprimirlo en la misma linea y en la siguiente
                int id = rs.getInt("id_partida");
                
                int idN = 0;
                if(rs.next()) {
                    idN = rs.getInt("id_partida");
                    rs.previous();
                }
                // Si no son iguales hacemos un salto de línea
                if (id != idN)
                    System.out.println();
            }
            
            // Cerramos conexiones
            con.close();
            sentencia.close();
            
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algún error. ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    public void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        
        // Bucle que se acabará cuando el usuario escoga la opcion salir
        do {
            
            System.out.println("----MENÚ PRINCIPAL----");
            System.out.println("Elige una opción");
            System.out.println("1. Jugar partida");
            System.out.println("2. Ranking");
            System.out.println("3. Histórico");
            System.out.println("4. Jugadores");
            System.out.println("5. Salir ->");
            opcion = sc.nextInt();
            System.out.println();          
            
            switch (opcion) {
                case 1:
                    jugarPartida();     // Llamada al método jugarPartida;
                    break;
                case 2:
                    ranking(); // Llamada al método ranking()
                    break;
                case 3:
                    historico(); // Llamada al método histórico()
                    break;
                case 4:
                    Jugadores j = new Jugadores("SinNombre", 0);
                    j.menuJugadores();
                    break;
                case 5:
                    break;      // Sale del bucle
            }
            System.out.println(); 
        } while (opcion != 5);
    }
    
    
    
    /**
     * Método para pedir al usuario el nº de jugadores que van a jugar, para luego llamarlo
     * en el método introducirJugadores para saber cuántos jugadores introducir
     * @return numJugadores
     */
    private int pedirNumJugadores() {
        Scanner sc = new Scanner(System.in);
        
        try {
            // Pedimos el nº de jugadores
            System.out.println("Dime cuantos jugadores van a jugar: ");
            setNumJugadores(sc.nextInt());
            
            // Si el nº de jugaoder esta fuera del rango entonces salta la excepción
            if(this.numJugadores < 1 || this.numJugadores > 6)
                throw new IllegalArgumentException("NÚMERO DE JUGADORES INCORRECTO");
            else
                return this.numJugadores;
            
        } catch(IllegalArgumentException e) {
            throw e;
        }
    }
    
    
    /**
     * Método para introducir los nombres de los jugadores, guardarlos en 
     * el ArrayList y ordenarlos
     */
    public void introducirJugadores() {
        // Número de jugadores que llama al método anterior
        int num = pedirNumJugadores();
        
        System.out.println("--------------------------");
        System.out.println("INTRODUCCIÓN DE JUGADORES");
        Scanner sc = new Scanner(System.in);
        
        try {
            // Recorremos el arrayList
            for (int i=0; i < num; i++) {
                System.out.println("-------JUGADOR " + (i+1) + "-------");
                // Pedimos nombre
                System.out.println("Ingrese su nombre de jugador");
                String nombre = sc.nextLine();
                Jugadores j = new Jugadores(nombre, 0);
                
                // Si el nombre es único, añadimos el jugador, si no salta la excepción
                if (j.comprobarJugador(nombre)) {
                    jugadores.add(j);
                    System.out.println();
                    
                } else {
                    throw new IllegalArgumentException("El jugador no existe en la BBDD");
                }
            }
            
        } catch (IllegalArgumentException e) {
            throw e;
        }
        
        // Función que ordena el ArrayList de manera aleatoria
        Collections.shuffle(jugadores);
    }
    
    
    /**
     * Método para pedir al usuario que eliga las rondas y comprueba si la ronda
     * que ha introducido es válida
     */
    public void pedirRondas() {
        Scanner sc = new Scanner(System.in);
        try {
            
            // Pide el nº de rondas
            System.out.println("Dime cuantas rondas se van a jugar");
            System.out.println("Existen estas opciones: ");
            System.out.println("Partida rápida = 3 rondas");
            System.out.println("Partida corta = 5 rondas");
            System.out.println("Partida normal = 10 rondas");
            System.out.println("Partida larga = 20 rondas");
            rondas = sc.nextInt();
            // Si el nº de rondas no coincide con alguna de esas entonces se produce la excepción
            if(rondas != 3 && rondas != 5 && rondas != 10 && rondas != 20)
                throw new IllegalArgumentException("Número de rondas no válido");
            
            else {
                
                switch(rondas) {
                    case 3: System.out.println("Elegiste la partida rápida");
                    break;

                    case 5: System.out.println("Elegiste la partida corta");
                    break;

                    case 10: System.out.println("Elegiste la partida normal");
                    break;

                    case 20: System.out.println("Elegiste la partida larga");
                    break;
                }
            }
            
        } catch (IllegalArgumentException e) {
            throw e;
        }
        System.out.println();
    }
    
    

    /**
     * Método donde se jugará la partida
     */
    public void jugarPartida() {
        introducirJugadores();
        pedirRondas();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("-----EMPIEZA LA PARTIDA-----");
        
        // Bucle for anidado para recorres todas las rondas y todos los jugadores
        // Para cada ronda
        for(int r=1 ; r<=rondas ; r++) {
            System.out.println("-----RONDA " + r + "-----");
            int random = /*(int) (1 + Math.random() + (3-1+1))*/2;
            
            // Para cada jugador
            for(int j=0; j<jugadores.size(); j++) {
                
                // Creamos objeto de preguntas para poder utilizar el método que la genera
                Preguntas p = new Preguntas();
                switch (random) {
                    case 1:
                            String [] exp = p.generarPregunta();


                            // Bucle for para recorrer la expresión y poder mostrarsela al jugador
                            System.out.println("Pregunta para el jugador " + jugadores.get(j).getNombre());
                            System.out.println("Resuelve la siguiente expresión matemática: ");
                            for(int i=0; i < exp.length; i++) {
                                System.out.print(exp[i]);
                            }

                            // Respuesta del jugador
                            System.out.println();
                            System.out.print("Respuesta: ");
                            int respuestaNum = sc.nextInt();
                            System.out.println();

                            // Evaluamos la respuesta
                            if (respuestaNum == p.evaluarExpresionArray (exp)) {

                                System.out.println("!Enhorabuena, has acertado¡");
                                // Se suman los puntos al jugador
                                jugadores.get(j).sumarPuntos();
                                System.out.println();

                            } else {

                                System.out.println("Has fallado");
                                // Se muestra la respuesta correcta
                                System.out.println("La respuesta correcta es: " + p.evaluarExpresionArray(exp));
                                System.out.println();
                            }
                        break;
                    
                    case 2: //Guardamos la palabra oculta en una variable
                        String palabra = p.generarPreguntaLetras();
                        
                        // Bucle for para recorrer la expresión y poder mostrarsela al jugador
                        System.out.println("Pregunta para el jugador " + jugadores.get(j).getNombre());
                        System.out.println("Adivina que palabra es: " + palabra);
                        
                        // Respuesta del jugador
                        System.out.println();
                        System.out.print("Respuesta: ");
                        String respuestaLetras = sc.nextLine();
                        System.out.println();
                        
                        // Evaluamos la respuesta
                        if (respuestaLetras.equalsIgnoreCase(p.evaluarPreguntaLetras())) {

                            System.out.println("!Enhorabuena, has acertado¡");
                            // Se suman los puntos al jugador
                            jugadores.get(j).sumarPuntos();
                            System.out.println();

                        } else {

                            System.out.println("Has fallado");
                            // Se muestra la respuesta correcta
                            System.out.println("La respuesta correcta es: " + p.evaluarPreguntaLetras());
                            System.out.println();
                        }
                        break;
                        
                    case 3:
                }   
            }
            
            // Bucle for para mostrar los puntos de todos los jugadores y terminar la ronda
            System.out.println();
            System.out.println("------PUNTOS DE LOS JUGADORES------");
            for (int i=0; i < jugadores.size(); i++) {
                System.out.println(jugadores.get(i).getNombre() + ": " + jugadores.get(i).getPuntos());
            }
            
            System.out.println();
            
            finalPartida();
        }
    }
    
    /**
     * Método para finalizar la partida
     */
    public void finalPartida() {
        // Conexiones
        Connection con;
        Statement sentencia;
        String sql;
        
        String url = "jdbc:mysql://localhost:3306/concurso";
        
        try {
            con = DriverManager.getConnection(url, "root", "1234");
            sentencia = con.createStatement();
            
            // Sentencia que se ejecutará en la base de datos
            sql = "insert into partida values(default, now())";
            sentencia.executeUpdate(sql);
            
            for (int i=0; i < jugadores.size(); i++) {
                // Información del jugador
                sql = "insert into juegan values(default, '" + jugadores.get(i).getNombre() + "', " + jugadores.get(i).getPuntos() + ")";
                sentencia.executeUpdate(sql);
            }
            
            // Cerramos conexiones
            con.close();
            sentencia.close();
            
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algún error en la BBDD. ERROR:" + e.getMessage());
            e.printStackTrace();
        }
        
        
        System.out.println("La partida ha finalizado.");
        System.out.println();
        
        int ganador = 0;
        String nombreGanador = "";
        
        // Bucle for para mostrar los puntos de todos los jugadores
        System.out.println("------PUNTOS FINALES DE LOS JUGADORES------");
        for (int i=0; i < jugadores.size(); i++) {
            // Información del jugador
            System.out.println(jugadores.get(i).getNombre() + ": " + jugadores.get(i).getPuntos());
            
            // Concición que va comparando los puntos para guardar al ganador
            if (jugadores.get(i).getPuntos() > ganador){
                ganador = jugadores.get(i).getPuntos();
                nombreGanador = jugadores.get(i).getNombre();
            }         
        }
        // Mostramos el ganador
        System.out.println("¡¡EL GANADOR ES " + nombreGanador.toUpperCase() + " CON UN TOTAL DE PUNTOS DE " + ganador + "!!");
    }
}
