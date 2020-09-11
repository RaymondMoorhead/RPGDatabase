package com.rpgdatabase.utility;

public class Pair<T1, T2> {
	public T1 first;
	public T2 second;
	
	public T1 getFirst() {
		return first;
	}
	public void setFirst(T1 first) {
		this.first = first;
	}
	public T2 getSecond() {
		return second;
	}
	public void setSecond(T2 second) {
		this.second = second;
	}
}
