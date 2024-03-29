Add prescription

Narrative: Ajouter des prescriptions
En tant qu'intervenant
Je veux pouvoir ajouter des prescriptions à un patient
Afin de lui fournir ses médicaments lors de son séjour et d'en garder un historique
 
Scénario: Ajouter une prescription avec des informations manquantes
Etant donné que un patient est existant
Et une prescription avec des données manquantes
Quand j'ajoute cette prescription au dossier du patient
Alors une erreur est retournée
Alors cette erreur a le code "PRES001"
Et le protocole HTTP retourné est le 400

Scénario: Ajouter une prescription avec un médicament connu
Etant donné que un patient est existant
Et une prescription valide avec DIN
Quand j'ajoute cette prescription au dossier du patient
Alors cette prescription est conservée
Et le protocole HTTP retourné est le 201

Scénario: Ajouter une prescription avec un médicament par nom
Etant donné que un patient est existant
Et une prescription avec nom de médicament
Quand j'ajoute cette prescription au dossier du patient
Alors cette prescription est conservée
Et le protocole HTTP retourné est le 201

Scénario: Ajouter une prescription avec un médicament par DIN et par NOM
Etant donné que un patient est existant
Et une prescription avec un DIN et un nom de médicament
Quand j'ajoute cette prescription au dossier du patient
Alors une erreur est retournée
Alors cette erreur a le code "PRES001"
Et le protocole HTTP retourné est le 400

Scénario: Ajouter une prescription avec un médicament inconnu
Etant donné que un patient est existant
Et une prescription avec un DIN inexistant
Quand j'ajoute cette prescription au dossier du patient
Alors une erreur est retournée
Alors cette erreur a le code "PRES001"
Et le protocole HTTP retourné est le 400

Scénario: Ajouter une prescription avec un nombre de renouvellements invalide 
Etant donné que un patient est existant
Et une prescription avec un nombre de renouvellements invalide 
Quand j'ajoute cette prescription au dossier du patient
Alors une erreur est retournée
Alors cette erreur a le code "PRES001"
Et le protocole HTTP retourné est le 400
