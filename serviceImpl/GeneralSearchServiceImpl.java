package cn.edu.bjtu.weibo.service.serviceImpl;


import java.util.List;



import cn.edu.bjtu.weibo.model.*;
import cn.edu.bjtu.weibo.service.GeneralSearchService;


public class GeneralSearchServiceImpl implements GeneralSearchService{

	

	private static final EntityType Weibo = null;
	private static final EntityType User = null;

	@SuppressWarnings("null")
	public List<GeneralSR> getGeneralSearchedResult(String keyword,
			int pageIndex, int numberPerPage) {
		// TODO Auto-generated method stub
		

		 
		//搜索用户名，SearchUserService实现类为SearchUserServiceImpl
			SearchUserServiceImpl Users = new SearchUserServiceImpl();
			List<UserSR> SearchUserList = Users.getSearchedUserList(keyword, pageIndex, numberPerPage);
			
			List<GeneralSR> GeneralList = null;//建立GeneralSR列表，存储搜索结果
			

			GeneralSR a=null;
			for(int i = 0;i<SearchUserList.size();i++){
				a.setResult(SearchUserList.get(i));
				a.setType(User);
				GeneralList.add(a);
			}

		//搜索微博内容，SearchContentService实现类为SearchContentServiceImpl
		SearchContentServiceImpl Content = new SearchContentServiceImpl();
		List<BaseContentSR> SearchedWeiboList = Content.getSearchedWeiboList(keyword, pageIndex, numberPerPage);
		
		GeneralSR b=null;
		for(int j = 0;j<SearchedWeiboList.size();j++){
			b.setResult(SearchedWeiboList.get(j));
			b.setType(Weibo);
			GeneralList.add(b);
		}

		

		List<GeneralSR> GeneralPageList = null;//分页，存储搜索结果
		for(int k = 0;k<GeneralList.size();k++){
			if (k >= ((pageIndex - 1) * numberPerPage) + 1
					&& k <= pageIndex * numberPerPage) {
			    GeneralPageList.add(GeneralList.get(k));
			}
		}
		
		
		
		return GeneralPageList;
	}

	public void updateSearchIndex() {
		// TODO Auto-generated method stub
		
	}
	

	
}
