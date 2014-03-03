package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import ca.ulaval.ift6002.m2.domain.room.Room;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.RoomDTO;

public class RoomDTOAssembler extends DTOAssembler<Room, RoomDTO> {

    @Override
    public RoomDTO toDTO(Room room) {
        return new RoomDTO(room.value);
    }

    @Override
    public Room fromDTO(RoomDTO dto) {
        return new Room(dto.room);
    }

}
