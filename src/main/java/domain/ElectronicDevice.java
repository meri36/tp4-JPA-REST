package domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("ElectronicDevice")
@Table(name = "ElectronicDevice")
public class ElectronicDevice extends Device {

	@Column(name = "Consommation")
	private double consommation;

	public double getConsommation() {
		return consommation;
	}

	public void setConsommation(double consommation) {
		this.consommation = consommation;
	}
}
