package com.myapp.assignment13;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DataLoader {
  // autowired repositories
  private CatalogueRepository catalogueRepo;
  private CartRepository cartRepo;
  // autowired in AllArgsConstructor
  // Catalogue
  @PostConstruct
  public void load(){
  catalogueRepo.deleteAll();
  Catalogue catalogue1 = new Catalogue();
  Catalogue catalogue2 = new Catalogue();
  Catalogue catalogue3 = new Catalogue();
  // catalogue 1
  catalogue1.setCatalogueId(1L);
  catalogue1.setCatalogueName("Super useful cutting set");
  catalogue1.setCataloguePrice(45.00);
  catalogue1.setCatalogueShortDesc("A collection of useful vegetables cutting tools.");
  // catalogue 2
  catalogue2.setCatalogueId(2L);
  catalogue2.setCatalogueName("Multi-purpose cooking oven");
  catalogue2.setCataloguePrice(140.00);
  catalogue2.setCatalogueShortDesc("Oven for baking, steaming, microwaving and grilling.");
  // catalogue 3
  catalogue3.setCatalogueId(3L);
  catalogue3.setCatalogueName("Shape-changing container set");
  catalogue3.setCataloguePrice(85.00);
  catalogue3.setCatalogueShortDesc("Mix and match your containers to fit your food content.");
  // Cart
  cartRepo.deleteAll();
  Cart cart1 = new Cart();
  Cart cart2 = new Cart();
  Cart cart3 = new Cart();
  // cart 1
  cart1.setCartId(1L);
  cart1.setCartQuantity(100);
  // cart 2
  cart2.setCartId(2L);
  cart2.setCartQuantity(170);
  // cart 3
  cart3.setCartId(3L);
  cart3.setCartQuantity(150);
  // relate the one-to-one mapping
  // set the owner cart first
  cart1.setMappedByCatalogue(catalogue1);
  cart2.setMappedByCatalogue(catalogue2);
  cart3.setMappedByCatalogue(catalogue3);
  // then, set the mapped by catalogue
  catalogue1.setOwnedByCart(cart1);
  catalogue2.setOwnedByCart(cart2);
  catalogue3.setOwnedByCart(cart3);
  // Save to repo
  // save to cart repo
  cartRepo.save(cart1);
  cartRepo.save(cart2);
  cartRepo.save(cart3);
  // save to catalogue repo
  catalogueRepo.save(catalogue1);
  catalogueRepo.save(catalogue2);
  catalogueRepo.save(catalogue3);
}
}
