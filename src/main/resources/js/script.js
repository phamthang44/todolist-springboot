function renderTodolistPage(todolist, tasks) {
    const container = document.querySelector('.container');
    container.innerHTML += `
        <div class="mb-6 todolist">
            <h2 class="text-xl font-semibold mb-2">${todolist.name} time : ${todolist.createdAt}</h2>
            <table class="w-full bg-white shadow-md rounded-lg overflow-hidden">
                <thead class="bg-gray-200">
                    <tr>
                        <th class="py-2 px-4 text-left">Task</th>
                        <th class="py-2 px-4 text-left">Status</th>
                        <th class="py-2 px-4 text-left">Priority</th>
                        <th class="py-2 px-4 text-left">Actions</th>
                    </tr>
                </thead>
                <tbody class="tasks"></tbody>
            </table>
        </div>
    `;
    const tbody = container.querySelector('.tasks');
    tasks.forEach(task => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td class="py-2 px-4">${task.title}</td>
            <td class="py-2 px-4">${task.status}</td>
            <td class="py-2 px-4">${task.priority}</td>
            <td class="py-2 px-4">
                <button class="text-blue-500 hover:text-blue-700" onclick="editTask(${todolist.id}, ${task.id})">Edit</button>
                <button class="text-red-500 hover:text-red-700" onclick="deleteTask(${todolist.id}, ${task.id})">Delete</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}
// renderTodolistPage(todolist, todolist.tasks);
const todolist = JSON.parse(localStorage.getItem('todolist'));
console.log(todolist)
renderTodolistPage(todolist, todolist.tasks)
