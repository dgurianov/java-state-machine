package gud.fun.statemachine.own.m1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class M1Controller {

    @Autowired
    private StateMachine<M1States, M1Events> stateMachine;



    @RequestMapping("/m1")
    public void m1() {
        stateMachine.sendEvent(M1Events.TONEW);

    }
}
