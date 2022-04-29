package sit.int221.oasipbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.oasipbackend.entities.Eventcategory;

public interface EventcategoryRepository extends JpaRepository<Eventcategory, String> {
}