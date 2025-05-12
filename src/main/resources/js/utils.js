export function addTask(container, taskText) {
    const div = document.createElement("div");
    div.className = "text-lg";
    div.textContent = taskText;
    container.appendChild(div);
}


export const taskOperation = {
    editTask: async function (taskId, todoListId) {
        // alert(`Edit task ${taskId} in todo list ${todoListId}`);
        // Thêm logic gọi API để edit task
        // const data = await getTaskByTodoListIdAndTaskId(todoListId, taskId);
        const response = await fetch(`/api/task/edit`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                taskId: +taskId,
                todoListId: +todoListId
            })
        })
        return response.json();

    },
    deleteTask: function (taskId) {
        if (confirm(`Are you sure you want to delete task ${taskId}?`)) {
            fetch(`/api/task/${taskId}`, {
                method: 'DELETE'
            })
                .then(response => {
                    if (response.ok) {
                        alert(`Task ${taskId} deleted successfully`);
                        window.location.href = '/'
                    } else {
                        alert(`Failed to delete task ${taskId}`);
                    }
                })
                .catch(error => {
                    console.error('Error deleting task:', error);
                });
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
