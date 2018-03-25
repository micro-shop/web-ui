package cz.microshop.webui.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.microshop.webui.model.OrderDetails;

public interface OrderDetailsDao extends JpaRepository<OrderDetails, Long> {

}
