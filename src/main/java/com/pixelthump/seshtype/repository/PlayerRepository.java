package com.pixelthump.seshtype.repository;
import com.pixelthump.seshtype.repository.model.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
    boolean existsByPlayerId_PlayerNameAndPlayerId_SeshCode(String playerName, String seshCode);
}
