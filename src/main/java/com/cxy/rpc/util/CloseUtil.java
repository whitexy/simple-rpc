package com.cxy.rpc.util;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @desc
 * @author chenxy
 * @date createTime:May 17, 2017 12:10:59 AM
 */
public class CloseUtil {

  public static void closeAll(Closeable... all) {
    for (Closeable item : all) {
      if (null != item) {
        try {
          item.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void close(Socket... all) {
    if (!ArrayUtils.isEmpty(all)) {
      for (Socket item : all) {
        if (null != item) {
          try {
            item.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  public static void closeAll(ServerSocket... all) {
    for (ServerSocket item : all) {
      if (null != item) {
        try {
          item.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
