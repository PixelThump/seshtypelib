package com.pixelthump.seshtype.service;
import com.pixelthump.seshtype.repository.model.State;

public interface StateFactory {

    State createSeshTypeState(String seshCode);
}
