/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpop;

import PseudoTreeConstruction.AckMessage;
import PseudoTreeConstruction.LayerMessage;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class Messages {

    public List<LayerMessage> layerMessage[] = new LinkedList[Constants.maxAgents];
    public List<AckMessage> ackMessage[] = new LinkedList[Constants.maxAgents];

    public Messages() {
        for (int i = 0; i < Constants.maxAgents; i++) {
            layerMessage[i] = new LinkedList<LayerMessage>();
            ackMessage[i] = new LinkedList<AckMessage>();
        }
    }

    public synchronized LayerMessage getLayerMessage(int index) {
        LayerMessage ret = null;
        if (layerMessage[index].size() == 0) {
            return ret;
        } else {
            ret = layerMessage[index].get(0).deepcopy();
            layerMessage[index].remove(0);
        }
        return ret;
    }

    public synchronized void addLayerMessage(int index, LayerMessage msg) {
        if(index<Constants.maxAgents) layerMessage[index].add(msg);
    }

    public synchronized AckMessage getAckMessage(int index) {
        AckMessage ret = null;
        if (ackMessage[index].size() == 0) {
            return ret;
        } else {
            ret = ackMessage[index].get(0).deepcopy();
            ackMessage[index].remove(0);
        }
        return ret;
    }

    public synchronized void addAckMessage(int index, AckMessage msg) {
        ackMessage[index].add(msg);
    }

}
