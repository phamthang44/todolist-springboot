export function addTask(container, taskText) {
    const div = document.createElement("div");
    div.className = "text-lg";
    div.textContent = taskText;
    container.appendChild(div);
}


export const taskOperation = {
    editTask: function (todoListId, taskId) {
        alert(`Edit task ${taskId} in todo list ${todoListId}`);
        // Thêm logic gọi API để edit task
    },
    deleteTask: function (todoListId, taskId) {
        if (confirm(`Are you sure you want to delete task ${taskId}?`)) {
            alert(`Deleted task ${taskId} in todo list ${todoListId}`);
            // Thêm logic gọi API để xóa task
        }
    }
}

export async function findTodolistById(id) {
    const foundTodoList = await fetchTodolistById(id);
    if (foundTodoList) {
        return foundTodoList;
    } else {
        throw new Error('Todolist not found');
    }
}

async function fetchTodolistById(id) {
    const apiUrl = `/api/todo/${id}`;
    const response = await fetch(apiUrl);
    if (response.ok) {
        return await response.json();
    } else {
        throw new Error('Failed to fetch todolist');
    }
}
