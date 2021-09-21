package edu.gatech.chai.omopv5.dba.service;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.cloud.bigquery.FieldValueList;

import edu.gatech.chai.omopv5.model.entity.Death;

@Service
public class DeathServiceImp extends BaseEntityServiceImp<Death> implements DeathService {

	public DeathServiceImp() {
		super(Death.class);
	}

	@Override
	public Death construct(ResultSet rs, Death entity, String alias) {
		return DeathService._construct(rs, entity, alias);
	}

	@Override
	public Death construct(FieldValueList rowResult, Death entity, String alias, List<String> columns) {
		return DeathService._construct(rowResult, entity, alias, columns);
	}
	
}
