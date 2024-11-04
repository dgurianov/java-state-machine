package gud.fun.statemachine.own.m1;

import gud.fun.statemachine.springexamples.sm1.machines.GudEvents;
import gud.fun.statemachine.springexamples.sm1.machines.GudStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class StateMachineApplication implements CommandLineRunner {

	@Autowired
	private StateMachine<M1States, M1Events> stateMachine;

	public static void main(String[] args) {
		SpringApplication.run(StateMachineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Either start manually as below or in M1Config see .autoStartup(true);
//		stateMachine.start();
		stateMachine.sendEvent(M1Events.TORUNNING);
		stateMachine.sendEvent(M1Events.TOREADY);
	}
}