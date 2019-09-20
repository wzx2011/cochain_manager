package com.manage.cochain.service;


import com.manage.cochain.entity.dto.DemoUrlDTO;
import com.manage.cochain.entity.vo.DemoUrlVO;

import java.util.List;
import java.util.Map;
/**
 * 远程接口上链路径信息 Service接口层
 * @author wzx
 * @create 2019年05月17日 09:34:37
 **/
public interface IDemoUrlService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月17日 09:34:37
	 * @Param DemoUrlDTO
	 * @return List<DemoUrlVO>
	 **/
	Map<String,Object> getDemoUrlAllList(DemoUrlDTO demoUrlDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年05月17日 09:34:37
	* @Param DemoUrlDTO
	* @return Map
	**/
	Map<String, Object> saveDemoUrl(DemoUrlDTO demoUrlDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年05月17日 09:34:37
	* @Param DemoUrlDTO
	* @return Map
	**/
	Map<String, Object> updateDemoUrl(DemoUrlDTO demoUrlDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年05月17日 09:34:37
	* @Param DemoUrlDTO
	* @return Map
	**/
	Map<String, Object> deleteDemoUrlById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年05月17日 09:34:37
	* @Param id
	* @return DemoUrlVO
	**/
	DemoUrlVO getDemoUrlById(Integer id);

	/**
	 * 获取远程路径集合
	 * @param demoUrlDTO
	 * @return
	 */
	List<DemoUrlVO> getDemoUrlVOList(DemoUrlDTO demoUrlDTO);
}
