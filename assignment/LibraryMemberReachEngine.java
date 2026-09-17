import java.util.LinkedHashMap;
import java.util.Map;

public class LibraryMemberReachEngine {

    public static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier.toLowerCase()) {

            case "public":
                return "ALLOWED";

            case "protected":
            case "default":
                if (accessorContext.equals("SAME_CLASS")
                        || accessorContext.equals("SAME_PACKAGE")) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "private":
                if (accessorContext.equals("SAME_CLASS")) {
                    return "ALLOWED";
                }
                return "DENIED";

            default:
                return "DENIED";
        }
    }

    public static String summarizeByModifier(String[][] attempts) {

        if (attempts == null) {
            return "";
        }

        Map<String, int[]> stats = new LinkedHashMap<>();

        stats.put("private", new int[]{0, 0});
        stats.put("default", new int[]{0, 0});
        stats.put("protected", new int[]{0, 0});
        stats.put("public", new int[]{0, 0});

        for (String[] attempt : attempts) {

            if (attempt != null && attempt.length >= 2) {

                String mod = attempt[0].toLowerCase();
                String context = attempt[1];

                if (stats.containsKey(mod)) {

                    String result = classifyAccess(mod, context);

                    if (result.equals("ALLOWED")) {
                        stats.get(mod)[0]++;
                    } else {
                        stats.get(mod)[1]++;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        int count = 0;

        for (Map.Entry<String, int[]> entry : stats.entrySet()) {

            sb.append(entry.getKey())
              .append(": ")
              .append(entry.getValue()[0])
              .append(" allowed / ")
              .append(entry.getValue()[1])
              .append(" denied");

            count++;

            if (count < stats.size()) {
                sb.append(" | ");
            }
        }

        return sb.toString();
    }
}


class LibraryMember {

    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId,
                         String branchCode,
                         double finesOwed,
                         String displayName) {

        if (membershipId == null) {
            throw new IllegalArgumentException(
                "Membership ID cannot be null."
            );
        }

        String trimmedId = membershipId.trim();

        if (trimmedId.length() < 4) {
            throw new IllegalArgumentException(
                "Membership ID must be at least 4 characters."
            );
        }

        this.membershipId = trimmedId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    public String getMembershipId() {
        return membershipId;
    }
}