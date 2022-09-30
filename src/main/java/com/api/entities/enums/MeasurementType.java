package com.api.entities.enums;

public enum MeasurementType {
    ONE_SENSOR(0),
    TWO_SENSORS(1),
    THREE_SENSORS(2);

    final private Integer code;

    private MeasurementType(Integer code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MeasurementType valueOf(int code) {
        for (MeasurementType value : MeasurementType.values()) {
            if (code == value.getCode()) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid code status");
    }

}
