package exceptions;
public class PasajeroAlreadyExistException extends Exception {
 private static final long serialVersionUID = 1L;
 
 public PasajeroAlreadyExistException()
  {
    super();
  }
  /**This exception is triggered if the question already exists 
  *@param s String of the exception
  */
  public PasajeroAlreadyExistException(String s)
  {
    super(s);
  }
}