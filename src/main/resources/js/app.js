import {taskOperation} from "/api/js/utils.js";

async function loadTodolist() {
    const response = await fetch('/api/todo/todolists');
    return await response.json();
}

// const container = document.querySelector('.container');
const data = loadTodolist();
data.then(data => {
    renderTodoLists(data);
});

// document.addEventListener("DOMContentLoaded", () => {
//     addTask(container, "Sample Task");
// });

// Hàm để render todo lists và tasks
function renderTodoLists(todoLists) {
    const container = document.getElementById('todo-lists');
    container.innerHTML = ''; // Xóa nội dung cũ
    todoLists.forEach(todoList => {
        const tasksHtml = todoList.tasks.map(task => `
          <tr class="task border-1 border-gray-600" data-task-id="${task.id}" data-todo-list-id="${todoList.id}">
            <td class="py-2 px-4">${task.title}</td>
            <td class="py-2 px-4">
              <span class="inline-block px-2 py-1 text-xs font-semibold rounded-full
                ${task.status === 'IN_PROGRESS' ? 'bg-yellow-100 text-yellow-800' :
            task.status === 'PENDING' ? 'bg-gray-100 text-gray-800' :
                'bg-green-100 text-green-800'}">
                ${task.status}
              </span>
            </td>
            <td class="py-2 px-4">
              <span class="inline-block px-2 py-1 text-xs font-semibold rounded-full
                ${task.priority === 'HIGH' ? 'bg-red-100 text-red-800' :
            task.priority === 'MEDIUM' ? 'bg-orange-100 text-orange-800' :
                'bg-blue-100 text-blue-800'}">
                ${task.priority}
              </span>
            </td>
            <td class="py-2 px-4 flex space-x-2">
              <button class="edit-task-btn text-blue-600 hover:underline">Edit</button>
              <button class="delete-task-btn text-red-600 hover:underline">Delete</button>
            </td>
          </tr>
        `).join('');

        const todoListHtml = `
          <div class="mb-6 todolist">
            <h2 class="text-xl font-semibold mb-2">${todoList.name} time: ${todoList.createdAt}</h2>
            <table class="w-full bg-white shadow-md rounded-lg overflow-hidden">
              <thead class="bg-gray-200">
                <tr>
                  <th class="py-2 px-4 text-left">Task</th>
                  <th class="py-2 px-4 text-left">Status</th>
                  <th class="py-2 px-4 text-left">Priority</th>
                  <th class="py-2 px-4 text-left">Actions</th>
                </tr>
              </thead>
              <tbody>
                ${tasksHtml}
              </tbody>
            </table>
          </div>
        `;
        container.innerHTML += todoListHtml;
    });
    const todoListsDOM = document.querySelectorAll('.todolist');
    todoListsDOM.forEach(todoList => {
        const tasks = todoList.querySelectorAll('.task');
        tasks.forEach(task => {
            const taskId = task.dataset.taskId;
            const todoListId = task.dataset.todoListId;
            const editButton = task.querySelector('.edit-task-btn');
            const deleteButton = task.querySelector('.delete-task-btn');

            editButton.addEventListener('click', () => {
                taskOperation.editTask(todoListId, taskId);
            });

            deleteButton.addEventListener('click', () => {
                taskOperation.deleteTask(todoListId, taskId);
            });
        });
    })
}

const formTodolist = document.getElementById('create-todolist-form');
if(formTodolist) {
    formTodolist.addEventListener('submit', (e) => {
        e.preventDefault();
        handleCreateTodolist(e).then(data => {
            if (data) {
                const newTodoList = {
                    id: data.id,
                    name: data.name,
                    createdAt: data.createdAt,
                    tasks: []
                };
                renderNewTodolist(newTodoList);
                formTodolist.reset();
            }
        });
    });
}

function renderNewTodolist(newTodoList) {
    const container = document.getElementById('todo-lists');
    container.innerHTML += `
          <div class="mb-6 todolist">
            <h2 class="text-xl font-semibold mb-2">${newTodoList.name} time : ${newTodoList.createdAt}</h2>
            <table class="w-full bg-white shadow-md rounded-lg overflow-hidden">
              <thead class="bg-gray-200">
                <tr>
                  <th class="py-2 px-4 text-left">Task</th>
                  <th class="py-2 px-4 text-left">Status</th>
                  <th class="py-2 px-4 text-left">Priority</th>
                  <th class="py-2 px-4 text-left">Actions</th>
                </tr>
              </thead>
              <tbody></tbody>
            </table>
          </div>
    `
}

async function handleCreateTodolist(e) {
    const formData = new FormData(e.target);
    const data = Object.fromEntries(formData.entries());
    console.log(data);
    const response = await fetch('/api/todo/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    if (response.ok) {
        return await response.json();
    } else {
        console.error('Error creating todo list:', response.statusText);
    }
}
// Nếu bạn muốn fetch dữ liệu từ API thực tế, hãy thay bằng đoạn code sau:
/*
fetch('your-api-endpoint')
  .then(response => response.json())
  .then(data => renderTodoLists(data))
  .catch(error => console.error('Error fetching data:', error));
*/

//create-task-form
