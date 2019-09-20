package com.manage.cochain.service.impl;


import com.manage.cochain.entity.dto.UserApiDTO;
import com.manage.cochain.entity.vo.AppVO;
import com.manage.cochain.entity.vo.UserApiVO;
import com.manage.cochain.mapper.UserApiMapper;
import com.manage.cochain.service.IAppService;
import com.manage.cochain.service.IUserApiService;
import com.manage.httpclient.cochain.OKHttp3Util;
import com.manage.util.ResponseData;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户信息 Service 实现层
 * @author wzx
 * @create 2019年05月07日 14:03:15
 **/
@Service
public class UserApiServiceImpl implements IUserApiService  {

	@Autowired
	private UserApiMapper userApiMapper;

	@Autowired
	private IAppService appService;

	private static Logger logger = Logger.getLogger(UserApiServiceImpl.class);

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月07日 14:03:15
	 * @Param UserApiDTO
	 * @return List<UserApiVO>
	 **/
	@Override
	public Map<String,Object> getUserApiAllList(UserApiDTO userApiDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<UserApiVO> userApiList = new ArrayList<>();
		Integer userApiTotal = userApiMapper.findUserApiTotal(userApiDTO);
		if(userApiTotal>0){
			userApiList = userApiMapper.getUserApiAllList(userApiDTO);
		}
		resultMap.put("rows", userApiList);
		resultMap.put("total", userApiTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 14:03:15
	 * @Param UserApiDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveUserApi(UserApiDTO userApiDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(userApiMapper.saveUserApi(userApiDTO)>0){
			resultMap.put("info", true);
			resultMap.put("msg", "添加成功！");
		}else {
			resultMap.put("info", false);
			resultMap.put("msg", "添加失败！");
		}
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年05月07日 14:03:15
	 * @Param UserApiDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateUserApi(UserApiDTO userApiDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(userApiMapper.updateUserApi(userApiDTO)>0){
			resultMap.put("info", true);
			resultMap.put("msg", "修改成功！");
		}else {
			resultMap.put("info", false);
			resultMap.put("msg", "修改失败！");
		}
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年05月07日 14:03:15
	 * @Param UserApiDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteUserApiById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(userApiMapper.deleteUserApiById(id)>0){
			resultMap.put("info", true);
			resultMap.put("msg", "修改成功！");
		}else {
			resultMap.put("info", false);
			resultMap.put("msg", "修改失败！");
		}
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年05月07日 14:03:15
	 * @Param id
	 * @return UserApiVO
	 **/
	@Override
	public UserApiVO getUserApiById(Integer id){
		return userApiMapper.getUserApiById(id);
	}

	/**
	 * 通过接口新增用户
	 *
	 * @param appId
	 * @param secretkey
	 * @return
	 */
	@Override
	public ResponseData doAddUserByCallInterface(int appId, String secretkey) {
		ResponseData responseData = new ResponseData();
		OKHttp3Util okHttp3Util = new OKHttp3Util();

		AppVO appVO = appService.getAppById(appId);
		String token = null;
		try {
			// 获取token信息
			token = okHttp3Util.getToken(appVO.getAppid(), appVO.getAppkey());
		} catch (Exception e) {
			String errorMsg = "调用token失败！" +
					"\t接口信息:okHttp3Util.getToken" +
					"\t接口参数：appId=" + appVO.getAppid() + ",appKey=" + appVO.getAppkey() +
					"\t错误信息：" + e.getMessage();
			logger.error(errorMsg);
			e.printStackTrace();
			responseData.setCode("0002");
			responseData.setMsg(errorMsg);
			return responseData;
		}

		JSONObject resultData = null;
		try {
			resultData = okHttp3Util.createUserApi(token,"1",secretkey);
			if(resultData.containsKey("uid")){
				responseData.setCode("0001");
				responseData.setMsg("调用第三方接口新增用户成功！");
			} else {
				String errorMsg = "调用第三方接口新增用户失败！" +
						"\t接口信息:okHttp3Util.createUserApi(token,\"1\",secretkey);" +
						"\t接口参数：token=" + token + ",secretkey=" + secretkey +
						"\t返回信息：" + resultData;
				responseData.setCode("0002");
				responseData.setMsg(errorMsg);
			}
		} catch (Exception e) {
			String errorMsg = "调用第三方接口新增用户失败！" +
					"\t接口信息:okHttp3Util.createUserApi(token,\"1\",secretkey);" +
					"\t接口参数：token=" + token + ",secretkey=" + secretkey +
					"\t错误信息：" + e.getMessage();
			logger.error(errorMsg);
			e.printStackTrace();
			responseData.setCode("0002");
			responseData.setMsg(errorMsg);
		}

		return responseData;
	}

	/**
	 * 获取用户集合信息
	 *
	 * @param userApiDTO
	 * @return
	 */
	@Override
	public List<UserApiVO> getUserApiVOList(UserApiDTO userApiDTO) {
		return userApiMapper.getUserApiVOList(userApiDTO);
	}
}
