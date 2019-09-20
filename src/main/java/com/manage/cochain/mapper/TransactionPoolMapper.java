package com.manage.cochain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.manage.cochain.entity.vo.TransactionPoolVO;
import com.manage.cochain.entity.dto.TransactionPoolDTO;

/**
 * 交易数据池 Mappeer 接口层
 * @author wzx
 * @create 2019年08月22日 10:22:10
**/
public interface TransactionPoolMapper {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询所有
	 * @Date 2019年08月22日 10:22:10
	 * @Param TransactionPoolDTO
	 * @return List<TransactionPoolVO>
	 **/
	List<TransactionPoolVO> getTransactionPoolAllList(TransactionPoolDTO transactionPoolDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 统计数据
	 * @Date 2019年08月22日 10:22:10
	 * @Param TransactionPoolDTO
	 * @return Integer
	 **/
	Integer findTransactionPoolTotal(TransactionPoolDTO transactionPoolDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年08月22日 10:22:10
	 * @Param TransactionPoolDTO
	 * @return int
	 **/
	int saveTransactionPool(TransactionPoolDTO transactionPoolDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年08月22日 10:22:10
	 * @Param TransactionPoolDTO
	 * @return int
	 **/
	int updateTransactionPool(TransactionPoolDTO transactionPoolDTO);

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年08月22日 10:22:10
	 * @Param TransactionPoolDTO
	 * @return int
	 **/
	int deleteTransactionPoolById(@Param("id")Integer id);

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年08月22日 10:22:10
	 * @Param id
	 * @return TransactionPoolVO
	 **/
	TransactionPoolVO getTransactionPoolById(@Param("id")Integer id);

}