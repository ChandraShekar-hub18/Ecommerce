package com.codewithchandu.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.codewithchandu.orderservice.dto.InventoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codewithchandu.orderservice.dto.OrderLineItemsDto;
import com.codewithchandu.orderservice.dto.OrderRequest;
import com.codewithchandu.orderservice.model.Order;
import com.codewithchandu.orderservice.model.OrderLineItems;
import com.codewithchandu.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	
	
	private final OrderRepository orderRepository;
	private final WebClient.Builder webClientBuilder;
	
	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
		.stream()
		.map(this :: mapToDto)
		.toList();
		
		order.setOrderLineItemsList(orderLineItems);

		List<String> skuCodes = order.getOrderLineItemsList().stream()
				.map(OrderLineItems::getSkuCode)
				.toList();

		//Call inventory service place order if it is in stock
		InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
						uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

		boolean result = Arrays.stream(inventoryResponses)
				.allMatch(InventoryResponse::isInStock);
		if(result){
			orderRepository.save(order);
		}
		else{
			throw new IllegalArgumentException("Product is not in stock, please try again later");

		}

		
	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		return orderLineItems;
		
	}
}
