package org.openapitools.configuration;

import com.fdobrotv.touristagency.dto.CarColor;
import com.fdobrotv.touristagency.dto.ServiceClass;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class EnumConverterConfiguration {

    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.carColorConverter")
    Converter<String, CarColor> carColorConverter() {
        return new Converter<String, CarColor>() {
            @Override
            public CarColor convert(String source) {
                return CarColor.fromValue(source);
            }
        };
    }
    @Bean(name = "org.openapitools.configuration.EnumConverterConfiguration.serviceClassConverter")
    Converter<String, ServiceClass> serviceClassConverter() {
        return new Converter<String, ServiceClass>() {
            @Override
            public ServiceClass convert(String source) {
                return ServiceClass.fromValue(source);
            }
        };
    }

}
