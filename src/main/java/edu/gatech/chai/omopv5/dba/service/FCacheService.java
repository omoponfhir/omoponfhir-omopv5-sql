/*
  * Filename: /Users/mc142/Documents/workspace/FHIR/OMOPonFHIR/omoponfhir-omopv5-sql/src/main/java/edu/gatech/chai/omopv5/dba/service/FactRelationshipService copy.java
  * Path: /Users/mc142/Documents/workspace/FHIR/OMOPonFHIR/omoponfhir-omopv5-sql/src/main/java/edu/gatech/chai/omopv5/dba/service
  * Created Date: Monday, February 27th 2023, 6:49:43 pm
  * Author: Myung Choi
  * 
  * Copyright (c) 2023 GTRI - Health Emerging and Advanced Technologies (HEAT)
  */
  package edu.gatech.chai.omopv5.dba.service;

  import java.sql.ResultSet;
  import java.sql.ResultSetMetaData;
  import java.sql.SQLException;
  import java.util.List;
 
  import com.google.cloud.bigquery.FieldValueList;
  import edu.gatech.chai.omopv5.model.entity.FCache;
 
  // TODO: Auto-generated Javadoc
  /**
   * The Interface FactRelationshipService.
   */
  public interface FCacheService extends IService<FCache> {
 
      /**
       * Search fact relationship.
       *
       * @param sql 		the string of sql
       * @return count	the count of the query sql
       */
      public Integer searchQueryCount(String sql);
 
      /**
       * Walk thru entire list and update cache if status is < 0
       */
      public void updateCache();
 
      /**
       * 
       * @param table
       */
      public void invalidate(String table);
 
      /**
       * 
       * @param keyText
       * @param valueText
       * @param valueInt
       * @param status
       * @return
       */
      public FCache updateQuery(String keyText, String valueText, Integer valueInt, Integer status);
 
      /**
       * 
       * @param rs
       * @param fCache
       * @param alias
       * @return
       */
      public static FCache _construct(ResultSet rs, FCache fCache, String alias) {
          if (fCache == null)
              fCache = new FCache();
 
          if (alias == null || alias.isEmpty())
              alias = FCache._getTableName();
 
          try {
              ResultSetMetaData metaData = rs.getMetaData();
              int totalColumnSize = metaData.getColumnCount();
              for (int i = 1; i <= totalColumnSize; i++) {
                  String columnInfo = metaData.getColumnName(i);
 
                  if (columnInfo.equalsIgnoreCase(alias + "_cache_id")) {
                      fCache.setId(rs.getLong(columnInfo));
                      if (rs.wasNull()) return null;
                  } else if (columnInfo.equalsIgnoreCase(alias + "_key_text")) {
                      fCache.setKeyText(rs.getString(columnInfo));
                  } else if (columnInfo.equalsIgnoreCase(alias + "_value_text")) {
                      fCache.setValueText(rs.getString(columnInfo));
                  } else if (columnInfo.equalsIgnoreCase(alias + "_value_int")) {
                      fCache.setValueInt(rs.getInt(columnInfo));
                  } else if (columnInfo.equalsIgnoreCase(alias + "_status")) {
                      fCache.setStatus(rs.getInt(columnInfo));
                  }
              }
          } catch (SQLException e) {
              e.printStackTrace();
              return null;
          }
 
          return fCache;
      }
 
      /**
       * 
       * @param rowResult
       * @param fCache
       * @param alias
       * @param columns
       * @return
       */
      public static FCache _construct(FieldValueList rowResult, FCache fCache, String alias,
              List<String> columns) {
          if (fCache == null)
              fCache = new FCache();
 
          if (alias == null || alias.isEmpty())
              alias = FCache._getTableName();
 
          for (String columnInfo : columns) {
              if (rowResult.get(columnInfo).isNull()) continue;
 
              if (columnInfo.equalsIgnoreCase(alias + "_cache_id")) {
                  fCache.setId(rowResult.get(columnInfo).getLongValue());
              } else if (columnInfo.equalsIgnoreCase(alias + "_key_text")) {
                  fCache.setKeyText(rowResult.get(columnInfo).getStringValue());
              } else if (columnInfo.equalsIgnoreCase(alias + "_value_text")) {
                  fCache.setValueText(rowResult.get(columnInfo).getStringValue());
              } else if (columnInfo.equalsIgnoreCase(alias + "_value_int")) {
                  fCache.setValueInt(rowResult.get(columnInfo).getNumericValue().intValue());
              } else if (columnInfo.equalsIgnoreCase(alias + "_status")) {
                  fCache.setStatus(rowResult.get(columnInfo).getNumericValue().intValue());
              }
          }
 
          return fCache;
      }
 
  }