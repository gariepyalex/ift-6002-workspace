Narrative:
Comme infirmière de bloc,
Je veux pouvoir indiquer quels instruments sont utilisés lors de l'intervention,
Afin d'assurer une traçabilité des instruments.

Scénario: Ajouter un instrument à une intervention
Etant donné que une intervention existante
Et un instrument valide
Quand j'ajoute cet instrument à l'intervention
Alors cet instrument a été ajouté à l'intervention
Et le protocole HTTP retourné est le 201

Scénario: Modifier le statut d'un instrument d'une intervention
Etant donné que une intervention existante
Et un instrument valide associé à cette intervention
Quand je modifie le statut de cet instrument
Alors cet instrument a été modifié
Et le protocole HTTP retourné est le 200

Scénario: Ajouter un instrument sans statut
Etant donné que une intervention existante
Et un instrument sans statut
Quand j'ajoute cet instrument à l'intervention
Alors une erreur est retournée
Alors cette erreur a le code "INT010"
Et le protocole HTTP retourné est le 400

Scénario: Modifier un instrument sans statut
Etant donné que une intervention existante
Et un instrument sans statut
Quand je modifie le statut de cet instrument
Alors une erreur est retournée
Alors cette erreur a le code "INT010"
Et le protocole HTTP retourné est le 400

Scénario: Modifier un instrument sans numéro de série
Etant donné que une intervention existante
Et un instrument sans numéro de série
Quand je modifie le statut de cet instrument
Alors une erreur est retournée
Alors cette erreur a le code "INT012"
Et le protocole HTTP retourné est le 400

Scénario: Ajouter deux fois un instrument avec le même numéro de série
Etant donné que une intervention existante
Et un instrument valide associé à cette intervention
Quand j'ajoute cet instrument à l'intervention
Alors une erreur est retournée
Alors cette erreur a le code "INT011"
Et le protocole HTTP retourné est le 400