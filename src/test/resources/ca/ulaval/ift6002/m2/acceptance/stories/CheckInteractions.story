CheckInteractions

Narrative: étecter les interactions
Comme intervenant,
Je veux que le système m'aide à détecter des interactions médicamenteuses,
Afin de prévenir une réaction grave du patient pouvant causer la mort.

Scénario: Ajout d'une prescription avec une interaction
Etant donné que un patient est existant
Et une prescription non-obsolete comportant des interactions associée à ce patient
Quand j'ajoute une prescription pour laquelle il y a une interaction au dossier du patient
Alors une erreur est retournée
Et cette erreur a le code "PRES002"