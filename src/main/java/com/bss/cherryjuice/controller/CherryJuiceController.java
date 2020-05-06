package com.bss.cherryjuice.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CherryJuiceController {

    private final CherryJuiceMapper mapper;
    public CherryJuiceController(CherryJuiceMapper mapper) {
        this.mapper = mapper;
    }

    @RequestMapping(value="/api/getUser", method=RequestMethod.POST)
    public GhostUser getUser(@RequestBody GhostUser user){ return this.mapper.userSelectByUserName(user.getUser_name());}

    @RequestMapping(value="/api/updatePassword", method=RequestMethod.POST)
    public int updatePassword(@RequestBody GhostUser user){ return this.mapper.userUpdatePassword(user.getUser_name(), user.getPassword(), user.getNew_password());}

    @RequestMapping(value="/api/getLock", method=RequestMethod.POST)
    public List<GhostLock> getLock(){ return this.mapper.lockSelectNotFree(); }

    @RequestMapping(value="/api/requireLock", method=RequestMethod.POST)
    public boolean requireLock(@RequestBody GhostLock lock){
        GhostLock myLock  = this.mapper.lockSelectRequire(lock.getGhost_host(), lock.getUser_name());
        if( myLock != null ){
            myLock.setUser_name(lock.getUser_name());
            myLock.setLock_status("reserved");
            int cnt = this.mapper.lockUpdate(myLock);
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping(value="/api/releaseLock", method=RequestMethod.POST)
    public boolean releaseLock(@RequestBody GhostLock lock){
        GhostLock myLock  = this.mapper.lockSelectRelease(lock.getGhost_host(), lock.getUser_name());
        if( myLock != null ){
            myLock.setUser_name("");
            myLock.setLock_status("free");
            int cnt = this.mapper.lockUpdate(myLock);
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping(value="/api/getGrpcPort", method=RequestMethod.POST)
    public GhostLock getGrpcPort(@RequestBody GhostLock lock){ return this.mapper.lockSelectGrpcPort(lock.getGhost_host(), lock.getUser_name());}

}


@Getter @Setter @NoArgsConstructor @ToString
class GhostUser {
    private long user_id;
    private String user_name;
    private String password;
    private String new_password;
}

@Getter @Setter @NoArgsConstructor @ToString
class GhostLock {
    private long lock_id;
    private String ghost_host;
    private String envoy_port;
    private String user_name;
    private String lock_status;
}


@Mapper
interface CherryJuiceMapper {
    @Select("SELECT user_id, user_name, password FROM ghost_user WHERE user_name = #{user_name}")
    GhostUser userSelectByUserName(@Param("user_name") String user_name);

    @Update("Update ghost_user set password = #{new_password} WHERE user_name = #{user_name} AND password = #{password}")
    int userUpdatePassword(@Param("user_name") String user_name, @Param("password") String password, @Param("new_password") String new_password);

    @Select("SELECT lock_id, ghost_host, envoy_port, user_name, lock_status FROM ghost_lock WHERE lock_status != 'free'")
    List<GhostLock> lockSelectNotFree();

    @Select("SELECT lock_id, ghost_host, envoy_port, user_name, lock_status FROM ghost_lock WHERE ghost_host = #{ghost_host} AND (lock_status = 'free' OR user_name = #{user_name}) ")
    GhostLock lockSelectRequire(@Param("ghost_host") String ghost_host, @Param("user_name") String user_name);

    @Select("SELECT lock_id, ghost_host, envoy_port, user_name, lock_status FROM ghost_lock WHERE ghost_host = #{ghost_host} AND lock_status != 'free' AND user_name = #{user_name} ")
    GhostLock lockSelectRelease(@Param("ghost_host") String ghost_host, @Param("user_name") String user_name);

    @Select("SELECT lock_id, ghost_host, envoy_port, user_name, lock_status FROM ghost_lock WHERE ghost_host = #{ghost_host} AND user_name = #{user_name} ")
    GhostLock lockSelectGrpcPort(@Param("ghost_host") String ghost_host, @Param("user_name") String user_name);

    @Update("Update ghost_lock set ghost_host = #{ghost_host}, user_name = #{user_name}, lock_status = #{lock_status} WHERE lock_id = #{lock_id}")
    int lockUpdate(GhostLock lock);

}


