
Database layer for OMOPonFHIR with OMOP v5.3.1 using SqlRender to access database like BigQuery.
Current testing procedure (Writing to OMOP only)

| RESOURCE             | TEST1 with internal | TEST2 with AoU data |
|----------------------|---------------------|---------------------|
| Patient              | checked		         | checked             |
| Encounter            | checked		         | checked             |
| Condition            | not tested		       | not tested          |
| Procedure            | not tested		       | not tested          |
| Observation.         | checked		         | checked             |
| MedicationStatement  | checked		         | checked             |
| MedicationOrder      | not tested		       | failed              |
| Organization         | checked		         | not tested          |
| Practitioner         | checked		         | checked             |
| DeviceUseStatement   | not tested		       | not tested          |
| DocumentReference    | not tested		       | not tested          |
| Immunization         | not tested		       | not tested          |
| AllergyIntolerance   | not tested		       | not tested          |
