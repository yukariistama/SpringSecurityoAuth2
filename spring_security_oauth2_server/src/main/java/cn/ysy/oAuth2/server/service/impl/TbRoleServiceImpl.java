package cn.ysy.oAuth2.server.service.impl;


import cn.ysy.oAuth2.server.mapper.TbRoleMapper;
import cn.ysy.oAuth2.server.service.TbRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TbRoleServiceImpl implements TbRoleService {

    @Resource
    private TbRoleMapper tbRoleMapper;

}
