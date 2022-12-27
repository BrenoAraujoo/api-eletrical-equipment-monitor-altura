SET session_replication_role = 'replica'; -- disable fk checks

delete from tb_equipment;
delete from tb_equipment_reading;

SET session_replication_role = 'origin'; -- enable fk checks


--tb_equipment
insert into tb_equipment (id,name, description, measurement_type) values (1,'equipment 1','Equipment 1 description', 0);
insert into tb_equipment (id,name, description, measurement_type) values (2,'equipment 2','Equipment 2 description', 1);
insert into tb_equipment (id,name, description, measurement_type) values (3,'equipment 3','Equipment 3 description' ,2);

--tb_equipment_reading

insert into tb_equipment_reading (id, equipment_id, sensor_a, sensor_b, sensor_c,date) values(1, 1,'20.0','30.0','12.0','2022-01-01T14:15:10')
