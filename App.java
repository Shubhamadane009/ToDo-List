package TODO;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {
    private static TaskDAO taskDAO = new TaskDAO();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("*********To-Do Application********");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Search Task");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();  

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    updateTask();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    searchTask();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


	private static void addTask() {
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter description: ");
        String description = sc.nextLine();
        Task task = new Task(0, title, description, "pending");
        try {
            taskDAO.addTask(task);
            System.out.println("Task added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    private static void viewTasks() {
        try {
            List<Task> tasks = taskDAO.getAllTasks();
            for (Task task : tasks) {
                System.out.println(task);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching tasks: " + e.getMessage());
        }
    }
    
    private static void updateTask() {
    	 System.out.print("Enter task ID to update: ");
    	    int id = sc.nextInt();
    	    sc.nextLine(); 

    	    System.out.print("Enter new title: ");
    	    String title = sc.nextLine();
    	    System.out.print("Enter new description: ");
    	    String description = sc.nextLine();
    	    System.out.print("Enter new status (pending/completed): ");
    	    String status = sc.nextLine();

    	    Task task = new Task(id, title, description, status);
    	    try {
    	        taskDAO.updateTask(task);
    	        System.out.println("Task updated successfully!");
    	    } catch (SQLException e) {
    	        System.out.println("Error updating task: " + e.getMessage());
    	    }
    	}
		
	

    private static void deleteTask() {
    	 System.out.print("Enter task ID to delete: ");
    	    int id = sc.nextInt();
    	    try {
    	        taskDAO.deleteTask(id);
    	        System.out.println("Task deleted successfully!");
    	    } catch (SQLException e) {
    	        System.out.println("Error deleting task: " + e.getMessage());
    	    }
	}
    
    private static void searchTask() {
    	System.out.print("Enter keyword to search: ");
        String keyword = sc.nextLine();
        try {
            List<Task> tasks = taskDAO.searchTask(keyword);
            if (tasks.isEmpty()) {
                System.out.println("No tasks found.");
            } else {
                for (Task task : tasks) {
                    System.out.println(task);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching tasks: " + e.getMessage());
        }
		
	}
     
}
