package com.tvd12.ezyfoxserver.client.util;

public class EzyStringsIterator extends EzyWrapperIterator<String> {

	public EzyStringsIterator(String[] array) {
		super(array);
	}
	
	public static EzyStringsIterator wrap(String[] array) {
		return new EzyStringsIterator(array);
	}
	
}
