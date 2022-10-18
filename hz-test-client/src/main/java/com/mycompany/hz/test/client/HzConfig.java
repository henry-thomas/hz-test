package com.mycompany.hz.test.client;

import com.hazelcast.client.config.ClientConfig;

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
