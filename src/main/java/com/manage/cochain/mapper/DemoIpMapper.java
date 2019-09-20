package com.manage.cochain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.cochain.entity.vo.DemoIpVO;
import com.manage.cochain.entity.dto.DemoIpDTO;

/**
 * 远程网址端口信息 Mappeer 接口层
 * @author wzx
 * @create 2019年05月17日 09:31:55
**/
public interface DemoIpMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年05月17日 09:31:55
	 * @Param DemoIpDTO
	 * @return List<DemoIpVO>
	 **/
	List<DemoIpVO> getDemoIpAllList(DemoIpDTO demoIpDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年05月17日 09:31:55
	 * @Param DemoIpDTO
	 * @return Integer
	 **/
	Integer findDemoIpTotal(DemoIpDTO demoIpDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年05月17日 09:31:55
	 * @Param DemoIpDTO
	 * @return int
	 **/
	int saveDemoIp(DemoIpDTO demoIpDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年05月17日 09:31:55
	 * @Param DemoIpDTO
	 * @return int
	 **/
	int updateDemoIp(DemoIpDTO demoIpDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年05月17日 09:31:55
	 * @Param DemoIpDTO
	 * @return int
	 **/
	int deleteDemoIpById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年05月17日 09:31:55
	 * @Param id
	 * @return DemoIpVO
	 **/
	DemoIpVO getDemoIpById(@Param("id")Integer id);

	/**
	 * 获取ip集合信息
	 * @param demoIpDTO
	 * @return
	 */
	List<DemoIpVO> getDemoIpVOList(DemoIpDTO demoIpDTO);
}