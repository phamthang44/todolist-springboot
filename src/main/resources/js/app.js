// import {renderTodolistPage} from "/api/js/script.js";
import {findTodolistById, taskOperation} from "/api/js/utils.js";

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
    if(container) {
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
            <td class="py-2 px-4">
                <button class="text-green-600 hover:underline view-task" data-task-id="${task.id}">View</button>
            </td>
          </tr>
        `).join('');

            const todoListHtml = `
          <div class="mb-6 todolist" data-todo-list-id="${todoList.id}">
            <h2 class="text-xl font-semibold mb-2 w-full flex"><span class="inline-block">${todoList.name}</span><span class="inline-block justify-end ml-auto">time: ${todoList.createdAt}</span></h2>
            <table class="w-full bg-white shadow-md rounded-lg overflow-hidden">
              <thead class="bg-gray-200">
                <tr>
                  <th class="py-2 px-4 text-left">Task</th>
                  <th class="py-2 px-4 text-left">Status</th>
                  <th class="py-2 px-4 text-left">Priority</th>
                  <th class="py-2 px-4 text-left">Actions</th>
                  <th class="py-2 px-4 text-left">View Task</th>
                  <th class="py-2 px-4 text-left"><button class="text-green-600 hover:underline view-todo" data-todo-list-id="${todoList.id}">View Todolist</button></th>
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
    }
    const viewTaskButtons = document.querySelectorAll('.view-task');
    if(viewTaskButtons) {
        viewTaskButtons.forEach(button => {
            button.addEventListener('click', (e) => {
                if(e.target.classList.contains('view-task') && e.target.hasAttribute('data-task-id')) {
                    const taskId = e.target.dataset.taskId
                    console.log(taskId);
                    window.location.href = `task.html?id=${taskId}`;
                }
            });
        });
    }

    const todoListsDOM = document.querySelectorAll('.todolist');
    todoListsDOM.forEach(todoList => {

        todoList.addEventListener("click", async (e) => {
            // console.log(e.target);
            if (e.target.classList.contains('view-todo') || e.target.hasAttribute('data-todo-list-id')) {
                const todoListId = todoList.dataset.todoListId;
                const todolist = await findTodolistById(todoListId);

                localStorage.setItem('todolist', JSON.stringify(todolist));
                window.location.href = "todolist.html";

            }
        })

        const tasks = todoList.querySelectorAll('.task');
        tasks.forEach(task => {
            const taskId = task.dataset.taskId;
            const todoListId = task.dataset.todoListId;
            const editButton = task.querySelector('.edit-task-btn');
            const deleteButton = task.querySelector('.delete-task-btn');

            editButton.addEventListener('click', () => {
                const data = taskOperation.editTask(taskId, todoListId);
                data.then(data => {
                    localStorage.setItem('task', JSON.stringify(data));
                    window.location.href = '/edit-task.html'
                })

            });


            deleteButton.addEventListener('click', () => {
                taskOperation.deleteTask(taskId);
            });
        });
    })
}
//
// const formTodolist = document.getElementById('create-todolist-form');
// // if(formTodolist) {
// //     formTodolist.addEventListener('submit', (e) => {
// //         e.preventDefault();
// //
// //     });
// // }
// // handleCreateTodolist(e).then(data => {
// //     if (data) {
// //         const newTodoList = {
// //             id: data.id,
// //             name: data.name,
// //             createdAt: data.createdAt,
// //             tasks: []
// //         };
// //         renderNewTodolist(newTodoList);
// //         formTodolist.reset();
// //     }
// // });
// function renderNewTodolist(newTodoList) {
//     const container = document.getElementById('todo-lists');
//     container.innerHTML += `
//           <div class="mb-6 todolist">
//             <h2 class="text-xl font-semibold mb-2">${newTodoList.name} time : ${newTodoList.createdAt}</h2>
//             <table class="w-full bg-white shadow-md rounded-lg overflow-hidden">
//               <thead class="bg-gray-200">
//                 <tr>
//                   <th class="py-2 px-4 text-left">Task</th>
//                   <th class="py-2 px-4 text-left">Status</th>
//                   <th class="py-2 px-4 text-left">Priority</th>
//                   <th class="py-2 px-4 text-left">Actions</th>
//                 </tr>
//               </thead>
//               <tbody></tbody>
//             </table>
//           </div>
//     `
// }

