insert into ghost_user (user_id, user_name, password) values (1,'apple','test');
insert into ghost_user (user_id, user_name, password) values (2,'orange','test');
insert into ghost_user (user_id, user_name, password) values (3,'mango','test');

insert into ghost_lock (lock_id, ghost_host, envoy_port, user_name, lock_status) values (1, 'localhost', '10000', 'apple', 'reserved');
insert into ghost_lock (lock_id, ghost_host, envoy_port, user_name, lock_status) values (2, '127.0.0.1', '10000', 'mango', 'reserved');
insert into ghost_lock (lock_id, ghost_host, envoy_port, user_name, lock_status) values (3, '192.168.33.12', '10000', '', 'free');
insert into ghost_lock (lock_id, ghost_host, envoy_port, user_name, lock_status) values (4, '192.168.33.13', '10000', '', 'free');
insert into ghost_lock (lock_id, ghost_host, envoy_port, user_name, lock_status) values (5, '10.145.239.38', '10001', '', 'free'); --u1
insert into ghost_lock (lock_id, ghost_host, envoy_port, user_name, lock_status) values (6, '10.145.239.36', '10000', 'apple', 'reserved'); --u2

-- home
insert into ghost_lock (lock_id, ghost_host, envoy_port, user_name, lock_status) values (7, '10.18.23.224', '10001', 'apple', 'reserved'); --u1
insert into ghost_lock (lock_id, ghost_host, envoy_port, user_name, lock_status) values (8, '10.18.23.225', '10000', 'apple', 'reserved'); --u3




--insert into dash_board (dash_board_id, ghost_host, table_schema, table_definition, statement, start_time, user_id, status) values (1,'localhost','test','test_tbl2','add column a int','2020-01-20 23:00:00', 'orange', 'cut-over');
--insert into dash_board (dash_board_id, ghost_host, table_schema, table_definition, statement, start_time, user_id, status) values (2,'localhost','test','test_tbl3','add column b int','2020-01-20 23:00:00', 'orange', 'cut-over');