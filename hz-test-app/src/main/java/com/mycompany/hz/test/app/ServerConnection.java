/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hz.test.app;

import com.mycompany.hz.test.app.hzUtils.HzController;
import com.mycompany.hz.test.lib.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henry
 */
public class ServerConnection implements Runnable {

    private String connId;
    private int port;
    private String host;
    private Socket client;
    private ObjectOutputStream outWriter;
    private static int parallelConnectionCount = 0;
    private int paralConnWaterMark = 0;

    public ServerConnection(Socket client) {
        this.client = client;
        this.port = client.getPort();
        this.host = client.getInetAddress().getHostAddress();
        parallelConnectionCount++;
        if (parallelConnectionCount > paralConnWaterMark) {
            paralConnWaterMark = parallelConnectionCount;
            System.out.println("Number of connections reached: " + paralConnWaterMark);
        }
    }

    public String connId() {
        return connId;
    }

    public void setServerId(String serverId) {
        this.connId = serverId;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public void sendData(Message data) throws IOException {
        outWriter.writeObject(data);
        outWriter.flush();
    }

    private Message processMessage(Message msg) throws IOException {

        HzController hz;
        try {
            hz = HzController.getInstance();
            System.out.println(msg.getMessage());

            HzController.put(msg);
            this.connId = msg.getMessage();

            hz.showConnected();

            msg.setMessage("Connected to " + HzController.getInstance().getAppId());
        } catch (Exception ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return msg;
    }

    @Override
    public void run() {
        Message inline;
        Message outline;

        ObjectInputStream ois;
        try {
            outWriter = new ObjectOutputStream(client.getOutputStream());
            InputStreamReader isr = new InputStreamReader(client.getInputStream());
            BufferedReader in = new BufferedReader(isr);

            ois = new ObjectInputStream(client.getInputStream());
            System.out.println("Client connected.");

            while ((inline = (Message) ois.readObject()) != null) {

                Message processMessage = processMessage(inline);
                sendData(processMessage);
                // client.close();

            }

            client.close();
            parallelConnectionCount--;
            HzController.remove(connId);
        } catch (IOException | ClassNotFoundException ex) {
            HzController.remove(connId);
            parallelConnectionCount--;
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, "Closing connection: {0}", ex.getClass());
        }
    }
}
