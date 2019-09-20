package com.manage.cochain.controller;

import com.manage.cochain.entity.dto.DemoProjectDTO;
import com.manage.cochain.entity.vo.DemoProjectVO;
import com.manage.cochain.service.IDemoProjectService;
import com.manage.util.ResponseData;
import com.wangzhixuan.commons.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 远程接口项目信息 Controller控制层
 * @author wzx
 * @create 2019年05月17日 09:33:36
 **/
@Controller
public class DemoProjectController extends BaseController{
	
	@Autowired
	private IDemoProjectService demoProjectService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入远程接口项目信息 首页
	 * @Date 2019年05月17日 09:33:36
	 * @return java.lang.String
	 **/
	@RequestMapping("goDemoProjectPage")
	public String goDemoProjectPage() {
		return "";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年05月17日 09:33:36
	* @Param []
	* @return java.lang.String
	**/
	@RequestMapping("goAddDemoProjectPage")
	public String goAddDemoProjectPage() {
		return "";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询远程接口项目信息 列表
	 * @Date 2019年05月17日 09:33:36
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getDemoProjectList")
	@ResponseBody
	public Map<String, Object> getDemoProjectList(int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		DemoProjectDTO demoProjectDTO=new DemoProjectDTO();
		demoProjectDTO.setStart((page - 1) * rows);
		demoProjectDTO.setEnd(rows);
		jsonMap = demoProjectService.getDemoProjectAllList(demoProjectDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月17日 09:33:36
	 * @Param [demoProjectDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddDemoProject")
	@ResponseBody
	public Map<String, Object> doAddDemoProject(DemoProjectDTO demoProjectDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = demoProjectService.saveDemoProject(demoProjectDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年05月17日 09:33:36
	* @Param [id, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdDemoProjectPage")
	public String goUpdDemoProjectPage(int id, Model model) {
		DemoProjectVO demoProjectDTO = demoProjectService.getDemoProjectById(id);
		model.addAttribute("demoProjectDTO", demoProjectDTO);
		return "";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年05月17日 09:33:36
	 * @Param [DemoProjectDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdDemoProject")
	@ResponseBody
	public Map<String, Object> doUpdDemoProject(DemoProjectDTO demoProjectDTO) {
		Map<String, Object> jsonMap = demoProjectService.updateDemoProject(demoProjectDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年05月17日 09:33:36
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delDemoProjectById")
	@ResponseBody
	public Map<String, Object> delDemoProjectById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = demoProjectService.deleteDemoProjectById(id);
		return jsonMap;
	}

	/**
	 * 获取project集合
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getDemoProjectVOList")
	@ResponseBody
	public ResponseData getDemoProjectVOList() throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		DemoProjectDTO demoProjectDTO = new DemoProjectDTO();
		List<DemoProjectVO> demoProjectVOS = demoProjectService.getDemoProjectVOList(demoProjectDTO);
		ResponseData responseData = new ResponseData();
		responseData.setCode("0001");
		responseData.setMsg("获取project信息成功！");
		responseData.setListData(demoProjectVOS);
		return responseData;
	}
	
}
