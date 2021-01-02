package devices;

import definitions.Radio;
import definitions.Socket;
import definitions.TooLoud;
import definitions.WrongVoltage;

/**
 * @author Konstantinos Loizas
 * @version 1.0
 * @created 20-?e?-2020 11:35:12 pµ
 */
public class GrundigRadio implements Radio {

    private double frequency;
    private boolean on = false;
    private int volume;
    public Socket radioSocket;

    private static int minRange = 220;
    private static int maxRange = 230;

    public void connect(Socket socket) throws WrongVoltage {

        if ((socket.getVoltage() < minRange) || (socket.getVoltage() > maxRange)) {
            throw new WrongVoltage();
        } else {
            this.radioSocket = socket;

        }

    }

    private boolean connected() {
        return radioSocket != null;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getVolume() {
        if (on) {
            return volume;
        } else
            return 0;
    }

    public boolean isOff() {
        return !on;
    }

    public boolean isOn() {
        return on;
    }

    public void onOff() {
        if (connected()) {
            on = !on;
        }

    }

    public void turnDown() {

        if (isOn()) {

            if (getVolume() > 0) {
                setVolume(getVolume() - 1);
            }

        }

    }

    public void turnUp() throws TooLoud {

        if (isOn()) {

            if (getVolume() < 100) {
                setVolume(getVolume() + 1);
            } else {
                throw new TooLoud();
            }
        }

    }

    @Override
    public String toString() {
        return "GrundigRadio [frequency=" + frequency + ", on=" + on + ", volume=" + volume + ", radioSocket="
                + radioSocket + "]";
    }
}