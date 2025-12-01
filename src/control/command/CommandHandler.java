package control.command;


import java.util.Stack;


/**
 * Pattern CommandHandler, il contient 2 stacks qui permettent de retenir différentes OpérationCommand
 * Il s'agit d'un singleton
 */
public class CommandHandler {
    private Stack<OperationCommand> undoStack = new Stack<OperationCommand>();
    private Stack<OperationCommand> redoStack = new Stack<OperationCommand>();
    static private CommandHandler instance = null;
    private CommandHandler(){
        
    }

    public static CommandHandler getInstance(){
        if(instance==null){
            instance = new CommandHandler();
        }
        return instance;
    }
    /**
     * Enregistre une action dans la pile et applique son effets
     * @param op L'action que l'on veut enregistrer
     */
    public void handle(OperationCommand op){
        undoStack.push(op);
        op.operate();
    }
    /**
     * Annule une action (applique l'inverse) via compensate , puis ajoute l'operation a la pile Redo
     */
    public void undo(){
        if(!(undoStack.empty())){  
            OperationCommand op = undoStack.pop();
            redoStack.push(op);
            op.compensate();
        }
        
    }
    /**
     * Refait une action via operate , pui ajoute une opération à la pile Undo
     */
    public void redo(){
        if (!(redoStack.empty())){
            OperationCommand op = redoStack.pop();
            undoStack.push(op);
            op.operate();
        }
    }
}
