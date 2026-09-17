import java.util.Arrays;

public class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        if (memberId == null || bookIds == null) {
            throw new IllegalArgumentException("Inputs cannot be null.");
        }

        for (String id : bookIds) {
            if (id == null || !id.matches("^BK-\\d{3}$")) {
                throw new IllegalArgumentException("Invalid book ID format: " + id);
            }
        }

        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        if (newId == null || !newId.matches("^BK-\\d{3}$")) {
            throw new IllegalArgumentException("Invalid book ID format: " + newId);
        }

        String[] updatedIds = Arrays.copyOf(bookIds, bookIds.length);
        updatedIds[index] = newId;
        return new LoanReceipt(this.memberId, updatedIds);
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " +
               referenceOnly + " reference-only | " + regular + " regular";
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private static String defaultPrefix;

    static {
        defaultPrefix = "REF-";
    }

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = (roomNumber == null || roomNumber.trim().isEmpty()) ? "Main Reading Room" : roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}