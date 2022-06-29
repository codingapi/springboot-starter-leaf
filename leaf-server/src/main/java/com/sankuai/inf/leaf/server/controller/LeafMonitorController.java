package com.sankuai.inf.leaf.server.controller;

import com.sankuai.inf.leaf.server.Constants;
import com.sankuai.inf.leaf.server.model.SegmentBufferView;
import com.sankuai.inf.leaf.server.segment.SegmentIDGenImpl;
import com.sankuai.inf.leaf.server.segment.model.LeafAlloc;
import com.sankuai.inf.leaf.server.segment.model.SegmentBuffer;
import com.sankuai.inf.leaf.server.service.SegmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@AllArgsConstructor
@ConditionalOnProperty(name = Constants.LEAF_SEGMENT_ENABLE,havingValue = "true")
public class LeafMonitorController {

    private final SegmentService segmentService;

    @RequestMapping(value = "cache")
    public String getCache(Model model) {
        Map<String, SegmentBufferView> data = new HashMap<>();
        SegmentIDGenImpl segmentIDGen = segmentService.getIdGen();
        if (segmentIDGen == null) {
            throw new IllegalArgumentException("You should config leaf.segment.enable=true first");
        }
        Map<String, SegmentBuffer> cache = segmentIDGen.getCache();
        for (Map.Entry<String, SegmentBuffer> entry : cache.entrySet()) {
            SegmentBufferView sv = new SegmentBufferView();
            SegmentBuffer buffer = entry.getValue();
            sv.setInitOk(buffer.isInitOk());
            sv.setKey(buffer.getKey());
            sv.setPos(buffer.getCurrentPos());
            sv.setNextReady(buffer.isNextReady());
            sv.setMax0(buffer.getSegments()[0].getMax());
            sv.setValue0(buffer.getSegments()[0].getValue().get());
            sv.setStep0(buffer.getSegments()[0].getStep());

            sv.setMax1(buffer.getSegments()[1].getMax());
            sv.setValue1(buffer.getSegments()[1].getValue().get());
            sv.setStep1(buffer.getSegments()[1].getStep());

            data.put(entry.getKey(), sv);

        }
        log.info("Cache info {}", data);
        model.addAttribute("data", data);
        return "segment";
    }

    @RequestMapping(value = "db")
    public String getDb(Model model) {
        SegmentIDGenImpl segmentIDGen = segmentService.getIdGen();
        if (segmentIDGen == null) {
            throw new IllegalArgumentException("You should config leaf.segment.enable=true first");
        }
        List<LeafAlloc> items = segmentIDGen.getAllLeafAllocs();
        log.info("DB info {}", items);
        model.addAttribute("items", items);
        return "db";
    }


}
