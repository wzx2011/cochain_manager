package com.manage.cochain.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.manage.cochain.mapper.TransactionPoolMapper;
import com.manage.cochain.entity.vo.TransactionPoolVO;
import com.manage.cochain.entity.dto.TransactionPoolDTO;
import com.manage.cochain.service.ITransactionPoolService;


/**
 * 交易数据池 Service 实现层
 * @author wzx
 * @create 2019年08月22日 10:22:10
 **/
@Service
public class TransactionPoolServiceImpl implements ITransactionPoolService  {

	@Autowired
	private TransactionPoolMapper transactionPoolMapper;

	/**
	 * @Author  wzx
	 * @Description //TODO 查询分页数据
	 * @Date 2019年08月22日 10:22:10
	 * @Param TransactionPoolDTO
	 * @return List<TransactionPoolVO>
	 **/
	@Override
	public Map<String,Object> getTransactionPoolAllList(TransactionPoolDTO transactionPoolDTO){
		Map<String,Object> resultMap=new HashMap<>();
		List<TransactionPoolVO> transactionPoolList = new ArrayList<>();
		Integer transactionPoolTotal = transactionPoolMapper.findTransactionPoolTotal(transactionPoolDTO);
		if(transactionPoolTotal>0){
			transactionPoolList = transactionPoolMapper.getTransactionPoolAllList(transactionPoolDTO);
		}
		resultMap.put("rows", transactionPoolList);
		resultMap.put("total", transactionPoolTotal);
		return resultMap;
	}

	/**
	 * @Author wzx
	 * @Description //TODO 新增
	 * @Date 2019年08月22日 10:22:10
	 * @Param TransactionPoolDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> saveTransactionPool(TransactionPoolDTO transactionPoolDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(transactionPoolMapper.saveTransactionPool(transactionPoolDTO)>0){
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
	 * @Date 2019年08月22日 10:22:10
	 * @Param TransactionPoolDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> updateTransactionPool(TransactionPoolDTO transactionPoolDTO){
		Map<String,Object> resultMap=new HashMap<>();
		if(transactionPoolMapper.updateTransactionPool(transactionPoolDTO)>0){
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
	 * @Date 2019年08月22日 10:22:10
	 * @Param TransactionPoolDTO
	 * @return int
	 **/
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> deleteTransactionPoolById(Integer id){
		Map<String,Object> resultMap=new HashMap<>();
		if(transactionPoolMapper.deleteTransactionPoolById(id)>0){
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
	 * @Date 2019年08月22日 10:22:10
	 * @Param id
	 * @return TransactionPoolVO
	 **/
	@Override
	public TransactionPoolVO getTransactionPoolById(Integer id){
		return transactionPoolMapper.getTransactionPoolById(id);
	}
	
}
