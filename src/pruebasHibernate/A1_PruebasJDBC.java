package pruebasHibernate;

import java.sql.Connection;
import java.sql.DriverManager;

public class A1_PruebasJDBC {
	public static void main(String[]args) {
		
		//PARA CONECTAR, ESPECIFICAR URL DEL SERVIDOR DONDE ESTÁ LA BBDD.
		//useSSL=false or true : ES UN PROTOCO DE CONEXIÓN SEGURA.
		String jdbcUrl = "jdbc:mysql://localhost:3306/relacionesHibernate2?useSSL=false";
		String usuario = "root";
		String contra = "";
		try {	
			
			System.out.println("Intentando conectar con la BBDD: " + jdbcUrl);
			
			Connection miConexion = DriverManager.getConnection(jdbcUrl, usuario, contra);
			
			System.out.println("Conexión exitosa!");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
