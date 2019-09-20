package com.manage.cochain.controller;

import com.manage.cochain.entity.dto.TaskLogDTO;
import com.manage.cochain.entity.vo.TaskLogVO;
import com.manage.cochain.service.ITaskLogService;
import com.wangzhixuan.commons.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务日志信息 Controller控制层
 * @author wzx
 * @create 2019年05月07日 11:45:23
 **/
@Controller
public class TaskLogController extends BaseController{
	
	@Autowired
	private ITaskLogService taskLogService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入任务日志信息 首页
	 * @Date 2019年05月07日 11:45:23
	 * @return java.lang.String
	 **/
	@RequestMapping("goTaskLogPage")
	public String goTaskLogPage() {
		return "financial/taskloginfo/tasklog_list";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年05月07日 11:45:23
	* @Param [model]
	* @return java.lang.String
	**/
	@RequestMapping("goAddTaskLogPage")
	public String goAddTaskLogPage() {
		return "financial/taskloginfo/tasklog_add";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询任务日志信息 列表
	 * @Date 2019年05月07日 11:45:23
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getTaskLogList")
	@ResponseBody
	public Map<String, Object> getTaskLogList(int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		TaskLogDTO taskLogDTO=new TaskLogDTO();
		taskLogDTO.setStart((page - 1) * rows);
		taskLogDTO.setEnd(rows);
		jsonMap = taskLogService.getTaskLogAllList(taskLogDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:45:23
	 * @Param [taskLogDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddTaskLog")
	@ResponseBody
	public Map<String, Object> doAddTaskLog(TaskLogDTO taskLogDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = taskLogService.saveTaskLog(taskLogDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年05月07日 11:45:23
	* @Param [id, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdTaskLogPage")
	public String goUpdTaskLogPage(int id, Model model) {
		TaskLogVO taskLogVO = taskLogService.getTaskLogById(id);
		model.addAttribute("taskLogVO", taskLogVO);
		return "financial/taskloginfo/tasklog_edit";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年05月07日 11:45:23
	 * @Param [TaskLogDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdTaskLog")
	@ResponseBody
	public Map<String, Object> doUpdTaskLog(TaskLogDTO taskLogDTO) {
		Map<String, Object> jsonMap = taskLogService.updateTaskLog(taskLogDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年05月07日 11:45:23
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delTaskLogById")
	@ResponseBody
	public Map<String, Object> delTaskLogById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = taskLogService.deleteTaskLogById(id);
		return jsonMap;
	}
	
}
