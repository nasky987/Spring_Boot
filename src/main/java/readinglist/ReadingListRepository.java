package readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by hreeman on 12/31/16.
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);
}
