package pe.com.carlosh.tallyapi.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findByActiveTrue(Pageable pageable);
    Page<Category> findByActiveFalse(Pageable pageable);
    Page<Category> findByNameContainingIgnoreCaseAndActiveTrue(String name, Pageable pageable);
}
