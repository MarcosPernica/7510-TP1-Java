package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Regla implements IRegla {
	private String nombreRegla;
	private ArrayList<String> arregloParametros;
	private ArrayList<String> arregloHechos;

	public final static ICommand normalizadorDefecto = new Formateador();	
	public final static Pattern patronHechosDefecto = Pattern.compile("^.*:-(.*)$");
	public final static Pattern patronNombreDefecto = Pattern.compile("^([^\\(]*)\\([^\\)]*\\).*$");
	public final static Pattern patronParametrosDefecto = Pattern.compile("^[^\\(]*\\(([^\\)]+)\\).*$");
	public final static Pattern patronValidacionDefecto = Pattern.compile("^[0-9a-zA-Z]{1,}\\(([0-9a-zA-Z]{1,}, *)*([0-9a-zA-Z]){1,}\\) *:- *([0-9a-zA-Z]{1,}\\(([0-9a-zA-Z]{1,},*)*([0-9a-zA-Z]){1,}\\), *)*([0-9a-zA-Z]{1,}\\(([0-9a-zA-Z]{1,}, *)*([0-9a-zA-Z]){1,}\\) *)");


	private Pattern patronHechos;
	private Pattern patronNombre;
	private Pattern patronParametros;
	private Pattern patronValidacion;
	private ICommand normalizador;

	private boolean validarRegla(String regla)
	{
		return patronValidacion.matcher(regla).matches();
	}
	
	private String obtenerNombreRegla(String reglaNormalizada)
	{
		Matcher m = patronNombre.matcher(reglaNormalizada);
		m.find();
		return m.group(1);
	}
	
	private ArrayList<String> obtenerParametrosRegla(String reglaNormalizada)
	{
		ArrayList<String> arreglo = new ArrayList<String>();

		Matcher m = patronParametros.matcher(reglaNormalizada);

		m.find();

		String[] partes = m.group(1).split(",");
		
		for(int i=0;i<partes.length;i++)
			arreglo.add(partes[i]);
		
		return arreglo;
	}

	private void constructorRegla(String regla) throws Exception
	{
		
		String reglaNormalizada = normalizador.accion(regla);
		
		if(!validarRegla(reglaNormalizada))
			throw new Exception("No es un hecho "+reglaNormalizada);
		
		//Extrae los elementos de la regla para optimizar el acceso.
		arregloParametros = obtenerParametrosRegla(reglaNormalizada);

		nombreRegla = obtenerNombreRegla(reglaNormalizada);

		
		//Extrae los hechos atomicos.
		arregloHechos = new ArrayList<String>();
		Matcher m = patronHechos.matcher(reglaNormalizada);
		m.find();
		String[] partes = m.group(1).replaceAll("\\),", "\\) ").split(" ");
		
		for(int i=0;i<partes.length;i++)
			arregloHechos.add(partes[i]);

	}

	public Regla(String regla) throws Exception
	{
		patronHechos = patronHechosDefecto;
		patronNombre = patronNombreDefecto;
		patronParametros = patronParametrosDefecto;
		patronValidacion = patronValidacionDefecto;
		normalizador = normalizadorDefecto;

		constructorRegla(regla);
	}

	public Regla(String regla, ICommand normalizadorExterno, Pattern patronHechosExterno, Pattern patronNombreExterno, Pattern patronParametrosExterno, Pattern patronValidacionExterno) throws Exception
	{
		patronHechos = patronHechosExterno;
		patronNombre = patronNombreExterno;
		patronParametros = patronParametrosExterno;
		patronValidacion = patronValidacionExterno;
		normalizador = normalizadorExterno;

		constructorRegla(regla);
	}
	
	private boolean obtenerVerdadHecho(String hechoNormalizado, ArrayList<IHecho> arregloHechos)
	{
		boolean res = false;
		
		Iterator<IHecho> i = arregloHechos.iterator();
		
		while(i.hasNext() && res == false)
			res |= i.next().match(hechoNormalizado);
		
		return res;
	}
	
	private boolean validarEquivalenciaReglaConsulta(String consulta)
	{
		ArrayList<String> parametrosConsulta = obtenerParametrosRegla(consulta);
		String nombreConsulta = obtenerNombreRegla(consulta);
		
		return nombreConsulta.equals(nombreRegla) && parametrosConsulta.size() == arregloParametros.size();		
	}
	
	public boolean match(String consultaNormalizada, ArrayList<IHecho> arregloHechos) throws Exception
	{
		if(!validarEquivalenciaReglaConsulta(consultaNormalizada))
			throw new Exception("La consulta no es aplicable");
		
		//Reemplaza los parametros por sus valores.
		ArrayList<String> parametrosConsulta = obtenerParametrosRegla(consultaNormalizada);
		ArrayList<String> arregloHechosAux = (ArrayList<String>) this.arregloHechos.clone();
		
		for(int i=0;i<parametrosConsulta.size();i++)
			for(int a=0;a<arregloHechosAux.size();a++)
				arregloHechosAux.set(a, arregloHechosAux.get(a).replaceAll(arregloParametros.get(i), parametrosConsulta.get(i)));

		boolean verdad = true;
		for(int i=0;i<arregloHechosAux.size() && verdad;i++)
			verdad = verdad && obtenerVerdadHecho(arregloHechosAux.get(i), arregloHechos);

		return verdad;
	
	}

}
