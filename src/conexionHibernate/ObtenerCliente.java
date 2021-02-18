package conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ObtenerCliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Cliente.class)
				.addAnnotatedClass(DetallesCliente.class)
				.buildSessionFactory();	
		Session miSession = miFactory.openSession();
		

		try {
			
		
			//beginTransaction(): EJECUTAR TRANSACCIÓN.
			//getTransaction().commit(): RESCATAMOS LA TRANSACCIÓN QUE HEMOS HECHO, Y CON COMMIT() FIJAMOS LA OPERACIÓN.  
			miSession.beginTransaction();
			
			//OBTENER OBJ's DETALLESCLIENTE
			DetallesCliente detallesDeCliente = miSession.get(DetallesCliente.class, 2);
			//IMPRIMIMOS DETALLES
			System.out.println(detallesDeCliente);
			//OBTENEMOS AL CLIENTE Y LO IMPRIMIMOS
			System.out.println(detallesDeCliente.getElCliente());
			
			
			System.out.println("AHORA VAMOS A BORRAR EN CASCADA");
			miSession.delete(detallesDeCliente);
			
			
			
			miSession.getTransaction().commit();
			
			
		}catch(Exception ex1){
			ex1.printStackTrace();
			
		}finally{
			miSession.close();
			miFactory.close();
		}
		
		
		}
		
		
}


