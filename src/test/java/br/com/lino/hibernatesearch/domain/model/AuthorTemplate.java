package br.com.lino.hibernatesearch.domain.model;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;


public class AuthorTemplate {

	
	public static void load(){

		Fixture.of(Author.class).addTemplate("valid", new Rule(){{
			add("name", "sabidão");
		}});
		
		Fixture.of(Book.class).addTemplate("valid", new Rule(){{
			add("title", "adfasdfsdaf");
			add("subtitle", lastName());
			add("authors", has(1).of(Author.class, "valid"));
		}});
		
	}

}