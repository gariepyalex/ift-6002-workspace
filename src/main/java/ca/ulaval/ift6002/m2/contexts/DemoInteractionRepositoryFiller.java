package ca.ulaval.ift6002.m2.contexts;

import java.util.List;

import ca.ulaval.ift6002.m2.domain.patient.Interaction;
import ca.ulaval.ift6002.m2.domain.patient.InteractionRepository;
import ca.ulaval.ift6002.m2.file.FileParser;

public class DemoInteractionRepositoryFiller {

    private final InteractionRepository interactionRepository;
    private final FileParser<Interaction> interactionParser;

    public DemoInteractionRepositoryFiller(InteractionRepository repository, FileParser<Interaction> interactionParser) {
        this.interactionRepository = repository;
        this.interactionParser = interactionParser;
    }

    public void fill() {
        List<Interaction> interactions = interactionParser.parse();

        interactionRepository.store(interactions);
    }

}
