package bg.tu_varna.sit.a4.fn22621660.contacts;

/**
 * The {@code ICommand} interface provides a method for executing a command with given arguments.
 * Implementations of this interface should define the specific behavior of the command.
 */
public interface ICommand
{
    /**
     * Executes the command with the specified arguments.
     *
     * @param args the arguments for the command. The content and format of these arguments
     *             are defined by the specific implementation.
     * @throws Exception if an error occurs during the execution of the command.
     */
    void execute(String[] args) throws Exception;
}
