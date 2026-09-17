import java.io.Serializable;
import java.util.Objects;

public class LibraryMember implements Serializable {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private Integer hashedSecurityAnswer;

    public LibraryMember() {
        this(null, null);
    }

    public LibraryMember(String name) {
        this(null, name);
    }

    public LibraryMember(String membershipId, String name) {
        this.membershipId = membershipId;
        this.name = name;
        this.premiumMember = false;
        this.hashedSecurityAnswer = null;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (this.membershipId == null && id != null) {
            this.membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {
        if (answer != null && !answer.trim().isEmpty()) {
            this.hashedSecurityAnswer = Objects.hash(answer.trim().toLowerCase());
        }
    }
}