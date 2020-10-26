package com.springboot.jagent.util;

import org.apache.commons.exec.*;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;
import org.springframework.stereotype.Component;
import java.io.ByteArrayOutputStream;
import java.io.File;
import com.springboot.jagent.response.JSONResponse;

@Component
public class Command
{
    private static final Logger log = (Logger) LoggerFactory.getLogger(Command.class);

    public JSONResponse run(String shell, String path, String timeout)
    {
        int exitCode=0;
        DefaultExecutor executor;
        DefaultExecuteResultHandler resultHandler;
        ExecuteWatchdog watchdog;
        ByteArrayOutputStream baos;
        JSONResponse jr = null;
        CommandLine cmd;

        try
        {
            baos = new ByteArrayOutputStream();
            executor = new DefaultExecutor();
            resultHandler = new DefaultExecuteResultHandler();
            jr = new JSONResponse();
            executor.setStreamHandler(new PumpStreamHandler(baos));
            log.info("TIMEOUT: " + timeout);
            watchdog = new ExecuteWatchdog(Integer.parseInt(timeout));
            executor.setWatchdog(watchdog);
            log.info("PATH: " + path);
            executor.setWorkingDirectory(new File(path.trim()));

            String [] Params = shell.split(" ");
            cmd = new CommandLine("./" + Params[0]);
            log.info("Params[0]: " +  Params[0]);

            if ( Params.length != 1 )
            {
                for (int i = 1; i < Params.length; i++)
                {
                    cmd.addArgument(Params[i]);
                    log.info("Params[" + Integer.toString(i)  + "]: " + Params[i]);
                }
            }

            executor.execute(cmd, resultHandler);
            resultHandler.waitFor();
            exitCode = resultHandler.getExitValue();
            log.info("exitCode:" + Integer.toString(exitCode));

            if (exitCode != 0)
            {
                jr.setStatus(false);
            }
            else
            {
               jr.setStatus(true);
            }

            String result = baos.toString("UTF-8");
            jr.setResult(result.replace("\r\n","          ").replace("\n","         "));
            log.info("result:" + result.replace("\r\n","    ").replace("\n","    "));

        }
        catch (Exception e)
        {
            log.error(e.toString());
        }
        return jr;
    }
}
