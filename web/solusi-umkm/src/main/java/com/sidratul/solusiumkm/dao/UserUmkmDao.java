package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.UserUmkm;
import java.util.List;

public interface UserUmkmDao {
    
    public List<UserUmkm> getAllUser(Integer aktif);
    
    public void saveUserUmkm(UserUmkm userUmkm);
    
    public UserUmkm getUserById(Integer id);
    
    public void deleteUserUmkm(Integer id);
    
    public UserUmkm getUserByUsername(String username);
    
    public UserUmkm getUserByUsernameAndPassword(String username, String password);
    
}
