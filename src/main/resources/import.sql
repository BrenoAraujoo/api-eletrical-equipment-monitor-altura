
--EQUIPMENT

INSERT INTO tb_equipment (description, measurement_type) values ('equipment 1',0)
INSERT INTO tb_equipment (description, measurement_type) values ('equipment 2',1)
INSERT INTO tb_equipment (description, measurement_type) values ('equipment 3',2)

--READDATA
--
----Sensor with two measures only
INSERT INTO tb_equipment_reading (equipment_id, sensora, sensorb, date_time) values (1,200.0, 110.0,'2022-09-05T12:22:15')
INSERT INTO tb_equipment_reading (equipment_id, sensora, sensorb, date_time) values (1,20.0, 10.0,'2022-09-05T12:22:15')
INSERT INTO tb_equipment_reading (equipment_id, sensora, sensorb, date_time) values (2,20.0, 10.0,'2022-09-05T12:22:15')
INSERT INTO tb_equipment_reading (equipment_id, sensora, sensorb, date_time) values (2,10.0, 11.0,'2022-09-05T12:22:15')
INSERT INTO tb_equipment_reading (equipment_id, sensora, sensorb, date_time) values (3,4.0, 11.0,'2022-09-05T12:22:15')
INSERT INTO tb_equipment_reading (equipment_id, sensora, sensorb, date_time) values (3,30.0, 12.0,'2022-09-05T12:22:15')

--Sensor with three measures

INSERT INTO tb_equipment_reading (equipment_id, sensora, sensorb, sensorc,date_time) values (1,3.0, 40.0,11.1,'2022-09-05T12:22:15')
INSERT INTO tb_equipment_reading (equipment_id, sensora, sensorb, sensorc,date_time) values (2,3.0, 11.0,11.1,'2022-09-05T12:22:15')
INSERT INTO tb_equipment_reading (equipment_id, sensora, sensorb, sensorc,date_time) values (3,13.0, 4.0,31.1,'2022-09-05T12:22:15')
