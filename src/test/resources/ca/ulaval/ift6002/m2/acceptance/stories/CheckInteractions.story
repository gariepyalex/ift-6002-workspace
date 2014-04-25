CheckInteractions

Narrative: Détecter les interactions
Comme intervenant,
Je veux que le système m'aide à détecter des interactions médicamenteuses,
Afin de prévenir une réaction grave du patient pouvant causer la mort.

Scénario: Ajout d'une prescription avec une interaction
Etant donné que un patient avec une prescription
Et une prescription récente qui interagit avec tous les médicaments
Quand j'ajoute cette prescription au dossier du patient
Alors une erreur est retournée
Alors cette erreur a le code "PRES002"
Et le protocole HTTP retourné est le 409
