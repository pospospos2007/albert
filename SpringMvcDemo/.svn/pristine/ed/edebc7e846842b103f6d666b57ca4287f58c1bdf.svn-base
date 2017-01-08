//package com.zdcf.test;
//
//import com.zdcf.test.TestFactory.EmailFactory;
//import com.zdcf.test.TestFactory.Factory;
//import com.zdcf.test.TestFactory.SmsFactory;
//
//public class FactoryManager {
//	  private static class SingletoSmsFactory{
//	        private static Factory instance = new SmsFactory();
//
//	        private static Factory getInstance() {
//	            return SingletoSmsFactory.instance;
//	        }
//	    }
//	    private static class SingletoEmailFactory{
//	        private static Factory instance = null;
//	        private static synchronized void initInstance() {
//	            if(null == instance) {
//	                instance = new  EmailFactory();
//	            }
//	        }
//	        private static Factory getInstance() {
//	            if(null == instance) {
//	                initInstance();
//	            }
//	            return instance;
//	        }
//	    }
//
//	    public static Factory getFactory(Class clazz) {
//	        String className = clazz.getName();
//	        Factory factory = null;
//	        if(className.equals(SmsFactory.class.getName())) {
//	            factory = SingletoSmsFactory.getInstance();
//	        }
//	        if(className.equals(EmailFactory.class.getName())) {
//	            factory = SingletoEmailFactory.getInstance();
//	        }
//	        return factory;
//	    }
//}
