package com.zdcf.test;


import com.aliyun.oss.ClientConfiguration;

public class DefaultClientConfiguration {

    private static final ClientConfiguration conf = new ClientConfiguration(); 

    private DefaultClientConfiguration() {
        // Set the maximum number of allowed open HTTP connections
        conf.setMaxConnections(100);
        // Set the amount of time to wait (in milliseconds) when initially establishing 
        // a connection before giving up and timing out
        conf.setConnectionTimeout(5000);
        // Set the maximum number of retry attempts for failed retryable requests
        conf.setMaxErrorRetry(3);
        // Set the amount of time to wait (in milliseconds) for data to betransfered over 
        // an established connection before the connection times out and is closed
        conf.setSocketTimeout(2000);
    }  

    public static ClientConfiguration getDefalutClientConfig(){                 
        return conf;        
    }
}