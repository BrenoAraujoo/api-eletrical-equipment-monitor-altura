CREATE TABLE tb_equipment(
id bigserial PRIMARY KEY,
name VARCHAR(40) NOT NULL,
description VARCHAR(50),
measurement_type int NOT NULL
);
CREATE TABLE tb_equipment_reading(
id bigserial PRIMARY KEY,
sensor_a NUMERIC(3,1),
sensor_b NUMERIC(3,1),
sensor_c NUMERIC(3,1),
date TIMESTAMP NOT NULL,
equipment_id bigserial REFERENCES tb_equipment(id)
);