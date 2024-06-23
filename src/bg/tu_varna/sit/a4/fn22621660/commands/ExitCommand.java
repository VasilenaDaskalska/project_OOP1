package bg.tu_varna.sit.a4.fn22621660.commands;

import bg.tu_varna.sit.a4.fn22621660.contacts.ICommand;

public class ExitCommand implements ICommand
{
    @Override
    public void execute(String[] args) {
        System.out.println("Exiting the program");
        System.exit(0);
    }
}