const formCreateTodo = document.getElementById('create-todolist-form');
if (formCreateTodo) {
    formCreateTodo.addEventListener('submit', (e) => {
        e.preventDefault();
        handleCreateTodolist(e).then(
            data => {
                if(data.status === "success") {
                    const newTodoList = {
                        id: data.id,
                        name: data.name,
                        createdAt: data.createdAt,
                        tasks: []
                    };
                    console.log(newTodoList);
                }
            }
        );
    });
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
        window.location = "/";
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


// const mutationObserver = new MutationObserver(function (mutations) {
//     mutations.forEach(function (mutation) {
//         if (mutation.type === 'childList') {
//             const todoLists = document.querySelectorAll('.todolist');
//             console.log(todoLists)
//             // todoLists.forEach(todolist => {
//             //     todolist.addEventListener("click", (e) => {
//             //         if(e.target.classList.contains('todolist')) {
//             //             const todoListId = todolist.dataset.todoListId;
//             //             const todolist = findTodolistById(todoListId);
//             //             todolist.then(todolist => {
//             //                 renderTodolistPage(todolist, todolist.tasks);
//             //             });
//             //         }
//             //     })
//             // })
//         }
//     });
// });
// mutationObserver.observe(document.body, {
//     childList: true,
//     subtree: true
// });
// document.addEventListener("DOMContentLoaded", () => {
//     const todoLists = document.querySelectorAll('.todolist');
//     console.log(todoLists)
// })

// const todoLists = document.querySelectorAll('.todolist');
// todoLists.forEach( (todoList) => {
//
// })
const handleTaskOperation = {
    createTask: async function(formCreateTask) {
        const todolist = JSON.parse(localStorage.getItem('todolist'));
        if (formCreateTask) {
            formCreateTask.addEventListener('submit', async (e) => {
                e.preventDefault();
                const formData = new FormData(e.target);
                const data = Object.fromEntries(formData.entries());
                console.log(data);
                const response = await fetch('/api/task/create', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        title: data.title,
                        status: data.status,
                        priority: data.priority,
                        todolistId: todolist.id,
                        dueDate: data.dueDate + 'T00:00:00',
                        createdAt: takeCurrentTime(), // ISO format
                        updatedAt: takeCurrentTime(),
                        description: data.description
                    })
                });
                if (response.ok) {
                    window.location = "/";
                } else {
                    console.error('Error creating task:', response.statusText);
                }
            });
        }
    }
}

const formCreateTask = document.getElementById('create-task-form');

handleTaskOperation.createTask(formCreateTask).then(r => console.log(r));

function takeCurrentTime() {
    const currentDate = new Date();
    let formattedDate = currentDate.toISOString().split("T");
    formattedDate[1] = formattedDate[1].split(".")[0];
    return formattedDate.join("T");
}

// edit page
const editOptions = document.getElementById("options-todolist");
if (editOptions) {
    const todoLists = getTodoLists();
    todoLists.then(todoLists => {
        renderEditOptions(todoLists);
    });
    function renderEditOptions(todoLists) {
        todoLists.forEach(todoList => {
            const option = document.createElement("option");
            option.value = todoList.id;
            option.textContent = todoList.name;
            editOptions.appendChild(option);
        })
    }

    editOptions.addEventListener("change", async (e) => {
        const todoListId = e.target.value;
        const todolist = await findTodolistById(todoListId);
        if(todolist) {
            renderFormEditTodoList(todolist);
        }
    });

}
    function renderFormEditTodoList(todolist) {
        const formEditTodoList = document.createElement("form")
        formEditTodoList.id = "edit-todolist-form";
        formEditTodoList.method = "PUT";
        formEditTodoList.action = "/api/todo/update";
        formEditTodoList.className = "flex flex-col space-y-4";
        const inputId = document.createElement("input");
        inputId.type = "hidden";
        inputId.name = "id";
        inputId.value = todolist.id;
        formEditTodoList.appendChild(inputId);
        const inputName = document.createElement("input");
        inputName.type = "text";
        inputName.name = "name";
        inputName.placeholder = "Todolist name";
        inputName.className = "border border-gray-300 rounded p-2";
        inputName.value = todolist.name;
        const saveButton = document.createElement("button");
        saveButton.type = "submit";
        saveButton.className = "bg-blue-500 text-white rounded p-2";
        saveButton.textContent = "Save";
        formEditTodoList.appendChild(inputName);
        formEditTodoList.appendChild(saveButton);
        const container = document.querySelector(".container-edit-todo");
        if (container) {
            container.innerHTML = ''; // Xóa nội dung cũ
            container.appendChild(formEditTodoList);
        }

        if (formEditTodoList) {
            formEditTodoList.addEventListener("submit", async (e) => {
                e.preventDefault();
                const formData = new FormData(e.target);
                const data = Object.fromEntries(formData.entries());
                console.log(data);
                const response = await fetch(formEditTodoList.action, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        id: todolist.id,
                        name: data.name,
                    })
                });
                if (response.ok) {
                    // console.log("Update successfully todolist");
                    // response.json().then(
                    //     data => {
                    //         console.log(data);
                    //         const updatedTodoList = {
                    //             id: data.id,
                    //             name: data.name,
                    //             updatedAt: data.updatedAt,
                    //             tasks: []
                    //         };
                    //         // localStorage.setItem('updated-todolist', JSON.stringify(updatedTodoList));
                    //     }
                    // )
                    // console.log(response.json());
                    window.location = "/";
                } else {
                    console.error('Error creating task:', response.statusText);
                }
            });
    }
}


