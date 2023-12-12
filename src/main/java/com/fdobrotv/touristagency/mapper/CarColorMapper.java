package com.fdobrotv.touristagency.mapper;

import com.fdobrotv.touristagency.dto.CarColor;

public class CarColorMapper {
    public static CarColor toDTO(com.fdobrotv.touristagency.entity.enums.CarColor carColorEntityEnum) {
        return CarColor.fromValue(carColorEntityEnum.name());
    }

    public static com.fdobrotv.touristagency.entity.enums.CarColor toEntity(CarColor color) {
        return com.fdobrotv.touristagency.entity.enums.CarColor.fromValue(color.getValue());
    }
}
