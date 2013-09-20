package br.com.lino.hibernatesearch.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;

@Entity
@Indexed
@Analyzer(impl = BrazilianAnalyzer.class)
public class Book {

	@Id
	@GeneratedValue
	private Integer id;

	@Field
	private String title;

	@Field
	private String subtitle;

	@DateBridge(resolution = Resolution.DAY)
	private Date publicationDate;

	@IndexedEmbedded
	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade(value = { CascadeType.ALL })
	private List<Author> authors = new ArrayList<>();

	protected Book() {
	};

	public Book(String title, String subtitle) {
		this.title = title;
		this.subtitle = subtitle;
	}

	public Integer getId() {
		return id;
	}

	public boolean teste() {
		assert "teste" == "teste";

		return false;
	}

	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", subtitle=" + subtitle + ", publicationDate=" + publicationDate + "]";
	}

}
