package edu.gatech.chai.omopv5.dba.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import edu.gatech.chai.omopv5.model.entity.CareSite;
import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.FPerson;
import edu.gatech.chai.omopv5.model.entity.Provider;
import edu.gatech.chai.omopv5.model.entity.VisitDetail;
import edu.gatech.chai.omopv5.model.entity.VisitOccurrence;

public interface VisitDetailService extends IService<VisitDetail> {
	public static VisitDetail _construct(ResultSet rs, VisitDetail visitDetail, String alias) {
		if (visitDetail == null) visitDetail = new VisitDetail();
		
		if (alias == null || alias.isEmpty())
			alias = VisitDetail._getTableName();

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int totalColumnSize = metaData.getColumnCount();
			for (int i = 1; i <= totalColumnSize; i++) {
				String columnInfo = metaData.getColumnName(i);

				if (columnInfo.equalsIgnoreCase(alias + "_visit_detail_id")) {
					visitDetail.setId(rs.getLong(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase("fPerson_person_id")) {
					FPerson fPerson = FPersonService._construct(rs, null, "fPerson");
					visitDetail.setFPerson(fPerson);
				}

				if (columnInfo.equalsIgnoreCase("visitDetailConcept_concept_id")) {
					Concept visitDetailConcept = ConceptService._construct(rs, null, "visitDetailConcept");
					visitDetail.setVisitDetailConcept(visitDetailConcept);
				}

				if (columnInfo.equalsIgnoreCase(alias + "_visit_detail_start_date")) {
					visitDetail.setVisitDetailStartDate(rs.getDate(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_visit_detail_start_datetime")) {
					visitDetail.setVisitDetailStartDateTime(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_visit_detail_end_date")) {
					visitDetail.setVisitDetailEndDate(rs.getDate(columnInfo));
				}
				
				if (columnInfo.equalsIgnoreCase(alias + "_visit_detail_end_datetime")) {
					visitDetail.setVisitDetailEndDateTime(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("visitDetailTypeConcept_concept_id")) {
					Concept visitDetailTypeConcept = ConceptService._construct(rs, null, "visitDetailTypeConcept");
					visitDetail.setVisitDetailTypeConcept(visitDetailTypeConcept);
				}
				
				if (columnInfo.equalsIgnoreCase("provider_provider_id")) {
					Provider provider = ProviderService._construct(rs, null, "provider");
					visitDetail.setProvider(provider);
				}

				if (columnInfo.equalsIgnoreCase("careSite_provider_id")) {
					CareSite careSite = CareSiteService._construct(rs, null, "careSite");
					visitDetail.setCareSite(careSite);
				}

				if (columnInfo.equalsIgnoreCase("admittingSourceConcept_concept_id")) {
					Concept admittingSourceConcept = ConceptService._construct(rs, null, "admittingSourceConcept");
					visitDetail.setAdmittingSourceConcept(admittingSourceConcept);
				}
				
				if (columnInfo.equalsIgnoreCase("dischargeToConcept_concept_id")) {
					Concept dischargeToConcept = ConceptService._construct(rs, null, "dischargeToConcept");
					visitDetail.setDischargeToConcept(dischargeToConcept);
				}
				
				if (columnInfo.equalsIgnoreCase("precedingVisitDetail_visit_detail_id")) {
					VisitDetail precedingVisitDetail = VisitDetailService._construct(rs, null, "precedingVisitDetail");
					visitDetail.setPrecedingVisitDetail(precedingVisitDetail);
				}
				
				if (columnInfo.equalsIgnoreCase(alias + "_visit_detail_source_value")) {
					visitDetail.setVisitDetailSourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("visitDetailSourceConcept_concept_id")) {
					Concept visitDetailSourceConcept = ConceptService._construct(rs, null, "visitDetailSourceConcept");
					visitDetail.setVisitDetailSourceConcept(visitDetailSourceConcept);
				}
				
				if (columnInfo.equalsIgnoreCase(alias + "_admitting_source_value")) {
					visitDetail.setAdmittingSourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase(alias + "_discharge_to_source_value")) {
					visitDetail.setDischargeToSourceValue(rs.getString(columnInfo));
				}

				if (columnInfo.equalsIgnoreCase("visitDetailParent_visit_detail_id")) {
					VisitDetail visitDetailParent = VisitDetailService._construct(rs, null, "visitDetailParent");
					visitDetail.setVisitDetailParent(visitDetailParent);
				}

				if (columnInfo.equalsIgnoreCase("visitOccurrence_visit_occurrence_id")) {
					VisitOccurrence visitOccurrence = VisitOccurrenceService._construct(rs, null, "visitOccurrence");
					visitDetail.setVisitOccurrence(visitOccurrence);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return visitDetail;
	}
}
