package gud.fun.statemachine.springexamples.sm1.config;

import gud.fun.statemachine.springexamples.sm1.machines.GudEvents;
import gud.fun.statemachine.springexamples.sm1.machines.GudStates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.AbstractStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends AbstractStateMachineConfigurerAdapter<GudStates, GudEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<GudStates, GudEvents> states) throws Exception {
        states
                .withStates()
                .initial(GudStates.S1, initialAction())
                .state(GudStates.S1, entryAction(), exitAction())
                .state(GudStates.S2, entryAction(), exitAction());
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<GudStates, GudEvents> transitions) throws Exception {
        transitions
                .withExternal()
                .source(GudStates.S1).target(GudStates.S2).event(GudEvents.E1).action(transitionAction())
                .and()
                .withExternal()
                .source(GudStates.S2).target(GudStates.S1).event(GudEvents.E2).action(transitionAction());
    }

    @Bean
    public Action<GudStates, GudEvents> initialAction() {
        return context -> System.out.println("Initial action executed");
    }

    @Bean
    public Action<GudStates, GudEvents> entryAction() {
        return context -> System.out.println("Entry action executed");
    }

    @Bean
    public Action<GudStates, GudEvents> exitAction() {
        return context -> System.out.println("Exit action executed");
    }

    @Bean
    public Action<GudStates, GudEvents> transitionAction() {
        return context -> System.out.println("Transition action executed");
    }
}