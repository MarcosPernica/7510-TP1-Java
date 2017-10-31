package ar.uba.fi.tdd.rulogic.model;

import java.util.regex.Pattern;

//Formatea/Normaliza el string dado.
public class Formateador implements ICommand {

	public String accion(String texto) {
		String n = "\\n";
		String t = "\\t";
		String s = " ";
		String p = "\\.";

		texto = texto.replaceAll(n,"");
		texto = texto.replaceAll(t,"");
		texto = texto.replaceAll(s,"");
		texto = texto.replaceAll(p,"");

		return texto;
	}
	
}
