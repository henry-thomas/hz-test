/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hz.test.client;

import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.cluster.MembershipListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henry
 */
public class HzMembershipListener implements MembershipListener {

    private static final Logger LOG = Logger.getLogger(HzMembershipListener.class.getName());

    @Override
    public void memberAdded(MembershipEvent membershipEvent) {
        LOG.log(Level.INFO, "memberAdded: {0}:{1}", new Object[]{membershipEvent.getMember().getAddress().getHost(), membershipEvent.getMember().getAddress().getPort()});
    }

    @Override
    public void memberRemoved(MembershipEvent membershipEvent) {
        LOG.log(Level.INFO, "memberRemoved: {0}:{1}", new Object[]{membershipEvent.getMember().getAddress().getHost(), membershipEvent.getMember().getAddress().getPort()});
    }

}
