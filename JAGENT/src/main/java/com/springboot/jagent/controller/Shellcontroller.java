package com.springboot.jagent.controller;

import com.springboot.jagent.domain.Jagentconfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import com.springboot.jagent.util.Command;
import com.springboot.jagent.response.JSONResponse;
import com.springboot.jagent.domain.Param;

@Controller
@ResponseBody
public class Shellcontroller
{
    private static final Logger log = (Logger)LoggerFactory.getLogger(Shellcontroller.class);
    Command cmd;
    JSONResponse jr;
    Jagentconfig jc;

    public Shellcontroller(Jagentconfig jc)
    {
           this.jc = jc;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/jagent/api/v1")
    public JSONResponse postRequest(@RequestBody Param param)
    {
        try
        {
            String ShellName = param.getData();
            log.info("ShellName:" + ShellName);
            cmd = new Command();
            jr = new JSONResponse();
            jr = cmd.run(ShellName, jc.getPath(), jc.getTimeout());
            log.info("exitCode: " + String.valueOf(jr.getStatus()));

            if (jr.getStatus()) {
                log.info("docker-compose execute success");
                jr.setMsg("docker-compose execute success");
            } else {
                log.info("docker-compose execute fail");
                jr.setMsg("docker-compose execute fail");
            }
        }

        catch (Exception e)
        {
            log.error(e.toString());
        }
        return jr;
    }

}
