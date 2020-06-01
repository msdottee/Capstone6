package co.grandcircus.Capstone6;

import co.grandcircus.Capstone6.dao.TaskRepository;
import co.grandcircus.Capstone6.dao.UserRepository;
import co.grandcircus.Capstone6.entity.Task;
import co.grandcircus.Capstone6.entity.User;
import co.grandcircus.Capstone6.model.SaveTask;
import co.grandcircus.Capstone6.model.SaveTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static co.grandcircus.Capstone6.interceptor.SessionInterceptor.USER_SESSION_ATTRIBUTE_NAME;

@Controller
public class TaskController {


	@Autowired
	private TaskRepository taskRepo;

	@Autowired 
	private UserRepository userRepo;

	@Autowired 
	private HttpSession session;
	
	@RequestMapping("/")
	public String loginOrWelcomePage() {
		if (session.getAttribute(USER_SESSION_ATTRIBUTE_NAME) != null) {
			return "welcome";
		} else {
			return "login";
		}
	}
	
	@RequestMapping("/login-submit")
	public String submitLoginForm(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
		Optional<User> foundUser = userRepo.findByEmailAndPassword(email, password);
		
		if (foundUser.isPresent()) {
			session.setAttribute(USER_SESSION_ATTRIBUTE_NAME, foundUser.get());
			return "redirect:/";
		} else {
			model.addAttribute("message", "Incorrect email or password.");
			return "login";
		}
	}
	
	@RequestMapping("/sign-up")
	public String signUpForm() {
		
		return "sign-up";
	}
	
	@PostMapping("/signup-submit")
	public String signUpSubmit(User user) {
				
		userRepo.save(user);
		
		return "redirect:/";
	}
	
	@RequestMapping("/my-tasks")
	public String taskList(Model model) {
		User user = (User) session.getAttribute(USER_SESSION_ATTRIBUTE_NAME);
		model.addAttribute("tasks", taskRepo.findByUserId(user.getId()));
		return "task-list";
	}
	
	@RequestMapping("/add-task")
	public String showAddForm() {
		return "add-task";
	}
	
	@PostMapping("/add-task")
	public String submitAddTaskForm(Task task) {
		User user = (User) session.getAttribute(USER_SESSION_ATTRIBUTE_NAME);
		user = userRepo.findById(user.getId()).get();
		task.setUser(user);
		taskRepo.save(task);
		return "redirect:/my-tasks";
	}
	
	@RequestMapping("/task-edit")
	public String showEditTaskList(Model model) {
		User user = (User) session.getAttribute(USER_SESSION_ATTRIBUTE_NAME);
		model.addAttribute("tasks", taskRepo.findByUserId(user.getId()));
		return "task-edit";
	}
	
	@PostMapping("/task-edit")
	public String submitEditTaskList(SaveTasks saveTasks, Model model) {
		User user = (User) session.getAttribute(USER_SESSION_ATTRIBUTE_NAME);

		for (SaveTask saveTask : saveTasks.getTasks()) {
			Optional<Task> optionalTask = taskRepo.findByUserIdAndId(user.getId(), saveTask.getId());

			optionalTask.ifPresent(task -> {
				task.setComplete(saveTask.isComplete());
				taskRepo.save(task);
			});
		}

		return "redirect:/my-tasks";
	}
	
	@RequestMapping("/logout")
	public String logout(RedirectAttributes redir) {
		session.invalidate();

		redir.addFlashAttribute("message","Logged out successfully");

		return "redirect:/";
	}
}
