package cn.edu.bjtu.weibo.impl;//�½�һ��impl������service����ʵ����


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cn.edu.bjtu.weibo.model.*;
import cn.edu.bjtu.weibo.service.GeneralSearchService;


public class GeneralSearchServiceImpl implements GeneralSearchService{

	

	private static final EntityType Weibo = null;
	private static final EntityType User = null;

	@SuppressWarnings("null")
	@Override
	public List<GeneralSR> getGeneralSearchedResult(String keyword,
			int pageIndex, int numberPerPage) {
		// TODO Auto-generated method stub
		

		 
		//�����û���������SearchUserServiceʵ����ΪSearchUserServiceImpl�����������
			SearchUserServiceImpl Users = new SearchUserServiceImpl();
			List<UserSR> SearchUserList = Users.getSearchedUserList(keyword, pageIndex, numberPerPage);
			
			List<GeneralSR> GeneralList = null;//����GeneralSR�б��洢�������
			

			GeneralSR a=null;
			for(int i = 0;i<SearchUserList.size();i++){
				a.setResult(SearchUserList.get(i));
				a.setType(User);
				GeneralList.add(a);
			}

		//����΢�����ݣ�����SearchContentServiceʵ����ΪSearchContentServiceImpl�����������
		SearchContentServiceImpl Content = new SearchContentServiceImpl();
		List<BaseContentSR> SearchedWeiboList = Content.getSearchedWeiboList(keyword, pageIndex, numberPerPage);
		
		GeneralSR b=null;
	    for (BaseContentSR key : contentMap.keySet()) {
			b.setResult(key);	
			b.setType(Weibo);
			GeneralList.add(b);
		}

		

		List<GeneralSR> GeneralPageList = null;//��ҳ���洢�������
		for(int k = 0;k<GeneralList.size();k++){
			if (k >= ((pageIndex - 1) * numberPerPage) + 1
					&& k <= pageIndex * numberPerPage) {
			    GeneralPageList.add(GeneralList.get(k));
			}
		}
		
		
		
		return GeneralPageList;
	}
	

	
}
