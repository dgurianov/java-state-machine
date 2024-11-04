package gud.fun.statemachine.own.m2withpersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class StateMachineApplication implements CommandLineRunner {

	@Autowired
	private StateMachine<M2States, M2Events> stateMachine;

	public static void main(String[] args) {
		SpringApplication.run(StateMachineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		stateMachine.start();
		// Optionally, send events to the state machine
		stateMachine.sendEvent(M2Events.TORUNNING);
		stateMachine.sendEvent(M2Events.TOREADY);
	}
}