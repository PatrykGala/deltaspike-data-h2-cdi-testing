package pl.patrykgala.ee.testing.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import pl.patrykgala.ee.testing.jpa.entity.Book;

@Repository
public interface BookRepository extends EntityRepository<Book, Long> {

    List<Book> findByShelve_place(String place);

    List<Book> findByTitle(String title);

    Optional<Book> findByTitleAndId(String title, Long id);

    @Query("SELECT b from Book b where b.title = ?1")
    Optional<Book> findByTitleQuery(String title);
}
