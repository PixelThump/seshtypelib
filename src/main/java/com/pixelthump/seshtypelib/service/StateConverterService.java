package com.pixelthump.seshtypelib.service;
import com.pixelthump.seshtypelib.repository.model.State;
import com.pixelthump.seshtypelib.repository.model.player.Player;
import com.pixelthump.seshtypelib.service.model.AbstractServiceState;

public interface StateConverterService {

    AbstractServiceState getHostState(State state);

    AbstractServiceState getControllerState(Player player, State state);
}
