!--Fonctionnalité: Ajouter des prescriptions
!--	En tant qu'intervenant
!--	Je veux pouvoir ajouter des prescriptions à un patient
!--	Afin de lui fournir ses médicaments lors de son séjour et d'en garder un historique
!-- 
!--	Scénario: Ajouter une prescription avec des informations manquantes
!--		Étant donné un patient existant
!--		Et une prescription valide avec des données manquantes
!--		Lorsque j'ajoute cette prescription au dossier du patient
!--		Alors une erreur est retournée
!--		Et cette erreur a le code "PRES001"
!-- 
!--	Scénario: Ajouter une prescription avec un médicament connu
!--		Étant donné un patient existant
!--		Et une prescription valide avec DIN
!--		Lorsque j'ajoute cette prescription au dossier du patient
!--		Alors cette prescription est conservée
!--
!--	Scénario: Ajouter une prescription avec un médicament par nom
!--		Étant donné un patient existant
!--		Et une prescription valide avec nom de médicament
!--		Lorsque j'ajoute cette prescription au dossier du patient
!--		Alors cette prescription est conservée
!--
!--	Scénario: Ajouter une prescription avec un médicament inconnu
!--		Étant donné un patient existant
!--		Et une prescription valide avec DIN
!--		Et que ce DIN n'existe pas
!--		Lorsque j'ajoute cette prescription au dossier du patient
!--		Alors une erreur est retournée
!--		Et cette erreur a le code "PRES001"
!--
!--	Scénario: Ajouter une prescription avec un din et un nom
!--		Étant donné un patient existant
!--		Et une prescription valide avec DIN
!--		Et avec nom de médicament
!--		Lorsque j'ajoute cette prescription au dossier du patient
!--		Alors une erreur est retournée
!--		Et cette erreur a le code "PRES001"
!--
!--	Scénario: Ajouter une prescription avec un din et un nom contenant seulement des espaces
!--		Étant donné un patient existant
!--		Et une prescription valide avec DIN
!--		Et avec nom de médicament contenant seulement des espaces
!--		Lorsque j'ajoute cette prescription au dossier du patient
!--		Alors cette prescription est conservée
