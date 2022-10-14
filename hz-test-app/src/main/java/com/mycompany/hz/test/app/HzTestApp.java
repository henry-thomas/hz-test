/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.hz.test.app;

import com.mycompany.hz.test.app.hzUtils.HzController;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author henry
 */
public class HzTestApp {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogManager().getLogger("");
        logger.setLevel(Level.INFO);

//        HazelcastInstance hz = Hazelcast.newHazelcastInstance(null);
        try {

            

            String appId = args.length > 0 ? args[0] : "Server-1";
            Integer port = args.length > 1 ? Integer.valueOf(args[1]) : 4004;

            try ( ServerSocket server = new ServerSocket(port)) {

                HzController.getInstance(appId);

                while (true) {
                    Socket client = server.accept();
                    Thread sthread = new Thread(new ServerConnection(client));
                    sthread.setName(client.getInetAddress().getHostAddress() + "-" + client.getPort());
                    sthread.start();
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(HzTestApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
