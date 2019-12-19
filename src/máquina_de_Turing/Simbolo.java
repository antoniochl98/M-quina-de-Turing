package m·quina_de_Turing;

/**
 * 
 * @author alu0101038377
 * @descripcion Representa a los s√≠mbolos de la pila
 *
 */
public class Simbolo {
	private String simbolo_;
	private boolean blanco_;
	
	public Simbolo(String simbolo) {
		setSimbolo(simbolo);
	}

	public String getSimbolo() {
		return simbolo_;
	}

	public void setSimbolo(String simbolo_) {
		this.simbolo_ = simbolo_;
	}
	
	public boolean equals(Object other) {
		return simbolo_.equals(((Simbolo) other).getSimbolo()); 
	}
	
	public Object clone() {
		return new Simbolo(simbolo_);
	}

	public boolean isBlanco() {
		return blanco_;
	}

	public void setBlanco(boolean blanco_) {
		this.blanco_ = blanco_;
	}

}
