package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;

public interface IRegla {
	public boolean match(String consulta, ArrayList<IHecho> arregloHechos) throws Exception;
}
