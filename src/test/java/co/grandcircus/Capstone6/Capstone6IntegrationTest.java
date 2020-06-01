package co.grandcircus.Capstone6;

import co.grandcircus.Capstone6.dao.TaskRepository;
import co.grandcircus.Capstone6.dao.UserRepository;
import co.grandcircus.Capstone6.entity.Task;
import co.grandcircus.Capstone6.entity.User;
import co.grandcircus.Capstone6.interceptor.SessionInterceptor;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class Capstone6IntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void before() {
        taskRepository.deleteAll();
        userRepository.deleteAll();

        User newUser = new User();
        newUser.setName("Smokey");
        newUser.setEmail("smokey@gmail.com");
        newUser.setPassword("Who wants breakfast?");

        this.user = userRepository.save(newUser);

        Task task1 = new Task("Homework", LocalDate.parse("2020-05-31"), false, this.user);
        Task task2 = new Task("Dinner", LocalDate.parse("2020-05-31"), true, this.user);
        Task task3 = new Task("Workout", LocalDate.parse("2020-05-31"), false, this.user);
        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);

    }

    @Test
    public void indexReturnsLoginWithNoSession() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void indexReturnsWelcomeWithSession() throws Exception {
        mvc.perform(get("/")
                .sessionAttr(SessionInterceptor.USER_SESSION_ATTRIBUTE_NAME, user))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"));
    }

    @Test
    public void loginRedirectsToIndexOnSuccess() throws Exception {
        MvcResult result = mvc.perform(get("/login-submit")
        .queryParam("email", user.getEmail())
        .queryParam("password", user.getPassword()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andReturn();
        assertThat(result.getRequest().getSession().getAttribute("user")).isNotNull();
    }

    @Test
    public void loginDisplaysMessageOnFailedCredentials() throws Exception {
        MvcResult result = mvc.perform(get("/login-submit")
        .queryParam("email", user.getEmail())
        .queryParam("password", "bad"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("message", "Incorrect email or password."))
                .andReturn();
        assertThat(result.getRequest().getSession().getAttribute("user")).isNull();

    }

    @Test
    public void showSignUpForm() throws Exception {
        mvc.perform(get("/sign-up"))
                .andExpect(status().isOk());
    }

    @Test
    public void signUpSubmitFormAddsUserToDatabase() throws Exception {
        mvc.perform(post("/signup-submit")
        .param("email", "akuber1al@cmu.edu")
        .param("name", "Ashley")
        .param("password", "chicken"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        User createdUser = userRepository.findAll().get(1);
        assertThat(createdUser.getEmail()).isEqualTo("akuber1al@cmu.edu");
        assertThat(createdUser.getName()).isEqualTo("Ashley");
        assertThat(createdUser.getPassword()).isEqualTo("chicken");
    }

    @Test
    public void listShowsAllOfCurrentUsersTasks() throws Exception {
        mvc.perform(get("/my-tasks")
                .sessionAttr(SessionInterceptor.USER_SESSION_ATTRIBUTE_NAME, user))
                .andExpect(status().isOk())
                .andExpect(view().name("task-list"))
                .andExpect(model().attribute("tasks", hasSize(3)));
    }

    @Test
    public void showAddForm() throws Exception {
        mvc.perform(get("/add-task")
                .sessionAttr(SessionInterceptor.USER_SESSION_ATTRIBUTE_NAME, user))
                .andExpect(status().isOk());
    }

    @Test
    public void addsTaskForUser() throws Exception {
        mvc.perform(post("/add-task")
                .param("dueDate", "2020-01-01")
                .param("description", "Do work!")
                .sessionAttr(SessionInterceptor.USER_SESSION_ATTRIBUTE_NAME, user))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/my-tasks"));

        List<Task> tasks = taskRepository.findByUserId(user.getId());

        for (Task task : tasks) {
            if (task.getDescription().equals("Do work!")) {
                assertThat(task.getId()).isNotNull();
                assertThat(task.getDescription()).isEqualTo("Do work!");
                assertThat(task.getUser().getId()).isEqualTo(user.getId());
                assertThat(task.getDueDate()).isEqualTo(LocalDate.parse("2020-01-01"));
                assertThat(task.isComplete()).isFalse();
                return;
            }
        }

        Assertions.fail("Created Task was not found.");
    }

    @Test
    public void showEditTaskListForm() throws Exception {
        mvc.perform(get("/task-edit")
                .sessionAttr(SessionInterceptor.USER_SESSION_ATTRIBUTE_NAME, user))
                .andExpect(status().isOk())
                .andExpect(view().name("task-edit"))
                .andExpect(model().attribute("tasks", hasSize(3)));
    }

    @Test
    public void editTaskSubmitSavesChangesToTasks() throws Exception {
        Task task1 = taskRepository.findByUserId(user.getId()).get(0);

        mvc.perform(post("/task-edit")
                .param("tasks[0].id", task1.getId().toString())
                .param("tasks[0].complete", String.valueOf(true))
                .sessionAttr(SessionInterceptor.USER_SESSION_ATTRIBUTE_NAME, user))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/my-tasks"));

        Optional<Task> task = taskRepository.findById(task1.getId());
        assertThat(task.isPresent());

        task.ifPresent(presentTask -> {
            assertThat(presentTask.isComplete()).isTrue();
        });
    }

    @Test
    public void logoutInvalidatesSessionAndRedirectsToLogin() throws Exception {
        MvcResult result = mvc.perform(get("/logout")
                .sessionAttr(SessionInterceptor.USER_SESSION_ATTRIBUTE_NAME, user))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(flash().attribute("message", is("Logged out successfully")))
                .andReturn();

        assertThat(result.getRequest().getSession()
                .getAttribute(SessionInterceptor.USER_SESSION_ATTRIBUTE_NAME))
                .isNull();
    }
}
