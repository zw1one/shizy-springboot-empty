package com.shizy.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class CacheUtil {

     /**
     * 缓存过期时间，10分钟
     */
    private final static int EXPIRED = 10 * 60;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 把对象放到缓存
     *
     * @param key     缓存唯一标志
     * @param value   缓存的字符串
     * @param expired 缓存过期时间，单位：秒
     */
    public void setStrToCache(String key, String value, int expired) {
        if (StringUtils.isBlank(key) || value == null) return;

        try {
            redisTemplate.opsForValue().set(key, value, expired, TimeUnit.SECONDS);
        } catch (Exception e) {
            LogUtil.error("set Str to cache fail.", e);
        }
    }

    public void setStrToCache(String key, String value, int expired, TimeUnit timeUnit) {
        if (StringUtils.isBlank(key) || value == null) return;

        try {
            redisTemplate.opsForValue().set(key, value, expired, timeUnit);
        } catch (Exception e) {
            LogUtil.error("set Str to cache fail.", e);
        }
    }

    /**
     * 把对象放到缓存
     *
     * @param key     缓存唯一标志
     * @param value   缓存的对象
     * @param expired 缓存过期时间，单位：秒
     */
    public void setObjectToCache(String key, Object value, int expired) {
        if (StringUtils.isBlank(key) || value == null) return;

        try {
            redisTemplate.opsForValue().set(key, JSON.toJSONString(value), expired, TimeUnit.SECONDS);
        } catch (Exception e) {
            LogUtil.error("set object to cache fail.", e);
        }
    }

    public void setObjectToCache(String key, Object value, int expired, TimeUnit timeUnit) {
        if (StringUtils.isBlank(key) || value == null) return;

        try {
            redisTemplate.opsForValue().set(key, JSON.toJSONString(value), expired, timeUnit);
        } catch (Exception e) {
            LogUtil.error("set object to cache fail.", e);
        }
    }

    /**
     * 把List集合放到缓存
     *
     * @param key   缓存唯一标识
     * @param value 缓存的集合
     */
    public void setListToCache(String key, List value) {
        if (StringUtils.isBlank(key) || value == null) return;

        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            redisTemplate.expire(key, EXPIRED, TimeUnit.SECONDS);
        } catch (Exception e) {
            LogUtil.error("set list to cache fail.", e);
        }
    }

    /**
     * 从缓存中获取对象
     *
     * @param key
     * @return
     */
    public String getObjectFromCache(String key) {
        if (StringUtils.isBlank(key)) return null;

        try {
            Object value = redisTemplate.opsForValue().get(key);

            if (value != null) return value.toString();
        } catch (Exception e) {
            LogUtil.error("get [{}]  from cache fail.", key, e);
        }
        return null;
    }

    /**
     * 从缓存中获取List集合
     *
     * @param key 缓存唯一标识
     * @return
     */
    public List getListFromCache(String key) {
        if (StringUtils.isBlank(key)) return null;
        try {
            List value = redisTemplate.opsForList().range(key, 0, -1);
            if (value != null) return value;
        } catch (Exception e) {
            LogUtil.error("get list from cache fail.", key, e);
        }
        return null;
    }

    /**
     * 从缓存中删除数据
     *
     * @param key 缓存唯一标识
     */
    public void deleteObjectFromCache(String key) {
        if (StringUtils.isBlank(key)) return;
        try {
            redisTemplate.delete(key);
            // 以下删除会造成百度AIsessionId为null，报错
//            redisTemplate.opsForValue().set(key, null, 1, TimeUnit.SECONDS);
        } catch (Exception e) {
            LogUtil.error("delete object from cache fail.", e);
        }
    }

    /**
     * 把对象放到缓存，默认过期时间为10分钟
     *
     * @param key   缓存唯一标志
     * @param value 缓存的对象
     */
    public void setObjectToCache(String key, Object value) {
        setObjectToCache(key, value, EXPIRED);
    }

    /**
     * 从缓存中获取对象
     *
     * @param key   缓存唯一标志
     * @param clazz 对象对应的类
     * @return
     */
    public <T> T getObjectFromCache(String key, Class<T> clazz) {
        if (StringUtils.isBlank(key) || clazz == null) return null;

        try {
            Object value = redisTemplate.opsForValue().get(key);

            if (value != null) return JSON.parseObject(value.toString(), clazz);
        } catch (Exception e) {
            LogUtil.error("get [{}] object[{}] from cache fail.", key, clazz.getName(), e);
        }

        return null;
    }


    /**
     * 批量从缓存中获取对象
     *
     * @param keys  缓存唯一标志列表
     * @param clazz 对象对应的类
     * @return 只返回在缓存中存在的对象
     */
    public <T> Map<String, T> getObjectsFromCache(List<String> keys, Class<T> clazz) {
        if (keys == null || clazz == null) return null;

        Map<String, T> objects = new HashMap<String, T>();

        try {
            List<Object> values = redisTemplate.opsForValue().multiGet(keys);

            if (values != null) {
                for (int i = 0; i < keys.size(); i++) {
                    objects.put(keys.get(i), (T) values.get(i));
                }
            }
        } catch (Exception e) {
            LogUtil.error("get {} objects[{}] from cache fail.", keys.toString(), clazz.getName(), e);
        }


        return objects;
    }

    /**
     * 查看redis中是否存在指定的key
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        if (key == null) return false;
        boolean exist = redisTemplate.hasKey(key);
        return exist;
    }
}
