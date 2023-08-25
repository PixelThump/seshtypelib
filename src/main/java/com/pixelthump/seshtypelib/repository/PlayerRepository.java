package com.pixelthump.seshtypelib.repository;
import com.pixelthump.seshtypelib.repository.model.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
    boolean existsByPlayerId_PlayerNameAndPlayerId_SeshCode(String playerName, String seshCode);
}
