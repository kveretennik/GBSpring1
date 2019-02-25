CREATE SEQUENCE IF NOT EXISTS store.manufacturers_s
    AS BIGINT
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    NO CYCLE;

DROP TABLE IF EXISTS store.manufacturers;

CREATE TABLE IF NOT EXISTS store.manufacturers
(
    manufacturer_id    BIGINT DEFAULT NEXTVAL('store.manufacturers_s'::REGCLASS) NOT NULL
        PRIMARY KEY,
--     created_by         BIGINT                                                    NOT NULL,
--     creation_date      INTEGER                                                   NOT NULL,
--     last_updated_by    INTEGER                                                   NOT NULL,
--     last_update_date   INTEGER                                                   NOT NULL,
    manufacturer_name  VARCHAR(255)                                              NOT NULL,
    manufacturer_desc  TEXT
);

CREATE SEQUENCE IF NOT EXISTS store.customers_s
    AS BIGINT
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    NO CYCLE;

DROP TABLE IF EXISTS store.customers;

CREATE TABLE IF NOT EXISTS store.customers
(
    customer_id     BIGINT DEFAULT NEXTVAL('store.customers_s'::REGCLASS) NOT NULL
        PRIMARY KEY,
--     created_by         BIGINT                                            NOT NULL,
--     creation_date      INTEGER                                           NOT NULL,
--     last_updated_by    BIGINT                                            NOT NULL,
--     last_update_date   INTEGER                                           NOT NULL,
    last_name       VARCHAR(80)                                           NOT NULL,
    first_name      VARCHAR(80)                                           NOT NULL,
    middle_name     VARCHAR(80)                                           NOT NULL,
    full_name       VARCHAR(240)                                          NOT NULL,
    login           VARCHAR(20)                                           NOT NULL,
    password        VARCHAR(255)                                          NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS store.items_s
    AS BIGINT
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    NO CYCLE;

DROP TABLE IF EXISTS store.items;

CREATE TABLE IF NOT EXISTS store.items
(
    item_id            BIGINT DEFAULT NEXTVAL('store.items_s'::REGCLASS) NOT NULL
        PRIMARY KEY,
--     created_by         BIGINT                                            NOT NULL,
--     creation_date      INTEGER                                           NOT NULL,
--     last_updated_by    BIGINT                                            NOT NULL,
--     last_update_date   INTEGER                                           NOT NULL,
    item_number        VARCHAR(40)                                       NOT NULL,
    item_name          VARCHAR(80)                                       NOT NULL,
    item_description   TEXT,
    primary_uom_code   VARCHAR(3)                                        NOT NULL,
    manufacturer_id    BIGINT                                            NOT NULL
        CONSTRAINT items_manufacturer_id_fk
            REFERENCES store.manufacturers(manufacturer_id)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    onhand_quantity    NUMERIC(15,3) DEFAULT 0                           NOT NULL
        CONSTRAINT items_onhand_quantity_chk
            CHECK (onhand_quantity >= 0),
    item_price         NUMERIC(15,2) DEFAULT 0
        CONSTRAINT items_item_price_chk
            CHECK (item_price >= 0)
);

CREATE SEQUENCE IF NOT EXISTS store.order_headers_s
    AS BIGINT
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    NO CYCLE;

DROP TABLE IF EXISTS store.order_headers;

CREATE TABLE IF NOT EXISTS store.order_headers
(
    order_header_id     BIGINT DEFAULT NEXTVAL('store.order_headers_s'::REGCLASS) NOT NULL
        PRIMARY KEY,
--     created_by         BIGINT                                            NOT NULL,
--     creation_date      INTEGER                                           NOT NULL,
--     last_updated_by    BIGINT                                            NOT NULL,
--     last_update_date   INTEGER                                           NOT NULL,
    order_number        BIGINT                                                    NOT NULL,
    customer_id         BIGINT                                                    NOT NULL
        CONSTRAINT order_headers_customer_id_fk
            REFERENCES store.customers(customer_id)
            ON UPDATE CASCADE
            ON DELETE CASCADE,
    ship_address_id     BIGINT
);

CREATE SEQUENCE IF NOT EXISTS store.order_lines_s
    AS BIGINT
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    NO CYCLE;

DROP TABLE IF EXISTS store.order_lines;

CREATE TABLE IF NOT EXISTS store.order_lines
(
    order_line_id     BIGINT DEFAULT NEXTVAL('store.order_lines_s'::REGCLASS) NOT NULL
        PRIMARY KEY,
--     created_by         BIGINT                                            NOT NULL,
--     creation_date      INTEGER                                           NOT NULL,
--     last_updated_by    BIGINT                                            NOT NULL,
--     last_update_date   INTEGER                                           NOT NULL,
    order_header_id   BIGINT                                                  NOT NULL
        CONSTRAINT order_lines_order_header_id_fk
            REFERENCES store.order_headers(order_header_id)
            ON UPDATE CASCADE
            ON DELETE CASCADE,
    line_number       INTEGER                                                 NOT NULL,
        CONSTRAINT order_lines_line_number_chk
            CHECK (line_number > 0),
    item_id           BIGINT                                                  NOT NULL
        CONSTRAINT items_item_id_fk
            REFERENCES store.items(item_id)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,
    line_quantity     NUMERIC(15,3)                                           NOT NULL
        CONSTRAINT order_lines_line_quantity_chk
            CHECK (line_number > 0),
    line_price        NUMERIC                                                 NOT NULL
        CONSTRAINT order_lines_line_price_chk
            CHECK (line_price >= 0)
);