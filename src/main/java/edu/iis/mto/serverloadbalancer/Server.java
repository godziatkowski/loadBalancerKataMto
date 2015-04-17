/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Godzio
 */
public class Server {

    private double MAXIMUM_LOAD = 100.0d;
    public double currentLoadPercentage = 0;
    public final int capacity;
    private List<Vm> vms = new ArrayList<Vm>();

    Server( int capacity ) {
        this.capacity = capacity;
    }

    public boolean contains( Vm vm ) {
        return true;

    }

    void addVm( Vm vm ) {
        vms.add( vm );
        currentLoadPercentage += (double) vm.size / (double) this.capacity * MAXIMUM_LOAD;
    }

    public int vmsCount() {
        return vms.size();

    }
}
