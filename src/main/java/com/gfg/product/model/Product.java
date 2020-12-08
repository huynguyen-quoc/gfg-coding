package com.gfg.product.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.gfg.product.model.validation.group.InsertProductGroup;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@JsonRootName("product")
@DynamicUpdate
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@ToString
public class Product implements Serializable {

  @Serial
  private static final long serialVersionUID = 4329050405073908752L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotEmpty(groups = InsertProductGroup.class)
  private String productId;
  @NotEmpty(groups = InsertProductGroup.class)
  private String title;
  @NotEmpty(groups = InsertProductGroup.class)
  private String description;
  @NotEmpty(groups = InsertProductGroup.class)
  private String brand;
  @NotNull(groups = InsertProductGroup.class)
  private BigDecimal price;
  @NotEmpty(groups = InsertProductGroup.class)
  private String color;

}
