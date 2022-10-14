package com.mycompany.hz.test.client;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;

public class HzConfig {

    private static final ClientConfig cnf = new ClientConfig();

    public static ClientConfig defaultConfig() {
        cnf.setClusterName("hz-test-cluster");
        return cnf;
    }

    public static ClientConfig tcpDiscovery() {
        ClientConfig defaultConfig = defaultConfig();
//        cnf.get
        return cnf;
    }

}
