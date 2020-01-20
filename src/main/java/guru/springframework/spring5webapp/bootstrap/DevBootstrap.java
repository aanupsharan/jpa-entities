package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Publisher publisher1 = new Publisher();
        publisher1.setName("Anup");
        publisher1.setAddress("12454 Mckelvey Road");

        publisherRepository.save(publisher1);

        Publisher publisher2 = new Publisher();
        publisher2.setName("Priya");
        publisher2.setAddress("1600 Dorsett Road");

        publisherRepository.save(publisher2);
        //Eric
        Author eric = new Author("Eric", "Evans");
        Book b1 = new Book("Domain Driven Design", "1234", publisher1);
        eric.getBooks().add(b1);
        b1.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(b1);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book b2 = new Book("J2EE Development without EJB", "23444", publisher2);
        rod.getBooks().add(b2);
        b2.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(b2);
    }
}
