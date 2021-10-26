package edu.gatech.chai.omopv5.dba.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.ohdsi.sql.SqlTranslate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;

import edu.gatech.chai.omopv5.model.entity.BaseEntity;
import edu.gatech.chai.omopv5.model.entity.Concept;
import edu.gatech.chai.omopv5.model.entity.FactRelationship;
import edu.gatech.chai.omopv5.model.entity.Note;

// TODO: Auto-generated Javadoc
/**
 * The Class FactRelationshipServiceImp.
 */
@Service
public class FactRelationshipServiceImp extends BaseEntityServiceImp<FactRelationship>
		implements FactRelationshipService {
	private static final Logger logger = LoggerFactory.getLogger(FactRelationshipServiceImp.class);

	/**
	 * Instantiates a new fact relationship service imp.
	 */
	public FactRelationshipServiceImp() {
		super(FactRelationship.class);
	}

	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.FactRelationshipService#searchMeasurementUsingMethod(java.lang.Long)
	 */
	public <V extends BaseEntity> List<V> searchMeasurementUsingMethod(Long domainId) {
		List<V> retVal = new ArrayList<V>();

		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		// Concept ID:
		// 21 = Measurement, 27 = Observation,
		// 44818800 = Using finding method
		// 58 = Type Concept, 26 = Note Type
		// 44818721 = Contains
		String queryString = "SELECT fact_relationship.domain_concept_id_1 as fact_relationship_domain_concept_id_1, "
			+ "fact_relationship.fact_id_1 as fact_relationship_fact_id_1, "
			+ "fact_relationship.domain_concept_id_2 as fact_relationship_domain_concept_id_2, "
			+ "fact_relationship.fact_id_2 as fact_reationship_fact_id_2, "
			+ "fact_relationship.relationship_concept_id as fact_relationship_relationship_concept_id "
			+ "FROM fact_relationship fact_relationship WHERE fact_relationship.domain_concept_id_1 = 21 "
			+ "AND fact_relationship.relationship_concept_id = 44818800 AND fact_relationship.fact_id_1 = @fact1";
		parameterList.add("fact1");
		valueList.add(domainId.toString());

		queryString = renderedSql(queryString, parameterList, valueList);

		try {
			if (isBigQuery()) {
				TableResult result = runBigQuery(queryString);
				List<String> columns = listOfColumns(queryString);
				for (FieldValueList row : result.iterateAll()) {
					FactRelationship entity = construct(row, null, getSqlTableName(), columns);
					
					Long domainConcept2 = entity.getDomainConceptId2();
					Long fact2 = entity.getFactId2();

					if (domainConcept2 == 58L) {
						Concept obsConcept = new Concept(fact2);
						retVal.add((V) obsConcept);
					} else if (domainConcept2 == 26L) {
						Note obsNote = new Note(fact2);
						retVal.add((V) obsNote);
					}
//
//					if (entity != null) {
//						entities.add(new Note(entity.getFactId1()));
//					}
				}
			} else {
				// ResultSet rs = runQuery(queryString);

				String query = SqlTranslate.translateSql(queryString, databaseConfig.getSqlRenderTargetDialect());
				logger.debug("searchMeasurementUsingMethod: Query after SqlRender translate to " + databaseConfig.getSqlRenderTargetDialect() + ": " + query);
				
				Statement stmt = getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(query);
	
				while (rs.next()) {
					FactRelationship entity = construct(rs, null, "fact_relationship");

					Long domainConcept2 = entity.getDomainConceptId2();
					Long fact2 = entity.getFactId2();

					if (domainConcept2 == 58L) {
						Concept obsConcept = new Concept(fact2);
						retVal.add((V) obsConcept);
					} else if (domainConcept2 == 26L) {
						Note obsNote = new Note(fact2);
						retVal.add((V) obsNote);
					}

//					if (entity != null)
//						entities.add(new Note(entity.getFactId1()));
				}
			}
		} catch (Exception e) {
			try {
				closeConnection();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return retVal;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.FactRelationshipService#searchMeasurementContainsComments(java.lang.Long)
	 */
	public List<Note> searchMeasurementContainsComments(Long domainId) {
		List<Note> entities = new ArrayList<Note>();

		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		// 44818721 = Contains
		String queryString = "SELECT fact_relationship.domain_concept_id_1 as fact_relationship_domain_concept_id_1, "
			+ "fact_relationship.fact_id_1 as fact_relationship_fact_id_1, "
			+ "fact_relationship.domain_concept_id_2 as fact_relationship_domain_concept_id_2, "
			+ "fact_relationship.fact_id_2 as fact_reationship_fact_id_2, "
			+ "fact_relationship.relationship_concept_id as fact_relationship_relationship_concept_id "
			+ "FROM fact_relationship fact_relationship WHERE fact_relationship.domain_concept_id_1 = 21 "
			+ "AND fact_relationship.fact_id_1 = @fact1 AND fact_relationship.domain_concept_id_2 = 26 AND fact_relationship.relationship_concept_id = 44818721";
		parameterList.add("fact1");
		valueList.add(domainId.toString());

		queryString = renderedSql(queryString, parameterList, valueList);

		try {
			if (isBigQuery()) {
				TableResult result = runBigQuery(queryString);
				List<String> columns = listOfColumns(queryString);
				for (FieldValueList row : result.iterateAll()) {
					FactRelationship entity = construct(row, null, getSqlTableName(), columns);
					if (entity != null) {
						entities.add(new Note(entity.getFactId1()));
					}
				}
			} else {
				// ResultSet rs = getQueryEntityDao().runQuery(queryString);
				String query = SqlTranslate.translateSql(queryString, databaseConfig.getSqlRenderTargetDialect());
				logger.debug("searchMeasurementContainsComments: Query after SqlRender translate to " + databaseConfig.getSqlRenderTargetDialect() + ": " + query);
		
				Statement stmt = getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(query);
	
				while (rs.next()) {
					FactRelationship entity = construct(rs, null, "fact_relationship");
					if (entity != null)
						entities.add(new Note(entity.getFactId1()));
				}
			}
		} catch (Exception e) {
			try {
				closeConnection();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return entities;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.FactRelationshipService#searchFactRelationship(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	public List<FactRelationship> searchFactRelationship(Long domainConcept1, Long factId1, Long domainConcept2,
			Long factId2, Long relationshipId) {
		List<FactRelationship> entities = new ArrayList<FactRelationship>();

		String queryString = "SELECT fact_relationship.domain_concept_id_1 as fact_relationship_domain_concept_id_1, "
			+ "fact_relationship.fact_id_1 as fact_relationship_fact_id_1, "
			+ "fact_relationship.domain_concept_id_2 as fact_relationship_domain_concept_id_2, "
			+ "fact_relationship.fact_id_2 as fact_reationship_fact_id_2, "
			+ "fact_relationship.relationship_concept_id as fact_relationship_relationship_concept_id "
			+ "FROM fact_relationship fact_relationship WHERE";
		List<String> parameterList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();

		// Construct where clause here.
		String where_clause = "";
		if (domainConcept1 != null) {
			where_clause = FactRelationship._getColumnName("domainConceptId1") + " = @domain1";
			
			parameterList.add("domain1");
			valueList.add(domainConcept1.toString());
		}

		if (factId1 != null) {
			if (where_clause == "")
				where_clause = FactRelationship._getColumnName("factId1") + " = @fact1";
			else
				where_clause += " AND " + FactRelationship._getColumnName("factId1") + " = @fact1";

			parameterList.add("fact1");
			valueList.add(factId1.toString());
		}

		if (domainConcept2 != null) {
			if (where_clause == "")
				where_clause = FactRelationship._getColumnName("domainConceptId2") + " = @domain2";
			else
				where_clause += " AND " + FactRelationship._getColumnName("domainConceptId2") + " = @domain2";
			
			parameterList.add("domain2");
			valueList.add(domainConcept2.toString());
		}

		if (factId2 != null) {
			if (where_clause == "")
				where_clause = FactRelationship._getColumnName("factId2") + " = @fact2";
			else
				where_clause += " AND " + FactRelationship._getColumnName("factId2") + " = @fact2";

			parameterList.add("fact2");
			valueList.add(factId2.toString());
		}

		if (relationshipId != null) {
			if (where_clause == "")
				where_clause = FactRelationship._getColumnName("relationshipConcept") + " = @relationship";
			else
				where_clause += " AND " + FactRelationship._getColumnName("relationshipConcept") + " = @relationship";

			parameterList.add("relationship");
			valueList.add(relationshipId.toString());
		}

		queryString += " " + where_clause;

		queryString = renderedSql(queryString, parameterList, valueList);

		logger.debug("Query for FactRelationship: " + queryString);
		logger.debug(FactRelationship._getColumnName("domainConceptId1") + ":" + domainConcept1 + " " 
				+ FactRelationship._getColumnName("factId1") + ":" + factId1 + " " 
				+ FactRelationship._getColumnName("domainConceptId2") + ":" + domainConcept2 + " "
				+ FactRelationship._getColumnName("factId2") + ":" + factId2 + " "
				+ FactRelationship._getColumnName("relationshipConcept") + ":" + relationshipId);

		try {
			if (isBigQuery()) {
				TableResult result = runBigQuery(queryString);
				List<String> columns = listOfColumns(queryString);
				for (FieldValueList row : result.iterateAll()) {
					FactRelationship entity = construct(row, null, getSqlTableName(), columns);
					if (entity != null) {
						entities.add(entity);
					}
				}
			} else {
				// ResultSet rs = getQueryEntityDao().runQuery(queryString);
				List<FactRelationship> myEntities = runQuery(queryString, null, "fact_relationship");
				entities.addAll(myEntities);

				// while (rs.next()) {
				// 	FactRelationship entity = construct(rs, null, "t");
				// 	if (entity != null)
				// 		entities.add(entity);
				// }
			}
		} catch (Exception e) {
			try {
				closeConnection();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

//
//		String query;
//		List<FactRelationship> factRelationships = null;
//		if (factId2 != null) {
//			query = "SELECT t FROM FactRelationship t WHERE domain_concept_id_1 = :domain1 AND fact_id_1 = :fact1 AND domain_concept_id_2 = :domain2 AND fact_id_2 = :fact2 AND relationship_concept_id = :relationship";
//		} else {
//			query = "SELECT t FROM FactRelationship t WHERE domain_concept_id_1 = :domain1 AND fact_id_1 = :fact1 AND domain_concept_id_2 = :domain2 AND relationship_concept_id = :relationship";
//		}

		return entities;
	}

	@Override
	public FactRelationship findById(Long id) {
		return null;
	}

	@Override
	public Long removeById(Long id) {
		return null;
	}

	@Override
	public FactRelationship update(FactRelationship entity) {
		return null;
	}

	@Override
	public FactRelationship construct(ResultSet rs, FactRelationship entity, String alias) {
		return FactRelationshipService._construct(rs, entity, alias);
	}

	@Override
	public FactRelationship construct(FieldValueList rowResult, FactRelationship entity, String alias,
			List<String> columns) {
		return FactRelationshipService._construct(rowResult, entity, alias, columns);
	}

}
