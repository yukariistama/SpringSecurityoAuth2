package cn.ysy.oAuth2.server.service;


import cn.ysy.oAuth2.server.domain.TbUser;

public interface TbUserService {
    default TbUser getByUsername(String username) {
        return null;
    }

   // TbUser getByUsername(String username);
}
