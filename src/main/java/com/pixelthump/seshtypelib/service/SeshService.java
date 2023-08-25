package com.pixelthump.seshtypelib.service;
import com.pixelthump.seshtypelib.repository.model.State;
import com.pixelthump.seshtypelib.repository.model.command.Command;
import com.pixelthump.seshtypelib.repository.model.player.Player;
import com.pixelthump.seshtypelib.service.model.SeshInfo;
import com.pixelthump.seshtypelib.service.model.AbstractServiceState;

public interface SeshService {

    SeshInfo getSeshInfo(String seshCode);

    SeshInfo hostSesh(String seshCode);

    void sendCommandToSesh(Command command, String seshCode);

    State getSesh(String seshCode);

    AbstractServiceState joinAsController(String seshCode, Player player);
    AbstractServiceState joinAsHost(String seshCode);

    AbstractServiceState getStateForPlayer(String seshCode, String playerName);
}
