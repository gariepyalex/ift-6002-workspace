Add a Prescription

Narrative:
In order to give a patient his drugs and to keep an history
As a medical responder
I want to add prescriptions to a patient
					 
Scenario:  Add a prescription with missing data
Given an existing patient
And a valid prescription with missing data
When adding a prescription to the patient
Then an error is reported
And this error has code 'PRES001'