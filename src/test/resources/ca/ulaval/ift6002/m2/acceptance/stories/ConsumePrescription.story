Consume prescription

Narrative: Consommer les prescriptions
Comme pharmacien,
Je veux pouvoir indiquer qu'un renouvellement a été utilisé,
Afin de déterminer lorsqu'une prescription est terminée.
 
Scénario: Consommer les prescriptions avec un patient inexistant
Etant donné que un patient est inexistant
Et une prescription valide
Etant donné que une consommation valide
Quand j'ajoute cette consommation
Alors une erreur est retournée
Et cette erreur a le code "PRES010"

Scénario: Consommer les prescriptions avec un patient existant
Etant donné que un patient est existant
Et une prescription valide
Etant donné que une consommation valide
Quand j'ajoute cette consommation
Alors une erreur est retournée
Et cette erreur a le code "PRES011"