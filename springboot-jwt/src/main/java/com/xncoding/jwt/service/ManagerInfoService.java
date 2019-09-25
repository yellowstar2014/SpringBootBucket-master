package com.xncoding.jwt.service;

import com.xncoding.jwt.dao.entity.ManagerInfo;
import com.xncoding.jwt.dao.entity.SysRole;
import com.xncoding.jwt.shiro.ShiroKit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.xncoding.jwt.dao.entity.*;
import com.xncoding.jwt.dao.repository.ManagerInfoDao;
import javax.annotation.Resource;
import java.util.Collections;
import org.apache.shiro.authc.UnknownAccountException;
import com.xncoding.jwt.exception.ForbiddenUserException;

/**
 * 后台用户管理
 */

@Service
public class ManagerInfoService {

    @Resource
    private ManagerInfoDao managerInfoDao;

    /**
     * 通过名称查找用户
     * @param username
     * @return
     */
    public ManagerInfo findByUsername(String username) {
        ManagerInfo managerInfo =  managerInfoDao.findByUsername(username);
        if (managerInfo == null) {
            throw new UnknownAccountException();
        }
        if (managerInfo.getState() == 2) {
            throw new ForbiddenUserException();
        }
        if (managerInfo.getPidsList() == null) {
            managerInfo.setPidsList(Collections.singletonList(0));
        } else if (managerInfo.getPidsList().size() == 0) {
            managerInfo.getPidsList().add(0);
        }
        return managerInfo;
    }

    /**
     * 通过名称查找用户
     * 这里我直接写常量，实际生产环境会通过DAO访问数据库
     *
     * @param username
     * @return
     */
//    public ManagerInfo findByUsername(String username) {
//        ManagerInfo managerInfo = new ManagerInfo();
//        managerInfo.setUsername(username);
//        managerInfo.setPids("1,2,3");
//        managerInfo.setPidsList(Arrays.asList(1, 2, 3));
//        managerInfo.setPnames("第1个,第2个");
//        managerInfo.setState(1);
//        managerInfo.setCreatedTime(new Date());
//        managerInfo.setName("系统管理员");
//        // 随机数
//        managerInfo.setSalt("ef748186673033723bbf4e056f4ec92e");
//        //managerInfo.setPassword("da9c3061ae5c0973a3d48e4e721cfbad");
//        managerInfo.setPassword("75fd1c5eaf64f4c5f24b9fcca1230140");//密码是123456
//        List<SysRole> roles = new ArrayList<>();
//        SysRole role = new SysRole();
//        role.setId(1);
//        role.setRole("admin");
//        role.setDescription("超级管理员");
//
//        List<Permission> permissions = new ArrayList<>();
//        Permission permission = new Permission();
//        permission.setId(1);
//        permission.setPermission("p:admin");
//        permissions.add(permission);
//        role.setPermissions(permissions);
//
//        roles.add(role);
//        managerInfo.setRoles(roles);
//        return managerInfo;
//    }



}
