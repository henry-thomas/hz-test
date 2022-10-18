///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mycompany.hz.test.app;
//
//import com.mycompany.hz.test.app.hzUtils.HzController;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.logging.Level;
//import java.util.logging.LogManager;
//import java.util.logging.Logger;
//import javax.annotation.PostConstruct;
//import javax.enterprise.context.ApplicationScoped;
//
///**
// *
// * @author henry
// */
//@ApplicationScoped
//public class ConnectionEndpoint {
//
//    Logger logger = LogManager.getLogManager().getLogger("");
//
//    {
//        logger.setLevel(Level.INFO);
//    }
//
//    @PostConstruct
//    private void init() {
//        logger.info("ConnectionEndoint init");
//        try {
//
////            String appId = args.length > 0 ? args[0] : "Server-1";
//            Integer port = 4004;
//
//            try ( ServerSocket server = new ServerSocket(port)) {
//
//                HzController.init(server.getInetAddress().getHostAddress());
//
//                while (true) {
//                    Socket client = server.accept();
//                    Thread sthread = new Thread(new ServerConnection(client));
//                    sthread.setName(client.getInetAddress().getHostAddress() + "-" + client.getPort());
//                    sthread.start();
//                }
//            }
//
//        } catch (IOException ex) {
//            Logger.getLogger(ConnectionEndpoint.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//}
