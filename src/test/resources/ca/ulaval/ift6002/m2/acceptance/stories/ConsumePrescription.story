Consume prescription

Narrative: Consommer les prescriptions
Comme pharmacien,
Je veux pouvoir indiquer qu'un renouvellement a été utilisé,
Afin de déterminer lorsqu'une prescription est terminée.
 
Scénario: Consommer les prescriptions d'un patient inexistant
Etant donné que un patient est inexistant
Et une prescription valide
Etant donné que une consommation valide
Quand j'ajoute cette consommation
Alors une erreur est retournée
Et cette erreur a le code "PRES010"

Scénario: Consommer une prescription non-assignée au patient existant
Etant donné que un patient est existant
Et une prescription valide
Etant donné que une consommation valide
Quand j'ajoute cette consommation
Alors une erreur est retournée
Et cette erreur a le code "PRES011"

Scénario: Consommer un nombre de prescriptions excédant le nombre de renoullement restants
Etant donné que un patient avec une prescription
Et cette prescription assignée au patient
Etant donné que une consommation excédant le nombre de renouvellement restants
Quand j'ajoute cette consommation
Alors une erreur est retournée
Et cette erreur a le code "PRES012"

Scénario: Consommer avec des informations manquantes une prescription
Etant donné que un patient avec une prescription
Et cette prescription assignée au patient
Etant donné que une consommation invalide
Quand j'ajoute cette consommation
Alors une erreur est retournée
Et cette erreur a le code "PRES013"

Scénario: Consommer une prescription correctement
Etant donné que un patient avec une prescription
Et cette prescription assignée au patient
Etant donné que une consommation valide
Quand j'ajoute cette consommation
Alors cette consommation est effectuée