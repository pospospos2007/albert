package com.zdcf.search;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.zdcf.base.Constants;
import com.zdcf.model.Movie;
import com.zdcf.model.Theme;
import com.zdcf.model.Zhihu;
import com.zdcf.search.entity.ChnlMovieSearch;
import com.zdcf.search.entity.ChnlThemeSearch;
import com.zdcf.search.entity.ChnlZhihuSearch;
import com.zdcf.search.repository.ChnlMovieRepository;
import com.zdcf.search.repository.ChnlThemeRepository;
import com.zdcf.search.repository.ChnlZhihuRepository;

@Service("indexService")
public class IndexService implements IIndexService {
	
	@Autowired
	protected ChnlZhihuRepository chnlZhihuRepository;
	
	@Autowired
	protected ChnlThemeRepository chnlThemeRepository;
	
	@Autowired
	protected ChnlMovieRepository chnlMovieRepository;
	
	
	public void process(Object object, Constants.Cache.Type type){
		
		if(object instanceof Zhihu){
			
			Zhihu zhihu = (Zhihu)object;
			
			ChnlZhihuSearch zhihuSearch = new ChnlZhihuSearch();
        	BeanUtils.copyProperties(zhihu, zhihuSearch);
        	
        	if(Constants.Cache.Type.save == type){
        		chnlZhihuRepository.save(zhihuSearch);
        		
        	} else if(Constants.Cache.Type.del == type){
        		chnlZhihuRepository.delete(zhihuSearch.getId());
        		
        	} else if(Constants.Cache.Type.update == type){
        		chnlZhihuRepository.index(zhihuSearch);
        	}
        	
		} else if(object instanceof Theme){
			
			Theme  theme = (Theme)object;
			ChnlThemeSearch chnlThemeSearch = new ChnlThemeSearch();
        	
			BeanUtils.copyProperties(theme, chnlThemeSearch);
        	
        	if(Constants.Cache.Type.save == type){
        		chnlThemeRepository.save(chnlThemeSearch);
        		
        	} else if(Constants.Cache.Type.del == type){
        		chnlThemeRepository.delete(chnlThemeSearch.getId());
        		
        	} else if(Constants.Cache.Type.update == type){
        		chnlThemeRepository.index(chnlThemeSearch);
        	}
			 
		}else if(object instanceof Movie){
			
			Movie  movie = (Movie)object;
			ChnlMovieSearch chnlMovieSearch = new ChnlMovieSearch();
        	
			BeanUtils.copyProperties(movie, chnlMovieSearch);
        	
			if(Constants.Cache.Type.save == type){
				chnlMovieRepository.save(chnlMovieSearch);
        		
        	} else if(Constants.Cache.Type.del == type){
        		chnlMovieRepository.delete(chnlMovieSearch.getId());
        		
        	} else if(Constants.Cache.Type.update == type){
        		chnlMovieRepository.index(chnlMovieSearch);
        	}
			 
		} 
	}
	
}
