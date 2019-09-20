package com.manage.cochain.controller;

import com.manage.cochain.entity.dto.DemoUrlDTO;
import com.manage.cochain.entity.vo.DemoUrlVO;
import com.manage.cochain.service.IDemoUrlService;
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
 * 远程接口上链路径信息 Controller控制层
 * @author wzx
 * @create 2019年05月17日 09:34:37
 **/
@Controller
public class DemoUrlController extends BaseController{
	
	@Autowired
	private IDemoUrlService demoUrlService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入远程接口上链路径信息 首页
	 * @Date 2019年05月17日 09:34:37
	 * @return java.lang.String
	 **/
	@RequestMapping("goDemoUrlPage")
	public String goDemoUrlPage() {
		return "";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年05月17日 09:34:37
	* @Param []
	* @return java.lang.String
	**/
	@RequestMapping("goAddDemoUrlPage")
	public String goAddDemoUrlPage() {
		return "";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询远程接口上链路径信息 列表
	 * @Date 2019年05月17日 09:34:37
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getDemoUrlList")
	@ResponseBody
	public Map<String, Object> getDemoUrlList(int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		DemoUrlDTO demoUrlDTO=new DemoUrlDTO();
		demoUrlDTO.setStart((page - 1) * rows);
		demoUrlDTO.setEnd(rows);
		jsonMap = demoUrlService.getDemoUrlAllList(demoUrlDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月17日 09:34:37
	 * @Param [demoUrlDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddDemoUrl")
	@ResponseBody
	public Map<String, Object> doAddDemoUrl(DemoUrlDTO demoUrlDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = demoUrlService.saveDemoUrl(demoUrlDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年05月17日 09:34:37
	* @Param [id, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdDemoUrlPage")
	public String goUpdDemoUrlPage(int id, Model model) {
		DemoUrlVO demoUrlDTO = demoUrlService.getDemoUrlById(id);
		model.addAttribute("demoUrlDTO", demoUrlDTO);
		return "";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年05月17日 09:34:37
	 * @Param [DemoUrlDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdDemoUrl")
	@ResponseBody
	public Map<String, Object> doUpdDemoUrl(DemoUrlDTO demoUrlDTO) {
		Map<String, Object> jsonMap = demoUrlService.updateDemoUrl(demoUrlDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年05月17日 09:34:37
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delDemoUrlById")
	@ResponseBody
	public Map<String, Object> delDemoUrlById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = demoUrlService.deleteDemoUrlById(id);
		return jsonMap;
	}

	/**
	 * 获取远程路径集合
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getDemoUrlVOList")
	@ResponseBody
	public ResponseData getDemoUrlVOList(String projectValue, Integer urlType) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		DemoUrlDTO demoUrlDTO = new DemoUrlDTO();
		demoUrlDTO.setProjectValue(projectValue);
		demoUrlDTO.setUrlType(urlType);
		List<DemoUrlVO> demoUrlVOS = demoUrlService.getDemoUrlVOList(demoUrlDTO);
		ResponseData responseData = new ResponseData();
		responseData.setCode("0001");
		responseData.setMsg("获取Url信息成功！");
		responseData.setListData(demoUrlVOS);
		return responseData;
	}
	
}
