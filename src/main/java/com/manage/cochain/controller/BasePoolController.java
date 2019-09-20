package com.manage.cochain.controller;

import com.manage.cochain.entity.dto.BasePoolDTO;
import com.manage.cochain.entity.vo.BasePoolVO;
import com.manage.cochain.service.IBasePoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.wangzhixuan.commons.base.BaseController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础数据池 Controller控制层
 * @author wzx
 * @create 2019年08月22日 10:21:07
 **/
@Controller
public class BasePoolController extends BaseController{
	
	@Autowired
	private IBasePoolService basePoolService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入基础数据池 首页
	 * @Date 2019年08月22日 10:21:07
	 * @return java.lang.String
	 **/
	@RequestMapping("goBasePoolPage")
	public String goBasePoolPage() {
		return "financial/poolinfo/basepool_list";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年08月22日 10:21:07
	* @Param []
	* @return java.lang.String
	**/
	@RequestMapping("goAddBasePoolPage")
	public String goAddBasePoolPage() {
		return "";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询基础数据池 列表
	 * @Date 2019年08月22日 10:21:07
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getBasePoolList")
	@ResponseBody
	public Map<String, Object> getBasePoolList(int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		BasePoolDTO basePoolDTO=new BasePoolDTO();
		basePoolDTO.setStart((page - 1) * rows);
		basePoolDTO.setEnd(rows);
		jsonMap = basePoolService.getBasePoolAllList(basePoolDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年08月22日 10:21:07
	 * @Param [basePoolDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddBasePool")
	@ResponseBody
	public Map<String, Object> doAddBasePool(BasePoolDTO basePoolDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = basePoolService.saveBasePool(basePoolDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年08月22日 10:21:07
	* @Param [id, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdBasePoolPage")
	public String goUpdBasePoolPage(int id, Model model) {
		BasePoolVO basePoolVO = basePoolService.getBasePoolById(id);
		model.addAttribute("basePoolVO", basePoolVO);
		return "financial/poolinfo/basepool_edit";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年08月22日 10:21:07
	 * @Param [BasePoolDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdBasePool")
	@ResponseBody
	public Map<String, Object> doUpdBasePool(BasePoolDTO basePoolDTO) {
		Map<String, Object> jsonMap = basePoolService.updateBasePool(basePoolDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年08月22日 10:21:07
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delBasePoolById")
	@ResponseBody
	public Map<String, Object> delBasePoolById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = basePoolService.deleteBasePoolById(id);
		return jsonMap;
	}
	
}
