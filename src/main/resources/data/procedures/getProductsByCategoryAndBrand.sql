USE [cesco]
GO
/****** Object:  StoredProcedure [production].[getProductsByCategoryAndBrand]    Script Date: 2020-12-22 오후 2:26:20 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:      <Author,,Name>
-- Create date: <Create Date,,>
-- Description: <Description,,>
-- =============================================
CREATE PROCEDURE [production].[getProductsByCategoryAndBrand] 
    -- Add the parameters for the stored procedure here
    @categoryName varchar(255), 
    @brandName varchar(255)
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    -- Insert statements for procedure here
 SELECT 
    p.product_id as productId, 
    c.category_name as categoryName,
    b.brand_name as brandName,
    p.product_name as productName,
    p.model_year as modelYear,
    p.list_price as listPrice
 FROM production.products p
  JOIN production.brands b ON b.brand_id = p.brand_id
  JOIN production.categories c ON c.category_id = p.category_id
 WHERE b.brand_name = @brandName AND c.category_name = @categoryName
END




