package com.pixelthump.seshtypelib.service;
import com.pixelthump.seshtypelib.repository.CommandRespository;
import com.pixelthump.seshtypelib.repository.PlayerRepository;
import com.pixelthump.seshtypelib.repository.StateRepository;
import com.pixelthump.seshtypelib.repository.model.State;
import com.pixelthump.seshtypelib.repository.model.command.Command;
import com.pixelthump.seshtypelib.repository.model.command.CommandId;
import com.pixelthump.seshtypelib.repository.model.player.Player;
import com.pixelthump.seshtypelib.service.model.SeshInfo;
import com.pixelthump.seshtypelib.service.model.AbstractServiceState;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class SeshServiceImpl implements SeshService {

    private final StateFactory stateFactory;
    private final StateRepository stateRepository;
    private final CommandRespository commandRespository;
    private final PlayerRepository playerRepository;
    private final StateConverterService stateConverterService;

    public SeshServiceImpl(StateFactory stateFactory, StateRepository stateRepository, CommandRespository commandRespository, PlayerRepository playerRepository, StateConverterService stateConverterService) {

        this.stateFactory = stateFactory;
        this.stateRepository = stateRepository;
        this.commandRespository = commandRespository;
        this.playerRepository = playerRepository;
        this.stateConverterService = stateConverterService;
    }

    @Override
    public SeshInfo getSeshInfo(String seshCode) {

        State sesh = getSesh(seshCode);
        return extractSeshInfo(sesh);
    }

    @Override
    @Transactional
    public SeshInfo hostSesh(String seshCode) {

        if (stateRepository.existsBySeshCode(seshCode)) {

            String responseMessage = "Sesh with seshCode " + seshCode + " already exists";
            throw new ResponseStatusException(HttpStatus.CONFLICT, responseMessage);
        }

        State sesh = createSesh(seshCode);
        stateRepository.save(sesh);

        return extractSeshInfo(sesh);
    }

    @Override
    @Transactional
    public void sendCommandToSesh(Command command, String seshCode) {

        final State sesh = getSesh(seshCode);
        command.setCommandId(new CommandId(sesh));
        commandRespository.save(command);
    }

    public State getSesh(String seshCode) {

        Optional<State> state = stateRepository.findBySeshCodeAndActive(seshCode, true);

        if (state.isEmpty()) {

            String responseMessage = "Sesh with seshCode " + seshCode + " not found";
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, responseMessage);
        }

        return state.get();
    }

    @Override
    public AbstractServiceState joinAsController(String seshCode, Player player) {

        State state = getSesh(seshCode);
        boolean seshIsFull = state.getPlayers().size() == state.getMaxPlayer();
        boolean playerAlreadyJoined = playerRepository.existsByPlayerId_PlayerNameAndPlayerId_SeshCode(seshCode, player.getPlayerId().getPlayerName());
        if ((seshIsFull || playerAlreadyJoined)) {

            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        player.setState(state);
        player.setVip(false);
        player.setPoints(0L);
        playerRepository.save(player);
        state.getPlayers().add(player);
        state.setHasChanged(true);
        stateRepository.save(state);
        return this.stateConverterService.getControllerState(player, state);
    }

    @Override
    public AbstractServiceState joinAsHost(String seshCode) {

        State state = getSesh(seshCode);
        if (state.isHostJoined()) {

            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        state.setHostJoined(true);
        state.setHasChanged(true);
        stateRepository.save(state);
        return this.stateConverterService.getHostState(state);
    }

    @Override
    public AbstractServiceState getStateForPlayer(String seshCode, String playerName) {

        State state = stateRepository.findBySeshCode(seshCode);

        AbstractServiceState result;
        if (playerName.equals("host")) {
            result = this.stateConverterService.getHostState(state);
        } else {
            Player player = state.getPlayers().stream().filter(player1 -> player1.getPlayerId().getPlayerName().equals(playerName)).findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
            result = this.stateConverterService.getControllerState(player, state);
        }

        return result;
    }

    private SeshInfo extractSeshInfo(State sesh) {

        SeshInfo seshInfo = new SeshInfo();
        seshInfo.setSeshCode(sesh.getSeshCode());

        return seshInfo;
    }

    private State createSesh(String seshCode) {

        return stateFactory.createSeshTypeState(seshCode);
    }
}
