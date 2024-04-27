package com.codewithchandu.orderservice.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<OrderLineItemsDto> orderLineItemsDtoList;
}
