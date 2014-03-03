package ca.ulaval.ift6002.m2.infrastructure.persistence.assemblers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6002.m2.domain.operation.room.Room;
import ca.ulaval.ift6002.m2.infrastructure.persistence.dto.RoomDTO;

public class RoomDTOAssemblerTest {

    private static final String ROOM_VALUE = "room";
    private static final RoomDTO ROOM_DTO = new RoomDTO(ROOM_VALUE);
    private static final Room ROOM = new Room(ROOM_VALUE);

    private RoomDTOAssembler roomAssembler;

    @Before
    public void setUp() {
        roomAssembler = new RoomDTOAssembler();
    }

    @Test
    public void givenDTOWhenAssemblingRoomShouldReturnCorrespondingRoom() {
        Room roomBuilt = roomAssembler.fromDTO(ROOM_DTO);

        assertEquals(ROOM, roomBuilt);
    }

    @Test
    public void givenRoomWhenAssemblingDTOShouldReturnCorrespondingDTO() {
        RoomDTO dtoBuilt = roomAssembler.toDTO(ROOM);

        assertRoomDTOEquals(ROOM_DTO, dtoBuilt);
    }

    private void assertRoomDTOEquals(RoomDTO expected, RoomDTO actual) {
        assertEquals(expected.room, actual.room);
    }
}
