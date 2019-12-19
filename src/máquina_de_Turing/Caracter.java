package m·quina_de_Turing;


/**
 * 
 * @author alu0101038377
 * @descripcion Representa a los s√≠mbolos de la cinta
 *
 */
public class Caracter {
	private String caracter_;
	
	public Caracter(String caracter) {
		setSimbolo(caracter);
	}

	public String getSimbolo() {
		return caracter_;
	}

	public void setSimbolo(String caracter) {
		this.caracter_ = caracter;
	}
	
	public boolean equals(Object other) {
		return caracter_.equals(((Caracter) other).getSimbolo()); 
	}
	
	public Object clone() {
		return new Caracter(caracter_);
	}
}
