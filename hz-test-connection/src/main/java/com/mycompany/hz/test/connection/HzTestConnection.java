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

    private static Socket sock;

    public static void main(String[] args) {
        String appId = args.length > 0 ? args[0] : "unknown app";
        boolean connected = false;
        while (!connected) {
            try {
                sock = new Socket("localhost", 4004);
                ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());

                Message message = new Message();
                message.setMessage(appId);

                oos.writeObject(message);

                Message readObject = (Message) ois.readObject();
                System.out.println(readObject.getMessage());
                connected = true;
                sock.close();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(HzTestConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
