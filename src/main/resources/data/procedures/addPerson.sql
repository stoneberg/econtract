USE [cesco]
GO
/****** Object:  StoredProcedure [test].[addPerson]    Script Date: 2020-12-22 오후 2:28:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [test].[addPerson] (
    @name VARCHAR(32),
    @age INT,
    @id INT OUTPUT
) AS
BEGIN
    SET NOCOUNT ON;
    INSERT test.person (u_name, u_age) values (@name, @age);
    SET @id = SCOPE_IDENTITY();
END;
 