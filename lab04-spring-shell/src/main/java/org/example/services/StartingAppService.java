package org.example.services;



import org.example.domain.Student;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class StartingAppService {

    private PropertiesService propertiesService;
    private QuizService quizService;
    private ConsoleIOService consoleIOService;


    private String firstName;
    private String lastName;


    public StartingAppService(PropertiesService propertiesService,
                              QuizService quizService,
                              ConsoleIOService consoleIOService) {
        this.propertiesService = propertiesService;
        this.quizService = quizService;
        this.consoleIOService = consoleIOService;
    }

    @ShellMethod(value = " Login and Begin ", key = {"login", "l"})
    public void login(@ShellOption(defaultValue = "FirstName") String firstName,
                        @ShellOption(defaultValue = "Last Name") String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        Student student = new Student(firstName,lastName);
        this.quizService.setStudent(student);
        this.consoleIOService.outputString(String.format(propertiesService.getStartMessage(), firstName, lastName));
    }

    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    @ShellMethod(value = "start quiz",key = {"start", "s"})
    public void startQuiz(){
        quizService.run();
    }
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    @ShellMethod(value = "get score", key = {"score"})
    public Double getScore(){
        return this.quizService.getScore();
    }
    @ShellMethod(value = "finish quiz", key = {"finish", "f"})
    public void quit(){
        System.exit(0);
    }
    private Availability isPublishEventCommandAvailable(){
       if(firstName == null | lastName == null){
           consoleIOService.outputString(this.propertiesService.getLoginDescription());
          return Availability.unavailable("First you need to login");
       } else {
           return Availability.available();
       }
    }
}
