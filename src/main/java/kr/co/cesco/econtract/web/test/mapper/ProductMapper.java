package kr.co.cesco.econtract.web.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.cesco.econtract.web.test.domain.Product;
import kr.co.cesco.econtract.web.test.dto.ProductDto;

@Mapper
public interface ProductMapper {
	
	// Collection ===================================================================
	
	List<ProductDto> findProducts();
	
	List<ProductDto> findProductsById(@Param("productId") Integer productId);
	
	List<ProductDto> findProductsById2(@Param("productId") Integer productId);
	
	List<Product.ProductDto2> findProductsById3(@Param("productId") Integer productId);
	
	List<Product.ProductDto2> findProductsById4(@Param("productId") Integer productId);
	
	Product.CategoryDto2 findCategoryById(@Param("categoryId") Integer categoryId);
	
	Product.BrandDto2 findBrandById(@Param("brandId") Integer brandId);
	
	List<Product.OrderItemDto2> findOrderItemsByProductId(@Param("productId") Integer productId);

}
