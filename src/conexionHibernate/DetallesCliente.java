package conexionHibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//HIBERNATE MAPEARÁ LAS PROPIEDADES DE EST CLASE Y PARA ESTO SE USARÁN LAS ANOTACIONES.

@Entity //PARA MAPEO DE CLASE A TABLA, HIBERNATE TRANSFORMA LAS CLASES EN ENTIDADES, PARA MAPEAR.
@Table(name="detalles_cliente") //REFERENCIA A LA TABLA A LA QUE NOS REFERIMOS.
public class DetallesCliente {

	
	public DetallesCliente(String web, String tfno, String comentarios) {
		this.web = web;
		this.tfno = tfno;
		this.comentarios = comentarios;
	}
	
	public DetallesCliente() {
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getTfno() {
		return tfno;
	}
	public void setTfno(String tfno) {
		this.tfno = tfno;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public Cliente getElCliente() {
		return elCliente;
	}
	public void setElCliente(Cliente elCliente) {
		this.elCliente = elCliente;
	}

	
	

	
	
	@Override
	public String toString() {
		return "DetallesCliente [id=" + id + ", web=" + web + ", tfno=" + tfno + ", comentarios=" + comentarios + "]";
	}






	//@Id:campo clave.
	//@GeneratedValue:LE DECIMOS QUE ES EL CAMPO PRINCIPAL, PARA QUE RECONOZCA EL ID DEL CLIENTE EN LA TABLA.
	//@Column:PARA MAPEO DE LAS COLUMNAS DE LA TABLA
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="web")
	private String web;
	@Column(name="tfno")
	private String tfno;
	@Column(name="comentarios")
	private String comentarios;
	
	//PARA CREAR RELACIÓN CON DIRECCION DESDE DETALLESCLIENTE A CLIENTE
	@OneToOne(mappedBy="detallesCliente")  //,cascade=CascadeType.ALL)
	private Cliente elCliente;
	
}

