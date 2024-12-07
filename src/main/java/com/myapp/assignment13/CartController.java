package com.myapp.assignment13;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value="/cart")
public class CartController {
  // autowired fields
  private CartService cartService;
  private CatalogueService catalogueService;
  // constructor
  public CartController(
    CartService cartService, CatalogueService catalogueService
  ){
    this.cartService = cartService;
    this.catalogueService = catalogueService;
  }
  // POST: save a new cart
  @PostMapping("/save")
  public ResponseEntity<?> saveCart(@RequestBody CartModel cartModel) {
      Map<String, Object> response = new LinkedHashMap<>();
      // create the catalogue object
      Catalogue catalogue = new Catalogue();
      catalogue.setCatalogueId(cartModel.getCartModelId());
      catalogue.setCatalogueName(cartModel.getCartModelName());
      catalogue.setCataloguePrice(cartModel.getCartModelPrice());
      catalogue.setCatalogueShortDesc(cartModel.getCartModelShortDesc());
      // create the cart object
      Cart cart = new Cart();
      cart.setCartId(cartModel.getCartModelId());
      cart.setCartQuantity(cartModel.getCartModelQuantity());
      cart.setMappedByCatalogue(catalogue); // set the foreign element
      // save the new cart to the repo
      cartService.save(cart);
      // prepare the response
      response.put("status", "Successful");
      response.put("message", "A new cart is created successfully.");
      return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
  // GET: find all carts
  @GetMapping("/all")
  public ResponseEntity<?> findAllCarts(){
    Map<String, Object> response = new LinkedHashMap<>();
    List<Cart> cartList = cartService.findAll();
    List<CartModel> cartModelList = new ArrayList<>();
    // iterate the results
    if(!cartList.isEmpty()){
      // iterate the cart list one by one to put into
      // the cart model.
      for(Cart cart: cartList){
        CartModel cartModel = new CartModel();
        cartModel.setCartModelId(cart.getCartId());
        cartModel.setCartModelName(cart.getMappedByCatalogue().getCatalogueName());
        cartModel.setCartModelPrice(cart.getMappedByCatalogue().getCataloguePrice());
        cartModel.setCartModelShortDesc(cart.getMappedByCatalogue().getCatalogueShortDesc());
        cartModel.setCartModelQuantity(cart.getCartQuantity());
        // push this element to the CartModel list.
        // this list will be in the response
        cartModelList.add(cartModel);
      }
      // prepare the response
      response.put("status", "Successful");
      response.put("data", cartModelList);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // results are empty
    else {
      response.clear();
      response.put("status", "Failed");
      response.put("message", "No data are found.");
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
  }
  // GET: get one cart by id
  @GetMapping("/{id}")
  public ResponseEntity<?> findOneCart(@PathVariable Long id){
    Map<String, Object> response = new LinkedHashMap<>();
    Cart cart = cartService.findOneCart(id);
    // if a cart is found
    if(cart!=null){
      CartModel cartModel = new CartModel();
      cartModel.setCartModelId(cart.getCartId());
      cartModel.setCartModelName(cart.getMappedByCatalogue().getCatalogueName());
      cartModel.setCartModelPrice(cart.getMappedByCatalogue().getCataloguePrice());
      cartModel.setCartModelShortDesc(cart.getMappedByCatalogue().getCatalogueShortDesc());
      cartModel.setCartModelQuantity(cart.getCartQuantity());
      // prepare the response
      response.put("status", "Successful");
      response.put("data", cartModel);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // no cart is found
    else {
      response.clear();
      response.put("status", "Failed");
      response.put("message", "No data are found.");
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }    
  }
 
  // GET: find by catalogue name
  @GetMapping("/search")
  public ResponseEntity<?> findByCatalogueName(@RequestParam String catalogueName){
    Map<String, Object> response = new LinkedHashMap<>();
    List<Catalogue> catalogueList = catalogueService.findByCatalogueName(catalogueName);
    // List the cart model
    List<CartModel> cartModelList = new ArrayList<>();
    // iterate the results
    if(!catalogueList.isEmpty()){
      // iterate the cart list one by one to put into
      // the cart model.
      for(Catalogue catalogue: catalogueList){
        CartModel cartModel = new CartModel();
        cartModel.setCartModelId(catalogue.getCatalogueId());
        cartModel.setCartModelName(catalogue.getCatalogueName());
        cartModel.setCartModelPrice(catalogue.getCataloguePrice());
        cartModel.setCartModelShortDesc(catalogue.getCatalogueShortDesc());
        cartModel.setCartModelQuantity(catalogue.getOwnedByCart().getCartQuantity());
        // push this element to the CartModel list.
        // this list will be in the response
        cartModelList.add(cartModel);
      }
      // prepare the response
      response.put("status", "Successful");
      response.put("data", cartModelList);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // results are empty
    else {
      response.clear();
      response.put("status", "Failed");
      response.put("message", "No data are found.");
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

  }
}
