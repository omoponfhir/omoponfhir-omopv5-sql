package edu.gatech.chai.omopv5.dba.service;

import java.sql.ResultSet;

import org.springframework.stereotype.Service;

import edu.gatech.chai.omopv5.model.entity.VisitDetail;

@Service
public class VisitDetailServiceImp extends BaseEntityServiceImp<VisitDetail> implements VisitDetailService {

	public VisitDetailServiceImp() {
		super(VisitDetail.class);
	}

	@Override
	public VisitDetail construct(ResultSet rs, VisitDetail entity, String alias) {
		return VisitDetailService._construct(rs, entity, alias);
	}

}
