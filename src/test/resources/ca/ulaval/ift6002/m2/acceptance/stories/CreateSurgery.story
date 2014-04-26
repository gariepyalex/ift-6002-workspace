Create Surgery
 
Narrative:
Comme personne responsable de la planification,
Je veux pouvoir créer les interventions à venir,
Afin d'être prêt (avoir tout le matériel) pour l'intervention.
 
Scénario: Créer une intervention avec des informations manquantes
Etant donné que un patient est existant
Et une intervention avec des informations manquantes
Quand j'ajoute cette intervention au dossier du patient
Alors une erreur est retournée
Alors cette erreur a le code "INT001"
Et le protocole HTTP retourné est le 400

Scénario: Créer une intervention avec un patient inexistant
Etant donné que un patient est inexistant
Et une intervention valide
Quand j'ajoute cette intervention au dossier du patient
Alors une erreur est retournée
Alors cette erreur a le code "INT002"
Et le protocole HTTP retourné est le 400

Scénario: Créer une intervention chirurgicale valide
Etant donné que un patient est existant
Et une intervention valide
Quand j'ajoute cette intervention au dossier du patient
Alors cette intervention est conservée
Et le protocole HTTP retourné est le 201
