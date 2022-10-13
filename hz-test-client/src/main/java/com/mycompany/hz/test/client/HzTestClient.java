/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.hz.test.client;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.LifecycleService;
import com.hazelcast.map.IMap;
import com.hazelcast.map.LocalMapStats;
import java.util.Map;

/**
 *
 * @author henry
 */
public class HzTestClient {

    private static final IMap<String, String> map = Hazelcast.newHazelcastInstance(null).getMap("hz-test");

    public static void main(String[] args) {
       
        
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String id = entry.getKey();
            String appId = entry.getValue();

            System.out.println("Hello " + id + " on " + appId);

        }
    }
}
