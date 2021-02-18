package conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class InsertaCliente {

	public static void main(String[]args) {
	
	SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Cliente.class)
			.addAnnotatedClass(DetallesCliente.class)
			.addAnnotatedClass(Pedido.class)
			.buildSessionFactory();	
	Session miSession = miFactory.openSession();
	

	try {
		//PASO 3:
		//CREAMOS OBJ DE TIPO CLIENTE y DETALLESCLIENTE
		Cliente cliente1 = new Cliente("Ana","Marín", "Gran vía");
		DetallesCliente detallescliente1 = new DetallesCliente("www..pildoras","29889283", "tercer cliente");
		
		
		//ASOCIAMOS LOS OBJ's
		cliente1.setDetallesCliente(detallescliente1);
		
		//PASO 4:
		//beginTransaction(): EJECUTAR TRANSACCIÓN.
		//save(cliente1): LA TRANSACCIÓN GUARDA AL OBJ CLIENTE EN LA BBDD.
		//getTransaction().commit(): RESCATAMOS LA TRANSACCIÓN QUE HEMOS HECHO, Y CON COMMIT() FIJAMOS LA OPERACIÓN.  
		miSession.beginTransaction();
		miSession.save(cliente1);
		miSession.getTransaction().commit();
		System.out.println("REGISTRO INSERTADO CORRECTAMENTE");
		
		
		
		miSession.close();
		
	}finally{
		miFactory.close();
	}
	
	
	}
	
}
