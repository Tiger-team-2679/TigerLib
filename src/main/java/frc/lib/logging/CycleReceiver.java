package frc.lib.logging;

public interface CycleReceiver extends DataReceiver {
    public boolean isOptionsForThisCycleReceiver(CycleReceiverOptions options);
}
