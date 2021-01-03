USE [cesco]
GO
/****** Object:  StoredProcedure [dbo].[getCustomersByState]    Script Date: 2020-12-22 오후 2:31:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:      <Author,,Name>
-- Create date: <Create Date,,>
-- Description: <Description,,>
-- =============================================
CREATE PROCEDURE [sales].[getCustomersByState] 
    -- Add the parameters for the stored procedure here
    @state varchar(25)
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    -- Insert statements for procedure here
        SELECT 
            customer_id,
            first_name,
            last_name,
            phone,
            email,
            street,
            city,
            state,
            zip_code
        FROM sales.customers
        WHERE state = @state
END
