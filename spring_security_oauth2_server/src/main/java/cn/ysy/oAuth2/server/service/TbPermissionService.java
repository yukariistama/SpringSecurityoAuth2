package cn.ysy.oAuth2.server.service;



import cn.ysy.oAuth2.server.domain.TbPermission;

import java.util.List;

public interface TbPermissionService {
    default List<TbPermission> selectByUserId(Long userId) {
        return null;
    }
}
