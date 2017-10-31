package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;



import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class KnowledgeBaseAcceptanceMathTest {

	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		knowledgeBase = new KnowledgeBase();
		knowledgeBase.parseDB("numeros.db");
	}

	@Test
	public void test_add_zero_zero_zero_true(){
		
		try
		{
			Assert.assertTrue(this.knowledgeBase.answer("add (zero,zero,zero)."));
		}
		catch (Exception e)
		{

		}
	}

	@Test
	public void test_add_zero_two_two_true(){
		
		try
		{
			Assert.assertTrue(this.knowledgeBase.answer("add (zero,two,two)."));
		}
		catch (Exception e)
		{

		}
	}

	@Test
	public void test_add_zero_zero_four_false(){
		
		try
		{
			Assert.assertFalse(this.knowledgeBase.answer("add (zero,zero,four)."));
		}
		catch (Exception e)
		{

		}
	}

	@Test
	public void test_substract_zero_zero_zero_true(){
		
		try
		{
			Assert.assertTrue(this.knowledgeBase.answer("substract (zero,zero,zero)."));
		}
		catch (Exception e)
		{

		}
	}

	@Test
	public void test_substract_zero_five_zero_false(){
		
		try
		{
			Assert.assertFalse(this.knowledgeBase.answer("substract (zero,five,zero)."));
		}
		catch (Exception e)
		{

		}
	}

	@Test
	public void test_substract_zero_one_two_false(){
		
		try
		{
			Assert.assertTrue(this.knowledgeBase.answer("substract (zero,one,two)."));
		}
		catch (Exception e)
		{

		}
	}

	

}
