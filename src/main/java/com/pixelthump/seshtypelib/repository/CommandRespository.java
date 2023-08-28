package com.pixelthump.seshtypelib.repository;
import com.pixelthump.seshtypelib.repository.model.command.Command;
import com.pixelthump.seshtypelib.repository.model.command.CommandId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandRespository extends JpaRepository<Command, CommandId> {

    List<Command> findByCommandId_State_SeshCodeOrderByCommandId_TimestampAsc(String seshCode);

}
