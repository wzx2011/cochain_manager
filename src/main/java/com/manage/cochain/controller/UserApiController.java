package com.manage.cochain.controller;

import com.manage.cochain.entity.dto.UserApiDTO;
import com.manage.cochain.entity.vo.AppVO;
import com.manage.cochain.entity.vo.UserApiVO;
import com.manage.cochain.service.IUserApiService;
import com.manage.httpclient.cochain.OKHttp3Util;
import com.manage.util.ResponseData;
import com.wangzhixuan.commons.base.BaseController;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户信息 Controller控制层
 * @author wzx
 * @create 2019年05月07日 14:03:15
 **/
@Controller
public class UserApiController extends BaseController{
	
	@Autowired
	private IUserApiService userApiService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入用户信息 首页
	 * @Date 2019年05月07日 14:03:15
	 * @return java.lang.String
	 **/
	@RequestMapping("goUserApiPage")
	public String goUserApiPage() {
		return "financial/userapiinfo/userapi_list";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年05月07日 14:03:15
	* @Param [model]
	* @return java.lang.String
	**/
	@RequestMapping("goAddUserApiPage")
	public String goAddUserApiPage() {
		return "financial/userapiinfo/userapi_add";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询用户信息 列表
	 * @Date 2019年05月07日 14:03:15
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getUserApiList")
	@ResponseBody
	public Map<String, Object> getUserApiList(int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		UserApiDTO userApiDTO=new UserApiDTO();
		userApiDTO.setStart((page - 1) * rows);
		userApiDTO.setEnd(rows);
		jsonMap = userApiService.getUserApiAllList(userApiDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 14:03:15
	 * @Param [userApiDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddUserApi")
	@ResponseBody
	public Map<String, Object> doAddUserApi(UserApiDTO userApiDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = userApiService.saveUserApi(userApiDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年05月07日 14:03:15
	* @Param [ id, paramMap, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdUserApiPage")
	public String goUpdUserApiPage(int id, Model model) {
		UserApiVO userApiVO = userApiService.getUserApiById(id);
		model.addAttribute("userApiVO", userApiVO);
		return "financial/userapiinfo/userapi_edit";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年05月07日 14:03:15
	 * @Param [UserApiDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdUserApi")
	@ResponseBody
	public Map<String, Object> doUpdUserApi(UserApiDTO userApiDTO) {
		Map<String, Object> jsonMap = userApiService.updateUserApi(userApiDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年05月07日 14:03:15
	 * @Param [ id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delUserApiById")
	@ResponseBody
	public Map<String, Object> delUserApiById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = userApiService.deleteUserApiById(id);
		return jsonMap;
	}

	/**
	 * 调用第三方接口新增用户
	 * @param appId
	 * @param secretkey
	 * @return
	 */
	@RequestMapping("doAddUserByCallInterface")
	@ResponseBody
	public ResponseData doAddUserByCallInterface(int appId,String secretkey) {
		ResponseData responseData = new ResponseData();
		responseData = userApiService.doAddUserByCallInterface(appId,secretkey);
		return responseData;
	}

	/**
	 * 获取用户集合信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getUserApiVOList")
	@ResponseBody
	public ResponseData getUserApiVOList(String appid) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserApiDTO userApiDTO = new UserApiDTO();
		userApiDTO.setAppid(appid);
		List<UserApiVO> userApiVOS = userApiService.getUserApiVOList(userApiDTO);
		ResponseData responseData = new ResponseData();
		responseData.setCode("0001");
		responseData.setMsg("获取用户集合信息成功！");
		responseData.setListData(userApiVOS);
		return responseData;
	}
	
}
