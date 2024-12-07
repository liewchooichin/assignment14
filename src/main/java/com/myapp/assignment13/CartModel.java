package com.myapp.assignment13;


import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CartModel {
  /*
   * This class is necessary to display to results of mapping.
   * This will prevent recursive loop.
   */
  private Long cartModelId;
  private String cartModelName;
  private Double cartModelPrice;
  private String cartModelShortDesc;
  private Integer cartModelQuantity;
}
