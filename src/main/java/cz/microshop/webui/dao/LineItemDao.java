package cz.microshop.webui.dao;

public interface LineItemDao { //extends JpaRepository<LineItem, Long> {
	/*@Query("SELECT l FROM LineItem l where l.product.id = :productId AND l.cart.id = :cartId")
	public Optional<LineItem> findByProductIdAndCartId(@Param("productId") Long productId, @Param("cartId") Long cartId);*/
}
