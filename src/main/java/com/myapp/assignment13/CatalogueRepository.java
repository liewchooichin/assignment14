package com.myapp.assignment13;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CatalogueRepository extends JpaRepository<Catalogue, Long> {
  List<Catalogue> findByCatalogueNameContainsIgnoreCase(String catalogueName);
}
