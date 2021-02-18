package conexionHibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//HIBERNATE MAPEARÁ LAS PROPIEDADES DE EST CLASE Y PARA ESTO SE USARÁN LAS ANOTACIONES.

@Entity //PARA MAPEO DE CLASE A TABLA, HIBERNATE TRANSFORMA LAS CLASES EN ENTIDADES, PARA MAPEAR.
@Table(name="cliente") //REFERENCIA A LA TABLA A LA QUE NOS REFERIMOS.
public class Cliente {

	
	//HACEMOS QUE LA CLASE SEA CAPAZ DE CREAR OBJ'S DE TIPO CLIENTE, PARA ESTO SE USAN:
			//2 CONSTRUCTORES.
			//GETTERS Y SETTERS 
	
	public Cliente() {
	}
		
	public Cliente(String nombre, String apellido, String direccion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public DetallesCliente getDetallesCliente() {
		return detallesCliente;
	}
	public void setDetallesCliente(DetallesCliente detallesCliente) {
		this.detallesCliente = detallesCliente;
	}

	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + "]";
	}



	//MÉTODO PARA AGREGAR LOS PEDIDOS PARA EL CLIENTE QUE HAYA HECHO LOS PEDIDOS
	public void agregarPedidos(Pedido elPedido) {
		
		if(pedidos==null) pedidos = new ArrayList<>();
		
		pedidos.add(elPedido);
		
		elPedido.setCliente(this);
	}




	//@Id:campo clave.
	//@GeneratedValue:LE DECIMOS QUE ES EL CAMPO PRINCIPAL, PARA QUE RECONOZCA EL ID DEL CLIENTE EN LA TABLA.
	//@Column:PARA MAPEO DE LAS COLUMNAS DE LA TABLA
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellido")
	private String apellido;
	@Column(name="direccion")
	private String direccion;
	
	
	//@OneToOne:PARA MAPEAR RELACIÓN DE 1 A 1 ENTRE LAS 2 CLASES.
	//PARA DEFINIR A TRAVÉS DE Q CAMPO SE RELACIONAN LAS TABLAS.
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id")
	private DetallesCliente detallesCliente;
	
	
	//CAMPO PARA ALMACENAR VARIOS PEDIDOS
	//MAPEA EL CAMPO "cliente" QUE PERTENECE A LA CLASE "Pedidos", ... Y ESPECIFICAMOS LAS CASCADAS.
	@OneToMany(fetch=FetchType.EAGER,mappedBy="cliente",cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private List<Pedido>pedidos;
	
}

