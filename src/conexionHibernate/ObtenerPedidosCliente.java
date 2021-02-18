package conexionHibernate;

import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ObtenerPedidosCliente {

	public static void main(String[]args) {
	
	SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Cliente.class)
			.addAnnotatedClass(DetallesCliente.class)
			.addAnnotatedClass(Pedido.class)
			.buildSessionFactory();	
	Session miSession = miFactory.openSession();
	

	try {
		
		//beginTransaction(): EJECUTAR TRANSACCIÓN.
		//getTransaction().commit(): RESCATAMOS LA TRANSACCIÓN QUE HEMOS HECHO, Y CON COMMIT() FIJAMOS LA OPERACIÓN.  
		miSession.beginTransaction();
		
		//CREAMOS OBJ TIPO CLIENTE y OBTENEMOS AL CLIENTE DE LA BBDD, SEGÚN SU ID
		Cliente elCliente = miSession.get(Cliente.class, 5);
		
		//ONTENEMOS INFORMACIÓN DE LOS PEDIDOS
		System.out.println("CLIENTE: " + elCliente);
		System.out.println("PEDIDOS: " + elCliente.getPedidos());
		
		miSession.getTransaction().commit();
	
	}catch(Exception e){	
		e.printStackTrace();
	}finally{
		miSession.close();
		miFactory.close();
	}
	
	
	}
	
}
