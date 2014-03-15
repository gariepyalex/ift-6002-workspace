##Remise 2
Numéro d'équipe : M2

Pour la remise 2, voir le tag *remise2* sur la branche *master*.

###Membres :

* Mathieu Carpentier 111 044 647
* Jean-Samuel Bédard 111 043 631
* Alexandre Gariépy 111 046 788
* Dario Martins Silva 111 050 189
* Yohan Caron 111 049 757
* Alexandre Walsh 111 049 448
* Marc-Olivier Duval 111 048 144
* Jonathan Lessard 111 048 299
* Guillaume Lambert 111 040 923

###Statut des user stories :
####Terminé :
* Ajouter une prescription
* Créer une intervention chirurgicale
* Marquer l'utilisation d'un instrument
* Recherche de médicaments

####Non commencé :
* Détection des interactions
* Consommer les prescriptions
* Ignorer les prescriptions obsolétes pour les intéractions
* Obtenir le commaire des prescriptions d'un patient
* Obtenir le détail et l'historique des prescriptions
* Ajouter une prescription à un patient mort
* Rappeler les patients ayant eu un instrument contaminé

###Utilisation de l'application
Le port utilisé est 8080.

Le Main est dans le package ca.ulaval.ift6002.m2

###Notes quelconques
Nous sommes conscient d'un petit bug en hibernate où qu'on ne peut pas stocker plusieurs 
fois un même instrument anonyme. Ne considérant pas ceci, la story serait complétée et testée.

Il faut comprendre que nous n'avons pas encore vu les tests d'intégrations, 
alors nous avons seulement des tests unitaires. 
