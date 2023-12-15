/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog08practica;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.*;
import java.util.Arrays;

/**
 *
 * @author Sergio
 */
public class Preguntas {
    
    // ATRIBUTO
    private String palabra;
    
    // MÉTODOS
    /**
     * Método que genera la pregunta que responderán los jugadores
     * @return expresión que hemos generado
     */
    public String[] generarPregunta() {
        
        // numEnteros que genera el número de enteros que habrá en la expresión
        int numEnteros = (int) (4 + Math.random() * (8-4+1));
        
        // Array donde se guardará la expresión
        String [] exp = new String [numEnteros + numEnteros - 1];
        
        // Array donde se guardarán los operadores para después introducirlos
        // en la expresión
        String [] operador = new String [] {"*", "+", "-"};
        
        // Bucle for para recorrer la expresión e ir introduciendo los enteros
        // en las posiciones impares y los operadores en las posiciones pares
        for(int i=0; i < exp.length; i++) {
            
            if (i % 2 != 0) {
                exp[i] = operador[(int) (Math.random() * 3)];
            } else {
                int valor = (int) (2 + Math.random() * (12-2+1));
                exp[i] = Integer.toString(valor);
            }
        }
        
        // Devolvemos la expresión completa
        return exp;
    }
    
    /**
     * Evalúa una expresión matemática dada como array de String donde en las posiciones impares
     * hay números y en las impares hay operadores (suma, resta y multiplicación)
     * @param exp Array de String que contiene la expresión
     * @return Devuelve el entero resultado de la expresión. Como el resultado es entero, los 
     * operandos deben ser enteros. Los operadores solo pueden ser suma, resta y multiplicación.
     */
    public static int evaluarExpresionArray (String [] exp) {
        
        int valor = 0;
        String [] expRed = new String [exp.length];
        expRed = Arrays.copyOf(exp, exp.length);
        
        // Recorre el array para realizar las multiplicaciones en primer lugar,
        // por la jerarquía de operaciones.
        // Obtiene un nuevo array donde ya ha calculado las multiplicaciones, solo con sumas y restas
        for (int i=0; i<expRed.length; i++) {
            if (expRed[i] == "*") {
                expRed[i+1] = multiplicar (expRed[i-1], expRed[i+1]);
                expRed[i-1] = "0";
                if (i-2>0 && expRed[i-2]=="-")
                    expRed[i] = "-";
                else 
                    expRed[i] = "+";
            }
        }
        
        // Calcula las operaciones de suma y resta del array
        String op = "";
        for (int i=0; i<expRed.length; i++) {
            if (i==0) {
                valor = Integer.parseInt (expRed[i]);
            } else if (expRed[i] == "+") {
                op = "suma";
            } else if (expRed[i] == "-") {
                op = "resta";
            } else {
                if (op == "suma") {
                    valor = valor + Integer.parseInt (expRed[i]);
                    op = "";
                } else if (op == "resta") {
                    valor = valor - Integer.parseInt (expRed[i]);
                    op = "";
                }
            }
        }
        return valor;
    }
    
    /**
     * Método privado para calcular la multiplicacion de dos números 
     * @param num1 String que indica el primer operando, que tendrá que convertir a entero para operar
     * @param num2 String que indica el segundo operando, que tendrá que convertir a entero para operar
     * @return String 
     */
    private static String multiplicar (String num1, String num2) {
        int num1Int, num2Int, resInt;
        String resString;
        
        num1Int = Integer.parseInt (num1);
        num2Int = Integer.parseInt (num2);
        resInt = num1Int * num2Int;
        resString = Integer.toString (resInt);
        return resString;
    }
    
    
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
     * @return 
     */
    public String generarPreguntaLetras() {
        
        // Variable para la palabra
        this.palabra = leerPalabra();
        System.out.println(palabra);
        char [] arrayPalabra = palabra.toCharArray();
        
        int asteriscos = (int) arrayPalabra.length / 3;
        
        for (int i=0; i < asteriscos; i++) {
            arrayPalabra [(int) (i + Math.random() * (arrayPalabra.length-i))] = '*';
        }
        
        /*for (int i=0; i < arrayPalabra.length; i++) {
            System.out.print(arrayPalabra[i]);
        }*/
        
        String p = String.valueOf(arrayPalabra);
        System.out.println(p);
        return p;
    }
    
    /**
     * Método para evaluar la pregunta generada
     * @param respuesta
     * @return 
     */
    public String evaluarPreguntaLetras () {
        
        return this.palabra;
        /*if (respuesta.equals(this.palabra)) { 
            System.out.println("!Enhorabuena, has acertado¡");
            return this.palabra;
            
        } else
            return this.palabra;*/
    }
    
    public void mostrarPreguntaIngles() {
        // Conexiones
        Connection con;
        Statement sentencia;
        String sql;
        
        String url = "jdbc:mysql://localhost:3306/concurso";
        
        try {
            con = DriverManager.getConnection(url, "root", "1234");
            sentencia = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            // Sentencia que se ejecutara en la base de datos
            sql = "select * from preguntas";
            
            // Ejecutamos la sentencia
            ResultSet rs = sentencia.executeQuery(sql);
            
            // Numero aleatorio entre 1 y 1000
            int aleatorio = (int) (1 + Math.random() + (999-1+1));
            // Colocamos el cursor en una posición aleatoria
            rs.absolute(aleatorio);
            // Lo mandamos a la siguiente fila para asegurar que empieza por la pregunta
            rs.next();
            
            while(rs.next()) {
                String pregunta = rs.getString("pregunta");
                System.out.println(pregunta);
                System.out.println(rs.getString("respuestaCorrecta"));
                System.out.println(rs.getString("respuesta1"));
                System.out.println(rs.getString("respuesta2"));
                System.out.println(rs.getString("respuesta3"));
            }
            
            // Cerramos las conexiones
            con.close();
            sentencia.close();
            
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algún error. ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void evaluarPreguntaIngles() {
        
    }
}
