/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iis.mto.serverloadBalancer;

/**
 *
 * @author Godzio
 */
public class LoadBalancer {

    public static void balance( Server[] servers, Vm[] vms ) {
        if ( vms.length > 0 ) {
            servers[0].currentLoad = 100.0d;
        }
    }

}
