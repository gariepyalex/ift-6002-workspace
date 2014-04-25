Dead patient

Narrative: Ajouter une prescription à un patient mort
Comme agent de santé,
Je ne doit pas pouvoir ajouter une prescription à un patient décédé,
Afin de détecter les erreurs ou les comportements illicites.

Scénario: 
Etant donné que un patient est mort
Et une prescription valide
Quand j'ajoute cette prescription au dossier du patient
Alors une erreur est retournée
Alors cette erreur a le code "PATDCD"
Et le protocole HTTP retourné est le 410