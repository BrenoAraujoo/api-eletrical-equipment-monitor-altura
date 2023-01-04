package com.api.domain.entities.enums;

public enum MeasurementType {
    ONE_SENSOR(0),
    TWO_SENSORS(1),
    THREE_SENSORS(2);

    final private Integer code;

    MeasurementType(Integer code) {
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
        throw new IllegalArgumentException("Invalid code status xxx");
    }

    public static String getAllMeasurementCode() {

        int measurementType = MeasurementType.values().length;
        StringBuilder code = new StringBuilder()
                .append("[ ");

        for (MeasurementType value : MeasurementType.values()) {
            code.append(value.getCode());
            if (value.getCode() == measurementType - 1) {
                code.append(" ]");
            } else {
                code.append(", ");
            }
        }
        return code.toString();
    }

}
