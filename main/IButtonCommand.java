public interface IButtonCommand {
    void execute();
    void setReceiver(IButtonReceiver target);
}
