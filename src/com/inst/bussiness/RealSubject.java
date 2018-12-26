package com.inst.bussiness;

public class RealSubject implements Subject {

	@Override
	public void sayHey(String str) {
		System.out.println("hey:"+str);
	}

	@Override
	public void sayHello(String str) {
		System.out.println("hello:" + str);
	}

}
