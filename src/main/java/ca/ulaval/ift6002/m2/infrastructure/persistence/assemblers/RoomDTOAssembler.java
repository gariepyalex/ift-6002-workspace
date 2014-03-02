package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.RoomDTO;

public class RoomDTOAssembler {

    public RoomDTO toDTO(Room room) {
        return new RoomDTO(room.value);
    }

    public Room fromDTO(RoomDTO dto) {
        return new Room(dto.room);
    }

}
