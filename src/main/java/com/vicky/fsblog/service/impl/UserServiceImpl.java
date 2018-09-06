package com.vicky.fsblog.service.impl;

import com.vicky.fsblog.consts.SessionConstants;
import com.vicky.fsblog.entity.User;
import com.vicky.fsblog.entity.dto.form.UserLoginForm;
import com.vicky.fsblog.entity.dto.form.UserRegisterForm;
import com.vicky.fsblog.mapper.UserMapper;
import com.vicky.fsblog.service.api.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户信息操作业务实现类
 *
 * @author James
 */
@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  private UserMapper mMapper;

  @Override
  public User loginAuthentication(UserLoginForm loginForm) {
    List<User> userList = mMapper.select(new User().setUsername(loginForm.getUsername()).setPassword(DigestUtils.md5Hex(loginForm.getPassword())));
    if (null != userList && userList.size() == 1) {
      return userList.get(0);
    }
    return null;
  }

  @Override
  public boolean registerUsernameCheckExist(UserRegisterForm registerForm) {
    return mMapper.select(new User().setUsername(registerForm.getUsername())).size() > 0;
  }

  @Override
  public void insertUser(User user) {
    String pwdStr = user.getPassword();
    user.setPassword(DigestUtils.md5Hex(pwdStr));
    mMapper.insertSelective(user);
  }

  @Override
  public void joinSession(HttpServletRequest request, User user) {
    HttpSession requestSession = request.getSession(true);
    requestSession.setAttribute(SessionConstants.SESSION_CURRENT_USER, user);
  }

  @Override
  public void destroySession(HttpServletRequest request) {
    HttpSession requestSession = request.getSession(true);
    requestSession.removeAttribute(SessionConstants.SESSION_CURRENT_USER);
  }
}
