/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hz.test.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.cluster.Member;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.mycompany.hz.test.lib.Message;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author henry
 */
@ApplicationScoped
public class HzClientController {

    private static IMap<String, Message> map;
    private Set<Member> members;

    @PostConstruct
    public void init() {
        HazelcastInstance hz = HazelcastClient.newHazelcastClient(HzConfig.defaultConfig());
        hz.getCluster().addMembershipListener(new HzMembershipListener());

        map = hz.getMap("hz-test");
        map.addEntryListener(new HzMapListener(), true);
        System.out.println("Init HzClientController");
        members = hz.getCluster().getMembers();
//        while (true) {
//            try {
//                for (Map.Entry<String, Message> entry : map.entrySet()) {
//                    String id = entry.getKey();
//                    String appId = entry.getValue().getMessage();
//
//                    System.out.println("Hello " + id + " on " + appId);
//                }
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(HzTestClient.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

    public IMap<String, Message> getMap() {
        return map;
    }

    public Set<Member> getMembers() {
        return members;
    }

}
