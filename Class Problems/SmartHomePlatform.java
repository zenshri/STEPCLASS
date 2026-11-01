interface Dimmable { void dim(int level); }
interface Schedulable { void schedule(String time); }
interface EnergyMonitor { void showEnergy(); }

abstract class Device {
    protected String id;
    protected boolean on;
    Device(String id) { this.id = id; }
    public String getId() { return id; }
    public void turnOn() { on = true; System.out.println(id + " is ON"); }
    public void turnOff() { on = false; System.out.println(id + " is OFF"); }
}
class Light extends Device implements Dimmable, Schedulable {
    Light(String id) { super(id); }
    public void dim(int level) { System.out.println(id + " dimmed to " + level); }
    public void schedule(String time) { System.out.println(id + " scheduled " + time); }
}
class Fan extends Device implements Schedulable {
    Fan(String id) { super(id); }
    public void schedule(String time) { System.out.println(id + " scheduled " + time); }
}
class Plug extends Device implements EnergyMonitor {
    private double energy;
    Plug(String id, double energy) { super(id); this.energy = energy; }
    public void showEnergy() { System.out.println(id + " energy " + energy + " kWh"); }
}
public class SmartHomePlatform {
    static Device findDevice(Device[] devices, String id) {
        for (Device d : devices) if (d.getId().equals(id)) return d;
        return null;
    }
    static void process(String command, Device[] devices) {
        String[] p = command.split(" ");
        Device d = findDevice(devices, p[1]);
        if (d == null) { System.out.println(p[1] + " rejected: device not found"); return; }
        switch (p[0]) {
            case "ON": d.turnOn(); break;
            case "OFF": d.turnOff(); break;
            case "DIM":
                if (d instanceof Dimmable) ((Dimmable)d).dim(Integer.parseInt(p[2]));
                else System.out.println(d.getId() + " rejected: DIM unsupported");
                break;
            case "SCHEDULE":
                if (d instanceof Schedulable) ((Schedulable)d).schedule(p[2]);
                else System.out.println(d.getId() + " rejected: SCHEDULE unsupported");
                break;
            case "ENERGY":
                if (d instanceof EnergyMonitor) ((EnergyMonitor)d).showEnergy();
                else System.out.println(d.getId() + " rejected: ENERGY unsupported");
                break;
            default: System.out.println("Unknown command");
        }
    }
    public static void main(String[] args) {
        Device[] devices = {new Light("L1"), new Fan("F1"), new Plug("P1", 12)};
        process("ON L1", devices);
        process("DIM L1 40", devices);
        process("DIM F1 30", devices);
        process("SCHEDULE F1 22:00", devices);
        process("ENERGY P1", devices);
        process("ENERGY L1", devices);
    }
}
