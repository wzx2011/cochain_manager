package com.manage.cochain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.cochain.entity.vo.LogVO;
import com.manage.cochain.entity.dto.LogDTO;

/**
 * 日志信息 Mappeer 接口层
 * @author wzx
 * @create 2019年05月07日 11:44:02
**/
public interface LogMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年05月07日 11:44:02
	 * @Param LogDTO
	 * @return List<LogVO>
	 **/
	List<LogVO> getLogAllList(LogDTO logDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年05月07日 11:44:02
	 * @Param LogDTO
	 * @return Integer
	 **/
	Integer findLogTotal(LogDTO logDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:44:02
	 * @Param LogDTO
	 * @return int
	 **/
	int saveLog(LogDTO logDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年05月07日 11:44:02
	 * @Param LogDTO
	 * @return int
	 **/
	int updateLog(LogDTO logDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年05月07日 11:44:02
	 * @Param LogDTO
	 * @return int
	 **/
	int deleteLogById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年05月07日 11:44:02
	 * @Param id
	 * @return LogVO
	 **/
	LogVO getLogById(@Param("id")Integer id);

}