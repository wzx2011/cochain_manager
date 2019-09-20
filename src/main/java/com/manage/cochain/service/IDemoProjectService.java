package com.manage.cochain.service;


import com.manage.cochain.entity.vo.DemoProjectVO;
import com.manage.cochain.entity.dto.DemoProjectDTO;

import java.util.List;
import java.util.Map;
/**
 * 远程接口项目信息 Service接口层
 * @author wzx
 * @create 2019年05月17日 09:33:36
 **/
public interface IDemoProjectService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月17日 09:33:36
	 * @Param DemoProjectDTO
	 * @return List<DemoProjectVO>
	 **/
	Map<String,Object> getDemoProjectAllList(DemoProjectDTO demoProjectDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年05月17日 09:33:36
	* @Param DemoProjectDTO
	* @return Map
	**/
	Map<String, Object> saveDemoProject(DemoProjectDTO demoProjectDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年05月17日 09:33:36
	* @Param DemoProjectDTO
	* @return Map
	**/
	Map<String, Object> updateDemoProject(DemoProjectDTO demoProjectDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年05月17日 09:33:36
	* @Param DemoProjectDTO
	* @return Map
	**/
	Map<String, Object> deleteDemoProjectById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年05月17日 09:33:36
	* @Param id
	* @return DemoProjectVO
	**/
	DemoProjectVO getDemoProjectById(Integer id);

	/**
	 * 获取project集合
	 * @param demoProjectDTO
	 * @return
	 */
	List<DemoProjectVO> getDemoProjectVOList(DemoProjectDTO demoProjectDTO);
}
