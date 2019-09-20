package com.manage.wallet.service;


import com.manage.wallet.entity.vo.WalletTransactionVO;
import com.manage.wallet.entity.dto.WalletTransactionDTO;

import java.util.List;
import java.util.Map;
/**
 * 钱包交易信息 Service接口层
 * @author wzx
 * @create 2019年06月12日 13:49:34
 **/
public interface IWalletTransactionService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年06月12日 13:49:34
	 * @Param WalletTransactionDTO
	 * @return List<WalletTransactionVO>
	 **/
	Map<String,Object> getWalletTransactionAllList(WalletTransactionDTO walletTransactionDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年06月12日 13:49:34
	* @Param WalletTransactionDTO
	* @return Map
	**/
	Map<String, Object> saveWalletTransaction(WalletTransactionDTO walletTransactionDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年06月12日 13:49:34
	* @Param WalletTransactionDTO
	* @return Map
	**/
	Map<String, Object> updateWalletTransaction(WalletTransactionDTO walletTransactionDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年06月12日 13:49:34
	* @Param WalletTransactionDTO
	* @return Map
	**/
	Map<String, Object> deleteWalletTransactionById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年06月12日 13:49:34
	* @Param id
	* @return WalletTransactionVO
	**/
	WalletTransactionVO getWalletTransactionById(Integer id);

	/**
	 * 根据当前用户id和tokenId连表查询获取当前用户的钱包交易数据
	 * @param paraMap
	 * @return
	 */
	List<Map<String, Object>> getWalletTransactionByUserId(Map<String, Object> paraMap);
}
