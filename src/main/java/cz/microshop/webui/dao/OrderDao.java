package cz.microshop.webui.dao;

public interface OrderDao { // extends JpaRepository<Order, Long> {
/*
	@Query(value="from Order o WHERE o.user.id = ?1 ORDER BY o.createdAt DESC", 
			countQuery="SELECT COUNT(o) FROM Order o WHERE o.user.id = ?1 ORDER BY o.createdAt DESC", nativeQuery=false)
	Page<Order> findByUserId(Long userId, Pageable pageable);*/
}
