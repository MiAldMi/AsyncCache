package ru.docer.AsyncCache.service.serviceImpl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.docer.AsyncCache.service.Cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Реализация многопоточного асинхронного кэша
 */
@Component
public class CacheImpl implements Cache {
    /**
     * Хранилище для данных
     */
    ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    /**
     * Метод читает данные из кэша по ключу
     * @param key = ведется поиск записи
     * @return найденное значение
     */
    @Async
    public Integer read(String key) {
        return map.get(key);
    }

    /**
     * Метод записывает данные в хранилище
     * @param key = ключ для записи
     * @param value = ключ для записи
     */
    @Async
    public void write(String key, Integer value) {
        map.put(key, value);
    }
}
