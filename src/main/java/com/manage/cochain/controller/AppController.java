package com.manage.cochain.controller;

import com.manage.cochain.entity.dto.AppDTO;
import com.manage.cochain.entity.vo.AppVO;
import com.manage.cochain.service.IAppService;
import com.manage.util.ResponseData;
import com.wangzhixuan.commons.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 应用信息 Controller控制层
 * @author wzx
 * @create 2019年05月07日 14:19:49
 **/
@Controller
public class AppController extends BaseController{
	
	@Autowired
	private IAppService appService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入应用信息 首页
	 * @Date 2019年05月07日 14:19:49
	 * @return java.lang.String
	 **/
	@RequestMapping("goAppPage")
	public String goAppPage() {
		return "financial/appinfo/app_list";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年05月07日 14:19:49
	* @Param [model]
	* @return java.lang.String
	**/
	@RequestMapping("goAddAppPage")
	public String goAddAppPage() {
		return "financial/appinfo/app_add";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询应用信息 列表
	 * @Date 2019年05月07日 14:19:49
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getAppList")
	@ResponseBody
	public Map<String, Object> getAppList(int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		AppDTO appDTO=new AppDTO();
		appDTO.setStart((page - 1) * rows);
		appDTO.setEnd(rows);
		jsonMap = appService.getAppAllList(appDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 14:19:49
	 * @Param [appDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddApp")
	@ResponseBody
	public Map<String, Object> doAddApp(AppDTO appDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = appService.saveApp(appDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年05月07日 14:19:49
	* @Param [ id,  model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdAppPage")
	public String goUpdAppPage(int id,Model model) {
		AppVO appVO = appService.getAppById(id);
		model.addAttribute("appVO", appVO);
		return "financial/appinfo/app_edit";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年05月07日 14:19:49
	 * @Param [AppDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdApp")
	@ResponseBody
	public Map<String, Object> doUpdApp(AppDTO appDTO) {
		Map<String, Object> jsonMap = appService.updateApp(appDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年05月07日 14:19:49
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delAppById")
	@ResponseBody
	public Map<String, Object> delAppById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = appService.deleteAppById(id);
		return jsonMap;
	}

	/**
	 * 获取应用的所用集合信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getAppVOList")
	@ResponseBody
	public ResponseData getAppVOList() throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		AppDTO appDTO=new AppDTO();
		List<AppVO> appVOList = appService.getAppVOList(appDTO);
		List<AppVO> appList = new ArrayList<>();
		for(AppVO appVO:appVOList){
			appVO.setAppid(appVO.getAppid()+","+appVO.getAppkey());
		}
		ResponseData responseData = new ResponseData();
		responseData.setCode("0001");
		responseData.setMsg("获取应用实体信息成功！");
		responseData.setListData(appVOList);
		return responseData;
	}

}
