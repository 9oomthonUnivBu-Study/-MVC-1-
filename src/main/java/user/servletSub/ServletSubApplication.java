package user.servletSub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@ServletComponentScan
@SpringBootApplication
public class ServletSubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletSubApplication.class, args);
	}

}
