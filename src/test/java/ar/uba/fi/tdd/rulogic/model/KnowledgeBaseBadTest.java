package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;



import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class KnowledgeBaseBadTest {

	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		knowledgeBase = new KnowledgeBase();
	}

	

	@Test(expected = Exception.class)
	public void test_cargar_base_excepcion() throws Exception{
		
		knowledgeBase.parseDB("genteMal.db");
	}

	@Test(expected = Exception.class)
	public void test_varon_juan_excepcion() throws Exception{
		this.knowledgeBase.answer("varon(juan).");
	}

	@Test(expected = Exception.class)
	public void test_varon_maria_excepcion() throws Exception{
		this.knowledgeBase.answer("varon(maria).");
	}

	@Test(expected = Exception.class)
	public void test_mujer_cecilia_excepcion() throws Exception{
		this.knowledgeBase.answer("mujer(cecilia).");
	}

	@Test(expected = Exception.class)
	public void test_padre_juan_pepe_excepcion() throws Exception{
		this.knowledgeBase.answer("padre(juan,pepe).");
	}

	@Test(expected = Exception.class)
	public void test_padre_mario_pepe_excepcion() throws Exception{
		this.knowledgeBase.answer("padre(mario,pepe).");
	}

	@Test(expected = Exception.class)
	public void test_hijo_pepe_juan_excepcion() throws Exception{
		this.knowledgeBase.answer("hijo(pepe,juan).");
	}

	@Test(expected = Exception.class)
	public void test_hija_maria_roberto_excepcion() throws Exception{
		this.knowledgeBase.answer("hija(maria,roberto).");
	}

}
