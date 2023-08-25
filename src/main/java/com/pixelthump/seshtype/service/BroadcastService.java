package com.pixelthump.seshtype.service;
import com.pixelthump.seshtype.repository.model.State;

public interface BroadcastService {

    void broadcastSeshUpdate(State state);
}
