package com.manage.cochain.service;


import com.manage.cochain.entity.vo.DemoIpVO;
import com.manage.cochain.entity.dto.DemoIpDTO;

import java.util.List;
import java.util.Map;
/**
 * 远程网址端口信息 Service接口层
 * @author wzx
 * @create 2019年05月17日 09:31:55
 **/
public interface IDemoIpService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月17日 09:31:55
	 * @Param DemoIpDTO
	 * @return List<DemoIpVO>
	 **/
	Map<String,Object> getDemoIpAllList(DemoIpDTO demoIpDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年05月17日 09:31:55
	* @Param DemoIpDTO
	* @return Map
	**/
	Map<String, Object> saveDemoIp(DemoIpDTO demoIpDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年05月17日 09:31:55
	* @Param DemoIpDTO
	* @return Map
	**/
	Map<String, Object> updateDemoIp(DemoIpDTO demoIpDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年05月17日 09:31:55
	* @Param DemoIpDTO
	* @return Map
	**/
	Map<String, Object> deleteDemoIpById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年05月17日 09:31:55
	* @Param id
	* @return DemoIpVO
	**/
	DemoIpVO getDemoIpById(Integer id);

	/**
	 * 获取ip集合信息
	 * @param demoIpDTO
	 * @return
	 */
	List<DemoIpVO> getDemoIpVOList(DemoIpDTO demoIpDTO);
}
