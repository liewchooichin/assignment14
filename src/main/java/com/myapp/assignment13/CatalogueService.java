package com.myapp.assignment13;

import java.util.List;

public interface CatalogueService {
  List<Catalogue> findAll();
  List<Catalogue> findByCatalogueName(String catalogueName);
}
