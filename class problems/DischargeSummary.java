import java.util.Arrays;

public class DischargeSummary {

    private final String patientId;
    private final String[] medicationCodes;

    public DischargeSummary(String patientId, String[] medicationCodes) {

        if (patientId == null || medicationCodes == null) {
            throw new IllegalArgumentException("Inputs cannot be null.");
        }

        for (String code : medicationCodes) {
            if (code == null || !code.matches("^MED-[A-Z]$")) {
                throw new IllegalArgumentException(
                    "Invalid medication code format: " + code
                );
            }
        }

        this.patientId = patientId;
        this.medicationCodes =
                Arrays.copyOf(medicationCodes, medicationCodes.length);
    }

    public String getPatientId() {
        return patientId;
    }

    public String[] getMedicationCodes() {
        return Arrays.copyOf(
            medicationCodes,
            medicationCodes.length
        );
    }

    public DischargeSummary withCorrectedMedication(
            int index, String newCode) {

        if (index < 0 || index >= medicationCodes.length) {
            throw new IndexOutOfBoundsException(
                "Index out of bounds."
            );
        }

        if (newCode == null || !newCode.matches("^MED-[A-Z]$")) {
            throw new IllegalArgumentException(
                "Invalid medication code format."
            );
        }

        String[] updatedCodes =
                Arrays.copyOf(
                    medicationCodes,
                    medicationCodes.length
                );

        updatedCodes[index] = newCode;

        return new DischargeSummary(patientId, updatedCodes);
    }

    public static String processNightlyBatch(
            DischargeSummary[] summaries) {

        if (summaries == null) {
            return "0 processed | 0 null skipped | "
                 + "0 critical-care | 0 routine";
        }

        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        for (DischargeSummary summary : summaries) {

            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summary instanceof CriticalCareDischargeSummary) {
                criticalCare++;
            } else {
                routine++;
            }
        }

        return processed + " processed | "
             + nullSkipped + " null skipped | "
             + criticalCare + " critical-care | "
             + routine + " routine";
    }
}


class CriticalCareDischargeSummary extends DischargeSummary {

    private static final int standardIcuThreshold = 1;

    private final int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medicationCodes,
            int icuDays) {

        super(patientId, medicationCodes);

        this.icuDays =
                Math.max(icuDays, standardIcuThreshold);
    }

    public int getIcuDays() {
        return icuDays;
    }
}