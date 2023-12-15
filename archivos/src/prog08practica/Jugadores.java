/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog08practica;

import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author madrid
 */
public class Jugadores{
    
    // ATRIBUTOS
    private String nombre;
    private int puntos;
    
    
    // CONSTRUCTOR
    public Jugadores(String nombre, int puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
    }
    
    
    
    // GETTERS Y SETTERS

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * 
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    
    
    // MÉTODOS
    
    /**
     * Método para pedir el nombre
     */
    public void pedirNombre() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime tu nombre de jugador");
        this.nombre = sc.nextLine();
    }
    
    /**
     * Método que suma los puntos de cada jugador
     * @return puntos del jugador sumados
     */
    public int sumarPuntos() {
        this.puntos += 1000;
        return this.puntos;
    }
    
    /**
     * 
     * @return datos del jugador
     */
    public String toString() {
        return "Nombre del jugador: " + this.nombre + " | Puntos: " + this.puntos;
    }
    
    /**
     * Método para ver una lista de los jugadores
     */
    public void verJugadores() {
        // Conexiones
        Connection con;
        Statement sentencia;
        String sql;
        
        String url = "jdbc:mysql://localhost:3306/concurso";
        
        try {
            con = DriverManager.getConnection(url, "root", "1234");
            sentencia = con.createStatement();
            
            // Sentencia que se ejecutara en la base de datos
            sql = "select * from jugadores";
            
            // Ejecutamos la sentencia
            ResultSet rs = sentencia.executeQuery(sql);
            System.out.println("----LISTA DE JUGADORES----");
            
            // Mostramos los datos recogidos
            while(rs.next()) {
                String nombre = rs.getString("nombre");
                int puntuacion = rs.getInt("puntuacion");
                System.out.println("Nombre: " + nombre + " | Puntuación: " + puntuacion);
            }
            
            // Cerramos las conexiones
            con.close();
            sentencia.close();
            
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algún error. ERROR:" + e.getMessage());
        }
    }
    
    /**
     * Método que comprueba si existe el jugador que se introduce como parámetro
     * @param nombre
     * @return true si existe, false si no existe
     */
    public boolean comprobarJugador(String nombre) {
        // Conexiones
        Connection con;
        Statement sentencia;
        String sql;
        boolean existe = false;
        
        String url = "jdbc:mysql://localhost:3306/concurso";
        
        try {
            con = DriverManager.getConnection(url, "root", "1234");
            sentencia = con.createStatement();
            
            // Sentencia que se ejecutará en la base de datos
            sql = "select nombre from jugadores";
            // Ejecutamos la sentencia
            ResultSet rs = sentencia.executeQuery(sql);
            
            while(rs.next()) {
                // Si el nombre que pasamos como parametro está en la base de datos
                // entonces el jugador existe
                if(rs.getString("nombre").equalsIgnoreCase(nombre))
                    existe = true;
            }
            
            // Cerramos conexiones
            con.close();
            sentencia.close();
            
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algún error en la BBDD. ERROR:" + e.getMessage());
            e.printStackTrace();
        }
        
        return existe;
    }
    
    /**
     * Método que permite añadir un jugador a la base de datos
     */
    public void añadirJugador() {
        // Conexiones
        Connection con;
        Statement sentencia;
        String sql;
        
        String url = "jdbc:mysql://localhost:3306/concurso";
        
        try {
            con = DriverManager.getConnection(url, "root", "1234");
            sentencia = con.createStatement();
            
            // Pedimos el nombre
            Scanner sc = new Scanner(System.in);
            System.out.println("Dime tu nombre de jugador");
            String nombre = sc.nextLine();
            
            if(comprobarJugador(nombre)) {
                throw new SQLException("El jugador ya existe");
                
            } else
                // Sentencia para la base de datos
                sql = "insert into jugadores values('" + nombre + "', 0)";

                // Ejecutamos la sentencia
                sentencia.executeUpdate(sql);

                // Cambiamos la sentencia para que se guarde también en la tabla ranking
                sql = "insert into ranking values('" + nombre + "', 0)";
                // Ejecutamos la nueva sentencia
                sentencia.executeUpdate(sql);
                System.out.println("El jugador se añadió correctamente");
            
            // Cerramos conexiones
            con.close();
            sentencia.close();
            
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algún error en la BBDD. ERROR:" + e.getMessage());
        }
    }
    
    /**
     * Método que va eliminar el jugador que introduzcamos
     */
    public void eliminarJugador() {
        // Conexiones
        Connection con;
        Statement sentencia;
        String sql;
        
        String url = "jdbc:mysql://localhost:3306/concurso";
        
        try {
            con = DriverManager.getConnection(url, "root", "1234");
            sentencia = con.createStatement();
            
            // Pedimos el nombre
            Scanner sc = new Scanner(System.in);
            System.out.println("Dime el nombre del jugador que quieres eliminar");
            String nombre = sc.nextLine();
            
            // Sentencia para la base de datos
            sql = "delete from ranking where nombre = '" + nombre + "'";
            // Ejecutamos la sentencia
            sentencia.executeUpdate(sql);
            
            // Sentencia para la base de datos
            sql = "delete from jugadores where nombre = '" + nombre + "'";
            // Ejecutamos la sentencia
            sentencia.executeUpdate(sql);
            System.out.println("El jugador se eliminó correctamente");
            
            // Cerramos conexiones
            con.close();
            sentencia.close();
            
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algún error. ERROR:" + e.getMessage());
        }
    }
    
    /**
     * Método para modificar la puntuación
     */
    public void modificarPuntuacion() {
        // Conexiones
        Connection con;
        Statement sentencia;
        String sql;
        
        String url = "jdbc:mysql://localhost:3306/concurso";
        
        try {
            con = DriverManager.getConnection(url, "root", "1234");
            sentencia = con.createStatement();
            
            // Pedimos nombre del que queremos modificar
            Scanner sc = new Scanner(System.in);
            System.out.println("¿A que jugador le vas a modificar la puntuación?");
            String nombre = sc.nextLine();
            System.out.println("¿Que puntuación quieres ponerle?");           
            int puntuacion = sc.nextInt();
            
            // Sentencia para la base de datos
            sql = "update jugadores set puntuacion = " + puntuacion + " where nombre = '" + nombre + "'";

            // Ejecutamos la sentencia
            sentencia.executeUpdate(sql);
            System.out.println("Se actualizó correctamente la puntuación");
            
            // Cerramos conexiones
            con.close();
            sentencia.close();
            
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algún error. ERROR:" + e.getMessage());
        }
    }
    
    /**
     * Método que muestra un menú para la gestión de jugadores
     */
    public void menuJugadores() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        
        // Bucle que se acabará cuando el usuario escoga la opcion salir
        do {
            
            System.out.println("----GESTIÓN DE JUGADORES----");
            System.out.println("Elige una opción");
            System.out.println("1. Ver jugadores");
            System.out.println("2. Añadir jugador");
            System.out.println("3. Eliminar jugador");
            System.out.println("4. Salir ->");
            opcion = sc.nextInt();
            System.out.println();          
            
            switch (opcion) {
                case 1:
                    verJugadores(); // Llamada al método verJugadores()
                    break;
                case 2:
                    añadirJugador(); // Llamada al método añadirJugador()
                    break;
                case 3:
                    eliminarJugador(); // Llamada al método eliminarJugador()
                    break;
                case 4:
                    break; // Se sale del bucle
            }
            System.out.println(); 
        } while (opcion != 4);
    }
}
