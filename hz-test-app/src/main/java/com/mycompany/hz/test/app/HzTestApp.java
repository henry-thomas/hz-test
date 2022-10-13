/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.hz.test.app;

import com.mycompany.hz.test.app.hzUtils.HzController;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.LifecycleService;
import com.mycompany.hz.test.app.hzUtils.HzLifeCycleListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henry
 */
public class HzTestApp {

    public static void main(String[] args) {

        try {
            LifecycleService lifeCycle = Hazelcast.newHazelcastInstance(null).getLifecycleService();
            lifeCycle.addLifecycleListener(new HzLifeCycleListener());

            
            ServerSocket server = new ServerSocket(4004);

            String appId = args.length > 0 ? args[0] : "no-name";

            HzController.getInstance(appId);

            while (true) {
                Socket client = server.accept();
                Thread sthread = new Thread(new ServerConnection(client));
                sthread.setName(client.getInetAddress().getHostAddress() + "-" + client.getPort());
                sthread.start();
            }

        } catch (IOException ex) {
            Logger.getLogger(HzTestApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
