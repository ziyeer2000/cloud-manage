package org.cloud.manage.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.cloud.lang.jdbc.PageBounds;
import org.cloud.manage.interceptor.OnlineData;
import org.cloud.manage.interceptor.OnlineDataVo;
import org.cloud.manage.service.OnlineService;
import org.cloud.manage.utils.AuthUtil;
import org.cloud.web.session.Session;
import org.cloud.web.session.SessionInit;
import org.cloud.web.session.service.SessionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;


@Service
@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
public class OnlineServiceImpl implements OnlineService {

	@Override
	public Page<OnlineDataVo> findPage(long userId, PageBounds pageBounds) {
		
		List<OnlineData> list = new ArrayList<OnlineData>();
		SessionService sessionService = SessionInit.getService();
		
		List<Session> sessionList = new ArrayList<Session>();
		if (userId == -1) { //查询全部
			for (long uid : sessionService.findUserIdList()) {
				sessionList.addAll(sessionService.findByUserId(uid));
			}
		} else {
			sessionList = sessionService.findByUserId(userId);
		}
		
		for (Session session : sessionList) {
			list.add(session.get(AuthUtil.getSessionFlag(), OnlineData.class));
		}
		
		List<OnlineData> ret = new ArrayList<OnlineData>();
		List<OnlineDataVo> retVo = new ArrayList<OnlineDataVo>();
		int page = pageBounds.getPageNum();
		page = page > 0 ? page : 1;
		int limit = pageBounds.getPageSize();
		int start = (page - 1) * limit;
		int end = start + limit;
		int listSize = list.size();
		if (end > listSize) {
			end = listSize;
		}
		ret = list.subList(start, end);
		
		for (OnlineData bean : ret) {
			OnlineDataVo vo = new OnlineDataVo(bean);
			vo.setUserName(bean.getUser().getUserName());
			retVo.add(vo);
		}
		
		Page<OnlineDataVo> pageList = new Page<OnlineDataVo>(page, limit);
		pageList.setTotal(listSize);
		pageList.addAll(retVo);
		
		return pageList;
	}

	@Override
	public void offline(long userId, String sessionId) {
		
		SessionInit.getService().remove(userId, Collections.singletonList(sessionId));
	}
}
