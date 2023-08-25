package com.pixelthump.seshtype.rest.model.command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeshTypeCommand {

    private String playerName;
    private String type;
    private String body;

}
