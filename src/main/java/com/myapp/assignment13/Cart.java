package com.myapp.assignment13;

import java.sql.Date;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
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
@Entity
@Table(name = "cart")
public class Cart {
  // primary key
  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cart_id")
  public Long cartId;

  // other fields
  @Column(name = "cart_quantity")
  private Integer cartQuantity;

  @Column(name = "cart_created_at")
  @CreatedDate
  private Date cartCreatedAt;

  @Column(name = "cart_updated_at")
  @UpdateTimestamp
  private Date cartUpdatedAt;

  // relate to Catalogue
  // Cart is the foreign key owner
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_catalogue_id")
  private Catalogue mappedByCatalogue;
}
