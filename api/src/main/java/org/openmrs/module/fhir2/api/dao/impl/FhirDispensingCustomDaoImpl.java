package org.openmrs.module.fhir2.api.dao.impl;

import javax.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.Encounter;
import org.openmrs.module.fhir2.api.dao.FhirDispensingCustomDAO;
import org.openmrs.module.fhir2.api.search.param.SearchParameterMap;
import org.openmrs.util.DatabaseUtil;
import org.springframework.stereotype.Component;

@Component
public class FhirDispensingCustomDaoImpl extends BaseFhirDao<Encounter> implements FhirDispensingCustomDAO {
	
	@Override
	public List<String> getSearchResultUuids(@Nonnull SearchParameterMap theParams) {
		String query = "select distinct e.uuid from encounter e, orders o, drug_order do where o.order_id = do.order_id and e.encounter_id = o.encounter_id order by encounter_datetime desc;";
		List<List<Object>> results = DatabaseUtil.executeSQL(getSessionFactory().getCurrentSession(), query, true);
		List<String> stringResults = new ArrayList<String>();
		for (List<Object> result : results) {
			stringResults.add((String) result.get(0));
		}
		return stringResults;
	}
	
	// override other methods to fetch sub-resources as necessary?
	
}
