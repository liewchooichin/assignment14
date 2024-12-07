package com.myapp.assignment13;

import java.sql.Timestamp;

import javax.smartcardio.CardTerminals;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "catalogue")
public class Catalogue {
  // primary key
  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "catalogue_id")
  public Long catalogueId;

  // other fields
  @Column(name = "catalogue_name")
  private String catalogueName;

  @Column(name = "catalogue_price")
  private Double cataloguePrice;

  @Column(name = "catalogue_shortDesc")
  private String catalogueShortDesc;

  @Column(name = "catalogue_created_at")
  @CreationTimestamp
  private Timestamp catalogueCreatedAt;

  @Column(name = "catalogue_updated_at")
  @UpdateTimestamp
  private Timestamp catalogueUpdatedAt;

  // relate to Cart
  // Cart is the owner of catalogue.
  // Catalogue is a foreign key of Cart.
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "mappedByCatalogue")
  private Cart ownedByCart;

}
