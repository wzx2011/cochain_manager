package com.manage.cochain.service;


import com.manage.cochain.entity.vo.TransactionPoolVO;
import com.manage.cochain.entity.dto.TransactionPoolDTO;
import java.util.Map;
/**
 * 交易数据池 Service接口层
 * @author wzx
 * @create 2019年08月22日 10:22:10
 **/
public interface ITransactionPoolService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年08月22日 10:22:10
	 * @Param TransactionPoolDTO
	 * @return List<TransactionPoolVO>
	 **/
	Map<String,Object> getTransactionPoolAllList(TransactionPoolDTO transactionPoolDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年08月22日 10:22:10
	* @Param TransactionPoolDTO
	* @return Map
	**/
	Map<String, Object> saveTransactionPool(TransactionPoolDTO transactionPoolDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年08月22日 10:22:10
	* @Param TransactionPoolDTO
	* @return Map
	**/
	Map<String, Object> updateTransactionPool(TransactionPoolDTO transactionPoolDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年08月22日 10:22:10
	* @Param TransactionPoolDTO
	* @return Map
	**/
	Map<String, Object> deleteTransactionPoolById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年08月22日 10:22:10
	* @Param id
	* @return TransactionPoolVO
	**/
	TransactionPoolVO getTransactionPoolById(Integer id);
}
