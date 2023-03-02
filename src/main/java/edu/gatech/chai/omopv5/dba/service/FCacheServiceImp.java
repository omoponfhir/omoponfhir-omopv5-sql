/*
  * Filename: /Users/mc142/Documents/workspace/FHIR/OMOPonFHIR/omoponfhir-omopv5-sql/src/main/java/edu/gatech/chai/omopv5/dba/service/FactRelationshipServiceImp copy.java
  * Path: /Users/mc142/Documents/workspace/FHIR/OMOPonFHIR/omoponfhir-omopv5-sql/src/main/java/edu/gatech/chai/omopv5/dba/service
  * Created Date: Monday, February 27th 2023, 6:48:48 pm
  * Author: Myung Choi
  * 
  * Copyright (c) 2023 GTRI - Health Emerging and Advanced Technologies (HEAT)
  */
  package edu.gatech.chai.omopv5.dba.service;

  import java.sql.ResultSet;
  import java.util.ArrayList;
  import java.util.Arrays;
  import java.util.List;
 
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  import org.springframework.stereotype.Service;
 
  import com.google.cloud.bigquery.FieldValueList;
  import com.google.cloud.bigquery.TableResult;
 
  import edu.gatech.chai.omopv5.model.entity.FCache;
 
  /**
   * The Class FactRelationshipServiceImp.
   */
  @Service
  public class FCacheServiceImp extends BaseEntityServiceImp<FCache>
          implements FCacheService {
      private static final Logger logger = LoggerFactory.getLogger(FactRelationshipServiceImp.class);
 
      /**
       * Instantiates a new fact relationship service imp.
       */
      public FCacheServiceImp() {
          super(FCache.class);
      }
 
      public FCache findByKeyText(String keyText) {
          List<ParameterWrapper> paramList = new ArrayList<ParameterWrapper>();
          ParameterWrapper parameter = new ParameterWrapper("String", Arrays.asList("keyText"), Arrays.asList("="), Arrays.asList(keyText), "or");
          paramList.add(parameter);
 
          List<FCache> results = searchWithParams(0, 0, paramList, null);
          if (results != null && !results.isEmpty()) {
              return results.get(0);
          } 
 
          return null;
      }
 
      @Override
      public FCache construct(ResultSet rs, FCache entity, String alias) {
          return FCacheService._construct(rs, entity, alias);
      }
 
      @Override
      public FCache construct(FieldValueList rowResult, FCache entity, String alias,
              List<String> columns) {
          return FCacheService._construct(rowResult, entity, alias, columns);
      }
 
      @Override
      public Integer searchQueryCount(String sql) {
          // return -1 if this sql does not exit or status is -1.
          FCache fCache = findByKeyText(sql);
          if (fCache == null || fCache.getStatus() == -1) return -1;
 
          return fCache.getValueInt();
      }
 
      @Override
      public FCache updateQuery(String keyText, String valueText, Integer valueInt, Integer status) {
          FCache fCache = new FCache();
          fCache.setKeyText(keyText);
          fCache.setValueText(valueText);
          fCache.setValueInt(valueInt);
          fCache.setStatus(status);
 
          FCache exitingFCache = findByKeyText(fCache.getKeyText());
          if (exitingFCache == null) {
              return create(fCache);
          } else {
              exitingFCache.setValueInt(valueInt);
              exitingFCache.setStatus(status);
              return update(exitingFCache);
          }
 
      }
 
      @Override
      public void updateCache() {
          List<ParameterWrapper> paramList = new ArrayList<ParameterWrapper>();
          ParameterWrapper parameter = new ParameterWrapper("Integer", Arrays.asList("status"), Arrays.asList("<"), Arrays.asList("0"), "or");
          paramList.add(parameter);
 
          List<FCache> results = searchWithParams(0, 0, paramList, null);
          for (FCache fCache : results) {
              String sql = fCache.getKeyText();
 
              Long count = null;
              try {
                  if (isBigQuery()) {
                      TableResult result = runBigQuery(sql);
                      for (FieldValueList row : result.iterateAll()) {
                          count = row.get("count").getLongValue();
                          if (count != null) {
                              break;
                          }
                      }
                  } else {
                      count = runAliasQuery(sql, "count");;
                  }
              } catch (Exception e) {
                  e.printStackTrace();
              }
 
              if (count != null) {
                  fCache.setValueInt(count.intValue());
                  fCache.setStatus(0);
 
                  update(fCache);
              }
          }
      }
 
      @Override
      public void invalidate(String table) {
          if (getSqlTableName().equals(table)) return;
 
          List<ParameterWrapper> paramList = new ArrayList<ParameterWrapper>();
          ParameterWrapper parameter = new ParameterWrapper("String", Arrays.asList("keyText"), Arrays.asList("like"), Arrays.asList("%" + table + "%"), "or");
          paramList.add(parameter);
 
          List<FCache> results = searchWithParams(0, 0, paramList, null);
          for (FCache fCache : results) {
              fCache.setStatus(-1);
              fCacheService.update(fCache);
          }
      }
  }