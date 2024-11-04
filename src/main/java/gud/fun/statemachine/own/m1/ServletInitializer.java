package gud.fun.statemachine.own.m1;

import gud.fun.statemachine.springexamples.sm1.StateMachineApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StateMachineApplication.class);
	}

}
