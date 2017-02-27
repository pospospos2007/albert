package com.zdcf.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.zdcf.model.GoogleSearchResult;

public interface GoogleService {
	public List<GoogleSearchResult> search(String searchKey) throws ClientProtocolException, IOException;
	
}
