package restructureit.utils;

import java.util.ArrayList;
import java.util.List;

import com.sun.media.jfxmedia.events.NewFrameEvent;

/**
 * Manages all tasks to be executed.
 */
public class TaskManager {

	/**
	 * Tasks to be processed. Can be Refactoring or quality measurement tasks.
	 */
	private List<ITask> tasks = new ArrayList<ITask>();
	
	/**
	 * Get list of tasks to be processed.
	 * @return tasks to be processed
	 */
	public List<ITask> getTasks() {
		return tasks;
	}
	
	/**
	 * Get number of tasks.
	 * @return number of tasks in list
	 */
	public int getNumberOfTasks() {
		return tasks.size();
	}
	
	/**
	 * Add task to list to be processed.
	 * @param task task to add
	 */
	public void addTask(final ITask task) {
		if (!tasks.contains(task)) {
			tasks.add(task);
		}
	}
	
	/**
	 * Add tasks to list to be processed.
	 * @param tasks list of tasks to add
	 */
	public void addTasks(final List<ITask> tasks) {
		for (ITask task : tasks) {
			addTask(task);
		}	
	}
	
	/**
	 * Remove specified task from list to be processed.
	 * @param task task to be removed
	 */
	public void removeTask(final ITask task) {
		if (tasks.contains(task)) {
			tasks.remove(task);
		}
	}
	
	/**
	 * Remove specified tasks from list to be processed.
	 * @param tasks tasks to be removed
	 */
	public void removeTasks(final List<ITask> tasks) {
		for (ITask task : tasks) {
			removeTask(task);
		}
	}
	
	/**
	 * Remove all tasks from list to be processed.
	 */
	public void clearAllTasks() {
		tasks.clear();
	}
	
	/**
	 * Process all tasks in list.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void executeTasks() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		ITask task;
		ITask previousTask; 
		for (int i = 0; i < tasks.size(); i++) {
			
			task = tasks.get(i);
			if (i > 0) {
				previousTask = tasks.get(i - 1);
				
				//Get refactorings applied in refactoring task for quality report
				if (task instanceof QualityMeasurementTask && previousTask instanceof RefactoringTask) {
					task.setRefactoringsApplied(previousTask.getRefactoringsApplied());	
				}
			}
			
			task.processCode();
		}
		
		//clear all executed tasks.
		clearAllTasks();
	}
}
