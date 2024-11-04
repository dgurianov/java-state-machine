package gud.fun.statemachine.own.m2withpersist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.AbstractStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
public class M2Config extends AbstractStateMachineConfigurerAdapter<M2States, M2Events> {

    @Override
    public void configure(StateMachineStateConfigurer<M2States, M2Events> states) throws Exception {
        states
                .withStates()
                .initial(M2States.NEW, initialAction())
                .state(M2States.NEW, entryAction(), exitAction())
                .state(M2States.RUNNING, entryAction(), exitAction())
                .state(M2States.READY, entryAction(), exitAction());
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<M2States, M2Events> transitions) throws Exception {
        transitions
                .withExternal()
                .source(M2States.NEW).target(M2States.RUNNING).event(M2Events.TORUNNING).action(transitionAction())
                .and()
                .withExternal()
                .source(M2States.RUNNING).target(M2States.READY).event(M2Events.TOREADY).action(transitionAction())
                .and()
                .withExternal()
                .source(M2States.READY).target(M2States.NEW).event(M2Events.TONEW).action(transitionAction())
        ;
    }

    @Bean
    public Action<M2States, M2Events> initialAction() {
        return context -> System.out.println("Initial action executed");
    }

    @Bean
    public Action<M2States, M2Events> entryAction() {
        return context -> System.out.println("Entering state " + context.getTarget().getId());
    }

    @Bean
    public Action<M2States, M2Events> exitAction() {
        return context -> System.out.println("Exititing state " + context.getSource().getId());
    }

    @Bean
    public Action<M2States, M2Events> transitionAction() {
        return context -> System.out.println("Transition action executed");
    }
}