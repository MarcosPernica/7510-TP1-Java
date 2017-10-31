package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;



import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class KnowledgeBaseAcceptanceTest {

	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		knowledgeBase = new KnowledgeBase();
		knowledgeBase.parseDB("gente.db");
	}

	@Test
	public void test_varon_juan_true(){
		
		try
		{
			Assert.assertTrue(this.knowledgeBase.answer("varon (juan)."));
		}
		catch (Exception e)
		{

		}
	}

	@Test
	public void test_varon_maria_false(){
		
		try
		{
			Assert.assertFalse(this.knowledgeBase.answer("varon (maria)."));
		}
		catch (Exception e)
		{

		}
	}

	@Test
	public void test_mujer_cecilia_true(){
		
		try
		{
			Assert.assertTrue(this.knowledgeBase.answer("mujer (cecilia)."));
		}
		catch (Exception e)
		{

		}
	}

	@Test
	public void test_padre_juan_pepe_true(){
		
		try
		{
			Assert.assertTrue(this.knowledgeBase.answer("padre (juan,pepe)."));
		}
		catch (Exception e)
		{

		}
	}

	@Test
	public void test_padre_mario_pepe_false(){
		
		try
		{
			Assert.assertFalse(this.knowledgeBase.answer("padre (mario,pepe)."));
		}
		catch (Exception e)
		{

		}
	}

	@Test
	public void test_hijo_pepe_juan_true(){
		
		try
		{
			Assert.assertTrue(this.knowledgeBase.answer("hijo (pepe,juan)."));
		}
		catch (Exception e)
		{

		}
	}

	@Test
	public void test_hija_maria_roberto_false(){
		
		try
		{
			Assert.assertFalse(this.knowledgeBase.answer("hija (maria,roberto)."));
		}
		catch (Exception e)
		{

		}
	}

}
