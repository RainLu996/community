package com.nowcoder.community.service;

import com.nowcoder.community.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

/**
 * @author rainlu
 * @version 1.0.0
 * @Description 点赞Service
 */

@Service
public class LikeService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @param userId       :点赞的人
     * @param entityType   实体类型
     * @param entityId     实体id
     * @param entityUserId :被赞的人，即作者
     * @Description: 对实体点赞, 对相应的用户加赞。点一次为点赞，点两次为取消点赞
     **/
    public void like(int userId, int entityType, int entityId, int entityUserId) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
                String userLikeKey = RedisKeyUtil.getUserLikeKey(entityUserId);
                /*
                    redisTemplate.opsForSet().isMember(key, value)返回值说明：
                    · 当value存在于key的set之中，返回true
                    · 当value不存在于key的set之中，返回false
                    · 当key不存在，返回false
                 */
                boolean isMember = redisTemplate.opsForSet().isMember(entityLikeKey, userId);
                //多个更新操作，需要事务
                operations.multi();
                if (isMember) {
                    //取消赞
                    redisTemplate.opsForSet().remove(entityLikeKey, userId);
                    // 用户获得的赞总量-1
                    redisTemplate.opsForValue().decrement(userLikeKey);
                } else {
                    //点赞
                    redisTemplate.opsForSet().add(entityLikeKey, userId);
                    // 用户获得的赞总量+1
                    redisTemplate.opsForValue().increment(userLikeKey);
                }
                return operations.exec();
            }
        });

    }

    /**
     * @Description: 查询某实体（帖子、评论等）的点赞数量
     **/
    public long findEntityLikeCount(int entityType, int entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        return redisTemplate.opsForSet().size(entityLikeKey);
    }

    /**
     * @Description: 查询某人对某实体的点赞状态
     **/
    public int findEntityLikeStatus(int entityType, int entityId, int userId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        //此处返回int，是为了进行扩展。比如扩展踩，为止2.等等情况
        /* 实现方式：看某个实体中是否存在某个人的id */
        return redisTemplate.opsForSet().isMember(entityLikeKey, userId) ? 1 : 0;
    }

    /**
     * @Description: 查询某个用户获得的赞，用于在个人主页查看收获了多少赞
     **/
    public int findUserLikeCount(int userId) {
        String userLikeKey = RedisKeyUtil.getUserLikeKey(userId);
        Integer count = (Integer) redisTemplate.opsForValue().get(userLikeKey);
        return count == null ? 0 : count;
    }
}
