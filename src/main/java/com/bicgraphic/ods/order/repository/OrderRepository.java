package com.bicgraphic.ods.order.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bicgraphic.ods.order.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

	public List<Order> findOrdersByOrderNumber(String orderId);

	public List<Order> findOrdersByCustomerNumber(String customerNumber);

	public Order findByOrderNumber(String orderId);

	public Collection<Order> findByOrderDate(String orderDate);

}
