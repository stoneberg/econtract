CREATE TABLE sales.app_users
(
    id int IDENTITY (1,1) NOT NULL,
    username varchar(50) NOT NULL,
    fullname varchar(50) NOT NULL,
    password varchar(255) NOT NULL,
    deptname varchar(25) NOT NULL,
    position varchar(25) NOT NULL,
    email varchar(50) NOT NULL,
    user_type varchar(25),
    CONSTRAINT app_users_id_pk PRIMARY KEY (id),
    CONSTRAINT app_users_email_uc UNIQUE(email)
);


INSERT INTO sales.app_users
(username, fullname, password, deptname, position, email, user_type)
VALUES
('stoneberg', 'lee yupyeong', '$2a$10$oV1xXItlbl80Luh9Ujm67e9b1Oyc7Hj.79tp/OG3BZyERzVqW63lm', '개발부', '차장', 'stoneberg@gmail.net', 'SA'),
('zetlee', 'lee hojae', '$2a$10$1aXri0rCydsZt6y1u0PM/eHysVJorggeN0Zd6UxhNslmA80QDzcMq', '영업무', '과장', 'zetlee@gmail.net', 'SD'),
('redfoxer', 'lee sang', '$2a$10$rt/OZ9Wg6Rqz831GJ/PLm.FCyAN0.BftCzLY7glmN5llqViuBzN0G', '관리부', '차장', 'redfoxer@gmail.net', 'SC');




