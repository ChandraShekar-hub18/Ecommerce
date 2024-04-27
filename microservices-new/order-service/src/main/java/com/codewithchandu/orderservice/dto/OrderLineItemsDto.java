package com.codewithchandu.orderservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {
	
	private String skuCode;
	private BigDecimal price;
	private Integer quantity;

}
