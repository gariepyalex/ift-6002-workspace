Detailed summary of prescriptions

Narrative:
En tant qu'intervenant
Je veux obtenir le détail et l'historique des prescriptions
Afin de pouvoir faire un suivi auprès du patient

Scénario: Retrouver le sommaire détaillé des prescriptions d'un patient existant
Etant donné que un patient avec plusieurs prescriptions et des consommations
Quand je demande le sommaire détaillé des prescriptions de ce patient
Alors toutes les informations du sommaire détaillé sont affichées
Alors toutes les prescriptions sont affichées en ordre décroissant de date
Alors toutes les consommations des prescriptions sont affichées en ordre décroissant de date
Et le protocole HTTP retourné est le 200

Scénario: Retrouver le sommaire détaillé des prescriptions d'un patient inexistant
Etant donné que un patient est inexistant
Quand je demande le sommaire détaillé des prescriptions de ce patient
Alors une erreur est retournée
Alors cette erreur a le code "PRES010"
Et le protocole HTTP retourné est le 404