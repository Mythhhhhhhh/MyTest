package cn.myth.boot;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Configurable
public class MythTestBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MythTestBootApplication.class, args);
    }

}
