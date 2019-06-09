package org.globus.myproxy.test;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.globus.ftp.GridFTPClient;
import org.globus.myproxy.MyProxy;
import org.gridforum.jgss.ExtendedGSSCredential;
import org.gridforum.jgss.ExtendedGSSManager;
import org.ietf.jgss.GSSCredential;

public class GridFtpTest {

  public static void main(String[] args) throws Exception {
    //String proxyFile = "/Users/bing/chameleon/x509up_u20502";
    String proxyFile = "/Users/bing/chameleon/x509up_u1000";
    //String proxyFile = "/Users/bing/chameleon/x509up_test";
    
    //String x509up = "/Users/bing/chameleon/x509up_u1000";
    String proxyCertContent = new String(Files.readAllBytes(Paths.get(proxyFile)), "UTF-8");   
    byte[] proxyCertContentArray = proxyCertContent.getBytes();
    ExtendedGSSManager gssm = (ExtendedGSSManager) ExtendedGSSManager
        .getInstance();
    GSSCredential cred = gssm.createCredential(proxyCertContentArray,
        ExtendedGSSCredential.IMPEXP_OPAQUE,
        GSSCredential.DEFAULT_LIFETIME, null,
        GSSCredential.INITIATE_AND_ACCEPT);

    System.out.println("cred is generated and remain lifetime: " + cred.getRemainingLifetime());
    if (0 == cred.getRemainingLifetime()) {
      System.out.println("this cred is expired!");
    } else {
      System.out.println("this cred is ready!");
    }

    //FIXME: the below method does not work.
    /*
    final String proxyhost = "myproxy.xsede.org";
    int port = 7512;
    MyProxy proxy = new MyProxy(proxyhost, port);
    GSSCredential cred2 = proxy.get(cred, username, passwd, 3600);
    System.out.println("cred2 is generated and remain lifetime: " + cred2.getRemainingLifetime());
    */
    //String host = "oasis-dm.sdsc.xsede.org";
    String host = "129.114.108.18";
    int port = 2811;
    GridFTPClient client = new GridFTPClient(host, port);
    String username = "cc";
    //String username = ":globus-mapping:";
    //client.authenticate(cred, username);
    client.authenticate(cred);
    client.setPassive();
    System.out.println("gridclient is authenticated!");
  }

}
