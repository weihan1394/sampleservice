package com.govtech.sampleservice.repo.repositories;

import com.govtech.sampleservice.repo.entities.Batch;
import com.govtech.sampleservice.repo.repositories.base.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class BatchRepositoryImpl extends BaseRepository<Batch> implements BatchRepository {}
