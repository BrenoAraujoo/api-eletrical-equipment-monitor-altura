SET session_replication_role = 'replica'; -- disable fk checks

delete from tb_equipment;
delete from tb_equipment_reading;

SET session_replication_role = 'origin'; -- enable fk checks



--Restart sequence
ALTER SEQUENCE tb_equipment_id_seq RESTART WITH 1;
ALTER SEQUENCE tb_equipment_reading_id_seq RESTART WITH  1;

--tb_equipment
insert into tb_equipment (cod, name, description, measurement_type) values ('e123','equipment 1','Equipment with one sensor', 0);
insert into tb_equipment (cod, name, description, measurement_type) values ('ff133','equipment 2','Equipment with two sensors', 1);
insert into tb_equipment (cod, name, description, measurement_type) values ('eds222','equipment 3','Equipment with three sensors' ,2);

--tb_equipment_reading

insert into tb_equipment_reading (equipment_id, sensora, sensorb, sensorc,date) values(3,'20.0','30.0','12.0','2022-01-01T14:15:10');
insert into tb_equipment_reading (equipment_id, sensora, sensorb, sensorc,date) values(3,'120.0','130.0','12.0','2022-12-12T17:15:10'); fi

insert into tb_equipment_reading (equipment_id, sensora,date) values(1,'21.0','2022-11-11T11:22:10');
insert into tb_equipment_reading (equipment_id, sensora, sensorb,date) values(2,'2.0','3.0','2022-11-11T11:22:10');


