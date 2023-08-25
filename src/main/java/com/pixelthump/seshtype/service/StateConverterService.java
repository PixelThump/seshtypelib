package com.pixelthump.seshtype.service;
import com.pixelthump.seshtype.repository.model.State;
import com.pixelthump.seshtype.repository.model.player.Player;
import com.pixelthump.seshtype.service.model.AbstractServiceState;

public interface StateConverterService {

    AbstractServiceState getHostState(State state);

    AbstractServiceState getControllerState(Player player, State state);
}
