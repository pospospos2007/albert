package com.zdcf.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdcf.base.Constants;
import com.zdcf.dto.ZhihuDTO;
import com.zdcf.mapper.FileMapper;
import com.zdcf.mapper.MovieMapper;
import com.zdcf.model.Movie;
import com.zdcf.model.Zhihu;
import com.zdcf.service.BaseService;
import com.zdcf.service.MovieService;
import com.zdcf.tool.PageInfo;

@Service
@Transactional
public class MovieServiceImpl extends BaseService implements MovieService {

	@Resource
	private FileMapper fileMapper;
	
	@Resource
	private MovieMapper movieMapper;
	
	@Resource
	private RedisCacheService redisCacheService;
	
	private static Logger logger = Logger.getLogger(MovieServiceImpl.class);
	
	private static final String URL = "http://www.hdwan.net/page/";

	@Override
	public Movie getMovieById(int id) {
		
		Movie temp = new Movie();
		temp.setId(id);
		
		Movie movie = redisCacheService.get(temp.getCacheKey(), Movie.class);
		
		//不在缓存中，去数据库中查询
		if(movie == null){
			movie = movieMapper.getMovieById(id);
			if(movie !=null){
				movieMapper.updateMovieReviewNum(id);
				movie.setReviewNum(movie.getReviewNum()+1);
				super.sendMessage(movie, Constants.Cache.Type.update);
			}
		} 
		
		return movie;
		
	}
	
	@Override
	public List<Movie> getPage(PageInfo page) {
		Document doc=null;
		try {
			doc = getConnect(getPageUrl(page.getCurPageNo())).get();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Elements els = doc.select("#post_container > li");
		if(els != null){
			List<Movie> Movies = new ArrayList<>(); 
			for(int i = 0;i<els.size();i++){
				Element li = els.get(i);
				Elements as = li.select(".thumbnail a");
				for(Element a:as){
					String href = a.attr("href");
					Elements img = a.select(" > img");
					String imgUrl = img.attr("src");
					String title = a.attr("title");
					if(!checkHave(title)){
						Movie vo = getRecord(href);
						vo.setName(title);
						vo.setImg(imgUrl);
						movieMapper.addMovie(vo);
						logger.info("插入："+vo.getName());
						Movie vi = movieMapper.getMovieDetailbyName(title);
						vi.setReviewNum(0);
						vi.setAddTime(new Date());
						vi.setUpdateTime(new Date());
						super.sendMessage(vi, Constants.Cache.Type.save);
						Movies.add(vo);
					}
				}
//				if(i==2)break;
			}
			return Movies;
		}
		return null;
	}

	private boolean checkHave(String title) {
		Integer count = movieMapper.getMoviebyName(title);
		if(count != null){
			System.err.println("已经存在："+title);
			return true;
		}
		return false;
	}

	private Connection getConnect(String url) {
		return Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36").timeout(100000);
	}

	@Override
	public Movie getRecord(String url) {
		Movie vo = new Movie();
		Document doc=null;
		try {
			doc = getConnect(url).get();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Elements els = doc.select("#post_content");
		if(els != null && !els.isEmpty()){
			Element content = els.get(0);
			vo.setMetadata(content.html());
			Elements as = content.child(0).lastElementSibling().select("a");
			if(as != null && !as.isEmpty()){
				String href = as.get(0).attr("href");
				if(href != null && href.startsWith("http")){
					try {
						byte[] torrent = getTorrent(href);
						//TODO 种子另外保存
//						vo.setTorrent(torrent);
					} catch (Exception e) {
						vo.setDurl(href);
					}
				}else{
					vo.setDurl(href);
				}
			}
		}
		
		return vo;
	}

	@Override
	public byte[] getTorrent(String href) {
		Response response=null;
		try {
			response = getConnect(href).ignoreContentType(true).execute();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return response.bodyAsBytes();
	}

	@Override
	public PageInfo buildPage(int curNo) {
		return new PageInfo(curNo,10);
	}

	public String getPageUrl(int pageNo) {
		return URL+pageNo;
	}

	@Override
	public List<Movie> nextPage() {
		return null;
	}

	@Override
	public Movie nextRecord() {
		return null;
	}
	

}
