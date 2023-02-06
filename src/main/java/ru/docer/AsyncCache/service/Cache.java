package ru.docer.AsyncCache.service;

/**
 * Интерфейс для многопоточного асинхронного кэша
 */
public interface Cache {
    /**
     * Метод для чтения
     * @param key
     * @return
     */
    Integer read(String key);

    /**
     * Метод для записи
     * @param key
     * @param value
     */
    void write(String key, Integer value);
}
