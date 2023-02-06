package ru.docer.AsyncCache;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.docer.AsyncCache.service.Cache;
import ru.docer.AsyncCache.service.serviceImpl.CacheImpl;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AsyncCacheApplicationTests {

	@InjectMocks
	private final Cache cache = new CacheImpl();

	@Test
	void showCache() throws InterruptedException {
		AtomicReference<Integer> result = new AtomicReference<>();
		String key = "string";
		Integer value = 1;

		Thread thread1 = new Thread(() -> {
			cache.write(key, value);
		});
		thread1.start();
		thread1.join();

		Thread thread2 = new Thread(() -> {
			result.set(cache.read(key));
		});
		thread2.start();
		thread2.join();

		assertEquals(1, result.get());
	}

	@Test
	void emptyCache() throws InterruptedException {
		AtomicReference<Integer> result = new AtomicReference<>();

		Thread thread2 = new Thread(() -> {
			String key = "key";
			result.set(cache.read(key));
		});
		thread2.start();
		thread2.join();

		assertNull(result.get());
	}
}
