create table exchange_value (
    id int primary key,
    currency_from varchar(5),
    currency_to varchar(5),
    conversion_multiple int,
    port int
);
