package máquina_de_Turing;

import java.util.ArrayList;

public class Transicion {
	
	Simbolo s_leido_;
	Estado siguiente_;
	Simbolo s_escrito_;
	Movement m_;
	
	public Transicion(Simbolo s_leido, Estado siguiente, Simbolo s_escrito,Movement m) {
		if(s_leido==null || siguiente==null || s_escrito==null) {
			throw new NullPointerException("Arguments can´t be null");
		}
		s_leido_=s_leido;
		siguiente_=siguiente;
		s_escrito_=s_escrito;
		m_=m;
	}
	
	public void set_s_leido(Simbolo s) {
		if(s==null) {
			throw new NullPointerException("Arguments can´t be null");
		}
		s_leido_=s;
	}
	
	public void set_s_escrito(Simbolo p) {
		if(p==null) {
			throw new NullPointerException("Arguments can´t be null");
		}
		s_escrito_=p;
	}
	
	public ArrayList<Object> Transitar(ArrayList<Simbolo> s, int pos, Estado estado_actual){
		if(!s_leido_.equals(s.get(pos)))
			throw new IllegalArgumentException("EL simbolo pasado como parámetro no es el esperado");
		s.set(pos,s_escrito_);
		if(m_==Movement.L)
			pos--;
		else if(m_==Movement.R)
			pos++;
		ArrayList<Object> sol=new ArrayList<Object>();
		sol.add(pos);
		sol.add(siguiente_);
		sol.add(s);
		return sol;
	}
	
	/*public ArrayList<Object> Transitar(Simbolo s){
		if(!s_leido_.equals(s) && !s_leido_.equals(S_BLANCO))
			throw new IllegalArgumentException("EL simbolo pasado como parámetro no es el esperado");
		
		ArrayList<Object> sol=new ArrayList<Object>();
		sol.add(siguiente_);
		ArrayList<Simbolo> simbolos= (ArrayList<Simbolo>) s_escrito_.clone();
		if(s_leido_.equals(S_BLANCO)) {
			simbolos.add(s);
		}
		sol.add(simbolos);
		return sol;
	}*/

}
