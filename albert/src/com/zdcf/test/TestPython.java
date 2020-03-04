package com.zdcf.test;

import org.junit.Test;
import org.python.util.PythonInterpreter;

public class TestPython {

	
	@Test
	public void  test1(){
		PythonInterpreter interpreter = new PythonInterpreter();

        interpreter.exec("days=('mod','Tue','Wed','Thu','Fri','Sat','Sun'); ");
        interpreter.exec("print days[1];");
	}
}
