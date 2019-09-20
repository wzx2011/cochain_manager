package com.manage.cochain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.cochain.entity.vo.TaskLogVO;
import com.manage.cochain.entity.dto.TaskLogDTO;

/**
 * 任务日志信息 Mappeer 接口层
 * @author wzx
 * @create 2019年05月07日 11:45:23
**/
public interface TaskLogMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年05月07日 11:45:23
	 * @Param TaskLogDTO
	 * @return List<TaskLogVO>
	 **/
	List<TaskLogVO> getTaskLogAllList(TaskLogDTO taskLogDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年05月07日 11:45:23
	 * @Param TaskLogDTO
	 * @return Integer
	 **/
	Integer findTaskLogTotal(TaskLogDTO taskLogDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:45:23
	 * @Param TaskLogDTO
	 * @return int
	 **/
	int saveTaskLog(TaskLogDTO taskLogDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年05月07日 11:45:23
	 * @Param TaskLogDTO
	 * @return int
	 **/
	int updateTaskLog(TaskLogDTO taskLogDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年05月07日 11:45:23
	 * @Param TaskLogDTO
	 * @return int
	 **/
	int deleteTaskLogById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年05月07日 11:45:23
	 * @Param id
	 * @return TaskLogVO
	 **/
	TaskLogVO getTaskLogById(@Param("id")Integer id);

}