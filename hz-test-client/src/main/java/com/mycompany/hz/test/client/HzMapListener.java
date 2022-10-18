/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hz.test.client;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.EntryRemovedListener;
import com.hazelcast.map.listener.EntryUpdatedListener;
import com.hazelcast.map.listener.MapListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henry
 */
public class HzMapListener implements MapListener, EntryAddedListener<String, String>, EntryRemovedListener<String, String>, EntryUpdatedListener<String, String>{

    private static final Logger LOG = Logger.getLogger(HzMapListener.class.getName());

    @Override
    public void entryAdded(EntryEvent<String, String> event) {
        LOG.log(Level.INFO, "entryAdded: {0}", event.getKey());
    }

    @Override
    public void entryRemoved(EntryEvent<String, String> event) {
        LOG.log(Level.INFO, "entryRemoved: {0}", event.getKey());
    }

    @Override
    public void entryUpdated(EntryEvent<String, String> event) {
        LOG.log(Level.INFO, "entryUpdated: {0}", event.getKey());
    }
    
}
