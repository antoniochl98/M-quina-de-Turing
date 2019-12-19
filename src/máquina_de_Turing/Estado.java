package máquina_de_Turing;

import java.util.ArrayList;

public class Estado {

	private String nombre_;
	private boolean acept_;
	private boolean inicial_;
	private ArrayList<Transicion> transiciones_;
	
	public Estado(String nombre) {
		setNombre(nombre);
		setAcept(false);
		setInicial(false);
		transiciones_=new ArrayList<Transicion>();
	}

	public Estado() {
		transiciones_=new ArrayList<Transicion>();
	}

	public String getNombre() {
		return nombre_;
	}

	public void setNombre(String nombre_) {
		this.nombre_ = nombre_;
	}

	public boolean isAcept() {
		return acept_;
	}

	public void setAcept(boolean acept_) {
		this.acept_ = acept_;
	}

	public boolean isInicial() {
		return inicial_;
	}

	public void setInicial(boolean inicial_) {
		this.inicial_ = inicial_;
	}
	
	public Object clone() {
		Estado n_estado=new Estado(nombre_);
		n_estado.setAcept(acept_);
		n_estado.setInicial(inicial_);
		return n_estado;
	}

	public ArrayList<Transicion> getTransiciones() {
		return transiciones_;
	}

	public void addTransiciones(Transicion transicion) {
		transiciones_.add(transicion);
	}
		
}
