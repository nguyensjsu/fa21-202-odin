public class ButtonCommand implements IButtonCommand {

    IButtonReceiver target;

    public void execute() {
        target.doAction();
    }

    public void setReceiver(IButtonReceiver t) {
        target = t;
    }
}
