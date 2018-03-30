package cz.microshop.webui.dao;

import cz.microshop.webui.model.LineItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LineItemDao { //extends JpaRepository<LineItem, Long> {
	@Query("SELECT l FROM LineItem l where l.product.id = :productId AND l.cart.id = :cartId")
	public Optional<LineItem> findByProductIdAndCartId(@Param("productId") Long productId, @Param("cartId") Long cartId);
}
