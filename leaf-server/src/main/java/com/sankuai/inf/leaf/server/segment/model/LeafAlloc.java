package com.sankuai.inf.leaf.server.segment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Setter
@Getter
@Entity
public class LeafAlloc {

    @Id
    @Column(length = 128,name = "tag")
    private String key;

    @Column(length = 20)
    private long maxId;

    @Column(length = 11)
    private int step;

    private Date updateTime;

}
