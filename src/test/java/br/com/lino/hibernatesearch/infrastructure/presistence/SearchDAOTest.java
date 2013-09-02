package br.com.lino.hibernatesearch.infrastructure.presistence;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.lino.hibernatesearch.domain.model.Author;
import br.com.lino.hibernatesearch.domain.model.Book;
import br.com.lino.hibernatesearch.infrastructure.persistence.SearchDAO;
import br.com.lino.hibernatesearch.infrastructure.util.HibernateUtil;

public class SearchDAOTest {

	private Session session;

	@Test
	public void deveRetornarTodosOsLivros() {
		List<Book> list = new SearchDAO<Book>(session).list();

		assertEquals("Deveria ter retornardo 2 livros", 2, list.size());
	}

	@Before
	public void setUp() {
		session = HibernateUtil.currentSession();
		HibernateUtil.beginTransaction();

		Author author = new Author("title");
		session.save(author);

		Book book = new Book("title", "title");
		book.setAuthors(Arrays.asList(author));

		Book book2 = new Book("title", "title");
		book2.setAuthors(Arrays.asList(author));

		session.save(book);
		session.save(book2);
	}

	@After
	public void tearDown() {
		HibernateUtil.rollbackTransaction();
		HibernateUtil.closeSession();
	}

}
