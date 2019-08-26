package edu.asu.garbosells.UserManagement.UserClasses;

public class User {
  private long UserId;
  private UserRole Role;

  public long getUserId() {
    return UserId;
  }

  public UserRole getRole () {
    return Role;
  }
}
