Drug Research

Narrative: 
En tant qu'archiviste
Je veux pouvoir chercher des médicaments par leur nom
Afin de pouvoir trouver le DIN dans le cas d'une prescription manuscrite par un médecin

Scénario: Recherche avec trop peu de caractères
Quand je cherche un médicament avec moins de caractères que la limite requise
Alors une erreur est retournée
Alors cette erreur a le code "DIN001"
Et le protocole HTTP retourné est le 400

Scénario: Recherche avec un mot clé valide dans le nom
Quand je cherche des médicaments avec un mot-clé qui se retrouve dans quelques noms de médicaments
Alors la liste de médicaments retournée contient ceux-ci
Et le protocole HTTP retourné est le 200

Scénario: Recherche avec un mot clé valide dans la description
Quand je cherche des médicaments avec un mot-clé qui se retrouve dans quelques descriptions de médicaments
Alors la liste de médicaments retournée contient ceux-ci
Et le protocole HTTP retourné est le 200

Scénario: Recherche avec un mot clé contenant un patron générique
Quand je cherche des médicaments avec un mot-clé qui contient un patron générique
Alors la liste de médicaments retournée contient ceux-ci
Et le protocole HTTP retourné est le 200