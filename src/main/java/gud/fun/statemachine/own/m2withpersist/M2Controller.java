package gud.fun.statemachine.own.m2withpersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class M2Controller {

    @Autowired
    private StateMachine<M2States, M2Events> stateMachine;



    @RequestMapping("/m1")
    public void m1() {
        stateMachine.sendEvent(M2Events.TONEW);

    }
}
