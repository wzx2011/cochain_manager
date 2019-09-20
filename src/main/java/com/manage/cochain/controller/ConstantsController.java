package com.manage.cochain.controller;

import com.manage.cochain.entity.dto.ConstantsDTO;
import com.manage.cochain.entity.vo.ConstantsVO;
import com.manage.cochain.service.IConstantsService;
import com.wangzhixuan.commons.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 常量信息 Controller控制层
 * @author wzx
 * @create 2019年05月07日 11:42:11
 **/
@Controller
public class ConstantsController extends BaseController{
	
	@Autowired
	private IConstantsService constantsService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入常量信息 首页
	 * @Date 2019年05月07日 11:42:11
	 * @return java.lang.String
	 **/
	@RequestMapping("goConstantsPage")
	public String goConstantsPage() {
		return "financial/constantsinfo/constants_list";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年05月07日 11:42:11
	* @Param [model]
	* @return java.lang.String
	**/
	@RequestMapping("goAddConstantsPage")
	public String goAddConstantsPage() {
		return "financial/constantsinfo/constants_add";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询常量信息 列表
	 * @Date 2019年05月07日 11:42:11
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getConstantsList")
	@ResponseBody
	public Map<String, Object> getConstantsList(int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		ConstantsDTO constantsDTO=new ConstantsDTO();
		constantsDTO.setStart((page - 1) * rows);
		constantsDTO.setEnd(rows);
		jsonMap = constantsService.getConstantsAllList(constantsDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:42:11
	 * @Param [constantsDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddConstants")
	@ResponseBody
	public Map<String, Object> doAddConstants(ConstantsDTO constantsDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = constantsService.saveConstants(constantsDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年05月07日 11:42:11
	* @Param [id, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdConstantsPage")
	public String goUpdConstantsPage(int id, Model model) {
		ConstantsVO constantsVO = constantsService.getConstantsById(id);
		model.addAttribute("constantsVO", constantsVO);
		return "financial/constantsinfo/constants_edit";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年05月07日 11:42:11
	 * @Param [ConstantsDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdConstants")
	@ResponseBody
	public Map<String, Object> doUpdConstants(ConstantsDTO constantsDTO) {
		Map<String, Object> jsonMap = constantsService.updateConstants(constantsDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年05月07日 11:42:11
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delConstantsById")
	@ResponseBody
	public Map<String, Object> delConstantsById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = constantsService.deleteConstantsById(id);
		return jsonMap;
	}
	
}
