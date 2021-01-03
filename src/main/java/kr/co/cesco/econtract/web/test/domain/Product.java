package kr.co.cesco.econtract.web.test.domain;

import java.util.List;

import lombok.Data;

public class Product {
	
	@Data
	public static class CategoryDto2 {
		private Integer categoryId;
		private String categoryName;
	}

	@Data
	public static class BrandDto2 {
		private Integer brandId;
		private String brandName;
	}
	
	@Data
	public static class ProductDto2 {
		private Integer productId;
		private String productName;
		private Integer brandId;
		private Integer categoryId;
		private Integer modelYear;
		private Float listPrice;
		private CategoryDto2 category; // association
		private BrandDto2 brand;
		private List<OrderItemDto2> orderItems; // collection
	}
	
	@Data
	public static class OrderItemDto2 {
		private Integer orderId;
		private Integer itemId;
		private Integer productId;
		private Integer quantity;
		private Float listPrice;
		private Float discount;
	}

}
