/*******************************************************************************
  * Copyright (c) 2019 Georgia Tech Research Institute
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *     http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  *
  *******************************************************************************/
  package edu.gatech.chai.omopv5.model.entity;

  import java.lang.reflect.Field;
  import java.util.List;
 
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
 
  import edu.gatech.chai.omopv5.model.entity.custom.Column;
  import edu.gatech.chai.omopv5.model.entity.custom.GeneratedValue;
  import edu.gatech.chai.omopv5.model.entity.custom.GenerationType;
  import edu.gatech.chai.omopv5.model.entity.custom.Id;
  import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
  import edu.gatech.chai.omopv5.model.entity.custom.Table;
 
  /** 
   * This class adds some properties to the Omop data model Person, in order to provide
   * all the data specified for FHIR.
   * @author Ismael Sarmento
   */
  @Table(name = "f_cache")
  public class FCache extends BaseEntity {
      private static final Logger logger = LoggerFactory.getLogger(FCache.class);
 
      @Id
      @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cache_id_seq")
      @Column(name="cache_id", nullable=false)
      private Long id;
 
      @Column(name="key_text", nullable=false)
      private String keyText;
 
      @Column(name="value_text")
      private String valueText;
 
      @Column(name="value_int")
      private Integer valueInt;
 
      @Column(name="status")
      private Integer status;
 
      public FCache() {
          super();
      }
 
 
      public String getKeyText() {
          return keyText;
      }
 
      public void setKeyText(String keyText) {
          this.keyText = keyText;
      }
 
      public String getValueText() {
          return valueText;
      }
 
      public void setValueText(String valueText) {
          this.valueText = valueText;
      }
 
      public Integer getValueInt() {
          return valueInt;
      }
 
      public void setValueInt(Integer valueInt) {
          this.valueInt = valueInt;
      }
 
      public Integer getStatus() {
          return status;
      }
 
      public void setStatus(Integer status) {
          this.status = status;
      }
 
      public Long getId() {
          return id;
      }
 
      public void setId(Long id) {
          this.id = id;
      }
 
      @Override
      public Long getIdAsLong() {
          return getId();
      }
 
      @Override
      public String getColumnName(String columnVariable) {
          return FCache._getColumnName(columnVariable);
      }
 
      @Override
       public String getFirstColumnName() {
           return "key_text";
       }
 
      public static String _getColumnName(String columnVariable) {
          try {
              Field field = FCache.class.getDeclaredField(columnVariable);
              if (field != null) {
                  Column annotation = field.getDeclaredAnnotation(Column.class);
                  if (annotation != null) {
                      return FCache._getTableName() + "." + annotation.name();
                  } else {
                      JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
                      if (joinAnnotation != null) {
                          return FCache._getTableName() + "." + joinAnnotation.name();
                      }
 
                      System.out.println("WARNING: annotation is null for field="+field.toString());
                      return null;
                  }
              }
          } catch (NoSuchFieldException e) {
              if (!"id".equals(columnVariable)) e.printStackTrace();
           } catch (SecurityException e) {
               e.printStackTrace();
           }
 
          return null;
      }
 
      @Override
      public String getTableName() {
          return FCache._getTableName();
      }
 
      public static String _getTableName() {
          Table annotation = FCache.class.getDeclaredAnnotation(Table.class);
          if (annotation != null) {
              return annotation.name();
          }
          return "f_cache";
      }
 
      @Override
      public String getForeignTableName(String foreignVariable) {
          return FCache._getForeignTableName(foreignVariable);
      }
 
      public static String _getForeignTableName(String foreignVariable) {
          // There is no foreign key for this table
          return null;
      }
 
      @Override
      public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
          return FCache._getSqlTableStatement(parameterList, valueList);
      }
 
      public static String _getSqlTableStatement(List<String> parameterList, List<String> valueList) {
          return "select * from f_cache ";
      }
 
  }