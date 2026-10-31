import java.util.*;

interface ScoringRule {
    double calculate(double idea, double execution, double presentation);
}

class InnovationScoring implements ScoringRule {
    public double calculate(double idea, double execution, double presentation) {
        return idea * 0.5 + execution * 0.3 + presentation * 0.2;
    }
}

class OpenScoring implements ScoringRule {
    public double calculate(double idea, double execution, double presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Track {
    String name;
    ScoringRule rule;

    Track(String name, ScoringRule rule) {
        this.name = name;
        this.rule = rule;
    }
}

class Score {
    double idea, execution, presentation;

    Score(double idea, double execution, double presentation) {
        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
    }
}

class Project {
    String name;
    Score score;

    Project(String name) {
        this.name = name;
    }

    void addScore(Score score) {
        this.score = score;
    }
}

class Team {
    String name;
    List<Student> members;
    Track track;
    Project project;

    Team(String name, List<Student> members, Track track) {
        this.name = name;
        this.members = members;
        this.track = track;
    }

    boolean submitProject(Project project) {
        if (this.project != null) {
            System.out.println("Team can submit only one project.");
            return false;
        }

        this.project = project;
        System.out.println("Project '" + project.name
                + "' submitted by " + name + ".");
        return true;
    }
}

class Judge {
    String name;

    Judge(String name) {
        this.name = name;
    }

    void scoreProject(Project project, double idea,
                      double execution, double presentation,
                      Hackathon hackathon) {
        if (hackathon.published) {
            System.out.println("Rescore rejected: Results have already been published.");
            return;
        }

        if (project == null || idea < 0 || idea > 10 ||
                execution < 0 || execution > 10 ||
                presentation < 0 || presentation > 10) {
            System.out.println("Invalid project or score.");
            return;
        }

        project.addScore(new Score(idea, execution, presentation));
        System.out.println("Score recorded for '" + project.name + "'.");
    }
}

class Hackathon {
    String name;
    List<Team> teams = new ArrayList<>();
    Set<Student> registeredStudents =
            Collections.newSetFromMap(new IdentityHashMap<>());
    boolean published = false;

    Hackathon(String name) {
        this.name = name;
    }

    void registerTeam(Team team) {
        if (published) {
            System.out.println("Registration closed.");
            return;
        }

        if (team.members.size() < 2 || team.members.size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return;
        }

        for (Student student : team.members) {
            if (registeredStudents.contains(student)) {
                System.out.println("Registration failed: " + student.name
                        + " already belongs to a team.");
                return;
            }
        }

        registeredStudents.addAll(team.members);
        teams.add(team);

        System.out.println("Team " + team.name + " registered ("
                + team.members.size() + " members, "
                + team.track.name + " track).");
    }

    void publishResults() {
        if (published) return;

        for (Team team : teams) {
            if (team.project == null || team.project.score == null) {
                System.out.println("Cannot publish: Some projects are not scored.");
                return;
            }
        }

        for (Team team : teams) {
            Score s = team.project.score;
            double result = team.track.rule.calculate(
                    s.idea, s.execution, s.presentation);

            System.out.printf("Final score: %.2f%n", result);
        }

        published = true;
        System.out.println("Results published.");
    }
}

public class CodeSprintJudgingDesk {
    public static void main(String[] args) {

        Hackathon hackathon = new Hackathon("Code Sprint");

        Track innovation = new Track(
                "Innovation", new InnovationScoring());

        Track open = new Track("Open", new OpenScoring());

        Student s1 = new Student("Asha");
        Student s2 = new Student("Ravi");
        Student s3 = new Student("Neha");

        Team team1 = new Team("ByteBusters",
                Arrays.asList(s1, s2, s3), innovation);

        Team team2 = new Team("SoloCoder",
                Arrays.asList(new Student("Kiran")), open);

        hackathon.registerTeam(team1);
        hackathon.registerTeam(team2);

        Project project = new Project("SmartAttend");
        team1.submitProject(project);

        Judge judge = new Judge("Judge 1");

        judge.scoreProject(project, 8, 7, 9, hackathon);

        hackathon.publishResults();

        judge.scoreProject(project, 10, 7, 9, hackathon);
    }
}