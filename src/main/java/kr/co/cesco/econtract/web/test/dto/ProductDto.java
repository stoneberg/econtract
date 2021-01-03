package kr.co.cesco.econtract.web.test.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductDto {
	private Integer productId;
	private String productName;
	private Integer brandId;
	private Integer categoryId;
	private Integer modelYear;
	private Float listPrice;
	private CategoryDto category; // association
	private BrandDto brand;
	private List<OrderItemDto> orderItems; // collection

}
