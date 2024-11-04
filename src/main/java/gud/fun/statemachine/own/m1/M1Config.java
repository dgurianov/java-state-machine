package gud.fun.statemachine.own.m1;

import gud.fun.statemachine.springexamples.sm1.machines.GudEvents;
import gud.fun.statemachine.springexamples.sm1.machines.GudStates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.AbstractStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
public class M1Config extends AbstractStateMachineConfigurerAdapter<M1States, M1Events> {

    @Override
    public void configure(StateMachineStateConfigurer<M1States, M1Events> states) throws Exception {
        states
                .withStates()
                .initial(M1States.NEW, initialAction())
                .state(M1States.NEW, entryAction(), exitAction())
                .state(M1States.RUNNING, entryAction(), exitAction())
                .state(M1States.READY, entryAction(), exitAction())
        ;
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<M1States, M1Events> transitions) throws Exception {
        transitions
                .withExternal()
                .source(M1States.NEW).target(M1States.RUNNING).event(M1Events.TORUNNING).action(transitionAction())
                .and()
                .withExternal()
                .source(M1States.RUNNING).target(M1States.READY).event(M1Events.TOREADY).action(transitionAction())
                .and()
                .withExternal()
                .source(M1States.READY).target(M1States.NEW).event(M1Events.TONEW).action(transitionAction())
        ;
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<M1States, M1Events> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true);
//                .and()
//                .withSecurity()
//                .enabled(true)
//                .event("hasRole('USER')");
    }

    @Bean
    public Action<M1States, M1Events> initialAction() {
        return context -> System.out.println("Initial action executed");
    }

    @Bean
    public Action<M1States, M1Events> entryAction() {
        return context -> System.out.println("Entering state " + context.getTarget().getId());
    }

    @Bean
    public Action<M1States, M1Events> exitAction() {
        return context -> System.out.println("Exititing state " + context.getSource().getId());
    }

    @Bean
    public Action<M1States, M1Events> transitionAction() {
        return context -> System.out.println("Transition action executed");
    }
}