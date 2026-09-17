public class AccessRuleEngineExtended {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        String mod = fieldModifier.toLowerCase();

        switch (accessorContext) {
            case "SAME_CLASS":
                return "ALLOWED";
            case "SAME_PACKAGE":
                return mod.equals("private") ? "DENIED" : "ALLOWED";
            case "DIFFERENT_PACKAGE":
                return mod.equals("public") ? "ALLOWED" : "DENIED";
            case "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE":
                return (mod.equals("public") || mod.equals("protected")) ? "ALLOWED" : "DENIED";
            case "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE":
                return mod.equals("public") ? "ALLOWED" : "DENIED";
            default:
                return "DENIED";
        }
    }

    public static String describeContext(String accessorContext) {
        if (accessorContext == null || accessorContext.trim().isEmpty()) {
            return "";
        }

        String[] parts = accessorContext.trim().split("_");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            String word = parts[i].toLowerCase();
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    sb.append(word.substring(1));
                }
            }
            if (i < parts.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}