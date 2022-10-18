/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.hz.test.connection;

import com.mycompany.hz.test.lib.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henry
 */
public class HzTestConnection {

    public static void main(String[] args) {
        String connId = args.length > 0 ? args[0] : "unknown app";
        Integer port = args.length > 0 ? Integer.valueOf(args[1]) : 4004;

        boolean connected = false;
        Socket sock = null;
        while (true) {
            try {
                if (sock == null || !sock.isConnected()) {
                    sock = new Socket("localhost", port);
                }

                ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());

                Message message = new Message();
                message.setMessage(connId);

                oos.writeObject(message);

                Message readObject = (Message) ois.readObject();
                System.out.println(readObject.getMessage());
                Thread.sleep(60000);
                connected = true;
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(HzTestConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
