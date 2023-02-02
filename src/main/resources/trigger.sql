create trigger confirm
    AFTER UPDATE
    ON orders
    for each row
begin

    declare sum_of_order bigint;
    declare count_of_product int;

    set count_of_product:=(select COUNT(id) from products join order_product o on products.id = o.product_id
                           where order_id = NEW.id);

    set sum_of_order := (select sum(price) from products join order_product o on products.id = o.product_id
                         where order_id = NEW.id);

    if NEW.submit is true then
        insert into info (order_created_at, processed, order_id, info)
        values (now(), true, NEW.id, concat('Order with id N:', NEW.id, ' sum of order: ', sum_of_order,'UAH, ordered items:',count_of_product));
end if;
end;