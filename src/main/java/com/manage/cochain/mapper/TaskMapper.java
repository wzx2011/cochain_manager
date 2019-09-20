package com.manage.cochain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.cochain.entity.vo.TaskVO;
import com.manage.cochain.entity.dto.TaskDTO;

/**
 * 任务信息 Mappeer 接口层
 * @author wzx
 * @create 2019年05月07日 11:44:45
**/
public interface TaskMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年05月07日 11:44:45
	 * @Param TaskDTO
	 * @return List<TaskVO>
	 **/
	List<TaskVO> getTaskAllList(TaskDTO taskDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年05月07日 11:44:45
	 * @Param TaskDTO
	 * @return Integer
	 **/
	Integer findTaskTotal(TaskDTO taskDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月07日 11:44:45
	 * @Param TaskDTO
	 * @return int
	 **/
	int saveTask(TaskDTO taskDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年05月07日 11:44:45
	 * @Param TaskDTO
	 * @return int
	 **/
	int updateTask(TaskDTO taskDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年05月07日 11:44:45
	 * @Param TaskDTO
	 * @return int
	 **/
	int deleteTaskById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年05月07日 11:44:45
	 * @Param id
	 * @return TaskVO
	 **/
	TaskVO getTaskById(@Param("id")Integer id);

}