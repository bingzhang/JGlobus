package org.globus.examples;

import org.globus.ftp.FileInfo;
import org.globus.ftp.MlsxEntry;
import org.globus.ftp.exception.FTPException;

public class Mlsxentry {

  public static void main(String[] args) throws FTPException {
    String line = "Type=cdir;Modify=20190620063856;Size=4096;UNIX.mode=0755; .";
    MlsxEntry mlsxentry = new MlsxEntry(line);
    FileInfo fileinfo = new FileInfo();
    String name = mlsxentry.getFileName();
    fileinfo.setName(name);
    String factname = mlsxentry.SIZE;
    fileinfo.setSize(Integer.parseInt(mlsxentry.get(factname)));
    factname = mlsxentry.MODIFY;
    String dateString = mlsxentry.get(factname);
    fileinfo.setDate(dateString);
    factname = mlsxentry.PERM;
    String perm = mlsxentry.get(factname);
    if(null == perm ){
      factname = mlsxentry.UNIX_MODE;
      String mode = mlsxentry.get(factname);
      int permission = Integer.parseInt(mode, 8);  
      fileinfo.setMode(permission);
      System.out.println(name + " " + permission);
    }else{
      int permission = Integer.parseInt(perm);
      fileinfo.setMode(permission);
      System.out.println(name + " " + permission);
    }
    System.out.println(fileinfo);
  }

}
