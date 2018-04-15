package org.ycm.sims;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.ycm.sims.dao")
public class SimsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimsApplication.class, args);
	}
}
