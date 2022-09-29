package com.api.entities.enums;

public enum MeasurementType {
    ONE(1),
    TWO(2),
    THREE(3);

    private Integer code;


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
