package org.globus.examples;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

import org.globus.ftp.GridFTPClient;
import org.gridforum.jgss.ExtendedGSSCredential;
import org.gridforum.jgss.ExtendedGSSManager;
import org.ietf.jgss.GSSCredential;

public class GSIFTP {

  public static void main(String[] args) throws Exception {
    String proxyFile = args[0];
    String host = args[1];
    String username = args[2];
    //username = ":globus-mapping:";
    int port = 2811;
    
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

    GridFTPClient client = new GridFTPClient(host, port);
    client.authenticate(cred, username);
    @SuppressWarnings("unchecked")
    Vector<org.globus.ftp.FileInfo> vector = client.list();
    System.out.println(vector);
    System.out.println("gridclient is authenticated!");
  }

}
