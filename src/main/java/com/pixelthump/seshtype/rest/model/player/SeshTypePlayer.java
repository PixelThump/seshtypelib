package com.pixelthump.seshtype.rest.model.player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeshTypePlayer {

    private SeshTypePlayerId playerId;
    private String playerName;
    private Boolean vip;
    private Long points;
    private SeshTypePlayerIconName playerIconName;

    public SeshTypePlayer(SeshTypePlayerId playerId, boolean vip, long points, SeshTypePlayerIconName playerIconName) {

        this.playerId = playerId;
        this.vip = vip;
        this.points = points;
        this.playerIconName = playerIconName;
    }
}
