package gud.fun.statemachine.springexamples.sm1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import gud.fun.statemachine.springexamples.sm1.machines.GudStates;
import gud.fun.statemachine.springexamples.sm1.machines.GudEvents;

@SpringBootApplication
public class StateMachineApplication implements CommandLineRunner {

	@Autowired
	private StateMachine<GudStates, GudEvents> stateMachine;

	public static void main(String[] args) {
		SpringApplication.run(StateMachineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		stateMachine.start();
		// Optionally, send events to the state machine
		stateMachine.sendEvent(GudEvents.E1);
		stateMachine.sendEvent(GudEvents.E2);
	}
}