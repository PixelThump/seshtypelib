package com.pixelthump.seshtypelib.service;
import com.pixelthump.seshtypelib.repository.model.State;

public interface StateFactory {

    State createSeshTypeState(String seshCode);
}
