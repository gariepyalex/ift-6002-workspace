Narrative:
Comme infirmière de bloc,
Je veux pouvoir indiquer quels instruments sont utilisés lors de l'intervention,
Afin d'assurer une traçabilité des instruments.

Scénario: Ajouter un instrument à une intervention
Etant donné que une intervention existante
Et un instrument valide
Quand j'ajoute cet instrument à l'intervention
Alors cet instrument a été ajouté à l'intervention

Scénario: Modifier le statut d'un instrument d'une intervention
Etant donné que une intervention existante
Et un instrument existant
Quand je modifie le statut de cet instrument
Alors cet instrument a été modifié

Scénario: Ajouter un instrument sans statut
Etant donné que une intervention existante
Et un instrument sans statut
Quand j'ajoute cet instrument à l'intervention
Alors une erreur est retournée
Et cette erreur a le code "INT010"

Scénario: Modifier un instrument sans statut
Etant donné que une intervention existante
Et un instrument sans statut
Quand je modifie le statut de cet instrument
Alors une erreur est retournée
Et cette erreur a le code "INT010"