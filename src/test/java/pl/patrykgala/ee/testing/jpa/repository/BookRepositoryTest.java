package pl.patrykgala.ee.testing.jpa.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityTransaction;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.patrykgala.ee.testing.jpa.AbstractEntityManagerTest;
import pl.patrykgala.ee.testing.jpa.entity.Book;
import pl.patrykgala.ee.testing.jpa.entity.Shelve;

@RunWith(CdiTestRunner.class)
public class BookRepositoryTest extends AbstractEntityManagerTest {

    @BeforeClass
    public static void insertData() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<Shelve> data = createData();
        data.forEach(em::persist);
        transaction.commit();
    }

    @Inject
    private BookRepository repo;

    @Test
    public void test_configuration() {
        assertNotNull(repo);
    }

    @Test
    public void findResultTest() {
        List<Book> all = repo.findAll();
        assertNotNull(all);
        assertThat(all).isNotEmpty();
    }

    @Test
    public void findBy_Method() {
        assertNotNull(repo);
        List<Book> all = repo.findByTitle("Harry Potter");
        assertThat(all).isNotEmpty();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void find_ByMethod_InRelation() {
        List<Book> all = repo.findByShelve_place("room");
        assertThat(all).isNotEmpty();
        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    public void find_ByMethod_TwoArguments_Optional() {
        Optional<Book> invalidId = repo.findByTitleAndId("The Lord of the Rings", 1L);
        assertThat(invalidId).isEmpty();
        Optional<Book> expected = repo.findByTitleAndId("The Lord of the Rings", 6L);
        assertThat(expected).isNotEmpty();
    }

    @Test
    public void findBy_StringQuery() {
        Optional<Book> invalidId = repo.findByTitleQuery("The Lord of the Rings");
        assertThat(invalidId).isNotEmpty();

    }

    private static List<Shelve> createData() {
        Book book1 = new Book("Harry Potter");
        Book book2 = new Book("Harry Potter2");
        Book book3 = new Book("Harry Potter3");
        Shelve shelve = new Shelve("room", Arrays.asList(book1, book2, book3));

        Book book4 = new Book("The Lord of the Rings");
        Book book5 = new Book("The Lord of the Rings2");
        Book book6 = new Book("The Lord of the Rings3");
        Shelve shelve1 = new Shelve("bed room", Arrays.asList(book4, book5, book6));
        return Arrays.asList(shelve, shelve1);
    }
}