package máquina_de_Turing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

public class Maquina {
	
	Hashtable<String,Caracter> alfabeto_;
	Hashtable<String,Simbolo> simbolos_;
	Simbolo Simbolo_blanco_;
	Estado estado_actual_;
	Hashtable<String,Estado> estados_;

	public Maquina(String fichero) throws IOException {
		LeeMaquina(fichero);
	}

	private void LeeMaquina(String fichero) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fichero));
		String str = br.readLine();
		estados_=new Hashtable<String,Estado>();
		while(estados_.isEmpty()) {
			if(str.indexOf('#')!=-1)
				str= str.substring(0, str.indexOf('#'));
			str=str.replaceAll("\\s+", " ");
			if(!str.equals(" ") && !str.equals("")) {
				String[] ests=str.split(" ");
				for(int i=0;i<ests.length;i++) {
					estados_.put(ests[i], new Estado(ests[i]));
				}
			}
			str=br.readLine();
		}

		alfabeto_=new Hashtable<String, Caracter>();
		String[] alf=str.split("\\s+");
		for (int i=0;i<alf.length;i++) {
			alfabeto_.put(alf[i], new Caracter(alf[i]));
		}
		
		simbolos_=new Hashtable<String, Simbolo>();
		String[] sim=br.readLine().split("\\s+");
		for (int i=0;i<sim.length;i++) {
			simbolos_.put(sim[i],new Simbolo(sim[i]));
		}
		
		String est_ini=br.readLine();
		estado_actual_=estados_.get(est_ini);
		if(estado_actual_==null)
			throw new IOException("Estado inicial no definido");
		estados_.get(est_ini).setInicial(true);
		
		
		String simb=br.readLine();
		Simbolo_blanco_=simbolos_.get(simb);
		if(Simbolo_blanco_==null)
			throw new IOException("Simbolo blanco no definido");
		simbolos_.get(simb).setBlanco(true);
		
		String[] est_acp=br.readLine().split("\\s+");
		for (int i=0;i<est_acp.length;i++) {
			if(estados_.get(est_acp[i])==null) {
				throw new IOException("Estado de aceptación no definido");
			}
			estados_.get(est_acp[i]).setAcept(true);
		}
		
		
		while((str=br.readLine()) !=null) {
			String[] trans=str.split("\\s+");
			
			Simbolo s_leido= simbolos_.get(trans[1]);
			Estado siguiente= estados_.get(trans[2]);
			Simbolo s_escrito= simbolos_.get(trans[3]);
			
			if(s_leido==null || siguiente==null || s_escrito==null)
				throw new IOException("Error en la definición de las transisciones");
			
			estados_.get(trans[0]).addTransiciones(new Transicion(s_leido, siguiente, s_escrito, Movement.valueOf(trans[4])));
		}
		
	}
	
	public boolean Start(String str,boolean verbose) {
		
		ArrayList<Simbolo> cadena=new ArrayList<Simbolo>();
		
		for(int i=0;i <str.length();i++) {
			if(alfabeto_.get(str.substring(i, i+1))!=null)
				cadena.add(simbolos_.get(str.substring(i, i+1)));
			else
				throw new IllegalArgumentException("El elemento: \""+str.substring(i, i+1)+"\" no pertenece al alfabeto");
		}
		if(str.length()==0) {
			cadena.add(Simbolo_blanco_);
		}
		
		int n_pos=0;
		int pos;
		do{
			if(n_pos==-1) {
				cadena.add(0, Simbolo_blanco_);
				n_pos=0;
			}
			else if(n_pos==cadena.size()) {
				cadena.add(Simbolo_blanco_);
			}
			if(verbose) {
				for(int i=0;i<cadena.size();i++) {
					if(i==n_pos) {
						System.out.print("q");
					}
					System.out.print(cadena.get(i).getSimbolo());
				}
				System.out.println();
			}
			pos=n_pos;
			n_pos=transita(cadena,pos,verbose);
			
		}while(pos!=n_pos);
		
		for(int i=n_pos;i<cadena.size();i++) {
			
			System.out.print(cadena.get(i).getSimbolo());
		}
		System.out.println();
		
		return estado_actual_.isAcept();
	}
	
	public int transita(ArrayList<Simbolo> cinta, int pos,boolean verbose) {
		int n_pos=pos;
		for(Transicion tr:estado_actual_.getTransiciones()) {
			if(tr.s_leido_==cinta.get(pos)) {
				if(verbose) {
					System.out.print("Transición:"+estado_actual_.getNombre()+", "+cinta.get(pos).getSimbolo());
				}
				ArrayList<Object> sol=tr.Transitar(cinta, pos, estado_actual_);
				n_pos=(int) sol.get(0);
				estado_actual_=(Estado) sol.get(1);
				cinta=(ArrayList<Simbolo>) sol.get(2);
				if(verbose) {
	 				System.out.println(", "+ estado_actual_.getNombre()+", "+ cinta.get(pos).getSimbolo()+", "+ tr.m_.name());
					
					InputStreamReader isr = new InputStreamReader(System.in);
					BufferedReader br = new BufferedReader (isr);
					try {
						String c = br.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			}
		}
		return n_pos;
	}
}
