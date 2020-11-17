package ru.relex.miniBooking.bd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@MapperScan("ru.relex.miniBooking.bd.mapper")
@EnableTransactionManagement
public class DataBaseConfig {
}