async function getTodoLists() {
    const response = await fetch('/api/todo/todolists');
    return await response.json();
}

const deleteTodoListPage = document.getElementById("delete-todo-page");
if (deleteTodoListPage) {
    const deleteOptions = document.getElementById("options-todolist");
    const todoLists = getTodoLists();
    todoLists.then(todoLists => {
        renderDeleteOptions(todoLists);
    });
    function renderDeleteOptions(todoLists) {
        todoLists.forEach(todoList => {
            const option = document.createElement("option");
            option.value = todoList.id;
            option.textContent = todoList.name;
            deleteOptions.appendChild(option);
        })
    }
    const deleteForm = document.getElementById("delete-todo-form");
    if (deleteForm) {
        deleteForm.addEventListener("submit", async (e) => {
            e.preventDefault();
            const formData = new FormData(e.target);
            const data = Object.fromEntries(formData.entries());
            console.log(data);
            const response = await fetch(`/api/todo/delete/${data.id}`, {
                method: 'DELETE',
            });
            if (response.ok) {
                const message = await response.text(); // Lấy thông báo từ server
                console.log('Success:', message);
                // Chuyển hướng hoặc cập nhật UI
                window.location = "/";
            } else {
                const errorMessage = await response.text();
                console.error('Error deleting task:', response.status, errorMessage);
                alert(`Error: ${errorMessage}`); // Hiển thị lỗi cho người dùng
                console.error('Error creating task:', response.statusText);
            }
        });
    }
}

//Edit Task page
const editTaskPage = document.getElementById("edit-task-page");
if (editTaskPage) {
    // async function getTaskInfo(taskId) {
    //     const response = await fetch(`/api/task/${taskId}`);
    //     return await response.json();
    // }
    // const url = window.location.pathname; // Lấy phần pathname, ví dụ: /api/task/12345
    // const id = url.split('/').pop();
    // if (id) {
    //     const data = getTaskInfo(id);
    //     data.then(data => {
    //         console.log(data)
    //     })
    // }

    const data = JSON.parse(localStorage.getItem('task'));
    if(data) {
        const title = document.querySelector('.title-field');
        const status = document.querySelector('.status-field');
        const priority = document.querySelector('.priority-field');
        let dueDate = document.querySelector('.due-date-field');
        const description = document.querySelector('.description-field');
        const createdAt = document.querySelector('.created-at-time');
        const updatedAt = document.querySelector('.updated-at-time');
        const taskId = document.querySelector('.task-id');
        taskId.value = data.id;
        title.value = data.title;
        status.value = data.status;
        priority.value = data.priority;
        description.value = data.description;
        if(data.dueDate) {
            dueDate.innerHTML = `<span>Due date : ${data.dueDate.split("T")[0]}</span>`;
        } else {
            dueDate.innerHTML = "No due Date";
        }

        createdAt.innerHTML = `<span>Created at : ${data.createdAt.split("T")[0]}</span>`
        updatedAt.innerHTML = `<span>Updated at : ${data.updatedAt.split("T")[0]}</span>`;
        localStorage.removeItem('task')
    }



    const editTaskForm = document.getElementById("edit-task-form");
    if (editTaskForm) {
        editTaskForm.addEventListener("submit", async (e) => {
            e.preventDefault();
            const formData = new FormData(e.target);
            const data = Object.fromEntries(formData.entries());
            console.log(data);
            const response = await fetch(`/api/task/update`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    taskId: data.id,
                    title: data.title,
                    status: data.status,
                    priority: data.priority,
                    dueDate: data.dueDate ? data.dueDate + 'T00:00:00' : data.dueDate,
                    updatedAt: takeCurrentTime(),
                    description: data.description,
                })
            });
            if (response.ok) {
                window.location = "/";
            } else {
                console.error('Error creating task:', response.statusText);
            }
        });
    }
}