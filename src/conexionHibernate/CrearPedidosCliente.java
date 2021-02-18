package conexionHibernate;

import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CrearPedidosCliente {

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
		
		//CREAR PEDIDOS DEL CLIENTE
		Pedido pedido1 = new Pedido(new GregorianCalendar(2020,7,5));
		Pedido pedido2 = new Pedido(new GregorianCalendar(2020,8,16));
		Pedido pedido3 = new Pedido(new GregorianCalendar(2020,9,24));
		
		//GREGAR PEDIDOS AL CLIENTE
		elCliente.agregarPedidos(pedido1);
		elCliente.agregarPedidos(pedido2);
		elCliente.agregarPedidos(pedido3);
		
		//GUARDAR PEDIDOS EN BBDD
		miSession.save(pedido1);
		miSession.save(pedido2);
		miSession.save(pedido3);
		
		miSession.getTransaction().commit();
		System.out.println("REGISTROS INSERTADOS CORRECTAMENTE");
		
	}catch(Exception e){	
		e.printStackTrace();
	}finally{
		miSession.close();
		miFactory.close();
	}
	
	
	}
	
}
