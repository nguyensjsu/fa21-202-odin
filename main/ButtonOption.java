public class ButtonOption implements IButtonInvoker {
    private IButtonCommand cmd;
    public void setCommand(IButtonCommand c){
        cmd =c;
    }
    public void invoke() {
            cmd.execute();
    };
}
