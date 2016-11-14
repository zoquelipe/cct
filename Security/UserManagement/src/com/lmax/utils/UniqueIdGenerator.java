package com.lmax.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueIdGenerator {

	private static AtomicInteger ai = new AtomicInteger();
	public static int getNewId()
	{
		return ai.incrementAndGet();
	}
}
