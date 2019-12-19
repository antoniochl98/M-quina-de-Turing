package máquina_de_Turing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		try {
			Maquina mt= new Maquina(args[0]);
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader (isr);
			String cadena = br.readLine();
			if(mt.Start(cadena,false))
				System.out.println("Cadena: \""+cadena+"\" aceptada");
			else
				System.out.println("Cadena: \""+cadena+"\" NO aceptada");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
