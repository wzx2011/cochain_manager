package com.manage.wallet.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.manage.wallet.mapper.WalletTransactionMapper;
import com.manage.wallet.entity.vo.WalletTransactionVO;
import com.manage.wallet.entity.dto.WalletTransactionDTO;
import com.manage.wallet.service.IWalletTransactionService;


/**
 * 钱包交易信息 Service 实现层
 * @author wzx
 * @create 2019年06月12日 13:49:34
 **/
@Service
public class WalletTransactionServiceImpl implements IWalletTransactionService  {

	@Autowired
	private WalletTransactionMapper walletTransactionMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年06月12日 13:49:34
	 * @Param WalletTransactionDTO
	 * @return List<WalletTransactionVO>
	 **/
	@Override
	public Map<String,Object> getWalletTransactionAllList(WalletTransactionDTO walletTransactionDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<WalletTransactionVO> walletTransactionList = new ArrayList<>();
		Integer walletTransactionTotal = walletTransactionMapper.findWalletTransactionTotal(walletTransactionDTO);
		if(walletTransactionTotal>0){
			walletTransactionList = walletTransactionMapper.getWalletTransactionAllList(walletTransactionDTO);
		}
		resultMap.put("rows", walletTransactionList);
		resultMap.put("total", walletTransactionTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年06月12日 13:49:34
	 * @Param WalletTransactionDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveWalletTransaction(WalletTransactionDTO walletTransactionDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(walletTransactionMapper.saveWalletTransaction(walletTransactionDTO)>0){
			resultMap.put("info", true);
			resultMap.put("msg", "添加成功！");
		}else {
			resultMap.put("info", false);
			resultMap.put("msg", "添加失败！");
		}
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 修改
	 * @Date 2019年06月12日 13:49:34
	 * @Param WalletTransactionDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateWalletTransaction(WalletTransactionDTO walletTransactionDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(walletTransactionMapper.updateWalletTransaction(walletTransactionDTO)>0){
			resultMap.put("info", true);
			resultMap.put("msg", "修改成功！");
		}else {
			resultMap.put("info", false);
			resultMap.put("msg", "修改失败！");
		}
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 删除
	 * @Date 2019年06月12日 13:49:34
	 * @Param WalletTransactionDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteWalletTransactionById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(walletTransactionMapper.deleteWalletTransactionById(id)>0){
			resultMap.put("info", true);
			resultMap.put("msg", "修改成功！");
		}else {
			resultMap.put("info", false);
			resultMap.put("msg", "修改失败！");
		}
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 查询单个
	 * @Date 2019年06月12日 13:49:34
	 * @Param id
	 * @return WalletTransactionVO
	 **/
	@Override
	public WalletTransactionVO getWalletTransactionById(Integer id){
		return walletTransactionMapper.getWalletTransactionById(id);
	}

	/**
	 * 根据当前用户id和tokenId连表查询获取当前用户的钱包交易数据
	 *
	 * @param paraMap
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getWalletTransactionByUserId(Map<String, Object> paraMap) {
		return walletTransactionMapper.getWalletTransactionByUserId(paraMap);
	}

}
