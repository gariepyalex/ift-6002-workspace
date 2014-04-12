Fonctionnalité: Créer une intervention chirurgicale
	En tant que personne responsable de la planification
	Je veux pouvoir créer les intervention à venir
	Afin d'être prêt (avoir tout le matériel) pour l'intervention
	
	Scénario: Créer une intervention chirurgicale valide
		Étant donné un patient existant
		Et une intervention chirurgicale valide
		Lorsque j'ajoute cette intervention chirurgicale au dossier du patient
		Alors cette intervention chirurgicale est conservée
	
	Scénario: Créer une intervention chirurgicale avec des informations manquantes
		Étant donné un patient existant
		Et une intervention chirurgicale valide avec des données manquantes
		Lorsque j'ajoute cette intervention chirurgicale au dossier du patient
		Alors une erreur est retournée
		Et cette erreur a le code "INT001"
		
	Scénario: Créer une intervention chirurgicale avec un patient inexistant
		Étant donné un patient inexistant
		Et une intervention chirurgicale valide
		Lorsque j'ajoute cette intervention chirurgicale au dossier du patient
		Alors une erreur est retournée
		Et cette erreur a le code "INT002"
		
	Scénario: Créer une intervention chirurgicale sans statut
		Étant donné un patient existant
		Et une intervention chirurgicale valide sans statut
		Lorsque j'ajoute cette intervention chirurgicale au dossier du patient
		Alors cette intervention chirurgicale est conservée
		Et le statut de cette intervention est à "planifiée"
 