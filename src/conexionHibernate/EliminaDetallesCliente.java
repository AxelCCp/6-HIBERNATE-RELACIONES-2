package conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EliminaDetallesCliente {

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
		
		
		DetallesCliente detalleDelCliente = miSession.get(DetallesCliente.class, 4);
		
		//le decimos que el cliente no tiene detalles, para que no salga error al eliminar solo los..
		//detalles. O sea que este detalleCliente, no tiene cliente asociado.
		detalleDelCliente.getElCliente().setDetallesCliente(null);
		
		if(detalleDelCliente!= null) miSession.delete(detalleDelCliente);
		
		miSession.getTransaction().commit();
		if(detalleDelCliente!= null) System.out.println("REGISTRO ***ELIMINADO*** CORRECTAMENTE");
		else System.out.println("NO EXISTEN DATOS PARA ELIMINAR");
		miSession.close();
		
	}finally{
		miFactory.close();
	}
	
	
	}
	
}
