package org.lilyana;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.lilyana.dao.mapper")
public class SnaplinkAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnaplinkAdminApplication.class, args);
    }
}