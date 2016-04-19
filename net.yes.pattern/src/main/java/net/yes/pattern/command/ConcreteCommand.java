package net.yes.pattern.command;

public class ConcreteCommand implements Command {
    private Receiver receiver;  
    public ConcreteCommand(Receiver receiver){  
        this.receiver = receiver;  
    }  
    public void execute() {  
        this.receiver.doSomething();  
    }  
}
