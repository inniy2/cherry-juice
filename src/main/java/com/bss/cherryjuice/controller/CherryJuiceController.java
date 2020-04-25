package com.bss.cherryjuice.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @RequestMapping(value="/api/getLock", method=RequestMethod.POST)
    public List<GhostLock> getLock(){ return this.mapper.lockSelectNotFree(); }

}


@Getter @Setter @NoArgsConstructor @ToString
class GhostUser {
    private long user_id;
    private String user_name;
    private String password;
}

@Getter @Setter @NoArgsConstructor @ToString
class GhostLock {
    private long lock_id;
    private String ghost_host;
    private String user_name;
    private String lock_status;
}

@Mapper
interface CherryJuiceMapper {
    @Select("SELECT user_id, user_name, password FROM ghost_user WHERE user_name = #{user_name}")
    GhostUser userSelectByUserName(@Param("user_name") String user_name);

    @Select("SELECT lock_id, ghost_host, user_name, lock_status FROM ghost_lock WHERE lock_status != 'free'")
    List<GhostLock> lockSelectNotFree();
}


