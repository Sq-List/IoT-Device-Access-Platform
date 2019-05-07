package com.sqlist.web.util;

import net.schmizz.keepalive.KeepAliveProvider;
import net.schmizz.sshj.DefaultConfig;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * @author SqList
 * @date 2019/5/8 1:04
 * @description
 **/
@Component
public class ScpUtil {

    @Value("${config.flink.rest.ip}")
    private String remoteIp;

    @Value("${config.ssh.username}")
    private String username;

    @Value("${config.ssh.password}")
    private String password;

    private SSHClient sshClient;

//    private String

    public ScpUtil() {
        DefaultConfig defaultConfig = new DefaultConfig();
        defaultConfig.setKeepAliveProvider(KeepAliveProvider.KEEP_ALIVE);
        sshClient = new SSHClient(defaultConfig);
    }

    @PostConstruct
    private void init() throws IOException {
        sshClient.addHostKeyVerifier(new PromiscuousVerifier());
        sshClient.connect(remoteIp);
        //every 60sec
        sshClient.getConnection().getKeepAlive().setKeepAliveInterval(5);
        sshClient.authPassword(username, password);
        sshClient.useCompression();
    }

    @PreDestroy
    public void disconnect() throws IOException {
        sshClient.disconnect();
    }

    public void remoteSave(String localPath, String remotePath) throws IOException {
        sshClient.newSCPFileTransfer().upload(localPath, remotePath);
    }
}
