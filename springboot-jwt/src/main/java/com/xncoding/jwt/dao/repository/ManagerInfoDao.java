package com.xncoding.jwt.dao.repository;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xncoding.jwt.common.dao.repository.ManagerMapper;
import com.xncoding.jwt.dao.entity.ManagerInfo;

import java.util.List;
import java.util.Map;

/**
 * Description  :
 */
public interface ManagerInfoDao extends ManagerMapper {
    ManagerInfo findByUsername(String username);
}
