package com.sankuai.inf.leaf.server.controller;

import com.sankuai.inf.leaf.server.Constants;
import com.sankuai.inf.leaf.server.common.Result;
import com.sankuai.inf.leaf.server.common.Status;
import com.sankuai.inf.leaf.server.exception.InitException;
import com.sankuai.inf.leaf.server.exception.LeafServerException;
import com.sankuai.inf.leaf.server.exception.NoKeyException;
import com.sankuai.inf.leaf.server.segment.model.LeafAlloc;
import com.sankuai.inf.leaf.server.service.SegmentService;
import com.sankuai.inf.leaf.server.service.SnowflakeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@ConditionalOnProperty(name = Constants.LEAF_SEGMENT_ENABLE,havingValue = "true")
public class LeafSegmentController {

    private final SegmentService segmentService;

    @PostMapping(value = "/api/push")
    public String push(@RequestBody LeafAlloc leafAlloc)throws InitException {
        segmentService.save(leafAlloc);
        return "success";
    }

    @GetMapping(value = "/api/segment/get/{key}")
    public String getSegmentId(@PathVariable("key") String key) {
        return get(key, segmentService.getId(key));
    }


    private String get(@PathVariable("key") String key, Result id) {
        Result result;
        if (key == null || key.isEmpty()) {
            throw new NoKeyException();
        }
        result = id;
        if (result.getStatus().equals(Status.EXCEPTION)) {
            throw new LeafServerException(result.toString());
        }
        return String.valueOf(result.getId());
    }
}
