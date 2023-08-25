package com.pixelthump.seshtype.service;
import com.pixelthump.seshtype.repository.model.State;
import com.pixelthump.seshtype.service.model.AbstractServiceState;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class BroadcastServiceRestImpl implements BroadcastService {

    private final RestTemplate restTemplate;
    @Value("${pixelthump.backend-basepath}")
    private String backendBasePath;

    private final StateConverterService converterService;

    @Autowired
    public BroadcastServiceRestImpl(RestTemplate restTemplate, StateConverterService converterService) {

        this.restTemplate = restTemplate;
        this.converterService = converterService;
    }

    @Override
    public void broadcastSeshUpdate(State state) {

        log.info("Broadcasting with state={}", state);
        String apiUrl = backendBasePath + "/messaging/seshs/" + state.getSeshCode() + "/broadcasts/different";
        Map<String, AbstractServiceState> message = getStateMessage(state);
        log.info("Sending out broadcast={}", message);
        restTemplate.postForEntity(apiUrl, message, String.class);
    }

    private Map<String, AbstractServiceState> getStateMessage(State state) {

        Map<String, AbstractServiceState> stateMessage = new HashMap<>();
        stateMessage.put("host", converterService.getHostState(state));
        state.getPlayers().forEach(player -> stateMessage.put(player.getPlayerId().getPlayerName(),converterService.getControllerState(player,state)));
        return stateMessage;
    }
}
