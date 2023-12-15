/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prog08practica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 *
 * @author madrid
 */
public class volcarFicheroEnBBDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection con;
        String sql;
        
        String url = "jdbc:mysql://localhost:3306/concurso";
        
        try {
            con = DriverManager.getConnection(url, "root", "1234");
            
            sql = "insert into preguntas values(?, ?, ?, ?, ?)";
            
            PreparedStatement sentencia = con.prepareStatement(sql);
            
            BufferedReader br = new BufferedReader(new FileReader("ingles.txt"));
            String linea;
            
            while((linea = br.readLine()) != null) {
                String pregunta = linea;
                String respuestaCorrecta = br.readLine();
                String respuesta1 = br.readLine();
                String respuesta2 = br.readLine();
                String respuesta3 = br.readLine();
                
                sentencia.setString(1, pregunta);
                sentencia.setString(2, respuestaCorrecta);
                sentencia.setString(3, respuesta1);
                sentencia.setString(4, respuesta2);
                sentencia.setString(5, respuesta3);
                
                sentencia.executeUpdate();
            }
            
            System.out.println("Los datos se volcaron correctamente en la base de datos");
            con.close();
            
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algún error. ERROR:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("No se puede leer el fichero. ERROR:" + e.getMessage());
        }
    }
    
}
