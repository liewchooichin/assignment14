package com.myapp.assignment13;

import java.util.List;

public interface CartService {
  void save(Cart cart);
  List<Cart> findAll();
  Cart findOneCart(Long id);
}
