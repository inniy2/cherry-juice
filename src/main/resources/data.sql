insert into ghost_user (user_id, user_name, password) values (1,'apple','test');
insert into ghost_user (user_id, user_name, password) values (2,'orange','test');
insert into ghost_user (user_id, user_name, password) values (3,'mango','test');

insert into ghost_lock (lock_id, ghost_host, user_name, lock_status) values (1, 'localhost', 'apple', 'reserved');
insert into ghost_lock (lock_id, ghost_host, user_name, lock_status) values (2, '127.0.0.1', 'apple', 'on going');
insert into ghost_lock (lock_id, ghost_host, user_name, lock_status) values (3, '127.0.0.2', 'apple', 'free');

--insert into dash_board (dash_board_id, ghost_host, table_schema, table_definition, statement, start_time, user_id, status) values (1,'localhost','test','test_tbl2','add column a int','2020-01-20 23:00:00', 'orange', 'cut-over');
--insert into dash_board (dash_board_id, ghost_host, table_schema, table_definition, statement, start_time, user_id, status) values (2,'localhost','test','test_tbl3','add column b int','2020-01-20 23:00:00', 'orange', 'cut-over');