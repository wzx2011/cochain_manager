package com.manage.cochain.controller;

import com.manage.cochain.entity.dto.TaskDTO;
import com.manage.cochain.entity.vo.TaskVO;
import com.manage.cochain.service.ITaskService;
import com.wangzhixuan.commons.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务信息 Controller控制层
 * @author wzx
 * @create 2019年05月07日 11:44:45
 **/
@Controller
public class TaskController extends BaseController{
	
	@Autowired
	private ITaskService taskService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入任务信息 首页
	 * @Date 2019年05月07日 11:44:45
	 * @return java.lang.String
	 **/
	@RequestMapping("goTaskPage")
	public String goTaskPage() {
		return "financial/taskinfo/task_list";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年05月07日 11:44:45
	* @Param [model]
	* @return java.lang.String
	**/
	@RequestMapping("goAddTaskPage")
	public String goAddTaskPage() {
		return "financial/taskinfo/task_add";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询任务信息 列表
	 * @Date 2019年05月07日 11:44:45
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getTaskList")
	@ResponseBody
	public Map<String, Object> getTaskList(int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		TaskDTO taskDTO=new TaskDTO();
		taskDTO.setStart((page - 1) * rows);
		taskDTO.setEnd(rows);
		jsonMap = taskService.getTaskAllList(taskDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:44:45
	 * @Param [taskDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddTask")
	@ResponseBody
	public Map<String, Object> doAddTask(TaskDTO taskDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = taskService.saveTask(taskDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年05月07日 11:44:45
	* @Param [ id, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdTaskPage")
	public String goUpdTaskPage(int id, Model model) {
		TaskVO taskVO = taskService.getTaskById(id);
		model.addAttribute("taskVO", taskVO);
		return "financial/taskinfo/task_edit";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年05月07日 11:44:45
	 * @Param [TaskDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdTask")
	@ResponseBody
	public Map<String, Object> doUpdTask(TaskDTO taskDTO) {
		Map<String, Object> jsonMap = taskService.updateTask(taskDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年05月07日 11:44:45
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delTaskById")
	@ResponseBody
	public Map<String, Object> delTaskById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = taskService.deleteTaskById(id);
		return jsonMap;
	}
	
}
