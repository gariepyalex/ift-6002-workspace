Narrative: Ajouter des prescriptions
  En tant qu'intervenant
  Je veux pouvoir ajouter des prescriptions à un patient
  Afin de lui fournir ses médicaments lors de son séjour et d'en garder un historique
 
  Scénario: Ajouter une prescription avec des informations manquantes
    Étant donné un patient existant
    Et une prescription avec des données manquantes
    Quand j'ajoute cette prescription au dossier du patient
    Alors une erreur est retournée
    Et cette erreur a le code "PRES001"