package br.com.lino.hibernatesearch.infrastructure.persistence;

import java.util.List;

import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import br.com.lino.hibernatesearch.domain.model.Book;

public class SearchDAO<T> {

	private Session session;

	public SearchDAO(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public List<T> buscaByName() {
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		
		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();
		Query query = qb.keyword()
				.fuzzy()
				.onFields("title", "subtitle", "authors.name")
				.matching("sabid")
				.createQuery();

		FullTextQuery createFullTextQuery = fullTextSession.createFullTextQuery(query, Book.class);

		return createFullTextQuery.list();
	}
}