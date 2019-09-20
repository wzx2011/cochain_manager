package com.manage.cochain.controller;

import com.manage.cochain.entity.dto.DemoIpDTO;
import com.manage.cochain.entity.vo.DemoIpVO;
import com.manage.cochain.service.IDemoIpService;
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
 * 远程网址端口信息 Controller控制层
 * @author wzx
 * @create 2019年05月17日 09:31:55
 **/
@Controller
public class DemoIpController extends BaseController{
	
	@Autowired
	private IDemoIpService demoIpService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入远程网址端口信息 首页
	 * @Date 2019年05月17日 09:31:55
	 * @return java.lang.String
	 **/
	@RequestMapping("goDemoIpPage")
	public String goDemoIpPage() {
		return "";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年05月17日 09:31:55
	* @Param []
	* @return java.lang.String
	**/
	@RequestMapping("goAddDemoIpPage")
	public String goAddDemoIpPage() {
		return "";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询远程网址端口信息 列表
	 * @Date 2019年05月17日 09:31:55
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getDemoIpList")
	@ResponseBody
	public Map<String, Object> getDemoIpList(int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		DemoIpDTO demoIpDTO=new DemoIpDTO();
		demoIpDTO.setStart((page - 1) * rows);
		demoIpDTO.setEnd(rows);
		jsonMap = demoIpService.getDemoIpAllList(demoIpDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月17日 09:31:55
	 * @Param [demoIpDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddDemoIp")
	@ResponseBody
	public Map<String, Object> doAddDemoIp(DemoIpDTO demoIpDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = demoIpService.saveDemoIp(demoIpDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年05月17日 09:31:55
	* @Param [id, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdDemoIpPage")
	public String goUpdDemoIpPage(int id, Model model) {
		DemoIpVO demoIpDTO = demoIpService.getDemoIpById(id);
		model.addAttribute("demoIpDTO", demoIpDTO);
		return "";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年05月17日 09:31:55
	 * @Param [DemoIpDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdDemoIp")
	@ResponseBody
	public Map<String, Object> doUpdDemoIp(DemoIpDTO demoIpDTO) {
		Map<String, Object> jsonMap = demoIpService.updateDemoIp(demoIpDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年05月17日 09:31:55
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delDemoIpById")
	@ResponseBody
	public Map<String, Object> delDemoIpById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = demoIpService.deleteDemoIpById(id);
		return jsonMap;
	}

	/**
	 * 获取ip集合信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getDemoIpVOList")
	@ResponseBody
	public ResponseData getDemoIpVOList() throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		DemoIpDTO demoIpDTO = new DemoIpDTO();
		List<DemoIpVO> demoIpVOList = demoIpService.getDemoIpVOList(demoIpDTO);
		ResponseData responseData = new ResponseData();
		responseData.setCode("0001");
		responseData.setMsg("获取ip信息成功！");
		responseData.setListData(demoIpVOList);
		return responseData;
	}
	
}
