package com.pixelthump.seshtypelib.service.model.player;
import com.pixelthump.seshtypelib.service.model.State;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Player implements Serializable {

    @EmbeddedId
    private PlayerId playerId;
    @Column(name = "vip", nullable = false)
    private Boolean vip = false;
    @Column(name = "points")
    private Long points;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "state", nullable = false)
    private State state;

    public void addPoints(Integer pointsToAdd){

        this.points = points + pointsToAdd;
    }
}
