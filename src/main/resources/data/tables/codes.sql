CREATE TABLE sales.codes
(
    code       varchar(25) NOT NULL,
    code_nm    varchar(25) NOT NULL,
    code_desc  varchar(25) NOT NULL,
    code_group varchar(25),
    CONSTRAINT codes_code_pk PRIMARY KEY (code),
    CONSTRAINT codes_code_group_fk FOREIGN KEY (code_group) REFERENCES sales.codes (code)
);

INSERT INTO sales.codes
    (code, code_nm, code_desc)
VALUES ('VC', 'VIRTUAL_COIN', '가상화폐');

INSERT INTO sales.codes
    (code, code_nm, code_desc, code_group)
VALUES ('BTC', 'BIT_COIN', '비트코인', 'VC');

INSERT INTO sales.codes
    (code, code_nm, code_desc, code_group)
VALUES ('ETH', 'ETHEREUM', '이더리움', 'VC');

INSERT INTO sales.codes
    (code, code_nm, code_desc, code_group)
VALUES ('LTC', 'LITE_COIN', '라이트코인', 'VC');

INSERT INTO sales.codes
    (code, code_nm, code_desc, code_group)
VALUES ('XRP', 'RIPPLE', '리플', 'VC');

CREATE VIEW sales.codes_view AS
select c.code,
       c.code_nm,
       c.code_desc,
       cp.code    as code_group,
       cp.code_nm as code_group_nm
FROM sales.codes c
LEFT JOIN sales.codes cp
ON c.code_group = cp.code;

CREATE TABLE sales.user_virtual_coins
(
    id           int IDENTITY (1,1) NOT NULL,
    username     varchar(50)   NOT NULL,
    virtual_coin varchar(25)   NOT NULL,
    amount       int DEFAULT 0 NOT NULL,
    CONSTRAINT user_virtual_coins_id_pk PRIMARY KEY (id),
    CONSTRAINT user_virtual_coins_virtual_coin_fk FOREIGN KEY (virtual_coin) REFERENCES sales.codes (code)
);

INSERT INTO sales.user_virtual_coins
    (username, virtual_coin, amount)
VALUES ('stoneberg', 'BTC', 100);


CREATE TABLE sales.investors
(
    username varchar(50) NOT NULL,
    firstname varchar(25) NOT NULL,
    lastname varchar(25) NOT NULL,
    email varchar(50),
    use_yn varchar(1) NOT NULL DEFAULT 'Y',
    CONSTRAINT investors_username_pk PRIMARY KEY (username)
);

INSERT INTO sales.investors
(username, firstname, lastname, email, use_yn)
VALUES
('stoneberg', 'lee', 'yu pyeong', 'stoneberg@gmail.net', 'Y');

INSERT INTO sales.investors
(username, firstname, lastname, email, use_yn)
VALUES
('zetlee', 'lee', 'ho jae', 'zetlee@gmail.net', 'Y');

INSERT INTO sales.investors
(username, firstname, lastname, email, use_yn)
VALUES
('redfoxer', 'lee', 'sang', 'redfoxer@gmail.net', 'N');