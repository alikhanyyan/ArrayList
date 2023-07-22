public class WrongIndexException extends IndexOutOfBoundsException {
    public WrongIndexException () {}
    public WrongIndexException (String message) {
        super(message);
    }
}
