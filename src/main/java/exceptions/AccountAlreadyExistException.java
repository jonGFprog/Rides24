package exceptions;
public class AccountAlreadyExistException extends Exception {
 private static final long serialVersionUID = 1L;
 
 public AccountAlreadyExistException()
  {
    super();
  }
  /**This exception is triggered if the question already exists 
  *@param s String of the exception
  */
  public AccountAlreadyExistException(String s)
  {
    super(s);
  }
}