package com.db.pwcc.tre.elastic.repository.search;

import com.db.pwcc.tre.elastic.domain.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Employee} entity.
 */
public interface EmployeeSearchRepository extends ElasticsearchRepository<Employee, String> {
}
