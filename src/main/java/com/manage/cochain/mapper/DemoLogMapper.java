package com.manage.cochain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.cochain.entity.vo.DemoLogVO;
import com.manage.cochain.entity.dto.DemoLogDTO;

/**
 * 上链日志信息 Mappeer 接口层
 * @author wzx
 * @create 2019年05月22日 17:17:05
**/
public interface DemoLogMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年05月22日 17:17:05
	 * @Param DemoLogDTO
	 * @return List<DemoLogVO>
	 **/
	List<DemoLogVO> getDemoLogAllList(DemoLogDTO demoLogDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年05月22日 17:17:05
	 * @Param DemoLogDTO
	 * @return Integer
	 **/
	Integer findDemoLogTotal(DemoLogDTO demoLogDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月22日 17:17:05
	 * @Param DemoLogDTO
	 * @return int
	 **/
	int saveDemoLog(DemoLogDTO demoLogDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年05月22日 17:17:05
	 * @Param DemoLogDTO
	 * @return int
	 **/
	int updateDemoLog(DemoLogDTO demoLogDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年05月22日 17:17:05
	 * @Param DemoLogDTO
	 * @return int
	 **/
	int deleteDemoLogById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年05月22日 17:17:05
	 * @Param id
	 * @return DemoLogVO
	 **/
	DemoLogVO getDemoLogById(@Param("id")Integer id);

}