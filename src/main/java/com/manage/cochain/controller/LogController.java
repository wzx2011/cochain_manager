package com.manage.cochain.controller;

import com.manage.cochain.entity.dto.LogDTO;
import com.manage.cochain.entity.vo.LogVO;
import com.manage.cochain.service.ILogService;
import com.wangzhixuan.commons.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志信息 Controller控制层
 * @author wzx
 * @create 2019年05月07日 11:44:02
 **/
@Controller
public class LogController extends BaseController{
	
	@Autowired
	private ILogService logService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入日志信息 首页
	 * @Date 2019年05月07日 11:44:02
	 * @return java.lang.String
	 **/
	@RequestMapping("goLogPage")
	public String goLogPage() {
		return "financial/loginfo/log_list";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年05月07日 11:44:02
	* @Param [model]
	* @return java.lang.String
	**/
	@RequestMapping("goAddLogPage")
	public String goAddLogPage() {
		return "financial/loginfo/log_add";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询日志信息 列表
	 * @Date 2019年05月07日 11:44:02
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getLogList")
	@ResponseBody
	public Map<String, Object> getLogList(int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		LogDTO logDTO=new LogDTO();
		logDTO.setStart((page - 1) * rows);
		logDTO.setEnd(rows);
		jsonMap = logService.getLogAllList(logDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:44:02
	 * @Param [logDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddLog")
	@ResponseBody
	public Map<String, Object> doAddLog(LogDTO logDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = logService.saveLog(logDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年05月07日 11:44:02
	* @Param [id, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdLogPage")
	public String goUpdLogPage(int id, Model model) {
		LogVO logVO = logService.getLogById(id);
		model.addAttribute("logVO", logVO);
		return "financial/loginfo/log_edit";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年05月07日 11:44:02
	 * @Param [LogDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdLog")
	@ResponseBody
	public Map<String, Object> doUpdLog(LogDTO logDTO) {
		Map<String, Object> jsonMap = logService.updateLog(logDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年05月07日 11:44:02
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delLogById")
	@ResponseBody
	public Map<String, Object> delLogById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = logService.deleteLogById(id);
		return jsonMap;
	}
	
}
