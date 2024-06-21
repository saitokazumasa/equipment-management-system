package jp.ac.morijyobi.equipmentmanagementsystem.configuration;

import jp.ac.morijyobi.equipmentmanagementsystem.bean.entity.JsonEquipment;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.typehandler.EnumTypeHandler;
import jp.ac.morijyobi.equipmentmanagementsystem.bean.typehandler.JsonTypeHandler;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.AccountCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.DamagedCategory;
import jp.ac.morijyobi.equipmentmanagementsystem.constant.EquipmentState;
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
                    .register(JsonEquipment.class, JsonTypeHandler.class);
            config
                    .getTypeHandlerRegistry()
                    .register(AccountCategory.class, EnumTypeHandler.class);
            config
                    .getTypeHandlerRegistry()
                    .register(DamagedCategory.class, EnumTypeHandler.class);
            config
                    .getTypeHandlerRegistry()
                    .register(EquipmentState.class, EnumTypeHandler.class);
        };
    }
}
