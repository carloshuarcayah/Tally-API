package pe.com.carlosh.tallyapi.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findByActiveTrue(Pageable pageable);
    Page<Category> findByActiveFalse(Pageable pageable);
    Page<Category> findByNameContainingIgnoreCaseAndActiveTrue(String name, Pageable pageable);
    Optional<Category> findByIdAndActiveTrue(Long id);

    boolean existsByNameIgnoreCase(String name);
}
