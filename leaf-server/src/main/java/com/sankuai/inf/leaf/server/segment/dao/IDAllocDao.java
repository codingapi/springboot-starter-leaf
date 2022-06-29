package com.sankuai.inf.leaf.server.segment.dao;

import com.sankuai.inf.leaf.server.segment.model.LeafAlloc;

import java.util.List;

public interface IDAllocDao {

     List<LeafAlloc> getAllLeafAllocs();

     LeafAlloc updateMaxIdAndGetLeafAlloc(String tag);

     LeafAlloc updateMaxIdByCustomStepAndGetLeafAlloc(LeafAlloc leafAlloc);

     List<String> getAllTags();

     boolean save(LeafAlloc leafInfo);
}
