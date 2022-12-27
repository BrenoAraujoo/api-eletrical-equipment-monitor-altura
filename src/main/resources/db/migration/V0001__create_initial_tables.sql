CREATE TABLE tb_equipment(
id bigserial PRIMARY KEY,
cod VARCHAR(20) NOT NULL,
name VARCHAR(40) NOT NULL,
description VARCHAR(50),
measurement_type int NOT NULL
);
CREATE TABLE tb_equipment_reading(
id bigserial PRIMARY KEY,
sensorA FLOAT(2),
sensorB FLOAT(2),
sensorC FLOAT(2),
date TIMESTAMP NOT NULL,
equipment_id bigserial REFERENCES tb_equipment(id)
);