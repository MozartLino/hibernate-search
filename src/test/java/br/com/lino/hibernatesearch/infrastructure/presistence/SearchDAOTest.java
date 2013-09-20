package br.com.lino.hibernatesearch.infrastructure.presistence;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.lino.hibernatesearch.domain.model.AuthorTemplate;
import br.com.lino.hibernatesearch.domain.model.Book;
import br.com.lino.hibernatesearch.infrastructure.persistence.SearchDAO;
import br.com.lino.hibernatesearch.infrastructure.util.HibernateUtil;
import br.com.six2six.fixturefactory.Fixture;

public class SearchDAOTest {

	private Session session;

	@Test
	public void deveRetornarTodosOsLivros() {
		List<Book> list = new SearchDAO<Book>(session).buscaByName();

		assertEquals("Deveria ter retornardo 2 livros", 4, list.size());
	}

	@Before
	public void setUp() {
		session = HibernateUtil.currentSession();
		HibernateUtil.beginTransaction();

		AuthorTemplate.load();
		
		Book book = Fixture.from(Book.class).gimme("valid");
		
		session.save(book);
	}

	@After
	public void tearDown() {
		HibernateUtil.commitTransaction();
		HibernateUtil.closeSession();
	}

}
