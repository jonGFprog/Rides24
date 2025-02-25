package exceptions;
public class DriverAlreadyExistException extends Exception {
 private static final long serialVersionUID = 1L;
 
 public DriverAlreadyExistException()
  {
    super();
  }
  /**This exception is triggered if the question already exists 
  *@param s String of the exception
  */
  public DriverAlreadyExistException(String s)
  {
    super(s);
  }
}