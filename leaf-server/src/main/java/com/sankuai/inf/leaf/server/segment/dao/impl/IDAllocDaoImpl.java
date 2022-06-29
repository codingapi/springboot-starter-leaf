package com.sankuai.inf.leaf.server.segment.dao.impl;

import com.sankuai.inf.leaf.server.Constants;
import com.sankuai.inf.leaf.server.segment.dao.IDAllocDao;
import com.sankuai.inf.leaf.server.segment.dao.LeafAllocRepository;
import com.sankuai.inf.leaf.server.segment.model.LeafAlloc;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@ConditionalOnProperty(name = Constants.LEAF_SEGMENT_ENABLE,havingValue = "true")
@Repository
public class IDAllocDaoImpl implements IDAllocDao {

    private final LeafAllocRepository repository;

    @Override
    public List<LeafAlloc> getAllLeafAllocs() {
        return repository.findAll();
    }

    @Override
    public LeafAlloc updateMaxIdAndGetLeafAlloc(String tag) {
        repository.updateMaxId(tag);
        return repository.getLeafAllocByKey(tag);
    }

    @Override
    public LeafAlloc updateMaxIdByCustomStepAndGetLeafAlloc(LeafAlloc leafAlloc) {
        repository.updateMaxIdByCustomStep(leafAlloc.getStep(), leafAlloc.getKey());
        return repository.getLeafAllocByKey(leafAlloc.getKey());
    }

    @Override
    public List<String> getAllTags() {
        return repository.findAll().stream().map(LeafAlloc::getKey).collect(Collectors.toList());
    }

    @Override
    public boolean save(LeafAlloc leafInfo) {
        repository.save(leafInfo);
        return true;
    }
}
