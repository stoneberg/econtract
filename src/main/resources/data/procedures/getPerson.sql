USE [cesco]
GO
/****** Object:  StoredProcedure [test].[getPerson]    Script Date: 2020-12-22 오후 2:27:43 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:      <Author,,Name>
-- Create date: <Create Date,,>
-- Description: <Description,,>
-- =============================================
CREATE PROCEDURE [test].[getPerson]
    @personId INT
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    -- Insert statements for procedure here
    SELECT u_id as id, u_name as name, u_age as age from test.person where u_id = @personId;
END
