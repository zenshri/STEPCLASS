import java.util.*;

interface Capability {
    String getName();
    boolean apply(double value, String deviceName);
}

class PowerCapability implements Capability {
    boolean on = false;

    public String getName() {
        return "Power";
    }

    public boolean apply(double value, String name) {
        if (value != 0 && value != 1) {
            System.out.println("Rejected: Invalid power value.");
            return false;
        }

        on = value == 1;

        System.out.println(name + ": " + (on ? "ON" : "OFF"));
        return true;
    }
}

class BrightnessCapability implements Capability {
    double brightness;

    public String getName() {
        return "Brightness";
    }

    public boolean apply(double value, String name) {
        if (value < 0 || value > 100) {
            System.out.println("Rejected: Brightness must be between 0 and 100%.");
            return false;
        }

        brightness = value;
        System.out.println(name + ": brightness set to "
                + (int) value + "%.");
        return true;
    }
}

class TemperatureCapability implements Capability {
    double temperature;

    public String getName() {
        return "Temperature";
    }

    public boolean apply(double value, String name) {
        if (value < 16 || value > 30) {
            System.out.println("Rejected: " + name
                    + " temperature must be between 16°C and 30°C.");
            return false;
        }

        temperature = value;
        System.out.println(name + ": temperature set to "
                + (int) value + "°C.");
        return true;
    }
}

class Device {
    String name;
    Map<String, Capability> capabilities = new LinkedHashMap<>();

    Device(String name) {
        this.name = name;
    }

    void addCapability(Capability capability) {
        capabilities.put(capability.getName(), capability);
        System.out.println(name + ": " + capability.getName()
                + " capability added.");
    }

    boolean execute(String capabilityName, double value) {
        Capability capability = capabilities.get(capabilityName);

        if (capability == null) {
            return false;
        }

        return capability.apply(value, name);
    }

    boolean hasCapability(String capabilityName) {
        return capabilities.containsKey(capabilityName);
    }
}

class SceneStep {
    String capabilityName;
    double value;

    SceneStep(String capabilityName, double value) {
        this.capabilityName = capabilityName;
        this.value = value;
    }
}

class Scene {
    String name;
    List<SceneStep> steps = new ArrayList<>();

    Scene(String name) {
        this.name = name;
    }

    void addStep(String capability, double value) {
        steps.add(new SceneStep(capability, value));
    }

    void execute(List<Device> devices) {
        System.out.println("Scene '" + name + "' started.");

        int actions = 0;

        for (SceneStep step : steps) {
            for (Device device : devices) {
                if (device.hasCapability(step.capabilityName)) {
                    if (device.execute(step.capabilityName, step.value)) {
                        actions++;
                    }
                }
            }
        }

        System.out.println("Scene '" + name
                + "' completed: " + actions + " actions applied.");
    }
}

public class SmartLabControlPanel {
    public static void main(String[] args) {

        Device ac = new Device("Lab AC");
        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());

        Device lights = new Device("Ceiling Lights");
        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability(new PowerCapability());

        List<Device> devices = Arrays.asList(ac, lights, projector);

        Scene scene = new Scene("Lecture Mode");

        scene.addStep("Power", 1);
        scene.addStep("Brightness", 40);
        scene.addStep("Temperature", 24);

        scene.execute(devices);

        ac.execute("Temperature", 12);

        projector.addCapability(new BrightnessCapability());
        projector.execute("Brightness", 70);
    }
}