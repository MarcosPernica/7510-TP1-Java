package ar.uba.fi.tdd.rulogic.model;

import java.util.regex.Pattern;

public class Hecho implements IHecho{
	
	public final static Pattern patronDefecto = Pattern.compile("^[0-9a-zA-Z]{1,}\\(([0-9a-zA-Z]{1,}, *)*([0-9a-zA-Z]){1,}\\)$");
	public final static ICommand normalizadorDefecto = new Formateador();
	
	private String hecho;
	private Pattern patron;
	private ICommand normalizador;

	private boolean validarHecho(String texto)
	{
		return patron.matcher(texto).matches();
	}

	private void constructorHecho(String texto) throws Exception
	{
		hecho = normalizador.accion(texto);
		
		if(!validarHecho(hecho))
			throw new Exception("No es un hecho "+hecho);
	}
	
	public Hecho(String texto) throws Exception
	{
		patron = patronDefecto;
		normalizador = normalizadorDefecto;

		constructorHecho(texto);
	}

	public Hecho(String texto, Pattern patronValidacion, ICommand normalizadorExterno) throws Exception
	{
		patron = patronValidacion;
		normalizador = normalizadorExterno;

		constructorHecho(texto);
	}

	
	public boolean match(String consultaNormalizada)
	{
		return hecho.equals(consultaNormalizada);
	}

}
