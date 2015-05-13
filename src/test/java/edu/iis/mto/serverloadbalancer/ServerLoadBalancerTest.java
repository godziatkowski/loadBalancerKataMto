package edu.iis.mto.serverloadbalancer;

import org.junit.Test;

import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerLoadBalancer.balance;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static edu.iis.mto.serverloadbalancer.hasCurrentPercetageLoad.hasCurrentPercentageLoad;
import static edu.iis.mto.serverloadbalancer.hasVmCountOfMatcher.hasVmCountOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ServerLoadBalancerTest {

    @Test
    public void itCompiles() {
        assertThat( true, equalTo( true ) );
    }

    @Test
    public void emptyServerWithExpectedLoadEqual0() {
        Server theServer = a( server().withCapacity( 5 ) );

        balance( serverList( theServer ), vmList() );

        assertThat( theServer, hasCurrentPercentageLoad( 0.0d ) );
    }

    @Test
    public void serverFilledByOneMachine() {
        Server theServer = a( server().withCapacity( 10 ) );
        Vm theVm = a( vm().ofSize( 10 ) );

        balance( serverList( theServer ), vmList( theVm ) );

        assertThat( theServer, hasCurrentPercentageLoad( 100.0d ) );
        assertThat( theServer.contains( theVm ), is( true ) );
    }

    @Test
    public void serverHalfFilledByVm() {
        Server theServer = a( server().withCapacity( 50 ) );
        Vm theVm = a( vm().ofSize( 25 ) );

        balance( serverList( theServer ), vmList( theVm ) );

        assertThat( theServer, hasCurrentPercentageLoad( 50.0d ) );
        assertThat( theServer.contains( theVm ), is( true ) );

    }

    @Test
    public void serverWithFewVm() {
        Server theServer = a( server().withCapacity( 100 ) );
        Vm firstVm = a( vm().ofSize( 10 ) );
        Vm secondVm = a( vm().ofSize( 25 ) );
        Vm thirdVm = a( vm().ofSize( 35 ) );

        balance( serverList( theServer ), vmList( firstVm, secondVm, thirdVm ) );

        assertThat( theServer, hasCurrentPercentageLoad( 70.0d ) );
        assertThat( theServer, hasVmCountOf( 3 ) );
        assertThat( theServer.contains( firstVm ), is( true ) );
        assertThat( theServer.contains( secondVm ), is( true ) );
        assertThat( theServer.contains( thirdVm ), is( true ) );
    }


    private Server a( ServerBuilder builder ) {
        return builder.build();
    }

    private Vm a( VmBuilder builder ) {
        return builder.build();
    }

    private Server[] serverList( Server server ) {
        return new Server[]{ server };
    }

    private Vm[] vmList( Vm... vms ) {
        return vms;
    }

}
