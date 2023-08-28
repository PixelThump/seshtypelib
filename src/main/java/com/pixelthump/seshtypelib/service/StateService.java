package com.pixelthump.seshtypelib.service;
import com.pixelthump.seshtypelib.service.model.State;
import com.pixelthump.seshtypelib.service.model.messaging.AbstractServiceState;
import com.pixelthump.seshtypelib.service.model.player.Player;

import java.util.List;
import java.util.Optional;

public interface StateService {

    State findBySeshCode(String seshCode);

    boolean existsBySeshCode(String seshCode);

    Optional<? extends State> findBySeshCodeAndActive(String seshCode, Boolean active);

    List<? extends State> findByActive(Boolean active);

    State save(State state);

    AbstractServiceState getHostState(State state);

    AbstractServiceState getControllerState(Player player, State state);
}
