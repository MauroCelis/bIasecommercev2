package com.bi_as.biasApp.dao;

import com.bi_as.biasApp.domain.UserAdmin;
import com.bi_as.biasApp.domain.UserSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAdminRepository extends JpaRepository<UserAdmin,Integer> {

    @Query(value = "select * from user_admin where id_useradmin=?1",nativeQuery = true)
    UserAdmin finduseradmin(int var1);

    @Query(value="SELECT * FROM user_admin order by user_admin.id_useradmin DESC LIMIT 1",nativeQuery = true)
    UserAdmin findLastUserAdminRegister();
}


