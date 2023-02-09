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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.gatech.chai.omopv5.model.entity.custom.Column;
import edu.gatech.chai.omopv5.model.entity.custom.Id;
import edu.gatech.chai.omopv5.model.entity.custom.JoinColumn;
import edu.gatech.chai.omopv5.model.entity.custom.Table;

/** 
 * This class adds some properties to the Omop data model Person, in order to provide
 * all the data specified for FHIR.
 * @author Ismael Sarmento
 */
@Table(name = "f_person")
public class FPerson extends Person {
	private static final Logger logger = LoggerFactory.getLogger(FPerson.class);
	
	@Id
	@JoinColumn(name="person_id", table="person:person", nullable=false)
	private Long id;
	
	@Column(name="family_name")
	private String familyName;
	
	@Column(name="given1_name")	
	private String givenName1;
	
	@Column(name="given2_name")
	private String givenName2;

	@Column(name="prefix_name")
	private String prefixName;
	
	@Column(name="suffix_name")
	private String suffixName;
	
	@Column(name="preferred_language")
	private String preferredLanguage;
	
	@Column(name="ssn")
	private String ssn;
	
	@Column(name="maritalstatus")
	private String maritalStatus;
	
	@Column(name="active")
	private Short active;
	
	@Column(name="contact_point1")
	private String contactPoint1;
	
	@Column(name="contact_point2")
	private String contactPoint2;
	
	@Column(name="contact_point3")
	private String contactPoint3;
	
	public FPerson() {
		super();
	}

//	public Long getId() {
//		if (id == null) 
//			return super.getId();
//		return id;
//	}
//	
//	public void setId(Long id) {
//		this.id = id;
//		super.setId(id);
//	}
	
	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName1() {
		return givenName1;
	}

	public void setGivenName1(String givenName1) {
		this.givenName1 = givenName1;
	}

	public String getGivenName2() {
		return givenName2;
	}

	public void setGivenName2(String givenName2) {
		this.givenName2 = givenName2;
	}

	public String getPrefixName() {
		return prefixName;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	public String getSuffixName() {
		return suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	public Short getActive() {
		return active;
	}
	
	public void setActive(Short active) {
		this.active = active;
	}
	
	public String getContactPoint1() {
		return contactPoint1;
	}
	
	public void setContactPoint1(String contactPoint1) {
		this.contactPoint1 = contactPoint1;
	}
	
	public String getContactPoint2() {
		return contactPoint2;
	}
	
	public void setContactPoint2(String contactPoint2) {
		this.contactPoint2 = contactPoint2;
	}
	
	public String getContactPoint3() {
		return contactPoint3;
	}
	
	public void setContactPoint3(String contactPoint3) {
		this.contactPoint3 = contactPoint3;
	}
	
	
	public String getNameAsSingleString() {
		String name="";
		if (this.givenName1 != null && !this.givenName1.isEmpty())
			name = this.givenName1;
		if (this.givenName2 != null && !this.givenName2.isEmpty())
			name = name+" "+this.givenName2;
		if (this.familyName != null && !this.familyName.isEmpty()) 
			name = name+" "+this.familyName;
		
		return name;
	}

	@Override
	public String getColumnName(String columnVariable) {
		return FPerson._getColumnName(columnVariable);
	}
	
	@Override
  	public String getFirstColumnName() {
  		return "person_id";
  	}

	public static String _getColumnName(String columnVariable) {
		try {
			Field field = FPerson.class.getDeclaredField(columnVariable);
			if (field != null) {
				Column annotation = field.getDeclaredAnnotation(Column.class);
				if (annotation != null) {
					return Person._getTableName() + "." + annotation.name();
				} else {
					JoinColumn joinAnnotation = field.getDeclaredAnnotation(JoinColumn.class);
					if (joinAnnotation != null) {
						return Person._getTableName() + "." + joinAnnotation.name();
					}
					
					System.out.println("WARNING: annotation is null for field="+field.toString());
//					return null;
				}
			}
		} catch (NoSuchFieldException e) {
			// we just display warning message instead of exception trace as it's OK to have no field 
			// because f_person is extended from person table. f_person does not have what person table has.
			logger.warn("column, '" + columnVariable + "', does not exist in f_person table.");

		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return Person._getColumnName(columnVariable);
	}

	@Override
	public String getTableName() {
		return FPerson._getTableName();
	}
	
	public static String _getTableName() {
		if ("False".equalsIgnoreCase(System.getenv("F_TABLE"))) {
			return Person._getTableName();
		} else {
			Table annotation = FPerson.class.getDeclaredAnnotation(Table.class);
			if (annotation != null) {
				return annotation.name();
			}
			return "f_person";
		}
	}
	
	public String getParentTableName() {
		return Person._getTableName();
	}
	
	public static String _getAllfieldsName() {
		String allFieldsName = new String();
		Field[] fields = Person.class.getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			if (allFieldsName.isEmpty() == false)
				allFieldsName = allFieldsName.concat(", ");
			allFieldsName = allFieldsName.concat(Person._getColumnName(fieldName));
		}
		
		return allFieldsName;
	}
	
//	public String getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
//		return FPerson._getSqlSelectTableStatement(parameterList, valueList);
//	}
//	
//	public static String _getSqlSelectTableStatement(List<String> parameterList, List<String> valueList) {
//		String sql;
//		if ("False".equalsIgnoreCase(System.getenv("F_TABLE"))) {
//			sql = "select * from person p";
//		} else {
//			sql = "select * from person p inner join f_person fp ON p.person_id=fp.person_id ";
//		}
//
//		return sql;
//	}
}
