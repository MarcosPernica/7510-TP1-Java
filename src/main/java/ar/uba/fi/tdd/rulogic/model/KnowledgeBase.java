package ar.uba.fi.tdd.rulogic.model;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class KnowledgeBase {
	private boolean hayDB;
	private ArrayList<IHecho> arregloHechos;
	private ArrayList<IRegla> arregloReglas;

	private final static Pattern patronValidacion = Pattern.compile("^[0-9a-zA-Z]{1,}\\(([0-9a-zA-Z]{1,}, *)*([0-9a-zA-Z]){1,}\\)$");
	private final static Formateador normalizador = new Formateador();
	
	private boolean validarConsulta(String consulta)
	{
		return patronValidacion.matcher(consulta).matches();
	}
	
	public KnowledgeBase()
	{
		arregloHechos = new ArrayList<IHecho>();
		arregloReglas = new ArrayList<IRegla>();
	}

	private Hecho obtenerHecho(String texto) throws Exception
	{
		return new Hecho(texto);	
	}
	
	private Regla obtenerRegla(String texto) throws Exception
	{
		return new Regla(texto);
	}
	
	public boolean parseDB(String archivo) throws Exception
	{
		Hecho hecho = null;
		Regla regla = null;
		
		String texto;
		
		ClassLoader classLoader = getClass().getClassLoader();
		Scanner scanner = new Scanner (new File(classLoader.getResource(archivo).getFile()));
		scanner.useDelimiter(Pattern.compile("\\."));
		
		while(scanner.hasNext())
		{
			texto = normalizador.accion(scanner.next());
			if(texto == null || texto.isEmpty())
				continue;
			
			try
			{
				hecho = obtenerHecho(texto);
				arregloHechos.add(hecho);
			}
			catch (Exception e)
			{
				try
				{
					regla = obtenerRegla(texto);
					arregloReglas.add(regla);
				}
				catch (Exception b)
				{
					throw new Exception("Error: " + texto + " no es ni regla ni hecho");
				}
			}			
			
		}
		
		hayDB = true;
		return true;		
	}

	public boolean answer(String consulta) throws Exception {
		
		boolean resultado = false;
		String consultaNormalizada = normalizador.accion(consulta);
		
		if(!hayDB)
			throw new Exception("No hay base de datos");
		
		if(!validarConsulta(consultaNormalizada))
			throw new Exception("Error: " + consulta + " no es valida.");
		
		

		for(int i=0;i<arregloReglas.size();i++)
		{
			try
			{
				resultado = arregloReglas.get(i).match(consultaNormalizada, arregloHechos);
				return resultado;
			}
			catch (Exception e)
			{
				continue;
			}
		}
		
		resultado = false;
		
		for(int i=0;i<arregloHechos.size() && !resultado; i++)
			resultado = arregloHechos.get(i).match(consultaNormalizada);		
		
		return resultado;
	}

}
