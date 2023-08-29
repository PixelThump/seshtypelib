package com.pixelthump.seshtypelib.annotations;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@EnableEncryptableProperties
@EnableJpaRepositories(basePackages = {"com.pixelthump"})
@EntityScan(basePackages = {"com.pixelthump"})
public @interface SeshTypeApplication {

}
