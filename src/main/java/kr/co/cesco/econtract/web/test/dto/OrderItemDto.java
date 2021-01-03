package kr.co.cesco.econtract.web.test.dto;

import lombok.Data;

@Data
public class OrderItemDto {
	private Integer orderId;
	private Integer itemId;
	private Integer productId;
	private Integer quantity;
	private Float listPrice;
	private Float discount;
}
