package domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Home")
public class Home {

	private long id;
	@Column(name = "Taille")
	private double taille;
	@Column(name = "NombreDePieces")
	private long nombre_Piece;
	@Column(name = "Propriétaire")
	private Person person;

	private List<Device> devices;

	public Home(double length, long nombre_Piece, Person pers) {
		this.taille = length;
		this.nombre_Piece = nombre_Piece;
		this.person = pers;
	}
	
	public Home() {
		
	}

	public double gettaille() {
		return taille;
	}

	public void settaille(double taille) {
		this.taille = taille;
	}

	public long getnombre_Piece() {
		return nombre_Piece;
	}

	public void setnombre_Piece(long nombre_Piece) {
		this.nombre_Piece = nombre_Piece;
	}

	@OneToMany(mappedBy = "home", cascade = CascadeType.PERSIST)
	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	@ManyToOne
	@JoinColumn(name = "resident")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
