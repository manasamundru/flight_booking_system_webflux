package com.flightapp.util;

import java.util.UUID;

public class PnrGenerator {
	private PnrGenerator() {
		throw new IllegalStateException("Utility class");
	}
    public static String generate() {
    	return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
