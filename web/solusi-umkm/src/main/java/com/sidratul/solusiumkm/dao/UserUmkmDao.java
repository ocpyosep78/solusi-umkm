package com.sidratul.solusiumkm.dao;

import com.sidratul.solusiumkm.model.UserUmkm;
import java.util.List;

public interface UserUmkmDao {
    
    public List<UserUmkm> getAllUser(Boolean aktif);
    
    public void saveUserUmkm(UserUmkm userUmkm);
    
    public UserUmkm getUserById(Integer id);
    
    public void deleteUserUmkm(Integer id);
    
}
