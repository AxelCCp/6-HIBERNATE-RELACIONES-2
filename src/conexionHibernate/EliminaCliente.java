package conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EliminaCliente {

	public static void main(String[]args) {
	
	SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Cliente.class)
			.addAnnotatedClass(DetallesCliente.class)
			.buildSessionFactory();	
	Session miSession = miFactory.openSession();
	

	try {
		
	
		//beginTransaction(): EJECUTAR TRANSACCIÓN.
		//getTransaction().commit(): RESCATAMOS LA TRANSACCIÓN QUE HEMOS HECHO, Y CON COMMIT() FIJAMOS LA OPERACIÓN.  
		miSession.beginTransaction();
		
		//PASO ELIMINAR CLIENTE: 
		//PARA ELIMINAR A UN CLIENTE, SEGÚN EL N° DE ID, ALMACENAMOS LA INFORMACIÓN DENTRO DE DE "elCliente": 
		Cliente elCliente = miSession.get(Cliente.class, 1);
		if(elCliente!= null) miSession.delete(elCliente);
		
		miSession.getTransaction().commit();
		if(elCliente!= null) System.out.println("REGISTRO ***ELIMINADO*** CORRECTAMENTE");
		else System.out.println("NO EXISTEN DATOS PARA ELIMINAR");
		miSession.close();
		
	}finally{
		miFactory.close();
	}
	
	
	}
	
}
