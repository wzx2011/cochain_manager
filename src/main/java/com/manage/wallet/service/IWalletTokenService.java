package com.manage.wallet.service;


import com.manage.wallet.entity.vo.WalletTokenVO;
import com.manage.wallet.entity.dto.WalletTokenDTO;

import java.util.List;
import java.util.Map;
/**
 * 钱包积分信息 Service接口层
 * @author wzx
 * @create 2019年06月12日 13:50:56
 **/
public interface IWalletTokenService {

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年06月12日 13:50:56
	 * @Param WalletTokenDTO
	 * @return List<WalletTokenVO>
	 **/
	Map<String,Object> getWalletTokenAllList(WalletTokenDTO walletTokenDTO);

	/**
	* @Author wzx
	* @Description //TODO 新增
	* @Date 2019年06月12日 13:50:56
	* @Param WalletTokenDTO
	* @return Map
	**/
	Map<String, Object> saveWalletToken(WalletTokenDTO walletTokenDTO);

	/**
	* @Author wzx
	* @Description //TODO 修改
	* @Date 2019年06月12日 13:50:56
	* @Param WalletTokenDTO
	* @return Map
	**/
	Map<String, Object> updateWalletToken(WalletTokenDTO walletTokenDTO);

	/**
	* @Author wzx
	* @Description //TODO 删除
	* @Date 2019年06月12日 13:50:56
	* @Param WalletTokenDTO
	* @return Map
	**/
	Map<String, Object> deleteWalletTokenById(Integer id);

	/**
	* @Author wzx
	* @Description //TODO 查询单个
	* @Date 2019年06月12日 13:50:56
	* @Param id
	* @return WalletTokenVO
	**/
	WalletTokenVO getWalletTokenById(Integer id);

	/**
	 * 根据当前用户id连表查询获取当前用户的钱包积分数据
	 * @param id
	 * @return
	 */
	List<WalletTokenVO> getWalletTokenByUserId(Integer id);
}
