package com.myapp.assignment13;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@WebMvcTest(EmployeeController.class)
@SpringBootTest
@AutoConfigureMockMvc 
@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

  // Autowired fields
  @Autowired
  private MockMvc mockie;

  // object mapper to create json string
  @Autowired
  private ObjectMapper objectMapper;

  // mock a repository
  // then inject the mock repository into the service

  // GET one cart - OK
  @DisplayName("Get one cart - OK")
  @Test
  public void testGetOneCart_Success_Test() throws Exception {
    // create a sample

    // 1. get the request uri
    // IMPORTANT: The problem is with the generated id. Need to check the generated id before
    // running the test.
    // Check the value against something permanent, i.e. something that we set like name.
    RequestBuilder request = MockMvcRequestBuilders.get("/cart/2");
    // 2. perform the request and get response
    mockie.perform(request)
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.status").value("Successful"))
      .andExpect(jsonPath("$.data.cartModelName"). hasJsonPath())
      .andExpect(jsonPath("$.data.cartModelName")
        .value("Multi-purpose cooking oven"));     
  }

    // GET one cart - Fail
    @DisplayName("Get one cart - Failed")
    @Test
    public void getOneCart_Failed_Test() throws Exception {
      // create a sample
  
      // 1. get the request uri
      RequestBuilder request = MockMvcRequestBuilders.get("/cart/99");
      // 2. perform the request and get response
      mockie.perform(request)
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.status").value("Failed"));
    }

  // Get all carts
  @DisplayName("Get all carts")
  @Test
  public void getAllCartsTest() throws Exception {
    // Build the request
    RequestBuilder request = MockMvcRequestBuilders.get("/cart/all");
    // perform the request
    mockie.perform(request)
      .andDo(print())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.status").value("Successful"));
  }
  
  // Create a cart
  @DisplayName("Creata a cart")
  @Test
  public void createCartTest() throws Exception {
    // Create a cart 
    CartModel cartModel = new CartModel();
    cartModel.setCartModelId(1001L);
    cartModel.setCartModelName("Product 1");
    cartModel.setCartModelPrice(10.00);
    cartModel.setCartModelShortDesc("Product 1 description");
    cartModel.setCartModelQuantity(1000);
    // Convert to Json object
    String newCartJson = objectMapper.writeValueAsString(cartModel);
    // build the request
    RequestBuilder request = MockMvcRequestBuilders
      .post("/cart/save")
      .contentType(MediaType.APPLICATION_JSON)
      .content(newCartJson);
    // perform the request
    mockie.perform(request)
      .andDo(print())
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.status").value("Successful")); // 1 - OK
  }

  // search catalogues by name
  @DisplayName("Search catalogues by name")
  @Test
  public void searchCartNameTest() throws Exception{
    // build the request
    RequestBuilder request = MockMvcRequestBuilders
      .get("/cart/search?catalogueName=super");
    // perform the request
    mockie.perform(request)
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.data.[0].cartModelName").hasJsonPath())
      .andExpect(jsonPath("$.data.[0].cartModelName").value("Super useful cutting set"));
  }

  // Test to throw internal server exception
  @DisplayName("Throw internal server exception")
  @Test
  public void throwInternalExceptionTest() throws Exception {
    // build the request
    // malform url
    RequestBuilder request = MockMvcRequestBuilders
      .get("/cart-malform");
    // peform the request
    mockie.perform(request)
      .andDo(print())
      .andExpect(status().isInternalServerError());
   }

} // END OF CLASS
