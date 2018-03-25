package cz.microshop.webui.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cz.microshop.webui.model.LineItem;

public interface LineItemDao extends JpaRepository<LineItem, Long> {
	@Query("SELECT l FROM LineItem l where l.product.id = :productId AND l.cart.id = :cartId")
	public Optional<LineItem> findByProductIdAndCartId(@Param("productId") Long productId, @Param("cartId") Long cartId);
}
