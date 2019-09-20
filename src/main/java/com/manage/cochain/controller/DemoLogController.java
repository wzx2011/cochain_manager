package com.manage.cochain.controller;

import com.manage.cochain.entity.dto.DemoLogDTO;
import com.manage.cochain.entity.vo.DemoLogVO;
import com.manage.cochain.service.IDemoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.wangzhixuan.commons.base.BaseController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 上链日志信息 Controller控制层
 * @author wzx
 * @create 2019年05月22日 17:17:05
 **/
@Controller
public class DemoLogController extends BaseController{
	
	@Autowired
	private IDemoLogService demoLogService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入上链日志信息 首页
	 * @Date 2019年05月22日 17:17:05
	 * @return java.lang.String
	 **/
	@RequestMapping("goDemoLogPage")
	public String goDemoLogPage() {
		return "financial/chaininfo/demolog_list";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年05月22日 17:17:05
	* @Param []
	* @return java.lang.String
	**/
	@RequestMapping("goAddDemoLogPage")
	public String goAddDemoLogPage() {
		return "";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询上链日志信息 列表
	 * @Date 2019年05月22日 17:17:05
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getDemoLogList")
	@ResponseBody
	public Map<String, Object> getDemoLogList(String requestUrl, int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		DemoLogDTO demoLogDTO=new DemoLogDTO();
		demoLogDTO.setRequestUrl(requestUrl);
		demoLogDTO.setStart((page - 1) * rows);
		demoLogDTO.setEnd(rows);
		jsonMap = demoLogService.getDemoLogAllList(demoLogDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月22日 17:17:05
	 * @Param [demoLogDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddDemoLog")
	@ResponseBody
	public Map<String, Object> doAddDemoLog(DemoLogDTO demoLogDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = demoLogService.saveDemoLog(demoLogDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年05月22日 17:17:05
	* @Param [id, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdDemoLogPage")
	public String goUpdDemoLogPage(int id, Model model) {
		DemoLogVO demoLogVO = demoLogService.getDemoLogById(id);
		model.addAttribute("demoLogVO", demoLogVO);
		return "financial/chaininfo/demolog_detail";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年05月22日 17:17:05
	 * @Param [DemoLogDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdDemoLog")
	@ResponseBody
	public Map<String, Object> doUpdDemoLog(DemoLogDTO demoLogDTO) {
		Map<String, Object> jsonMap = demoLogService.updateDemoLog(demoLogDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年05月22日 17:17:05
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delDemoLogById")
	@ResponseBody
	public Map<String, Object> delDemoLogById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = demoLogService.deleteDemoLogById(id);
		return jsonMap;
	}
	
}
