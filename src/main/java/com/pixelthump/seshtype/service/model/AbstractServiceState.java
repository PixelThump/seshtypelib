package com.pixelthump.seshtype.service.model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public abstract class AbstractServiceState {
    private String seshCode;
}
