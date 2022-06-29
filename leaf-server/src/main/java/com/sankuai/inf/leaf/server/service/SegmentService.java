package com.sankuai.inf.leaf.server.service;

import com.sankuai.inf.leaf.server.Constants;
import com.sankuai.inf.leaf.server.IDGen;
import com.sankuai.inf.leaf.server.common.Result;
import com.sankuai.inf.leaf.server.exception.InitException;
import com.sankuai.inf.leaf.server.segment.SegmentIDGenImpl;
import com.sankuai.inf.leaf.server.segment.dao.IDAllocDao;
import com.sankuai.inf.leaf.server.segment.model.LeafAlloc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service("SegmentService")
@ConditionalOnProperty(name = Constants.LEAF_SEGMENT_ENABLE,havingValue = "true")
public class SegmentService {

    private final IDGen idGen;

    private final IDAllocDao dao;

    public SegmentService(IDAllocDao dao) throws InitException {
        this.dao = dao;
        // Config ID Gen
        idGen = new SegmentIDGenImpl(dao);
        if (idGen.init()) {
            log.info("Segment Service Init Successfully");
        } else {
            throw new InitException("Segment Service Init Fail");
        }
    }

    public Result getId(String key) {
        return idGen.get(key);
    }

    public SegmentIDGenImpl getIdGen() {
        if (idGen instanceof SegmentIDGenImpl) {
            return (SegmentIDGenImpl) idGen;
        }
        return null;
    }

    public void save(LeafAlloc leafAlloc) throws InitException{
        if(dao==null){
            throw new IllegalArgumentException("You should config leaf.segment.enable=true first");
        }
        boolean flag =  dao.save(leafAlloc);
        if(!flag){
            log.warn("Segment Service Running..");
            return;
        }
        if (idGen.init()) {
            log.info("Segment Service Init Successfully");
        } else {
            throw new InitException("Segment Service Init Fail");
        }
    }
}
