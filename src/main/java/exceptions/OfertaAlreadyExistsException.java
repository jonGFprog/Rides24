package exceptions;
public class OfertaAlreadyExistsException extends Exception {
 private static final long serialVersionUID = 1L;
 
 public OfertaAlreadyExistsException()
  {
    super();
  }
  /**This exception is triggered if the question already exists 
  *@param s String of the exception
  */
  public OfertaAlreadyExistsException(String s)
  {
    super(s);
  }
}