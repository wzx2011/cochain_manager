package com.manage.cochain.controller;

import com.manage.cochain.entity.dto.ArtifactIdsDTO;
import com.manage.cochain.entity.vo.ArtifactIdsVO;
import com.manage.cochain.service.IArtifactIdsService;
import com.wangzhixuan.commons.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品编码信息 Controller控制层
 * @author wzx
 * @create 2019年05月07日 11:40:41
 **/
@Controller
public class ArtifactIdsController extends BaseController{
	
	@Autowired
	private IArtifactIdsService artifactIdsService;

	/**
	 * @Author wzx
	 * @Description //TODO 进入商品编码信息 首页
	 * @Date 2019年05月07日 11:40:41
	 * @return java.lang.String
	 **/
	@RequestMapping("goArtifactIdsPage")
	public String goArtifactIdsPage() {
		return "financial/artifactidsinfo/artifactids_list";
	}

	/**
	* @Author wzx
	* @Description //TODO 进入新增页面
	* @Date 2019年05月07日 11:40:41
	* @Param [model]
	* @return java.lang.String
	**/
	@RequestMapping("goAddArtifactIdsPage")
	public String goAddArtifactIdsPage() {
		return "financial/artifactidsinfo/artifactids_add";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询商品编码信息 列表
	 * @Date 2019年05月07日 11:40:41
	 * @Param [page, rows]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("getArtifactIdsList")
	@ResponseBody
	public Map<String, Object> getArtifactIdsList(String hash, int page, int rows) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		ArtifactIdsDTO artifactIdsDTO=new ArtifactIdsDTO();
		artifactIdsDTO.setHash(hash);
		artifactIdsDTO.setStart((page - 1) * rows);
		artifactIdsDTO.setEnd(rows);
		jsonMap = artifactIdsService.getArtifactIdsAllList(artifactIdsDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:40:41
	 * @Param [artifactIdsDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doAddArtifactIds")
	@ResponseBody
	public Map<String, Object> doAddArtifactIds(ArtifactIdsDTO artifactIdsDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = artifactIdsService.saveArtifactIds(artifactIdsDTO);
		return jsonMap;
	}

	/**
	* @Author wzx
	* @Description //TODO 根据id查询信息，到修改页面
	* @Date 2019年05月07日 11:40:41
	* @Param [id, paramMap, model]
	* @return java.lang.String
	**/
	@RequestMapping("goUpdArtifactIdsPage")
	public String goUpdArtifactIdsPage(int id, Model model) {
		ArtifactIdsVO artifactIdsVO = artifactIdsService.getArtifactIdsById(id);
		model.addAttribute("artifactIdsVO", artifactIdsVO);
		return "financial/artifactidsinfo/artifactids_edit";
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改信息
	 * @Date 2019年05月07日 11:40:41
	 * @Param [ArtifactIdsDTO]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("doUpdArtifactIds")
	@ResponseBody
	public Map<String, Object> doUpdArtifactIds(ArtifactIdsDTO artifactIdsDTO) {
		Map<String, Object> jsonMap = artifactIdsService.updateArtifactIds(artifactIdsDTO);
		return jsonMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 根据id删除
	 * @Date 2019年05月07日 11:40:41
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping("delArtifactIdsById")
	@ResponseBody
	public Map<String, Object> delArtifactIdsById(Integer id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap = artifactIdsService.deleteArtifactIdsById(id);
		return jsonMap;
	}
	
}
