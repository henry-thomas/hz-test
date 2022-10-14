/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hz.test.app.hzUtils;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.LifecycleService;
import com.hazelcast.map.IMap;
import java.util.Map;

/**
 *
 * @author henry
 */
public class HzController {

    private String appId;
    private final IMap<String, String> map;
    private final Config hzConfig = new Config();
    private HazelcastInstance hz;
    private static HzController INSTANCE = null;

    public HzController() {
        hzConfig.setClusterName("hz-test-cluster");
        hz = Hazelcast.newHazelcastInstance(hzConfig);
        map = hz.getMap("hz-test");
        LifecycleService lifeCycle = hz.getLifecycleService();
        lifeCycle.addLifecycleListener(new HzLifeCycleListener());
        hz.addDistributedObjectListener(new HzDistrObjectListener());
    }

    public static HzController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HzController();
        }

        return INSTANCE;
    }

    public static HzController getInstance(String appId) {
        if (INSTANCE == null) {
            INSTANCE = getInstance();
        }

        INSTANCE.setAppId(appId);

        return INSTANCE;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public IMap<String, String> getMap() {
        return map;
    }

    public void showConnected() {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String connectionId = entry.getKey();
            String serverId = entry.getValue();

            System.out.println(connectionId + " is online on server: " + serverId);

        }
    }

    public static void send(String id) {
        if (INSTANCE.appId != null) {
            INSTANCE.map.put(id, INSTANCE.appId);
        }
    }

}
