package ca.ulaval.ift6002.m2.services;

import ca.ulaval.ift6002.m2.application.assemblers.InstrumentAssembler;
import ca.ulaval.ift6002.m2.application.assemblers.SurgeryAssembler;
import ca.ulaval.ift6002.m2.application.requests.InstrumentRequest;
import ca.ulaval.ift6002.m2.application.requests.SurgeryRequest;
import ca.ulaval.ift6002.m2.domain.instrument.Instrument;
import ca.ulaval.ift6002.m2.domain.instrument.InstrumentStatus;
import ca.ulaval.ift6002.m2.domain.instrument.Serial;
import ca.ulaval.ift6002.m2.domain.surgery.Surgery;
import ca.ulaval.ift6002.m2.domain.surgery.SurgeryRepository;
import ca.ulaval.ift6002.m2.locator.RepositoryLocator;

public class SurgeryService {

    private final InstrumentAssembler instrumentAssembler;
    private final SurgeryAssembler surgeryAssembler;
    private final SurgeryRepository surgeryRepository;

    public SurgeryService() {
        this.surgeryRepository = RepositoryLocator.getSurgeryRepository();
        this.surgeryAssembler = new SurgeryAssembler();
        this.instrumentAssembler = new InstrumentAssembler();
    }

    public Integer saveSurgery(SurgeryRequest surgeryRequest) {
        Surgery surgery = surgeryAssembler.fromRequest(surgeryRequest);

        surgeryRepository.store(surgery);

        return surgery.getNumber();
    }

    public void saveInstrument(String surgeryNumber, InstrumentRequest instrumentResponse) {
        Instrument instrument = instrumentAssembler.fromRequest(instrumentResponse);
        Surgery surgery = surgeryRepository.get(Integer.valueOf(surgeryNumber));

        surgery.add(instrument);

        surgeryRepository.store(surgery);
    }

    public void bookmarkInstrumentToStatus(String surgeryId, InstrumentRequest instrumentResponse) {
        Surgery surgery = surgeryRepository.get(Integer.valueOf(surgeryId));
        InstrumentStatus instrumentStatus = InstrumentStatus.determineFrom(instrumentResponse.status);
        Serial serial = new Serial(instrumentResponse.serial);

        surgery.bookmarkInstrumentToStatus(serial, instrumentStatus);

        surgeryRepository.store(surgery);
    }

    protected SurgeryService(SurgeryRepository surgeryRepository, SurgeryAssembler surgeryAssembler,
            InstrumentAssembler instrumentAssembler) {
        this.surgeryRepository = surgeryRepository;
        this.surgeryAssembler = surgeryAssembler;
        this.instrumentAssembler = instrumentAssembler;
    }
}
