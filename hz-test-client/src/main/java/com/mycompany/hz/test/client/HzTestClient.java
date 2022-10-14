/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.hz.test.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.LifecycleService;
import com.hazelcast.map.IMap;
import com.hazelcast.map.LocalMapStats;
import java.util.Map;

/**
 *
 * @author henry
 */
public class HzTestClient {

    private static IMap<String, String> map;

    public static void main(String[] args) {
        HazelcastInstance hz = HazelcastClient.newHazelcastClient(HzConfig.defaultConfig());
        hz.getCluster().addMembershipListener(new HzMembershipListener());
        map = hz.getMap("hz-test");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String id = entry.getKey();
            String appId = entry.getValue();

            System.out.println("Hello " + id + " on " + appId);
        }
    }
}
