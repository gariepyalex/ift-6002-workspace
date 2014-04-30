package ca.ulaval.ift6002.m2.services;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ift6002.m2.application.assemblers.InstrumentAssembler;
import ca.ulaval.ift6002.m2.application.assemblers.SurgeryAssembler;
import ca.ulaval.ift6002.m2.application.requests.InstrumentRequest;
import ca.ulaval.ift6002.m2.application.requests.SurgeryRequest;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.surgery.Surgery;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryRepository;

@RunWith(MockitoJUnitRunner.class)
public class SurgeryServiceTest {

    private static final String SURGERY_ID = "1232";
    private static final String UNUSED = InstrumentStatus.UNUSED.toString();

    private static final InstrumentRequest INSTRUMENT_RESPONSE = new InstrumentRequest("123", UNUSED, "1312422");

    private static final SurgeryRequest SURGERY_RESPONSE = new SurgeryRequest("Cataracte à l'oeil gauche", 101224,
            "0000-00-00T24:01:00", "blocB", "oeil", "en cours", 1);

    @Mock
    private SurgeryAssembler surgeryAssembler;

    @Mock
    private InstrumentAssembler instrumentAssembler;

    @Mock
    private SurgeryRepository surgeryRepository;

    @Mock
    private Instrument instrument;

    @Mock
    private Surgery surgery;

    @InjectMocks
    private SurgeryService surgeryService;

    @Before
    public void setUp() {
        willReturn(surgery).given(surgeryRepository).get(Integer.valueOf(SURGERY_ID));
    }

    @Test
    public void whenSavingSurgeryShouldStoreUsingRepository() {
        willReturn(surgery).given(surgeryAssembler).fromRequest(SURGERY_RESPONSE);
        surgeryService.saveSurgery(SURGERY_RESPONSE);
        verify(surgeryRepository).store(surgery);
    }

    @Test
    public void whenSavingInstrumentShouldAddInstrument() {
        willReturn(instrument).given(instrumentAssembler).fromRequest(INSTRUMENT_RESPONSE);
        surgeryService.saveInstrument(SURGERY_ID, INSTRUMENT_RESPONSE);
        verify(surgery).add(instrument);
    }

    @Test
    public void whenSavingInstrumentShouldStoreSurgeryUsingRepository() {
        surgeryService.saveInstrument(SURGERY_ID, INSTRUMENT_RESPONSE);

        verify(surgeryRepository).store(surgery);
    }

    @Test
    public void whenBookmarkingInstrumentShouldUpdateInstrumentStatus() {
        surgeryService.bookmarkInstrumentToStatus(SURGERY_ID, INSTRUMENT_RESPONSE);

        verify(surgery).bookmarkInstrumentToStatus(any(Serial.class), any(InstrumentStatus.class));
    }

    @Test
    public void whenBookmarkingInstrumentShouldStoreSurgeryUsingRepository() {
        surgeryService.bookmarkInstrumentToStatus(SURGERY_ID, INSTRUMENT_RESPONSE);

        verify(surgeryRepository).store(surgery);
    }
}
