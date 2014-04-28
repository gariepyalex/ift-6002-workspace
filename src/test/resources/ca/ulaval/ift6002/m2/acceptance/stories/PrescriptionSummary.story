Summary of prescriptions

Narrative: Obtenir le sommaire des prescriptions d'un patient
En tant qu'intervenant
Je veux obtenir un sommaire de l'historique des prescriptions d'un patient
Afin de pouvoir faire un suivi auprès du patient

Scénario: Retrouver le sommaire des prescriptions d'un patient inexistant
Etant donné que un patient est inexistant
Quand je demande le sommaire des prescriptions de ce patient
Alors une erreur est retournée
Alors cette erreur a le code "PRES010"
Et le protocole HTTP retourné est le 404

Scénario: Retrouver le sommaire des prescriptions d'un patient existant
Etant donné que un patient avec plusieurs prescriptions
Quand je demande le sommaire des prescriptions de ce patient
Alors toutes les informations du sommaire sont affichées
Alors toutes les prescriptions sont affichées en ordre décroissant de date
Et le protocole HTTP retourné est le 200