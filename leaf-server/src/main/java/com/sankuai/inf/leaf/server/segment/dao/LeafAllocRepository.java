package com.sankuai.inf.leaf.server.segment.dao;

import com.sankuai.inf.leaf.server.Constants;
import com.sankuai.inf.leaf.server.segment.model.LeafAlloc;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
@ConditionalOnProperty(name = Constants.LEAF_SEGMENT_ENABLE,havingValue = "true")
public interface LeafAllocRepository extends JpaRepository<LeafAlloc,String> {


    @Modifying
    @Query("UPDATE LeafAlloc SET maxId = maxId + step WHERE key = :key")
    void updateMaxId(@Param("key") String key);


    @Modifying
    @Query("UPDATE LeafAlloc SET maxId = maxId + :step WHERE key = :key")
    void updateMaxIdByCustomStep(@Param("step") int step,@Param("key")String key);

    LeafAlloc getLeafAllocByKey(String key);


}
