delimiter $$
create procedure proc1()
    begin
        declare i int default 0;
        while i < 500 do
            insert into user_info(name, age) values (concat('chen', cast(i as char(100))), i);
            set i=i+1;
        end while;
    end
$$
delimiter ;