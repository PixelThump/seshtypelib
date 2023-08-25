package com.pixelthump.seshtype.service;
import com.pixelthump.seshtype.repository.model.State;
import com.pixelthump.seshtype.repository.model.command.Command;
import com.pixelthump.seshtype.repository.model.player.Player;
import com.pixelthump.seshtype.service.model.SeshInfo;
import com.pixelthump.seshtype.service.model.AbstractServiceState;

public interface SeshService {

    SeshInfo getSeshInfo(String seshCode);

    SeshInfo hostSesh(String seshCode);

    void sendCommandToSesh(Command command, String seshCode);

    State getSesh(String seshCode);

    AbstractServiceState joinAsController(String seshCode, Player player);
    AbstractServiceState joinAsHost(String seshCode);

    AbstractServiceState getStateForPlayer(String seshCode, String playerName);
}
