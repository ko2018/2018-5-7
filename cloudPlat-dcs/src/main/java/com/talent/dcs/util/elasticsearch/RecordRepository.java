package com.talent.dcs.util.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component()
public interface RecordRepository extends ElasticsearchRepository<PhysicalExaminationRecordEs, String> {

}
