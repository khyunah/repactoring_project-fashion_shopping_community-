package com.shop.fashion.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.shop.fashion.model.Item;

public interface ShoppingRepository extends JpaRepository<Item, Integer> {

	@Query(value = "SELECT * FROM Item WHERE gender = 'man'", nativeQuery = true)
	Page<Item> CategoryItemMans(Pageable pageable);

	@Query(value = "SELECT * FROM Item WHERE gender = 'woman'", nativeQuery = true)
	Page<Item> CategoryItemWomans(Pageable pageable);

	@Query(value = "SELECT * FROM Item WHERE category =:category and gender = :gender", nativeQuery = true)
	Page<Item> CategoryItem(@Param(value = "category") String category, @Param(value = "gender") String gender,
			Pageable pageable);

	@Query(value = "select * from item where name = :name and gender = :gender", nativeQuery = true)
	List<Item> detailItemSizeColor(@Param(value = "name") String name, @Param(value = "gender") String gender);

	@Query(value = "SELECT * FROM item WHERE id = :id", nativeQuery = true)
	Page<Item> mFindById(@Param("id") int id, Pageable pageable);

	@Query(value = "SELECT * FROM item WHERE name LIKE %:name%", nativeQuery = true)
	Page<Item> mFindByName(@Param("name") String name, Pageable pageable);

	@Query(value = "SELECT * FROM item WHERE price >= :price", nativeQuery = true)
	Page<Item> mFindByPrice(@Param("price") String price, Pageable pageable);

	@Query(value = "SELECT * FROM item WHERE gender = :gender", nativeQuery = true)
	Page<Item> mFindByGender(@Param("gender") String gender, Pageable pageable);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE item SET amount = amount - ?1 where id = ?2", nativeQuery = true)
	int updateStock(@Param(value = "amount") int amount, @Param(value = "id") int id);

	@Query(value = "SELECT * FROM item WHERE category = :category", nativeQuery = true)
	Page<Item> mFindByCategory(@Param("category") String category, Pageable pageable);
	
	@Query(value = "SELECT amount FROM item WHERE id = :id", nativeQuery = true)
	int mFindByItemAmount(@Param("id") int id);
	
	// 장바구니에서 체크하는 수량
	@Query(value = "SELECT id, amount FROM item WHERE id = :id", nativeQuery = true)
	Item mFindByAmount(@Param("id") int id);

}
