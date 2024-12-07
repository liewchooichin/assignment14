package com.myapp.assignment13;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
  // private repository
  private CartRepository cartRepo;
  // constructor
  public CartServiceImpl(CartRepository cartRepo){
    this.cartRepo = cartRepo;
  }
  // create a cart
  @Override
  public void save(Cart cart){
    cartRepo.save(cart);
  }
  // list all employees
  @Override
  public List<Cart> findAll(){
    return cartRepo.findAll();
  }
  // list one cart
  @Override
  public Cart findOneCart(Long id){
    // find the cart by id
    Optional<Cart> optionalCart = cartRepo.findById(id);
    // if the result is present
    if(optionalCart.isPresent()){
      Cart foundCart = optionalCart.get();
      return foundCart;
    }
    // else no result is found
    else{
      return null;
    }
  }
}
