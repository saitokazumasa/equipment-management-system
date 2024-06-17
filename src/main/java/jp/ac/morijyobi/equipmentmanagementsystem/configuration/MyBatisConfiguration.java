package jp.ac.morijyobi.equipmentmanagementsystem.configuration;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.typehandler.JsonTypeHandler;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.Equipment;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfiguration {

    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return config -> {
            config
                    .getTypeHandlerRegistry()
                    .register(Equipment.class, JsonTypeHandler.class);
        };
    }
}
