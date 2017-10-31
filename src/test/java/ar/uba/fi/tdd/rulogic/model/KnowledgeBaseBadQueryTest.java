package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;



import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class KnowledgeBaseBadQueryTest {

	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		knowledgeBase = new KnowledgeBase();
		knowledgeBase.parseDB("gente.db");
	}

	

	@Test(expected = Exception.class)
	public void test_bad_query_1() throws Exception{
		knowledgeBase.answer("varon()");
	}

	@Test(expected = Exception.class)
	public void test_bad_query_2() throws Exception{
		knowledgeBase.answer("varon(X,)");
	}

}
