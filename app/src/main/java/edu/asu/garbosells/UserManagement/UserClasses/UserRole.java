package edu.asu.garbosells.UserManagement.UserClasses;

public class UserRole {
  private String Description;
  private boolean CanPost;
  private boolean CanDelete;

  public String getDescription() {
    return Description;
  }

  public boolean isCanPost() {
    return CanPost;
  }

  public boolean isCanDelete() {
    return CanDelete;
  }
}
