package com.fdobrotv.touristagency.mapper;

import com.fdobrotv.touristagency.dto.ServiceClass;

public class ServiceClassMapper {
    public static ServiceClass toDTO(com.fdobrotv.touristagency.entity.enums.ServiceClass serviceClassEntityEnum) {
        return ServiceClass.fromValue(serviceClassEntityEnum.name());
    }

    public static com.fdobrotv.touristagency.entity.enums.ServiceClass toEntity(ServiceClass serviceClass) {
        return com.fdobrotv.touristagency.entity.enums.ServiceClass.fromValue(serviceClass.name());
    }
}
