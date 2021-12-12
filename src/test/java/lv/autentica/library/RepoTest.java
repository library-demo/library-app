package lv.autentica.library;

import lv.autentica.library.entities.Author;
import lv.autentica.library.entities.Book;
import lv.autentica.library.enums.Languages;
import lv.autentica.library.repositories.AuthorRepository;
import lv.autentica.library.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.junit.JUnitTestRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RepoTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testAuthor(){
        Author author = new Author();
        author.setFirstName("Jane");
        author.setLastName("Doe");
        testEntityManager.persist(author);

        List<Author> authors = authorRepository.findAll();
        Assertions.assertFalse(authors.isEmpty());
    }

    @Test
    public void testBook(){
        Author author1 = new Author("Allen", "Downey");
        authorRepository.save(author1);
        Author author2 = new Author("Chris", "Mayfield");
        authorRepository.save(author2);

        Book book = new Book();
        book.setTitle("Think Java - How to Think Like a Computer Scientist");
        book.setImage("1492072508s.jpg");
        book.setSummary("Think Java is a hands-on introduction to computer " +
                "science and programming used by many universities and high " +
                "schools around the world. Its conciseness, emphasis on vocabulary, " +
                "and informal tone make it particularly appealing for readers " +
                "with little or no experience. The book starts with the most basic " +
                "programming concepts and gradually works its way to advanced " +
                "object-oriented techniques.");
        book.setISBN("9781492072478");
        book.setLanguage(Languages.ENG);
        book.setYear(2019);
        book.setPageCount(374);

        List<Author> authors = Arrays.asList(author1, author2);
        book.setAuthors(authors);
        bookRepository.save(book);

        List<Book> books = bookRepository.findAll();
        Assertions.assertFalse(books.isEmpty());
    }
}
