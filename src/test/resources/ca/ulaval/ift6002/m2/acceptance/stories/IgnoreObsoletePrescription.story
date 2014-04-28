Ignore Obsolete Prescription

Narrative: Ignorer les prescriptions obsolètes pour les intéractions
En tant qu'intervenant
Je ne veux pas recevoir des alertes d'intéraction avec des prescriptions obsolètes
Afin d'éviter le bruit qui pourrait nuire à ma décision

Scénario: Les prescriptions sans renouvellement ne sont pas utilisées lors du calcul des intéractions
Etant donné que un patient avec une prescription sans renouvellement
Et une prescription valide intéragissant avec l'ancienne
Quand j'ajoute cette prescription au dossier du patient
Alors cette prescription est conservée

Scénario: Les prescriptions avec dernier renouvellement il y a six mois ne sont pas utilisées lors du calcul des intéractions
Etant donné que un patient avec une prescription avec dernier renouvellement il y a six mois
Et une prescription valide intéragissant avec l'ancienne
Quand j'ajoute cette prescription au dossier du patient
Alors cette prescription est conservée