package edu.iis.mto.serverloadbalancer;

import org.junit.Test;

import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerLoadBalancer.balance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ServerLoadBalancerTest {

    @Test
    public void itCompiles() {
        assertThat( true, equalTo( true ) );
    }

    @Test
    public void oneEmptyServer() {
        Server theServer = a( server().withCapacity( 1 ) );

        balance( new Server[]{}, new Vm[]{} );

        assertThat( theServer.currentPercentageLoad(), equalTo( 0.0d ) );
    }

    @Test
    public void oneServerFilledByOneVm() {
        Server theServer = a( server().withCapacity( 10 ) );
        Vm theVm = a( vm().ofSize( 10 ) );

        balance( new Server[]{ theServer }, new Vm[]{ theVm } );

        assertThat( theServer.currentPercentageLoad(), equalTo( 100.0d ) );
        assertThat( theServer.contains( theVm ), is( true ) );
    }

    private Server a( ServerBuilder builder ) {
        return builder.build();
    }

}
