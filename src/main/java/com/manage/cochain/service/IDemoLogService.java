package com.manage.cochain.service;


import com.manage.cochain.entity.vo.DemoLogVO;
import com.manage.cochain.entity.dto.DemoLogDTO;
import java.util.Map;
/**
 * 上链日志信息 Service接口层
 * @author wzx
 * @create 2019年05月22日 17:17:05
 **/
public interface IDemoLogService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年05月22日 17:17:05
	 * @Param DemoLogDTO
	 * @return List<DemoLogVO>
	 **/
	Map<String,Object> getDemoLogAllList(DemoLogDTO demoLogDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年05月22日 17:17:05
	* @Param DemoLogDTO
	* @return Map
	**/
	Map<String, Object> saveDemoLog(DemoLogDTO demoLogDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年05月22日 17:17:05
	* @Param DemoLogDTO
	* @return Map
	**/
	Map<String, Object> updateDemoLog(DemoLogDTO demoLogDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年05月22日 17:17:05
	* @Param DemoLogDTO
	* @return Map
	**/
	Map<String, Object> deleteDemoLogById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年05月22日 17:17:05
	* @Param id
	* @return DemoLogVO
	**/
	DemoLogVO getDemoLogById(Integer id);
}
