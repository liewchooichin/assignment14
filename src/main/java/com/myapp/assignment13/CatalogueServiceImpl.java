package com.myapp.assignment13;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CatalogueServiceImpl implements CatalogueService {
  // repository
  private CatalogueRepository catalogueRepo;
  // constructor
  public CatalogueServiceImpl(CatalogueRepository catalogueRepo)  {
    this.catalogueRepo = catalogueRepo;
  }
  // find all
  @Override
  public List<Catalogue> findAll(){
    return catalogueRepo.findAll();
  };
  // find by catalogue name
  @Override
  public List<Catalogue> findByCatalogueName(String catalogueName){
    List<Catalogue> results = catalogueRepo
      .findByCatalogueNameContainsIgnoreCase(catalogueName);
    return results;
  }
}
