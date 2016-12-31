package readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hreeman on 12/31/16.
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {
}
