package com.manage.cochain.controller;

import com.manage.cochain.entity.dto.AppLogDTO;
import com.manage.cochain.entity.vo.AppLogVO;
import com.manage.cochain.service.IAppLogService;
import com.wangzhixuan.commons.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 应用日志信息 Controller控制层
 * @author wzx
 * @create 2019年05月07日 14:20:29
 **/
@Controller
public class AppLogController extends BaseController{
	
	@Autowired
	private IAppLogService appLogService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入应用日志信息 首页
	 * @Date 2019年05月07日 14:20:29
	 * @return java.lang.String
	 **/
	@RequestMapping("goAppLogPage")
	public String goAppLogPage() {
		return "financial/apploginfo/applog_list";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年05月07日 14:20:29
	* @Param [model]
	* @return java.lang.String
	**/
	@PostMapping("goAddAppLogPage")
	public String goAddAppLogPage() {
		return "financial/apploginfo/applog_add";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询应用日志信息 列表
	 * @Date 2019年05月07日 14:20:29
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getAppLogList")
	@ResponseBody
	public Map<String, Object> getAppLogList(int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		AppLogDTO appLogDTO=new AppLogDTO();
		appLogDTO.setStart((page - 1) * rows);
		appLogDTO.setEnd(rows);
		jsonMap = appLogService.getAppLogAllList(appLogDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 14:20:29
	 * @Param [appLogDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddAppLog")
	@ResponseBody
	public Map<String, Object> doAddAppLog(AppLogDTO appLogDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = appLogService.saveAppLog(appLogDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年05月07日 14:20:29
	* @Param [id, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdAppLogPage")
	public String goUpdAppLogPage(int id,Model model) {
		AppLogVO appLogVO = appLogService.getAppLogById(id);
		model.addAttribute("appLogVO", appLogVO);
		return "financial/apploginfo/applog_detail";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年05月07日 14:20:29
	 * @Param [AppLogDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdAppLog")
	@ResponseBody
	public Map<String, Object> doUpdAppLog(AppLogDTO appLogDTO) {
		Map<String, Object> jsonMap = appLogService.updateAppLog(appLogDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年05月07日 14:20:29
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delAppLogById")
	@ResponseBody
	public Map<String, Object> delAppLogById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = appLogService.deleteAppLogById(id);
		return jsonMap;
	}
	
}
