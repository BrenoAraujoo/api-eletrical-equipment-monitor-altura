SET session_replication_role = 'replica'; -- disable fk checks

delete from tb_equipment;
delete from tb_equipment_reading;

SET session_replication_role = 'origin'; -- enable fk checks


--tb_equipment
insert into tb_equipment (id ,cod, name, description, measurement_type) values (1,'e123','equipment 1','Equipment with one sensor', 0);
insert into tb_equipment (id ,cod, name, description, measurement_type) values (2,'ff133','equipment 2','Equipment with two sensors', 1);
insert into tb_equipment (id ,cod, name, description, measurement_type) values (3,'eds222','equipment 3','Equipment with three sensors' ,2);

--tb_equipment_reading

insert into tb_equipment_reading (id, equipment_id, sensora, sensorb, sensorc,date) values(1, 3,'20.0','30.0','12.0','2022-01-01T14:15:10');
insert into tb_equipment_reading (id, equipment_id, sensora, sensorb, sensorc,date) values(2, 3,'120.0','130.0','12.0','2022-12-12T17:15:10');

insert into tb_equipment_reading (id, equipment_id, sensora,date) values(3, 1,'21.0','2022-11-11T11:22:10');
insert into tb_equipment_reading (id, equipment_id, sensora, sensorb,date) values(4, 2,'2.0','3.0','2022-11-11T11:22:10');
