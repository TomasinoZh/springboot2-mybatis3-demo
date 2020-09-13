package cn.jrycn.demo.sb2mybatis.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

  public static String encrypt(String password) {
    // gensalt's log_rounds parameter determines the complexity
    // the work factor is 2**log_rounds, and the default is 10
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public static boolean checkpw(String password, String hashed) {
    return BCrypt.checkpw(password, hashed);
  }

  public static void main(String[] args) {
    String password = "123456";

    // Hash a password for the first time
    String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
    System.out.println(hashed);

    // gensalt's log_rounds parameter determines the complexity
    // the work factor is 2**log_rounds, and the default is 10
    String hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(12));
    System.out.println(hashed2);

    // Check that an unencrypted password matches one that has
    // previously been hashed
    String candidate = "1234567";
    if (BCrypt.checkpw(candidate, hashed))
      System.out.println("It matches");
    else
      System.out.println("It does not match");
  }

}
